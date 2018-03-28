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
	private static MDBchannel mdbchannel;
	private static MDRchannel mdrchannel;
	private static ExecutorService exec;
	private static ArrayList<Chunk> chunksStored = new ArrayList<Chunk>();

	private Peer() throws UnknownHostException{

			exec = Executors.newFixedThreadPool(100);
		try{
			this.mc_channel = new MultiCastChannel("228.0.0.5", 8080);
			this.mdrchannel = new MDRchannel("228.0.0.6",8081);
			this.mdbchannel = new MDBchannel("228.0.0.7", 8082);

			
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}


	}

	public static MDBchannel getMDBchannel(){
		return mdbchannel;
	}

	public static ArrayList<Chunk> getStoredChunks(){
		return chunksStored;
	}

	public static void addStoredChunk(Chunk chunk){
		chunksStored.add(chunk);
	}

	public static MultiCastChannel getMCchannel(){
		return mc_channel;
	}

	public static MDRchannel getMDRchannel(){
		return mdrchannel;
	}

	public static String getPeerID(){
		return peerID;
	}
	
	public static void messageMDB(byte[] message) throws RemoteException, UnknownHostException, InterruptedException {
		

		mdbchannel.sendMessage(message);

				
	}


	public static void messageMDR(byte[] message) throws RemoteException, UnknownHostException, InterruptedException {
		
	
		mdrchannel.sendMessage(message);

				
	}


	public static void messageMC(byte[] message) throws RemoteException, UnknownHostException, InterruptedException {
		

		mc_channel.sendMessage(message);

				
	}


	public void backup(String fileID, int repDegree) throws RemoteException, UnknownHostException, InterruptedException{
		exec.execute(new Backup(new File("C:\\Users\\Ventura\\Desktop\\exemplo\\"+fileID), repDegree));

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


        System.out.println(peerID);
		
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
		
		
		exec.execute(mc_channel);
		exec.execute(mdbchannel);
		exec.execute(mdrchannel);
		

		
	}
}