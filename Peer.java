import java.net.MulticastSocket;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.UnknownHostException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;

import java.util.concurrent.ConcurrentHashMap;

import java.io.File;


public class Peer implements RMIinterface {
	
	private static String peerID;
	private static int maxChunks=111110;
	private static  int maxSpace= 30000000;
	private static MultiCastChannel mc_channel;
	private static MDBchannel mdbchannel;
	private static MDRchannel mdrchannel;
	private static ExecutorService exec;
	private static ArrayList<FileStore> filesStored = new ArrayList<FileStore>();
	private static ConcurrentHashMap<String,String> degreeControl = new ConcurrentHashMap<>();
	private static ArrayList<Chunk> restored = new ArrayList<Chunk>();
	private static ArrayList<Integer> chunkNoRestored = new ArrayList<Integer>();
	

	private Peer() throws UnknownHostException{

			exec = Executors.newFixedThreadPool(1000);
		


	}

	public static int getMaxChunks(){
		return maxChunks;
	}

	public static void setMaxChunks(int chunksmax){
		maxChunks=chunksmax;
	}
	public static ArrayList<Integer> getRestoredNumber(){
		return chunkNoRestored;
	}

	public static ConcurrentHashMap<String,String> getDegreeControl(){
	 	return degreeControl;
	}

	public static int getMaxSpace(){
		return maxSpace;
	}

	public static void setMc(String ip, int port){
		try{
			
			
			mc_channel = new MultiCastChannel(ip, port);

			
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

	public static void setMdb(String ip, int port){
		try{
		mdbchannel = new MDBchannel(ip, port);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static void setMdr(String ip, int port){
		try{
		mdrchannel = new MDRchannel(ip,port);
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}



	
	public static ArrayList<Chunk> getRestored(){
		return restored;
	}

	public static void setSpaceValue(int max){
		maxSpace = max;
	}

	public static MDBchannel getMDBchannel(){
		return mdbchannel;
	}

	public static ArrayList<FileStore> getStoredFile(){
		return filesStored;
	}

	public static void addStoredFile(FileStore file){
		filesStored.add(file);
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



	public void restore(String fileID) throws RemoteException, UnknownHostException, InterruptedException{
		exec.execute(new Restore(new File("C:\\Users\\Ventura\\Desktop\\exemplo\\"+fileID)));
	}

	public void delete(String fileID) throws RemoteException, UnknownHostException, InterruptedException{
		exec.execute(new Delete(new File("C:\\Users\\Ventura\\Desktop\\exemplo\\"+fileID)));
	}

	public void reclaim(int space) throws RemoteException, UnknownHostException, InterruptedException{
		
		exec.execute(new Reclaim(space));
	}

	public void state() throws RemoteException, UnknownHostException, InterruptedException{
		System.out.println("Chose state subprotocol");
	}


	public static void main(String[] args) throws UnknownHostException{

		String version= args[0];
		peerID = args[1];
		String mcIp= args[2];
		int mcPort= Integer.parseInt(args[3]);
		String mdbIp= args[4];
		int mdbPort= Integer.parseInt(args[5]);
		String mdrIp= args[6];
		int mdrPort= Integer.parseInt(args[7]);

        System.setProperty("java.net.preferIPv4Stack", "true");


        System.out.println(peerID);
		
		try{

			
			Peer obj = new Peer();

			obj.setMc(mcIp,mcPort);
			obj.setMdb(mdbIp,mdbPort);
			obj.setMdr(mdrIp,mdrPort);
			
			
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