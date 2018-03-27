import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;


public class MessageTreatment implements Runnable{

	private String messageToBeTreated;
	private Peer peer;
	private int partCounter = 1;

	public MessageTreatment(String messageToBeTreated){
		this.messageToBeTreated = messageToBeTreated;

	}




	@Override
	public void run(){
			String[] messageTreated = messageToBeTreated.split("\\r\\n");
			//System.out.println(messageTreated.length);
			//String[] header =  messageTreated[0].split(" ");
				
			//	if(messageTreated[0].equals("PUTCHUNK")){
										

					this.partCounter += 1;
				byte[] content= messageTreated[0].getBytes();
				try{

				FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Ventura\\Desktop\\backup",Integer.toString(partCounter)));
				  	fos.write(content);
  					fos.close();

  				}catch(Exception ex){
  					ex.printStackTrace();
  				}

			//	}	
			}










}