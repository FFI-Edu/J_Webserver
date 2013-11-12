//from: http://piotrga.wordpress.com/2009/06/08/howto-compress-or-decompress-byte-array-in-java/

import java.io.*;
import java.util.zip.*;
import java.util.*;

public class GZIP{
    public static byte[] compress(byte[] content){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        System.out.printf("Compression ratio %f\n", (1.0f * content.length/byteArrayOutputStream.size()));
        return byteArrayOutputStream.toByteArray();
    }

    
    public static byte[] decompress(byte[] contentBytes){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Vector<Byte> v = new Vector<Byte>();
        boolean err;
        try{
            GZIPInputStream stream = new GZIPInputStream(new ByteArrayInputStream(contentBytes));
            byte[] buffer=new byte[1];
            int noRead;
            
            while ((noRead = stream.read(buffer)) != -1) {
                v.add(buffer[0]);
            }
        } catch(IOException e){
            return null;
        }
        byte[] bout=new byte[v.size()];
        for (int i=0;i<v.size();i++){
            bout[i]=(byte)v.get(i);
        }
        return bout;
    }
}