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

    private  String fileID;
    private  int chunkNo;
    private  int chunkReplication;
    private  byte[] content;

    public Chunk(String fileID, int chunkNo, int chunkReplication, byte[] content){

        this.fileID=fileID;
        this.chunkNo=chunkNo;
        this.chunkReplication=chunkReplication;
        this.content=content;
    }

    public  String getFileID(){
        return fileID;
    }

    public  int getChunkNo(){
        return chunkNo;
    }

    public  int getChunkReplication() {
        return chunkReplication;
    }

    public byte[] getContent() {
        return content;
    }
}
