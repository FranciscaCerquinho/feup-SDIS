import java.net.MulticastSocket;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.UnknownHostException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class Peer implements RMIinterface {
	
	private static String peerID;
	private static MultiCastChannel mc_channel;
	private static ExecutorService exec;

	private Peer() throws UnknownHostException{

			exec = Executors.newFixedThreadPool(5);
		try{
			this.mc_channel = new MultiCastChannel("224.0.0.4", 8888);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}


	}
	
	public void message(String message) throws UnknownHostException, InterruptedException {
		
		System.out.println("Hello, world! I'm peer number " + this.peerID);

		mc_channel.sendMessage(this.peerID, message);

					
		
	}

	public void backup() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose backup subprotocol");

	}

	public void restore() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose restore subprotocol");
	}

	public void delete() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose delete subprotocol");
	}

	public void reclaim() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose reclaim subprotocol");
	}

	public void state() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose state subprotocol");
	}


	public static void main(String[] args) throws UnknownHostException{

		
		peerID = args[0];



		
		try{
			
			Peer obj = new Peer();
			obj.mc_channel.setSubscribe(peerID);
			RMIinterface stub = (RMIinterface) UnicastRemoteObject.exportObject(obj, 0);
			
			
			Registry registry = LocateRegistry.getRegistry();
			
			registry.bind(peerID, stub);
			
			System.err.println("Peer ready");	
		}catch(Exception e){
			System.err.println("Peer exception: " + e.toString());
			e.printStackTrace();
		}
		
		//while(true){
		//	mc_channel.receiveMessage();
		//}
		exec.execute(mc_channel);
		//exec.execute(mc_channel);

		
	}
	
	
	
	
}