import java.net.UnknownHostException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

public class Backup{

    private static String filename;

    public Backup(String filename){

        this.filename=filename;

    }

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


    }
}
