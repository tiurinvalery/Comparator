import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void listenPort(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while(true) {
                Socket clientSocket = serverSocket.accept();

                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                FileOutputStream fos = new FileOutputStream("PathToStoreImage/img.jpg");
                int i;
                while((i=dis.read()) > -1) {
                    fos.write(i);
                }
                fos.flush();
                fos.close();
                dis.close();
                clientSocket.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
