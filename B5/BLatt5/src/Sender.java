import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender {
    public static void main(String[] args) throws IOException {
        Auto auto = new Auto("rot", 200, 1.5, "Audi");
        Socket client = new Socket("localhost", 1337);
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject(auto);
        oos.flush();
        oos.close();
        client.close();
    }
}
