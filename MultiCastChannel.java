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
	public static void sendMessage(String peer_sender, String message) throws UnknownHostException, InterruptedException{

		peer_sending = peer_sender;
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

	public static void openSocket(){
		try{
			receiverSocket = new MulticastSocket(port);

			//join the group

			receiverSocket.joinGroup(address);
		}catch(IOException e){
			e.printStackTrace();
		}

	}

//public static void receiveMessage() throws UnknownHostException {
	@Override
	public void run(){
		//buffer of bytes to store incoming bytes from the initiator peer

		/*System.out.println("I AM ***" + peer_subscribed);
		for(int i=0; i<10;i++){
			System.out.println("result: " + i);

		}*/

		byte[] buf = new byte[256];
		openSocket();

		try{
			while(true){

				DatagramPacket msgReceiverPacket = new DatagramPacket(buf,buf.length);
				receiverSocket.receive(msgReceiverPacket);

				if(peer_subscribed != peer_sending){			

				String answer = new String(buf, 0, buf.length);

				System.out.println("Peer received msg: " + answer);
				break;
			} 
			
			}




		}catch(IOException ex){
			ex.printStackTrace();
		}
		//catch(InterruptedException ex){
		//	ex.printStackTrace();
		//}


	}





}
