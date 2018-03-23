
public class Chunk{

    private static String fileID;
    private static int chunkNo;
    private static int chunkReplication;
    private static String content;

    public Chunk(String fileID, int chunkNo, int chunkReplication, String content){

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

    public static String getContent() {
        return content;
    }
}
