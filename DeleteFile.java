import java.net.UnknownHostException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.List;
import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.Random;
import java.util.concurrent.*;



public class DeleteFile implements Runnable{

	private String[] splitAnswer;

	public DeleteFile(String[] splitAnswer){

		this.splitAnswer = splitAnswer;

	}




	@Override
	public void run(){

		if(!(Peer.getPeerID().equals(splitAnswer[2]))){

		int a=0;
		System.out.println("ABOUT TO DELETE");
		System.out.println("test: " + Peer.getStoredFile().size());
		for(; a < Peer.getStoredFile().size();a++){
			if(splitAnswer[3].equals(Peer.getStoredFile().get(a).getFileID())){
				Peer.getStoredFile().remove(a);
			}
		}

		System.out.println("test2: " + Peer.getStoredFile().size());


		File dir = new File(System.getProperty("user.dir"), "backup");
		File[] directoryListing = dir.listFiles();
		for(int i=0; i < directoryListing.length; i++){

			String name = directoryListing[i].getName();
		
			String nameToProcess = name.substring(0,64);
			System.out.println("process! " + nameToProcess);
			System.out.println("process! to delete " + splitAnswer[3]);
			if(splitAnswer[3].equals(nameToProcess)){
				System.out.println("TO DELETEEE");
				directoryListing[i].delete();
			}
		}


	}



}









} 