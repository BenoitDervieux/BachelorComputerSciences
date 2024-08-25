import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.io.IOException;


public class FileSendingTest {

    private static String ipAddress = "127.0.0.1";
    private static DatagramSocket socket;
    

    public static void main(String[] args) throws IOException {
        System.out.println("Test sending file..");
        sendFile();
        receiveFile();  
    }

    public synchronized static void sendFile() {        
            try {
                DatagramSocket sendSocket = new DatagramSocket(12345);
                socket = sendSocket;
            } catch (SocketException e) {
                e.printStackTrace();
            }
            SendFileThread sendFileThread = new SendFileThread(socket, ipAddress);
            sendFileThread.start();
    }

    public synchronized static void receiveFile() throws IOException {

        ReceiveThread receiveFileThread = new ReceiveThread(socket, ipAddress);
        receiveFileThread.start();        
    }


    
}
