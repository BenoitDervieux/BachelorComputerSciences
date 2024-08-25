import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SendFileThread extends Thread {

    private static String ipAddress = "127.0.0.1";
    private static DatagramSocket socket;
    private int counter = 0;
    private final TheBoolean flag = new TheBoolean();

    public SendFileThread(DatagramSocket socket, String ipAddress) {
        SendFileThread.socket = socket;
        SendFileThread.ipAddress = ipAddress;
    }
    
    public synchronized void run() {
        try {

            String filename = "./read/testmore.txt";
            // sequence number in the datagram
            short blockNumber = 1;
            int totalChunks = getNumberOfChunk(filename);
    
            for (int i = 0; i < totalChunks; i++) {
                // Calculate the length of data for this chunk
                byte[] toSend = getChunkToSend(i, filename, blockNumber);
                flag.setFalse();
                while (flag.getFlag() == false) {
                    flag.setFalse();
                    System.out.println("Sending chunk: " + (i+1));
                    System.out.println("with block number: " + blockNumber);
                    sendChunk(toSend);
                    AckReceived ack = new AckReceived(socket, blockNumber, flag);
                    ack.start();
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() - time < 6000 && flag.getFlag() == false) {
                        
                    }
                    ack.interrupt();
                    
                }
                blockNumber++;
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForAck(int blockNumber) {
        DatagramPacket aPacket = new DatagramPacket(new byte[4], 4);
        System.out.println("Waiting for ACK...");
        try {
            socket.receive(aPacket);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (aPacket != null) {
            System.out.println("ACK received.");
            try {
                if (counter == 0) {
                    wait(10000);
                }
                counter++;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            flag.setTrue();
        }
    }


    public byte[] getChunkToSend(int i, String filename, int blockNumber) {

        // hard coded opcode for data sending
        byte[] opcode = {(byte) 0x00, 0x03};
        File file = new File(filename);
        byte[] fileData;
        byte[] toSend = null;
        try {
            fileData = Files.readAllBytes(file.toPath());
            int length = Math.min(512, fileData.length - i * 512);
            toSend = new byte[opcode.length + 2 + length]; // opcode + block number (2 bytes) + data
            int index = 0;
            // Copy the opcode
            for (byte by : opcode) {
                toSend[index++] = by;
            }
            // Copy the block number
            toSend[index++] = (byte) (blockNumber >> 8); // High byte
            toSend[index++] = (byte) blockNumber; // Low byte
    
            // Copy the data for this chunk
            System.arraycopy(fileData, i * 512, toSend, index, length);
            return toSend;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return toSend;
    }

    public int getNumberOfChunk(String filename) {

        File file = new File(filename);
            
        // Read the entire file
        byte[] fileData;
        int totalChunks = 0;
        try {
            fileData = Files.readAllBytes(file.toPath());
            // Calculate the total number of chunks
            totalChunks = (fileData.length + 511) / 512;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return totalChunks;
    }

    public void sendChunk(byte[] chunk) {

        // Send Packet
        DatagramPacket packet;
        try {
            packet = new DatagramPacket(chunk, chunk.length, InetAddress.getByName(ipAddress), 12345);
            socket.send(packet);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}
