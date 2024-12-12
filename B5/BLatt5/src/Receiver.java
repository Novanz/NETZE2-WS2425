import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(1337);
        Socket server = serverSocket.accept();
        ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
        Auto auto = (Auto) ois.readObject();
        System.out.println(auto.holeBeschreibung());
        System.out.println(auto.holeSpassfaktor());
        ois.close();
        server.close();
        serverSocket.close();
    }
}
