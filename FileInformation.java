import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileInformation{

    private String file;
    private int RepDegree;
    private String backupServiceID;
    private ArrayList<ChunkInfo> chunksInfo = new ArrayList<ChunkInfo>();

    public FileInformation(String f, int RepDegree, String backupServiceID) {
        this.file = f;
        this.RepDegree = RepDegree;
        this.backupServiceID=backupServiceID;

    }

    public String getFile(){return file;}

    public int getRepDegree(){return RepDegree;}

    public String getBackupServiceID(){return backupServiceID;}

    public ArrayList<ChunkInfo> getChunksInfo() {
        return chunksInfo;
    }

    public void addChunkInfo(ChunkInfo newChunk){
        chunksInfo.add(newChunk);
    }
}