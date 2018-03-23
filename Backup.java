import java.net.UnknownHostException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.List;

public class Backup{

  //  private static String filename;

 //   public Backup(String filename){

       // this.filename=filename;

    
/*
    public void message(String message) throws UnknownHostException, InterruptedException {

        try {

            ArrayList<Chunk> chunks = new ArrayList<Chunk>();
            int chunkID=0;

            while (true) {

                File f = new File(this.filename);

                FileInputStream fis = new FileInputStream(f);

                ByteArrayOutputStream buffer = new ByteArrayOutputStream((int) f.length());

                byte[] block = new byte[64000];

                int readAmount = 0;

                while ((readAmount = fis.read(block)) >= 0) {

                    if (readAmount > 0) {

                        buffer.write(block, 0, readAmount);

                        byte[] fileData = buffer.toByteArray();
                        String test = new String(fileData);

                        //chunks.add(new Chunk());
                    }

                }

                buffer.flush();

                buffer.close();

                fis.close();

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }*/


public static void splitFile(File f) throws IOException {
        int partCounter = 1;

       
        byte[] buffer = new byte[64000];

        String fileName = f.getName();

        //try-with-resources to ensure closing stream
        try (FileInputStream fis = new FileInputStream(f);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fileName, partCounter++);
                File newChunk = new File(f.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newChunk)) {
                    out.write(buffer, 0, bytesAmount);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        splitFile(new File("C:\\Users\\Ventura\\Desktop\\exemplo\\estrada.jpg"));
    }
}



