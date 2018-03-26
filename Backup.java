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

    public Backup(File f, int RepDegree){
        file=f;
        this.RepDegree=RepDegree;

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
                File newChunk = new File(file.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newChunk)) {
                    out.write(buffer, 0, bytesAmount);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}



