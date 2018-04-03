import java.net.MulticastSocket;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.UnknownHostException;

import java.util.ArrayList;

public class FileStore{


	private  String fileID;
	private ArrayList<Chunk> chunks = new ArrayList<>();
	private int repDegree;

	public FileStore(String fileID, int repDegree){

		this.fileID = fileID;
		this.repDegree = repDegree;


	}


	public  String getFileID(){
		return fileID;
	}

	public ArrayList<Chunk> getChunks(){
		return chunks;
	}

	public void addChunks(Chunk chunk){
		chunks.add(chunk);
	}



















}