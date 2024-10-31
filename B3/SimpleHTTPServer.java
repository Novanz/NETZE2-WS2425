import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO: remove debug output, comment code, add comments, add max-age to cookie

public class SimpleHTTPServer {
    private static Date parseCookie(String request) {
        System.out.println("in parseCookie...");
        String cookiePrefix = "Last-Access=";
        int startIndex = request.indexOf(cookiePrefix);
        System.out.println("startIndex: " + startIndex);
        if (startIndex == -1) {
            return null;
        }
        startIndex += cookiePrefix.length();
        int endIndex = request.indexOf(";", startIndex);
        if (endIndex == -1) {
            endIndex = request.length();
        }
        String dateString = request.substring(startIndex, endIndex);
        System.out.println("dateString: " + dateString);
        try {
            return new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss").parse(dateString);
        } catch (Exception e) {
            System.out.println("Fehler beim Parsen des Cookies");
            return null;
        }
    }

    // extractt html generattor
    private static String generateResponse(Date lastAccessTimestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        String currentTime = sdf.format(new Date());
        StringBuilder response = new StringBuilder();
        // HTTP-Header
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("Content-Type: text/html\r\n");
        response.append("Set-Cookie: Last-Access=").append(currentTime);
        //  .append("; Max-Age=3600\r\n");
        response.append("\r\n\n");
        // HTML-Body
        response.append("<!DOCTYPE html><html><body>");
        response.append("<h2>Hallo! Es ist der ").append(currentTime).append(" Uhr.</h2>");
        if (lastAccessTimestamp == null) {
            response.append("<p>Sie sind zum ersten Mal auf diesem Server gelandet.</p>");
        } else {
            String lastAccessTime = sdf.format(lastAccessTimestamp);
            long secondsPassed = (new Date().getTime() - lastAccessTimestamp.getTime()) / 1000;
            response.append("<p>Der letzte Zugriff erfolgte am ").append(lastAccessTime).append(" Uhr.</p>");
            response.append("<p>Es sind ").append(secondsPassed).append(" Sekunden seit dem letzten Zugriff verstrichen.</p>");
        }
        response.append("</body></html>");
        return response.toString();
    }

    private static String readText(Socket s) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = input.readLine()) != null) {
            System.out.println(str);
            if (str.isEmpty()) {
                break;
            }
            sb.append(str);
            sb.append("\r\n");
        }
        //input.close();
        System.out.println("end of while loop");
        return (sb.toString());
    }

    private static void sendText(Socket s, String string) {
        System.out.println("Durch Socket uebertragen...");
        try {
            System.out.println(string);
            OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
            osw.write(string);
            osw.flush();
        } catch (IOException e) {
            System.out.println("Fehler beim uebertragen ueber Socket");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("Auf eingehende Verbindungen warten...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientSocket.setSoTimeout(30000);
                System.out.println("Verbunden mit: " + clientSocket.getRemoteSocketAddress());
                String request = readText(clientSocket);
                Date lastAccessTimestamp = parseCookie(request);
                String response = generateResponse(lastAccessTimestamp);
                sendText(clientSocket, response);
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Erstellen des Sockets");
            e.printStackTrace();
        }
    }
}