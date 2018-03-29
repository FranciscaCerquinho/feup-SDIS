
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;



public class PutChunk implements Runnable{
	private int partCounter = 0;
	private byte[] body;
	private String[] splitAnswer;
	private static ExecutorService exec;


	public PutChunk(byte[] body, String[] splitAnswer){
		exec = Executors.newFixedThreadPool(100);
		this.body = body;
		this.splitAnswer=splitAnswer;
	}


	@Override
	public void run(){
		FileStore fileStore;
			boolean exists = false;
      			this.partCounter += 1;
      			int a = 0;
      			for(; a < Peer.getStoredFile().size(); a++){
      				 if(splitAnswer[3].equals(Peer.getStoredFile().get(a).getFileID())){
      				 	exists = true;
      				 	break;
      				 }
      				}
      			if(!exists){
					fileStore = new FileStore(splitAnswer[3], Integer.parseInt(splitAnswer[5]));
					Peer.addStoredFile(fileStore);
      			}else{
      				fileStore = Peer.getStoredFile().get(a);
      			}
      			
				try{

				FileOutputStream fos = new FileOutputStream(new File("/Users/francisca/Desktop/", splitAnswer[3]+splitAnswer[4]));
				  	fos.write(body);
  					fos.close();

  				}catch(Exception ex){
  					ex.printStackTrace();
  				}

  				  Chunk toStore = new Chunk(splitAnswer[3],Integer.parseInt(splitAnswer[4]), Integer.parseInt(splitAnswer[5]), body);
  				 
            System.out.println(Peer.getPeerID());
            System.out.println(splitAnswer[2]);


  				  if(!(Peer.getPeerID().equals(splitAnswer[2]))){
  				  
  				  	fileStore.addChunks(toStore);

  				  	System.out.println("Files stored: " + Peer.getStoredFile().size());
  				  	System.out.println("chunks per file: " + Peer.getStoredFile().get(0).getChunks().size());
  				  
  				  }
  				
  					
               	
               	 Message message = new Message();
               	 byte[] response = message.sendStored(splitAnswer[3], splitAnswer[4], Peer.getPeerID());
               	
               
              exec.execute(new SendMessageToChannel("mc",response));








	}









}