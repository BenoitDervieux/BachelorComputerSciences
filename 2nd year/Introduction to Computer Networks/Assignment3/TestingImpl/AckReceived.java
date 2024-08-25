import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class AckReceived extends Thread{

    private DatagramSocket socket;
    private int blockNumber;
    private final TheBoolean flag;

    public AckReceived(DatagramSocket socket, int blockNumber, TheBoolean flag) {
        this.socket = socket;
        this.blockNumber = blockNumber;
        this.flag = flag;
    }

    public synchronized void run() {
        while(!interrupted()) {
            DatagramPacket aPacket = new DatagramPacket(new byte[4], 4);
            System.out.println("Waiting for ACK...");
            try {
                socket.receive(aPacket);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (aPacket != null) {
                try {
                    System.out.println("J'attends la");
                    wait(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
                
            }
        }
        if (!interrupted()) {
            flag.setTrue();
            System.out.println("ACK received.");
        }
    } 
}
