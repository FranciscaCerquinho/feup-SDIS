import java.net.MulticastSocket;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.UnknownHostException;

import java.util.ArrayList;

public class FileStore{


	private static String fileID;
	private ArrayList<Chunk> chunks = new ArrayList<>();

	public FileStore(String fileID){

		this.fileID = fileID;


	}


	public static String getFileID(){
		return fileID;
	}

	public ArrayList<Chunk> getChunks(){
		return chunks;
	}

	public void addChunks(Chunk chunk){
		chunks.add(chunk);
	}



















}