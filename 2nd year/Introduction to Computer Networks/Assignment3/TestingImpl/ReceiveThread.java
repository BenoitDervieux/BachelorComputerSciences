import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;

public class ReceiveThread extends Thread {

    private static String ipAddress = "127.0.0.1";
    private static DatagramSocket socket;


    public ReceiveThread(DatagramSocket socket, String ipAddress) {
        ReceiveThread.socket = socket;
        ReceiveThread.ipAddress = ipAddress;   
    }

    public synchronized void run() {

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        int sequenceNumber = 0;
        boolean is516 = true;
        while (is516) {
        try {
            // Receive data
            byte[] receivedData = new byte[516];
            DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);
            socket.receive(packet);

            if (packet.getLength() < 516) {
                is516 = false;
            }
            
            // Here we are getting the sequence number
            sequenceNumber = (short) receivedData[2] * 10 + (short) receivedData[3];
            System.out.println("SEQUENCE NUMBER:" + sequenceNumber);

            // Crop only the data
            byte[] newlyreceived = new byte[packet.getLength() - 4];
            System.arraycopy(receivedData, 4, newlyreceived, 0, newlyreceived.length);

            // Write to the file
            byte [] mybytearray  = newlyreceived;
            InputStream is = new ByteArrayInputStream(newlyreceived);


            // Here is the method to have the same concept of files as in Windows
            String filename = "received.txt";
            String subFilename = "";
            String path = "./write/";
            File folder = new File(path);
            File file = new File(path + filename);
            int existingIndex = 1;
            if (file.exists() && sequenceNumber == 1) {
                if (subFilename.equals("")) {
                    subFilename = filename.split("\\.")[0] + "(" + existingIndex + ")." + filename.split("\\.")[1];
                    System.out.println("SUBFILNAME: " + subFilename);
                    System.out.println("FILE: " + file.getName());
                }
                for (File f: folder.listFiles()) {
                    try {
                        existingIndex = Integer.parseInt((f.getName().split("\\(")[1]).split("\\)")[0]);
                        subFilename = filename.split("\\.")[0] + "(" + ++existingIndex + ")." + filename.split("\\.")[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                }
            }
            if (!subFilename.equals("")) {
                filename = subFilename;
            }
            // end of the method


            File outputFile = new File(path + filename);
            fos = new FileOutputStream(outputFile, true);
            bos = new BufferedOutputStream(fos);
            int bytesRead;
            int current = sequenceNumber * 512;
            bytesRead = is.read(mybytearray,0,mybytearray.length);
            current = bytesRead;
            do {
                bytesRead =
                    is.read(mybytearray, current, (mybytearray.length-current));
                if(bytesRead >= 0) current += bytesRead;
            } while(bytesRead > -1);
            bos.write(mybytearray, 0 , current);
            bos.flush();


            // Send Ack back
            byte[] opcode = new byte[]{ (byte) 0x04,0x00 };
            byte[] blockNumber = new byte[]{ (byte) 0x00,0x01 };
            byte[] request = new byte[opcode.length + blockNumber.length];
            int index = 0;
            for (byte by : opcode ) {
                request[index] = by;
                index++;
            }
            for (byte by : blockNumber ) {
                request[index] = by;
                index++;
            }
            DatagramPacket packetpacket = new DatagramPacket(request, request.length, InetAddress.getByName(ipAddress), 12345);
            socket.send(packetpacket);
            System.out.println("File sent.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
          } 
        }
    }

    public static byte[] intToBytes(short number) {

        byte[] byteArray = new byte[2];

        // Extract the most significant byte (MSB) and least significant byte (LSB)
        byteArray[0] = (byte) ((number >> 8) & 0xFF);
        byteArray[1] = (byte) (number & 0xFF);

        return byteArray;

    }


    
}
