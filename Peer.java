import java.net.MulticastSocket;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Peer implements RMIinterface{
	
	private static String peerID;
	private MultiCastChannel mc_channel;
	
	
	private Peer(){

		this.mc_channel = new MultiCastChannel("224.0.0.3", 4444);


	}
	
	public String helloWorld(){
		return "Hello, world! I'm peer number " + this.peerID;
		
	}
	public static void main(String[] args){

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
		
		
		
	}
	
	
	
	
}