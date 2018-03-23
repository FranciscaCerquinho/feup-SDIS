import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.UnknownHostException;


public class MultiCastChannel{

	private static InetAddress address;
	private static int port;
	
	public MultiCastChannel(String address, int port) throws UnknownHostException{

		try {
			this.address = InetAddress.getByName(address);
			this.port = port;

		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	public static void sendMessage(String peer_sender, String message) throws UnknownHostException, InterruptedException{

		//open a datagramsocket to send data
			
      
		try(DatagramSocket senderSocket = new DatagramSocket()){

			//create a packet that will contain the data
			DatagramPacket msgPacket = new DatagramPacket(message.getBytes(),message.getBytes().length,address,port);
			senderSocket.send(msgPacket);

			System.out.println("Initiator peer sent packet with: " + message);
		} catch(IOException ex){
			ex.printStackTrace();

		}



	}

/*
	public static void openSocket(){
		try{
			receiverSocket = new MulticastSocket(port);

			//join the group

			receiverSocket.joinGroup(address);
		}catch(IOException e){
			e.printStackTrace();
		}
*/
	//}

public static void receiveMessage() throws UnknownHostException {

		//buffer of bytes to store incoming bytes from the initiator peer

		byte[] buf = new byte[256];

		//multicast socket that can be in a group

		try(MulticastSocket receiverSocket = new MulticastSocket(port)){

			//join the group

			receiverSocket.joinGroup(address);

			while(true){

				DatagramPacket msgReceiverPacket = new DatagramPacket(buf,buf.length);
				receiverSocket.receive(msgReceiverPacket);

				String answer = new String(buf, 0, buf.length);

				System.out.println("Peer received msg: " + answer);

			}


		}catch(IOException ex){
			ex.printStackTrace();
		}


	}





}
