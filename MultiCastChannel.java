import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.*;
import java.io.*;




import java.util.Random;
import java.util.concurrent.*;

public class MultiCastChannel implements Runnable{

	private static InetAddress address;
	private static int port;
	private static MulticastSocket receiverSocket;
	private static String peer_sending;
	private static String peer_subscribed;
	private static ScheduledThreadPoolExecutor exec;

	
	public MultiCastChannel(String address, int port ) throws UnknownHostException{

			exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);


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
		

		byte[] buf = new byte[64000];
		openSocket();

		try{
			while(true){

				DatagramPacket msgReceiverPacket = new DatagramPacket(buf,buf.length);
				receiverSocket.receive(msgReceiverPacket);


				if(peer_subscribed != peer_sending){	


				
				//String answer = new String(msgReceiverPacket.getData());
			
				
				//if(answer != null){
					byte[] toSend = Arrays.copyOfRange(buf, 0, buf.length-1);
				int rand = new Random().nextInt(400);	
				exec.schedule(new MessageTreatment(toSend),rand,TimeUnit.MILLISECONDS);
			//}
			

			
			} 
			
			}




		}catch(IOException ex){
			ex.printStackTrace();
		}
		

	}





}
