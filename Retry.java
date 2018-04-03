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

public class Retry implements Runnable{

	

	public int timeDelay= 2 ;

	public int counter = 1;

    private static ScheduledThreadPoolExecutor exec;

    private byte[] infoToSend;

   	private Chunk chunk;

	public Retry(byte[] infoToSend, Chunk chunk){

		exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(50);
		this.infoToSend = infoToSend;
		this.chunk = chunk;

	}



	@Override
	public void run(){

			String name = chunk.getFileID()+Integer.toString(chunk.getChunkNo());
			int test=0;

		



		if(Peer.getDegreeControl().containsKey(name)){
		 test = Integer.parseInt(Peer.getDegreeControl().get(name));
		}


		if( test < chunk.getChunkReplication() ){


			System.out.println("degree control: " + Peer.getDegreeControl().get(name));

		  exec.schedule(new SendMessageToChannel("mdb",infoToSend),0,TimeUnit.MILLISECONDS);
		
		

		  timeDelay = timeDelay * 2;

		  counter = counter +1;

		  System.out.println("Counter2 " + counter);

		}


		  if(counter < 5){
      
		  exec.schedule(this,timeDelay,TimeUnit.MILLISECONDS);
		}

		


	




	}

















}