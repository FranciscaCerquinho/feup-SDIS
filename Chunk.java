
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

}
