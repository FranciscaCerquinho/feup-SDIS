
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










public class RemovedAndPut implements Runnable{


	private String[] splitAnswer;
	private static ScheduledThreadPoolExecutor exec;

	public RemovedAndPut(String[] splitAnswer){
		this.splitAnswer = splitAnswer;
		exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(100);

	}



	@Override
	public void run(){





		String chunkName = splitAnswer[3] + splitAnswer[4];

		if(Peer.getDegreeControl().containsKey(chunkName)){

			int actualValue = Integer.parseInt(Peer.getDegreeControl().get(chunkName));
			String newValue = Integer.toString(actualValue-1);
			Peer.getDegreeControl().put(chunkName,newValue);	
		}



		if(Peer.getPeerID() != splitAnswer[2]){


			for(int i=0; i < Peer.getStoredFile().size(); i++){

				for(int a=0; a < Peer.getStoredFile().get(i).getChunks().size(); a++){
					String name = Peer.getStoredFile().get(i).getChunks().get(a).getFileID() + Integer.toString(Peer.getStoredFile().get(i).getChunks().get(a).getChunkNo());
					if(name.equals(chunkName)){
						
						Message message = new Message();

               
                		byte[] infoToSend = message.sendPutChunk(Peer.getStoredFile().get(i).getChunks().get(a), Peer.getPeerID());
                		int rand = new Random().nextInt(401);


						 exec.schedule(new SendMessageToChannel("mdb",infoToSend),rand,TimeUnit.MILLISECONDS);
						 break;

					}
				}

			}



		}








	}











}