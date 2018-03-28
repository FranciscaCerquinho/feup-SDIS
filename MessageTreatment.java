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


	private int partCounter = 0;

	public MessageTreatment(byte[] messageToBeTreated){

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
      	System.out.println(answer);
      	String typeOfMessage = splitAnswer[0];


      	switch(typeOfMessage){

      		case "PUTCHUNK":
      			byte[] body = Arrays.copyOfRange(this.messageToBeTreated, i+4, this.messageToBeTreated.length);
      			this.partCounter += 1;
				
				try{

				FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Ventura\\Desktop\\backup",splitAnswer[3]+splitAnswer[4]));
				  	fos.write(body);
  					fos.close();

  				}catch(Exception ex){
  					ex.printStackTrace();
  				}
  				
  					ScheduledThreadPoolExecutor exec  = Backup.getExecs();
  					int rand = new Random().nextInt(400);   
               	
               	 Message message = new Message();
               	 byte[] response = message.sendStored(splitAnswer[3], splitAnswer[4], Peer.getPeerID());
               	
               
               exec.schedule(new SendMessageToChannel("mc",response),rand,TimeUnit.MILLISECONDS);
  				

      		break;

      		case "hi":
      		System.out.println("hi");
      		break;


      		default:
      			System.out.println("Bad message.");

    	};
        			
       
					



				
			}










}