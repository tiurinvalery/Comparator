import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void sendFile(String ip, int port) {
        int i =0;
        try {
            final String imgSource = "file:///D:/client/1.jpg";
            FileInputStream fis = new FileInputStream(imgSource);
            Socket socket = new Socket(ip, port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            while(( i=fis.read())> -1) {
                dos.write(i);
            }
            fis.close();
            dos.close();
            socket.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
