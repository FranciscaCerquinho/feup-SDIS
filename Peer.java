import java.net.MulticastSocket;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Peer implements RMIinterface{
	
	private static int peerID;
	private static MulticastSocket socket;
	
	
	public Peer(){}
	
	public String helloWorld(){
		return "Hello, world!";
	}
	public static void main(String[] args){
		
		System.setProperty()
		
		try{
			
			Peer obj = new Peer();
			RMIinterface stub = (RMIinterface) UnicastRemoteObject.exportObject(obj,0);
			
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("trial", stub);
			
			System.err.println("Server ready");	
		}catch(Exception e){
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
}