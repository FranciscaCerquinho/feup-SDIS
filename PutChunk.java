
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




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

		
      			this.partCounter += 1;
				
				try{

				FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Ventura\\Desktop\\backup", splitAnswer[3]+splitAnswer[4]));
				  	fos.write(body);
  					fos.close();

  				}catch(Exception ex){
  					ex.printStackTrace();
  				}

  				  Chunk toStore = new Chunk(splitAnswer[3],Integer.parseInt(splitAnswer[4]), Integer.parseInt(splitAnswer[5]), body);
  				 
            System.out.println(Peer.getPeerID());
            System.out.println(splitAnswer[2]);


  				  if(!(Peer.getPeerID().equals(splitAnswer[2]))){
  				  	Peer.addStoredChunk(toStore);
  				  
  				  }
  				
  					
               	
               	 Message message = new Message();
               	 byte[] response = message.sendStored(splitAnswer[3], splitAnswer[4], Peer.getPeerID());
               	
               
              exec.execute(new SendMessageToChannel("mc",response));








	}









}