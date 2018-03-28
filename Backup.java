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


public class Backup implements Runnable{

        private File file;
        private int repDegree;
       // private FileInformation fileInformation;
        private Peer peer;
        private Message message = new Message();

    public Backup(File f, int RepDegree, Peer peer){
        file=f;
        this.repDegree=repDegree;
      //  this.fileInformation=fileInformation;
        this.peer = peer;

    }

 

@Override
public void run(){
        int partCounter = 1;

       
        byte[] buffer = new byte[64000];

        String fileName = file.getName();

      
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
               
                String filePartName = String.format("%s.%03d", fileName, partCounter++);
                File newFile = new File(file.getParent(), filePartName);
                String path = file.getParent().toString();
                String fileIdToEncrypt = path;
                String fileIDencrypted = Utils.sha256(fileIdToEncrypt);
                Chunk newChunk =  new Chunk(fileIDencrypted, partCounter, repDegree, buffer);
                Message message = new Message();

                byte[] infoToSend = message.sendPutChunk(newChunk, peer.getPeerID());

                


                ChunkInfo chunkInfo= new ChunkInfo(repDegree, partCounter);
              //  fileInformation.addChunkInfo(chunkInfo);

               
                   peer.message(infoToSend);
                  
            
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }

}



