public class ChunkInfo{

    private int chunkID;
    private int RepDegree;
    private int repDegreeExpected;

    public ChunkInfo(int RepDegree, int chunkID) {

        this.RepDegree = RepDegree;
        this.chunkID=chunkID;
    }

    public int getRepDegree(){
        return RepDegree;
    }

    public int getChunkID(){
        return chunkID;
    }

}