import java.net.MulticastSocket;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.UnknownHostException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;

import java.io.File;


public class Peer implements RMIinterface {
	
	private static String peerID;
	private static MultiCastChannel mc_channel;
	private static ExecutorService exec;

	private Peer() throws UnknownHostException{

			exec = Executors.newFixedThreadPool(5);
		try{
			this.mc_channel = new MultiCastChannel("228.0.0.4", 8080);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}


	}


	public static String getPeerID(){
		return peerID;
	}
	
	public void message(byte[] message) throws RemoteException, UnknownHostException, InterruptedException {
		
		System.out.println("Hello, world! I'm peer number " + this.peerID);

		mc_channel.sendMessage(this.peerID, message);

					
		
	}


	public void backup(String fileID, int repDegree) throws RemoteException, UnknownHostException, InterruptedException{
		exec.execute(new Backup(new File("C:\\Users\\Ventura\\Desktop\\exemplo\\"+fileID), repDegree, this));

	}

	public void restore() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose restore subprotocol");
	}

	public void delete() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose delete subprotocol");
	}

	public void reclaim(int maxDiskSpace) throws RemoteException, UnknownHostException, InterruptedException{
		//exec.execute(new Reclaim(maxDiskSpace, fileInformation));
		System.out.println("Chose reclaim subprotocol");
	}

	public void state() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose state subprotocol");
	}


	public static void main(String[] args) throws UnknownHostException{

		
		peerID = args[0];
        System.setProperty("java.net.preferIPv4Stack", "true");



		
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
		
		
		exec.execute(mc_channel);
		

		
	}
}