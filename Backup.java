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






import java.util.Random;
import java.util.concurrent.*;



public class Backup implements Runnable{


        private static ScheduledThreadPoolExecutor exec;
        private File file;
        private int repDegree;
       // private FileInformation fileInformation;
        private Message message = new Message();

    public Backup(File f, int repDegree){
       exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10000);
        file=f;
        this.repDegree=repDegree;
      

    }

    public static ScheduledThreadPoolExecutor getExecs(){
        return exec;
    }
 

@Override
public void run(){
        int partCounter = 0;

       int putChunkCounter = 0;
        byte[] buffer = new byte[64000];

        String fileName = file.getName();

      
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
               
                String filePartName = String.format("%s.%03d", fileName, partCounter++);
                File newFile = new File(file.getParent(), filePartName);
                String path = file.getParent().toString();
                String fileIdToEncrypt = path + file.lastModified();
                String fileIDencrypted = Utils.sha256(fileIdToEncrypt);
            
                byte[] resizedBuffer = Arrays.copyOfRange(buffer,0,bytesAmount);

                Chunk newChunk =  new Chunk(fileIDencrypted, partCounter, repDegree, resizedBuffer);
                Message message = new Message();

               
                byte[] infoToSend = message.sendPutChunk(newChunk, Peer.getPeerID());
               
                    int rand = new Random().nextInt(401);   

                exec.schedule(new SendMessageToChannel("mdb",infoToSend),0,TimeUnit.MILLISECONDS);
                exec.schedule(new Retry(infoToSend, newChunk),1,TimeUnit.MILLISECONDS);
               
                  try{
                        Thread.sleep(400);
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                  
            
            }
        }catch(IOException ex){

               
            ex.printStackTrace();
        }
    }

}



