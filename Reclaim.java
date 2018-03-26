public class Reclaim{

    private int maxDiskSpace;
    private int diskSpace;

    public Reclaim(int maxDiskSpace){
        this.maxDiskSpace=maxDiskSpace;
        this.diskSpace=0;
    }

    public int getMaxDiskSpace(){
        return maxDiskSpace;
    }

    public int getDiskSpace(){
        return diskSpace;
    }
}