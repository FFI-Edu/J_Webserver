import java.io.*;
import java.util.*;
import java.security.*;
import java.text.SimpleDateFormat;

public class FILE
{
    private File f;

    public FILE(String pathname)
    {
        f=new File(pathname);
    }
    
    public long getSize(){return f.length();}
    public long getDate(){return f.lastModified();}
    public String getName(){return f.getName();}
    public boolean isHidden(){return f.isHidden();} 
    public boolean isVisible(){return !f.isHidden();} 
    public boolean exists(){return f.isFile();}    
    public String getLastModified(){return new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z").format(new Date(this.getDate()));}
    
    public String getMD5(){
        byte[] bytes = this.getBytes();
        String md5s="";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest( bytes );
            md5s = new String(md5,"UTF-8");
        }catch(Exception e){
            LOG.write("Error in FILE:getMD5");
        }
        return md5s;
    }
    public String getBase64MD5(){
        String b64md5="";
        try{
            b64md5 = Base64.encodeArray( this.getMD5().getBytes("UTF-8") );
        }catch(Exception e){
            LOG.write("Error in FILE:getBase64MD5");
        }
        return b64md5;
    }
    
    public byte[] getBytes(){return getBytes(false);};
    public byte[] getBytes(boolean gzip){return getBytes(0, this.getSize()-1, gzip);};
    public byte[] getBytes(long from, long to){return getBytes(from, to, false);};
    public byte[] getBytes(long from, long to, boolean gzip){
        if( this.getSize()==0 )
            return null;
        if( !f.isFile() || f.isDirectory() || from>to || this.getSize()-1<to ){
            LOG.write("File Error! "+this.getName()+";from:"+from+";to:"+to+";", 700);
            return null;
        }
        if((to+1)-from > Integer.MAX_VALUE){
            LOG.write("File section to big! "+this.getName()+";from:"+from+";to:"+to+";", 701);
            return null;
        }
        
        byte[] out=null;
        FileInputStream fis=null;
        
        try{
            fis = new FileInputStream(f);
            fis.skip(from);
            byte[] cbuf=new byte[((int) ((to+1)-from))]; //! MAX OF 2.147.483.647 Bytes!
            int red = fis.read(cbuf);
            out=cbuf;
        }catch ( FileNotFoundException e ){
                System.err.println( "Datei gibt’s nicht!" );
        }catch ( Exception e ){
                LOG.write("Failed to read file section! "+this.getName()+";from:"+from+";to:"+to+";", 703);
        }finally{
            try{fis.close();}catch(Exception e){;}
        }
        
        if( out!=null && gzip )
            out=(new GZIP().compress(out));
        
        try{
            System.out.println("file to byte array! "+this.getName()+"; from:"+from+"; to:"+to+"; expect:"+((to+1)-from)+"; real:"+out.length+";");
        }catch(Exception e){;}
        return out;
    }
}
