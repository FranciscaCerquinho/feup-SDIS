
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.nio.file.StandardOpenOption;



public class RebuildFile implements Runnable{

		private String[] splitAnswer;
		private byte[] body;

	public RebuildFile(String[] splitAnswer, byte[] body){

		this.splitAnswer = splitAnswer;
		this.body=body;
	}





	@Override
	public void run(){

		boolean advance = false;

		

		int  counter=0;

		for(int i=0; i < Peer.getStoredFile().size(); i++){

			if(Peer.getStoredFile().get(i).getFileID().equals(splitAnswer[3])){

				advance = true;
				break;

			}

		}



		if(!advance){
			Chunk chunkToRestore = new Chunk(splitAnswer[3],Integer.parseInt(splitAnswer[4]),1,body);
			System.out.println("body length " + body.length);
			if(chunkToRestore.getContent().length < 64000){
			
				Peer.setMaxChunks(chunkToRestore.getChunkNo());
			}
			boolean exists = false;
			for(int a=0; a < Peer.getRestored().size(); a++){
				if(Peer.getRestored().get(a).getChunkNo() == Integer.parseInt(splitAnswer[4])){
					exists=true;

				}
			}

			if(!exists){


				Peer.getRestored().add(chunkToRestore);
				Peer.getRestoredNumber().add(chunkToRestore.getChunkNo());
			}

			System.out.println(Peer.getMaxChunks());
			System.out.println(Peer.getRestored().size());
			if(Peer.getRestored().size() == Peer.getMaxChunks()){
				System.out.println("works");
				File dir = new File(System.getProperty("user.dir"),"restore");
				dir.mkdir();
				File toWrite = new File(dir.getAbsolutePath()+"\\"+ splitAnswer[3] );
				System.out.println(toWrite.getAbsolutePath());
				Integer[] toSort = new Integer[Peer.getMaxChunks()];
				Peer.getRestoredNumber().toArray(toSort);
				 Arrays.sort(toSort);
				 System.out.println("begore sort:  " +   toSort.length);
				 for(int a=0; a < toSort.length; a++ ){
				 	System.out.println(toSort[a]);
				 }

				 for(int k=0; k < toSort.length; k++){
				 	for(int j=0; j < Peer.getRestored().size(); j++){
				 		if(Peer.getRestored().get(j).getChunkNo() == toSort[k] ){

							try{
								FileOutputStream fos = new FileOutputStream(toWrite, true);
								fos.write(Peer.getRestored().get(j).getContent());
								fos.close();
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

















