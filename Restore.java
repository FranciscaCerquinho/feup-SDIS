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

public class Restore implements Runnable{

	private File file;
	private static ExecutorService exec;



	public Restore(File file){
		this.file = file;
		exec = Executors.newFixedThreadPool(100);

	}







	@Override
	public void run(){

		long i=1;

		System.out.println("hi");
		String path = file.getParent().toString();

        String fileIdToEncrypt = path+file.lastModified();
        
        String fileIDencrypted = Utils.sha256(fileIdToEncrypt);

        long size = file.length();

        long numberOfChunks = size/64000 + 1;



        System.out.println("Number of chunks to restore: " + numberOfChunks);
          
        while(i <= numberOfChunks){

        Message message = new Message();
        int a = (int) i ;
        String chunkNo = Integer.toString(a);
        byte[] infoToSend = message.getChunk(fileIDencrypted, chunkNo, Peer.getPeerID());

        exec.execute(new SendMessageToChannel("mc",infoToSend));
        

        i++;
            try{
                        Thread.sleep(400);
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                  

    	}










	}

}