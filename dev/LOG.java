import java.util.*;
import java.text.*;
import java.io.*;
import java.net.URI;

public class LOG
{
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

    /**
     * Time + ' | ' +FAILSYMBOL+ ' | ' +LINE
     */
    public static void write(String line)
    {
        write(line,0);
    }
    public static void write(String line, int errorcode)
    {
        ;
    }
    
    /**
     * clear content or create ./log/events and
     */
    public static void clearlog()
    {
        File log=null;
        log = new File("log"+File.separator+"events.txt");
        
        if(log.exists())
            log.delete();
        
        log = new File("log"+File.separator+"events.txt");
        try{
            log.mkdir();
            log.createNewFile();
        }catch(Exception e){
            System.out.println("CRITICAL: Unable to create Log file");
        }
            if(log.canRead() && log.canWrite())
        {
            
            
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
