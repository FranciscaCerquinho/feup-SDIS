import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.UnknownHostException;


public class MultiCastChannel implements Runnable{

	private static InetAddress address;
	private static int port;
	private static MulticastSocket receiverSocket;
	private static String peer_sending;
	private static String peer_subscribed;
	
	public MultiCastChannel(String address, int port ) throws UnknownHostException{

		try {
			this.address = InetAddress.getByName(address);
			this.port = port;

		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}



	public void setSubscribe(String peer_subscribe_id){
		peer_subscribed = peer_subscribe_id;
	}
	public static void sendMessage(String peer_sender, byte[] toSendContent) throws UnknownHostException, InterruptedException{

		peer_sending = peer_sender;
		//open a datagramsocket to send data
			
      
		try(DatagramSocket senderSocket = new DatagramSocket()){

			//create a packet that will contain the data
			DatagramPacket msgPacket = new DatagramPacket(toSendContent ,toSendContent.length,address,port);
			senderSocket.send(msgPacket);

			System.out.println("Initiator peer sent packet with: content");
		} catch(IOException ex){
			ex.printStackTrace();

		}



	}

	public static void openSocket(){
		try{
			receiverSocket = new MulticastSocket(port);

			//join the group

			receiverSocket.joinGroup(address);
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	@Override
	public void run(){
		

		byte[] buf = new byte[256];
		openSocket();

		try{
			while(true){

				DatagramPacket msgReceiverPacket = new DatagramPacket(buf,buf.length);
				receiverSocket.receive(msgReceiverPacket);

				if(peer_subscribed != peer_sending){			

				String answer = new String(buf, 0, buf.length);

				System.out.println("Received");

			//	System.out.println("Peer received msg: " + answer);
			//	break;
			} 
			
			}




		}catch(IOException ex){
			ex.printStackTrace();
		}
		

	}





}
