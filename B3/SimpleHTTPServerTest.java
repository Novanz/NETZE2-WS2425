// AI generated test file for SimpleHTTPServer.java
// TODO: check if the test cases are correct and complete

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleHTTPServerTest {

    private Socket socket;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws IOException {
        socket = new Socket() {
            private final ByteArrayInputStream inputStream =
                    new ByteArrayInputStream("GET / HTTP/1.1\r\nSet-Cookie: Last-Access=01-11-2021 13:37:00\r\n\r\n".getBytes());
            private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            @Override
            public InputStream getInputStream() {
                return inputStream;
            }

            @Override
            public OutputStream getOutputStream() {
                return outputStream;
            }
        };
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    public void tearDown() throws IOException {
        socket.close();
        outputStream.close();
    }

    @Test
    public void testParseCookie() {
        String request = "GET / HTTP/1.1\r\nCookie: Last-Access=01-11-2021 13:37:00\r\n\r\n";
        Date expectedDate = new Date(1635764220000L); // 2021-11-01 13:37:00 in milliseconds
        Date parsedDate = SimpleHTTPServer.parseCookie(request);
        if ((parsedDate != null)) {
            assertEquals(expectedDate, (parsedDate));
        }
    }

    @Test
    public void testGenerateResponse() {
        Date lastAccessTimestamp = new Date(1635764220000L); // 2021-11-01 13:37:00 in milliseconds
        String response = SimpleHTTPServer.generateResponse(lastAccessTimestamp);
        assertTrue(response.contains("Hallo! Es ist der"));
        assertTrue(response.contains("Der letzte Zugriff erfolgte am"));
        assertTrue(response.contains("Es sind"));
    }

    @Test
    public void testReadText() throws IOException {
        String expectedRequest = "GET / HTTP/1.1\r\nSet-Cookie: Last-Access=01-11-2021 13:37:00\r\n";
        String request = SimpleHTTPServer.readText(socket);
        assertEquals(expectedRequest, request);
    }

    @Test
    public void testSendText() throws IOException {
        String response = "HTTP/1.1 200 OK\r\n\r\nHello, World!";
        SimpleHTTPServer.sendText(socket, response);
        assertEquals(response, socket.getOutputStream().toString());
    }
}