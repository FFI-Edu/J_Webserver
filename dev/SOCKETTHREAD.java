import java.net.*;
import java.io.*;
import java.lang.*;

//System.out.println ( conv.encode(":/éäöüß", "UTF-8")+"éäöüß" );

public class SOCKETTHREAD implements Runnable
{
    private int arrayPosition;
    private SETTINGS set;
    private Socket socket;
    private SERVER server;
    private long starttime,requesttime,processtime,sendtime;
    

    /**
     * Constructor for objects of class SOCKETTHREAD
     */
    public SOCKETTHREAD(int arrPos, Socket sock, SERVER serv, SETTINGS sett)
    {
        //LOG.write("New Sockettread in pos "+arrPos);
        arrayPosition   = arrPos;
        set             = sett;
        socket          = sock;
        
        new Thread( this ). start();
    }
    
    public void run(){
        starttime = System.currentTimeMillis();
        try{
            in();
        }catch(Exception e){
            e.printStackTrace();
            LOG.write("Error communicating with the client. "+e.toString(),3);
        }finally{
            close(server);
        }
    }
    
    public void in() throws Exception
    {
        int buffersize = socket.getReceiveBufferSize();
        BufferedReader bufferedReader = 
            new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        char[] buffer = new char[buffersize];
        //System.out.println("Block");
        int anzahlZeichen = bufferedReader.read(buffer, 0, buffersize); // blockiert bis Nachricht empfangen
        String request="";
        if(anzahlZeichen>0){
             request = new String(buffer, 0, anzahlZeichen);
        }else{
            close(server);
            LOG.write("There was a request without content.");
            System.out.println("There was a request without content.");
            return;
        }
        
        System.out.println("-------------------------------------------------------------");
        System.out.println("----------------------incoming request-----------------------");
        System.out.println("-------------------------------------------------------------");
        System.out.println(request);
        
        requesttime = System.currentTimeMillis()-starttime;
        
        new Thread( this ).sleep(4000);
        out(request);
    }
    public void out(String request) throws Exception
    {
        OutputStream out = socket.getOutputStream();
        
        REQUESTHANDLER handler=new REQUESTHANDLER(request);
        byte[] output=handler.getOutputBytes();
        processtime = System.currentTimeMillis()-starttime;
        
        System.out.println("-------------------------------------------------------------");
        System.out.println("----------------------outgoing answer------------------------");
        System.out.println("-------------------------------------------------------------");
        String outs="";
        try{
            outs= new String (output);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(outs);
        
        out.write(output);
        
        out.flush();
        out.close();
        
        sendtime = System.currentTimeMillis()-starttime;
        //STATISTIC.giveThreadTimes(starttime,requesttime,processtime,sendtime);
    }
    
    private void close(SERVER serv){
        try{
            socket.close();
            socket=null;
            new Thread( this ).interrupt();
            //LOG.write("if you see this Java is shit");
        }catch(Exception e){;}
        
    }
    
    public boolean isAlive(){
        if(socket==null)
            return false;
        if(System.currentTimeMillis()-starttime > set.getMaxThreadtime())
            return false;
        
        return true;
    }
        
    
    public String toString(){
        return "ThreadOn"+arrayPosition;
    }
}
