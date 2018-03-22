import java.net.UnknownHostException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;

public class Backup{

    private static String filename;

    public Backup(String filename){

        this.filename=filename;

    }

    public void message(String message) throws UnknownHostException, InterruptedException {

        try {
            while (true) {

                File f = new File(this.filename);

                //the input.dat is the file to load into memory.

                FileInputStream fis = new FileInputStream(f);

                ByteArrayOutputStream buffer = new ByteArrayOutputStream((int) f.length());

                byte[] block = new byte[64000];

                int readAmount = 0;

                while ((readAmount = fis.read(block)) >= 0) {

                    if (readAmount > 0) {

                        buffer.write(block, 0, readAmount);

                        byte[] fileData = buffer.toByteArray();
                        String test = new String(fileData);
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
