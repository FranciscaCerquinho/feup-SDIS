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


public class Restore{

	public static void mergeFiles(List<File> files, File into)
        throws IOException {
    try (FileOutputStream fos = new FileOutputStream(into);
         BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
        for (File f : files) {
            Files.copy(f.toPath(), mergingStream);
        }
    }
}










}