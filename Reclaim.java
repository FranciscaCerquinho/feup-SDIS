import java.util.ArrayList;

public class Reclaim implements Runnable{

    private int maxDiskSpace;
    private int diskSpace;
    private ArrayList<FileInformation> fileInformation;

    public Reclaim(int maxDiskSpace, ArrayList<FileInformation> fileInformation){
        this.maxDiskSpace=maxDiskSpace;
        this.diskSpace=0;
        this.fileInformation=fileInformation;
    }

    public int getMaxDiskSpace(){
        return maxDiskSpace;
    }

    public int getDiskSpace(){
        return diskSpace;
    }

    public void updateDiskSpace(int space){
        diskSpace+=space;
    }

    @Override
    public void run(){
/*
        for(int i=0; i < fileInformation.length;i++){

        }

        */

    }
}