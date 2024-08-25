
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TFTPServer 
{
	public static final int TFTPPORT = 4970;
	public static final int BUFSIZE = 516;
	public static final String READDIR = "read/"; //custom address at your PC
	public static final String WRITEDIR = "read/"; //custom address at your PC

	// OP codes
	public static final short OP_RRQ = 1;
	public static final short OP_WRQ = 2;
	public static final short OP_DAT = 3;
	public static final short OP_ACK = 4;
	public static final short OP_ERR = 5;

	public static void main(String[] args) {

		if (args.length > 0) {
			System.err.printf("usage: java %s\n", TFTPServer.class.getCanonicalName());
			System.exit(1);
		}
		//Starting the server
		try 
		{
			TFTPServer server= new TFTPServer();
			server.start();
		}
		catch (SocketException e) 
			{e.printStackTrace();}
	}
	
	private void start() throws SocketException {
		byte[] buf = new byte[BUFSIZE];
		
		// Create socket
		DatagramSocket socket= new DatagramSocket(null);
		
		// Create local bind point 
		SocketAddress localBindPoint= new InetSocketAddress(TFTPPORT);
		socket.bind(localBindPoint);

		System.out.printf("Listening at port %d for new requests\n", TFTPPORT);

		// Loop to handle client requests 
		while (true) 
		{        
			
			final InetSocketAddress clientAddress = receiveFrom(socket, buf);
			
			// If clientAddress is null, an error occurred in receiveFrom()
			if (clientAddress == null) 
				continue;

			final StringBuffer requestedFile = new StringBuffer();
			final int reqtype = ParseRQ(buf, requestedFile);

			new Thread() 
			{
				public void run() 
				{
					try 
					{
						DatagramSocket sendSocket= new DatagramSocket(0);

						// Connect to client
						sendSocket.connect(clientAddress);						
						
						System.out.printf("%s request for %s from %s using port %d\n",
								(reqtype == OP_RRQ)?"Read":"Write",
								clientAddress.getHostName(), clientAddress.getHostString(), clientAddress.getPort());  
								
						// Read request
						if (reqtype == OP_RRQ) {      
							requestedFile.insert(0, READDIR);
							HandleRQ(sendSocket, requestedFile.toString(), OP_RRQ);
						}
						// Write request
						else {       
							// Checks if opcode within 1-5 , else send error packet.
							if (reqtype < 1 || reqtype > 5) {
								errorPacket(sendSocket, 4, "Illegal transfer ID.");
								return;
							}
							
							requestedFile.insert(0, WRITEDIR);
							HandleRQ(sendSocket,requestedFile.toString(),OP_WRQ);  
						}
						sendSocket.close();
					} 
					catch (SocketException e) 
						{e.printStackTrace();}
				}
			}.start();
		}
	}
	
	/**
	 * Reads the first block of data, i.e., the request for an action (read or write).
	 * @param socket (socket to read from)
	 * @param buf (where to store the read data)
	 * @return socketAddress (the socket address of the client)
	 */
	private InetSocketAddress receiveFrom(DatagramSocket socket, byte[] buf) {
		// Create datagram packet
		DatagramPacket packet =  new DatagramPacket(buf, BUFSIZE);

		// Receive packet
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get client address and port from the packet
		InetAddress senderIP = packet.getAddress();
		int senderPort = packet.getPort();
		InetSocketAddress socketAddress = new InetSocketAddress(senderIP, senderPort);
		
		return socketAddress;
	}

	/**
	 * Parses the request in buf to retrieve the type of request and requestedFile
	 * 
	 * @param buf (received request)
	 * @param requestedFile (name of file to read/write)
	 * @return opcode (request type: RRQ or WRQ)
	 */
	private int ParseRQ(byte[] buf, StringBuffer requestedFile) {

		ByteBuffer wrap = ByteBuffer.wrap(buf);
		short opcode = wrap.getShort();

		int pos = -1;
		for (int i = 2; i < buf.length; i++) {
			if (buf[i] == 0) {
				pos = i;
				break;
			}
		}

		String fileName = new String(buf, 2, pos-2);
		System.out.println("Requested file = " + fileName);
		requestedFile.append(fileName);
  
		return opcode;
	}

	/**
	 * Handles RRQ and WRQ requests 
	 * 
	 * @param sendSocket (socket used to send/receive packets)
	 * @param requestedFile (name of file to read/write)
	 * @param opcode (RRQ or WRQ)
	 */
	private void HandleRQ(DatagramSocket sendSocket, String requestedFile, int opcode) {
		System.out.println(requestedFile);
		File file = new File(requestedFile);

		///////////////// READ REQUEST /////////////////
		if(opcode == OP_RRQ) {
			// See "TFTP Formats" in TFTP specification for the DATA and ACK packet contents

			byte[] buf = new byte[BUFSIZE - 4];

			FileInputStream inputStream = null;

			try {
				inputStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				System.err.println("File not found.");
				// File Not Found , Error code 1
				errorPacket(sendSocket, 1, "File not found.");
				return;
			}

			short blockNumber = 1;

			while (true) {
				int length_bytes;
				try {
					length_bytes = inputStream.read(buf);
				} catch (IOException e) {
					System.err.println("Error reading.");
					break;
				}

				// If end of input stream
				if (length_bytes == -1) {
					length_bytes = 0;
				}

				DatagramPacket sendPacket = dataPacket(blockNumber, buf, length_bytes);
				System.out.println("Sending packet...");

				if (send_DATA_receive_ACK(sendSocket, sendPacket, blockNumber++)) {
					System.out.println("Great Success. ");
				} else {
					System.err.println("Error.");
					// TODO send error
					errorPacket(sendSocket, 0, "Error connection..");
					break; // exit the loop after an error
				}

				// If lower  than max packet size, close (last packet/block has been sent)
				if (length_bytes < 512) {
					try {
						inputStream.close();
					} catch (IOException e) {
						System.err.println("Error closing file.");
					}
					break;
				}
			}

		///////////////// WRITE REQUEST /////////////////
		} else if (opcode == OP_WRQ) {
			System.out.println(requestedFile);

			// If file exists, send error packet, Error code 6
			if (file.exists()) {
				System.out.println("File already exists.");
				errorPacket(sendSocket, 6, "File already exists.");
				return;
			}

			FileOutputStream outputStream = null;

			try {
				outputStream = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				// Added error code 2 according  to task, as it otherwise is related to UNIX errors
				errorPacket(sendSocket, 2, "Couldnt create file. (AV)");
			}

			short blockNumber = 0;
			boolean flag = true;

			ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
			buffer.putShort(OP_ACK);
			buffer.putShort(blockNumber);
			DatagramPacket ackpacket = new DatagramPacket(buffer.array(), 4);
			System.out.println("Trying to send ACK for block: " + blockNumber);

			try {
				sendSocket.send(ackpacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			blockNumber++;
			
			while (flag) {
				DatagramPacket packet = receive_DATA_send_ACK(sendSocket, blockNumber);
				byte[] data = packet.getData();
				
				try {
					outputStream.write(data, 4, packet.getLength() - 4);
				} catch (IOException e) {
					System.err.println("Error writing data.");
					// Added error code 2 according  to task, as it otherwise is related to UNIX errors
					errorPacket(sendSocket, 2, "Error writing data. (AV)");
				}

				if (packet.getLength() - 4 < 512) {
					flag = false;
				}
				
				blockNumber++;
			}

			try {
				outputStream.close();
			} catch (IOException e) {
				System.err.println("Error closing file");
			}
		} else {

			// See "TFTP Formats" in TFTP specification for the ERROR packet contents
			errorPacket(sendSocket, 0, "Error, not defined.");

			return;
		}		
	}
	
	/**
	To be implemented
	*/
	private boolean send_DATA_receive_ACK(DatagramSocket socket, DatagramPacket sendPacket, int blocknum) {
		byte[] buf = new byte[BUFSIZE];

		// packet for receiving the ACK
		DatagramPacket packet = new DatagramPacket(buf, buf.length);

		// Timer for not received ACK
		int counter = 0;
		boolean ackReceived = false;

		while (counter <= 5 && !ackReceived) {
			System.out.println("Counter: " + counter);

			try {
				socket.send(sendPacket);
				System.out.println("Packet sent, block = " + blocknum);

				long timeout = 3000;
				long startTime = System.currentTimeMillis();
				long elapsedTime = 0;

				while (elapsedTime < timeout && !ackReceived) {
					try {
						// Set socket timeout for ACK reception
						socket.setSoTimeout((int) (timeout - elapsedTime));
						socket.receive(packet);

						ByteBuffer buffer = ByteBuffer.wrap(packet.getData());
						short op = buffer.getShort();
						// used for testing
						// short op = 1;
						int receivedBlock = buffer.getShort();

						if (op == OP_ACK && receivedBlock ==  blocknum) {
							System.out.println("Ack received for block number: " + blocknum);
							ackReceived = true;
							// return true;
		
						} else if (op == OP_ERR) {
							System.out.println("Error, Code:" + op);
		
						} else {
							System.out.println("Failed, wrong op or block number..");
							System.out.println("OP: "+ op + " BlockNr: " + receivedBlock);
							counter = 0;
							throw new SocketTimeoutException();
						}

					} catch (SocketTimeoutException e) {
						System.out.println("Timeout. Resending.");
					}

					elapsedTime = System.currentTimeMillis() - startTime;
				}

			} catch (IOException e) {
				System.err.println("IO Error. Resending.");
			} finally {
				try {
					socket.setSoTimeout(0);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			counter++;

		}
		if (!ackReceived) {
			System.out.println("Closing connection, timeout.");
		}

		return ackReceived;
	}
	
	private DatagramPacket receive_DATA_send_ACK(DatagramSocket sendSocket, short blockNumber) {
		byte[] buf = new byte[BUFSIZE];
		// short acknum = 0;

		// Send ack first
		ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
		buffer.putShort(OP_ACK);
		buffer.putShort(blockNumber);

		DatagramPacket ackpacket = new DatagramPacket(buffer.array(), 4);

		try {
			System.out.println("Trying to send ACK for block: " + blockNumber);
			sendSocket.send(ackpacket);

			// Receive data
			DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
			sendSocket.receive(receivedPacket);

			// Print received data
			byte[] data = receivedPacket.getData();
			String dataString = new String(data, StandardCharsets.UTF_8);
			String substring = dataString.substring(4);
			
			System.out.println(substring); // testing purposes

			// print OP code 
			ByteBuffer bufferFetchInfo = ByteBuffer.wrap(receivedPacket.getData());
			short x = bufferFetchInfo.getShort();
			System.out.println("OP CODE: " + x);

			short blockReceived = bufferFetchInfo.getShort();
			System.out.println("Block Numbers: \nReceived = " + blockReceived + "\tExpected = " + blockNumber);

			if (blockReceived == blockNumber) {
				return receivedPacket;
			} else {
				System.out.println("Not correct blocknum");
				return null;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	private DatagramPacket dataPacket(short block, byte[] data, int length) {

		ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
		buffer.putShort(OP_DAT);
		buffer.putShort(block);
		buffer.put(data, 0, length);

		return new DatagramPacket(buffer.array(), 4 + length);
	}

	private void errorPacket(DatagramSocket sendSocket, int errorCode, String errorMsg) {

		ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
		buffer.putShort(OP_ERR);
		buffer.putShort((short) errorCode);
		buffer.put(errorMsg.getBytes(StandardCharsets.UTF_8));
		buffer.put((byte) 0);
		
		DatagramPacket errorPacket = new DatagramPacket(buffer.array(), buffer.array().length);
		try {
			sendSocket.send(errorPacket);
		} catch (IOException e) {
			System.err.println("Problem sending error packet.");
			e.printStackTrace();
		}

	}
	
}



