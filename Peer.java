import java.net.MulticastSocket;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.UnknownHostException;


public class Peer implements RMIinterface {
	
	private static String peerID;
	private static MultiCastChannel mc_channel;
	private static String peer_sender;

	private Peer() throws UnknownHostException{

		try{
			this.mc_channel = new MultiCastChannel("224.0.0.3", 4444);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
	
	public void message(String peer_id) throws UnknownHostException, InterruptedException {

		peer_sender = peer_id;
		System.out.println(peer_sender+peer_id);
		
		System.out.println("Hello, world! I'm peer number " + this.peerID);

		mc_channel.sendMessage(this.peerID, "Hello peer");
			
		
	}
	public static void main(String[] args) throws UnknownHostException{

		peerID = args[0];

		
		
		try{
			
			Peer obj = new Peer();
			RMIinterface stub = (RMIinterface) UnicastRemoteObject.exportObject(obj, 0);
			
			
			Registry registry = LocateRegistry.getRegistry();
			
			registry.bind(peerID, stub);
			
			System.err.println("Peer ready");	
		}catch(Exception e){
			System.err.println("Peer exception: " + e.toString());
			e.printStackTrace();
		}
		
		while(true){
			if(peerID != peer_sender){
			mc_channel.receiveMessage();
		}else break;
		}
		
	}
	
	
	
	
}