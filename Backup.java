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
        private int RepDegree;
       // private FileInformation fileInformation;
        private Peer peer;
        private Message message = new Message();
        private String fileIDencrypted;

    public Backup(File f, int RepDegree, Peer peer){
      //  fileIDencrypted = Utils.sha256(fileInformation.getFile());
        file=f;
        this.RepDegree=RepDegree;
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

           //     Chunk newChunk =  new Chunk(fileIDencrypted, partCounter,);
                ChunkInfo chunkInfo= new ChunkInfo(RepDegree, partCounter);
              //  fileInformation.addChunkInfo(chunkInfo);

                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                    peer.message("backing up");
                  //  byte[] message = Message.sendPutChunk(newFile, Peer.getPeerID());

                }catch(IOException ex){
                    ex.printStackTrace();
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}



