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





public class RestoreFile implements Runnable{

	private String[] splitAnswer;
	private static ScheduledThreadPoolExecutor exec;


	public RestoreFile(String[] splitAnswer){
		

		exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10000);
		this.splitAnswer = splitAnswer;

	}



	@Override
	public void run(){

			
		

		if( !(Peer.getPeerID().equals(splitAnswer[2]))  ){

		for(int i=0; i < Peer.getStoredFile().size(); i++){
			if(Peer.getStoredFile().get(i).getFileID().equals(splitAnswer[3])){

			

				for(int a = 0; a < Peer.getStoredFile().get(i).getChunks().size(); a++){

					if(Peer.getStoredFile().get(i).getChunks().get(a).getChunkNo() == Integer.parseInt(splitAnswer[4])){

						byte[] content = Peer.getStoredFile().get(i).getChunks().get(a).getContent();

						

						Message message = new Message();

						byte[] response = message.chunked(splitAnswer[3], splitAnswer[4], Peer.getPeerID(), content);

						int rand = new Random().nextInt(401);
          

          				
        				exec.schedule(new SendMessageToChannel("mdr",response),rand,TimeUnit.MILLISECONDS);
        				   try{
                        Thread.sleep(400);
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
        				

				}
        				

				}


					}
			}
		}

	}

























}