import java.util.*;
import java.text.*;
import java.io.*;
import java.net.URI;

public class LOG
{
    private static String logname="events.txt";
    private static String crashname="crash.txt";
    
    /*
     * LOGFILE Style:
     *      Date       Time     ErrCode  Loginformation
     *      --------------------|-----|-----------------------------
     *      16.09.2013-01:20:21 |     | Startup
     *      16.09.2013-01:20:22 |     | Logevent
     *      16.09.2013-01:20:26 | 404 | no file at ./asd
     *      16.09.2013-01:20:26 | 001 | Failure at blabla
     *      
     */
    public static void write(String line)
    {
        write(line,0);
    }
    public static void write(String line, int errorcode)
    {
        String err="000";
        if(errorcode>100) err=""+errorcode;
        if(errorcode>9 && errorcode<100) err="0"+errorcode;
        if(errorcode<10 && errorcode!=0) err="00"+errorcode;
        
        if(err.equals("000")){err="   ";}
        
        File log = new File("log"+File.separator+logname);
        
        if(!log.exists()) clearlog();
        
        if(log.canRead() && log.canWrite())
        {
            FileWriter fw=null;
            try {
                fw = new FileWriter(log,true);
                fw.append("\n"+date()+" | "+err+" | "+line);
                System.out.println(line);
            }catch(Exception e){}
            finally {
                if (fw != null) try {fw.close();}catch(Exception e){};
            }
        }
    }
    
    /**
     * clear content or create ./log/events and
     */
    public static void clearlog()
    {
        File log=null;
        log = new File("log"+File.separator+logname);
        
        if(log.exists())
            log.delete();
        
        log = new File("log"+File.separator);
        log.mkdirs();
        log = new File("log"+File.separator+logname);
        try{            
            log.createNewFile();
        }catch(Exception e){
            System.out.println("CRITICAL: Unable to create Log file");
        }
        
        if(log.canRead() && log.canWrite())
        {
            FileWriter fw=null;
            try {
                fw = new FileWriter(log);
                fw.write("Date       Time     ErrCode  Loginformation"+File.separator+
                        "--------------------|-----|-----------------------------");
            }catch(Exception e){}
            finally {
                if (fw != null) try {fw.close();}catch(Exception e){};
            }
        }
    }
    
    /**
     * clear content or create ./log/events and
     */
    public static void crashlog()
    {
        File log=null;
        log = new File("log"+File.separator+crashname);
        
        if(log.exists())
            log.delete();
        
        log = new File("log"+File.separator);
        log.mkdirs();
        log = new File("log"+File.separator+crashname);
        try{            
            log.createNewFile();
        }catch(Exception e){
            System.out.println("CRITICAL: Unable to create Log file");
        }
        
        if(log.canRead() && log.canWrite())
        {
            FileWriter fw=null;
            try {
                fw = new FileWriter(log);
                fw.write("Date       Time     ErrCode  Loginformation"+File.separator+
                        "--------------------|-----|-----------------------------");
            }catch(Exception e){}
            finally {
                if (fw != null) try {fw.close();}catch(Exception e){};
            }
        }
    }
    
    private static String date(){
        String date="";
        
        Date dt = new Date();
        SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy-HH:mm:ss" );
        df.setTimeZone( TimeZone.getDefault() );
        date = df.format( dt );
        
        return date;        
    }
}
