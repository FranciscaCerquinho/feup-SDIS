
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


public class Delete implements Runnable{

    private File file;
    private static ExecutorService exec;

    public Delete(File file){


       exec = Executors.newFixedThreadPool(100);

        this.file = file;


    }




    @Override
    public void run(){

        String path = file.getParent().toString();

        String fileIdToEncrypt = path+file.lastModified();
        
        String fileIDencrypted = Utils.sha256(fileIdToEncrypt);
            Message message = new Message();
            byte[] infoToSend = message.deleted(fileIDencrypted, Peer.getPeerID());

            

        exec.execute(new SendMessageToChannel("mc",infoToSend));







    }




















}