import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;


public class MessageTreatment implements Runnable{

	private byte[] messageToBeTreated;
	private Peer peer;

	private int partCounter = 1;

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
        byte[] body = Arrays.copyOfRange(this.messageToBeTreated, i+4, this.messageToBeTreated.length);			
       
					this.partCounter += 1;
			//	byte[] content= messageTreated[0].getBytes();
				try{

				FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Ventura\\Desktop\\backup",splitAnswer[3]+splitAnswer[4]));
				  	fos.write(body);
  					fos.close();

  				}catch(Exception ex){
  					ex.printStackTrace();
  				}

			//	}	
			}










}