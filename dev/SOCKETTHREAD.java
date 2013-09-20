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
    private long starttime;
    

    /**
     * Constructor for objects of class SOCKETTHREAD
     */
    public SOCKETTHREAD(int arrPos, Socket sock, SERVER serv, SETTINGS sett)
    {
        //LOG.write("New Sockettread in pos "+arrPos);
        arrayPosition   = arrPos;
        set             = sett;
        socket          = sock;
        
        new Thread( this ).start();
    }
    
    public void run(){
        starttime = System.currentTimeMillis();
        try{
            in();
        }catch(Exception e){
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
        int anzahlZeichen = bufferedReader.read(buffer, 0, buffersize); // blockiert bis Nachricht empfangen
        String request = new String(buffer, 0, anzahlZeichen);
        
        System.out.println(request);
        
        out(request);
    }
    public void out(String request) throws Exception
    {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        printWriter.println("Hello,l1");
        new Thread( this ).sleep(1000);
        printWriter.println("Bye,l2");
        
        printWriter.flush();
        
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
