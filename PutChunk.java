
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
      boolean chunkExists=false;

      String toTest = splitAnswer[3] + splitAnswer[4];

      for(int p = 0 ; p < Peer.getStoredFile().size();p++){
        for(int o = 0; o < Peer.getStoredFile().get(p).getChunks().size();o++){
          if(toTest.equals(Peer.getStoredFile().get(p).getChunks().get(o).getFileID()+Integer.toString(Peer.getStoredFile().get(p).getChunks().get(o).getChunkNo())) ){
            chunkExists=true;
          }
        }
      }
      			this.partCounter += 1;
      			int a = 0;
            int accumulatedSpace = 0 ;
      			for(; a < Peer.getStoredFile().size(); a++){
      				 if(splitAnswer[3].equals(Peer.getStoredFile().get(a).getFileID())){
      				 	exists = true;
      				 	break;
      				 }
      				}


             for(int x = 0; x < Peer.getStoredFile().size(); x++){

              for(int h =0 ; h < Peer.getStoredFile().get(x).getChunks().size(); h++ ){
                  accumulatedSpace = accumulatedSpace + Peer.getStoredFile().get(x).getChunks().get(h).getContent().length;
              }
             }



               if(!(Peer.getPeerID().equals(splitAnswer[2])) && Peer.getMaxSpace() >= body.length + accumulatedSpace){

      			if(!exists){
					fileStore = new FileStore(splitAnswer[3], Integer.parseInt(splitAnswer[5]));
					Peer.addStoredFile(fileStore);
      			}else{
      				fileStore = Peer.getStoredFile().get(a);
      			}
      			
      			String  toCheck = splitAnswer[3]+splitAnswer[4];

      			boolean stop = false;

      			if(Peer.getDegreeControl().containsKey(toCheck)){
      				stop = ( Integer.parseInt(Peer.getDegreeControl().get(toCheck)) >= Integer.parseInt(splitAnswer[5]) );
      			}

      			if(!stop && Integer.parseInt(splitAnswer[5]) != 0 && !chunkExists ){
      		
      			

      			
				try{
					File dir = new File(System.getProperty("user.dir"),"backup");
					dir.mkdir();
				FileOutputStream fos = new FileOutputStream(new File(dir.getAbsolutePath(), splitAnswer[3]+splitAnswer[4]));
				  	fos.write(body);
  					fos.close();

  				}catch(Exception ex){
  					ex.printStackTrace();
  				}



  				  Chunk toStore = new Chunk(splitAnswer[3],Integer.parseInt(splitAnswer[4]), Integer.parseInt(splitAnswer[5]), body);
  				 
           


  				 
  				  
  				  	fileStore.getChunks().add(toStore);

  				  
  				  		
  				  
  				  	 Message message = new Message();
              	 	 byte[] response = message.sendStored(splitAnswer[3], splitAnswer[4], Peer.getPeerID());
               	
               
            		  exec.execute(new SendMessageToChannel("mc",response));

            		}

  				  }

  				
  					
               	
               	







	}









}