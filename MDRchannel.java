import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.*;
import java.io.*;




import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MDRchannel implements Runnable{

	private static InetAddress address;
	private static int port;
	private static MulticastSocket receiverSocket;

	private static ExecutorService exec;
	
	
	public MDRchannel(String address, int port) throws UnknownHostException{

			exec = Executors.newFixedThreadPool(100);
			

		try {
			this.address = InetAddress.getByName(address);
			this.port = port;

		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}



	
	public static void sendMessage(byte[] toSendContent) throws UnknownHostException, InterruptedException{

	
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


				


				
				//String answer = new String(msgReceiverPacket.getData());
			
				
				//if(answer != null){
					byte[] toSend = Arrays.copyOfRange(buf, 0, buf.length-1);
				
				exec.execute(new MessageTreatment(toSend));
			//}
			

			
		
			
			}




		}catch(IOException ex){
			ex.printStackTrace();
		}
		

	}





}
