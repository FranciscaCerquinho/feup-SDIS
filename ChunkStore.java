public class ChunkStore{
 
    private int chunkID;
    private int RepDegree;
    private int content_size;
 
    public ChunkStore(int RepDegree, int chunkID,int content_size) {
 
        this.RepDegree = RepDegree;
        this.chunkID=chunkID;
        this.content_size=content_size;
    }
 
    public int getRepDegree(){
        return RepDegree;
    }
 
    public int getChunkID(){
        return chunkID;
    }
 
    public int getContent_size(){return content_size;}
 
}