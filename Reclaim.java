import java.util.ArrayList;
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




public class Reclaim implements Runnable{

   private int space;
   private static ScheduledThreadPoolExecutor exec;
    public Reclaim(int space){
        this.space = space;
        exec = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(50);
    }

    
    @Override
    public void run(){

        Peer.setSpaceValue(space);
        int accumulatedSpace=0;
        int i = 0;
        int a = 0;

        for(; a < Peer.getStoredFile().size(); a++){

             System.out.println("i enter and the files are " + Peer.getStoredFile().size());

            for(; i < Peer.getStoredFile().get(a).getChunks().size(); i++){
                 System.out.println("i enter and the chunks are " + Peer.getStoredFile().get(a).getChunks().size());
                if(accumulatedSpace < space && (accumulatedSpace + Peer.getStoredFile().get(a).getChunks().get(i).getContent().length) < space ){


                    accumulatedSpace = accumulatedSpace + Peer.getStoredFile().get(a).getChunks().get(i).getContent().length;

                    System.out.println("Accumulated space: "+ accumulatedSpace);





                }else{
                   
                Message message = new Message();

                System.out.println("lets send a message");

                byte[] infoToSend = message.removed(Peer.getStoredFile().get(a).getChunks().get(i), Peer.getPeerID());

                  Peer.getStoredFile().get(a).getChunks().remove(i);
                  i = i-1;


                exec.schedule(new SendMessageToChannel("mc",infoToSend),0,TimeUnit.MILLISECONDS);
                }



            }



        }

    












    }















}