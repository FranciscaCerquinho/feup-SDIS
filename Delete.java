import java.net.UnknownHostException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.List;
import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.Random;
import java.util.concurrent.*;
import java.util.ArrayList;

public class Delete implements Runnable {


    private static ScheduledThreadPoolExecutor exec;
    private File file;
    private Message message = new Message();

    public Delete(File f) {
        exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(50);
        file = f;
    }


    @Override
    public void run() {

        String fileName = file.getName();
        String path = file.getParent().toString();
        String fileIdToEncrypt = path;
        String fileIDencrypted = Utils.sha256(fileIdToEncrypt);
        ArrayList<Chunk> chunks = new ArrayList<>();


            for (Integer i = 0; i < Peer.getStoredFile().size(); i++) {

                if(fileIDencrypted.equals(Peer.getStoredFile().get(i).getFileID())) {
                    System.out.println("entrei");
                    for (Integer j = 0; j < Peer.getStoredFile().get(i).getChunks().size(); j++) {

                        byte[] infoToSend = message.deleted(Peer.getStoredFile().get(i).getChunks().get(j), Peer.getPeerID());
                        int rand = new Random().nextInt(400);

                        exec.schedule(new SendMessageToChannel("mc", infoToSend), rand, TimeUnit.MILLISECONDS);
                    }
                    Peer.deleteStoredFile(i);
                    chunks = Peer.getStoredFile().get(i).getChunks();


                    File index= new File(path);
                    String[] entries = index.list();
                    
                    System.out.println("entries" + entries);
                    for (String s : entries) {
                        File currentFile = new File(index.getPath(), s);
                        currentFile.delete();
                    }
                    file.delete();

                }
            }


     
    }
}