import java.net.UnknownHostException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.List;



public class Chunk {

    private static String fileID;
    private static int chunkNo;
    private static int chunkReplication;
    private static byte[] content;

    public Chunk(String fileID, int chunkNo, int chunkReplication, byte[] content){

        this.fileID=fileID;
        this.chunkNo=chunkNo;
        this.chunkReplication=chunkReplication;
        this.content=content;
    }

    public static String getFileID(){
        return fileID;
    }

    public static int getChunkNo(){
        return chunkNo;
    }

    public static int getChunkReplication() {
        return chunkReplication;
    }

    public static byte[] getContent() {
        return content;
    }
}
