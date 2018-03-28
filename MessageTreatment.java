import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;

import java.util.Random;
import java.util.concurrent.*;

public class MessageTreatment implements Runnable{

	private static ScheduledThreadPoolExecutor exec;
	private byte[] messageToBeTreated;


	

	public MessageTreatment(byte[] messageToBeTreated){
        exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(100);

		this.messageToBeTreated = messageToBeTreated;
		

	}




	@Override
	public void run(){
			

			int i=0;	
        for (; i < this.messageToBeTreated.length-6; i++){
            if(this.messageToBeTreated[i]==0xD && this.messageToBeTreated[i+1] == 0xA && this.messageToBeTreated[i+2]==0xD && this.messageToBeTreated[i+3]==0xA){
                break;
            }
        }
        byte[] header = Arrays.copyOfRange(this.messageToBeTreated, 0, i);
       
      	String answer = new String(header, 0, header.length);
      	String[] splitAnswer = answer.split(" ");
      
      	String typeOfMessage = splitAnswer[0];

      	

      	switch(typeOfMessage){

      		case "PUTCHUNK":
          System.out.println(answer);
          byte[] body = Arrays.copyOfRange(this.messageToBeTreated, i+4, this.messageToBeTreated.length);
           int rand = new Random().nextInt(400);
          
            exec.schedule(new PutChunk(body, splitAnswer),rand,TimeUnit.MILLISECONDS);

      		
  				

      		break;

      		case "STORED":
      		

            System.out.println(answer);

      		break;


      		default:
      			System.out.println("Bad message.");

    	};
        			
       
					



				
			}










}