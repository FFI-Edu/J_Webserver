import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.URLEncoder;
import java.lang.*;

public class SERVER
{
    ServerSocket serverSocket;
    SETTINGS set;
    
    Thread [] activeThreads;
    
    public SERVER(SETTINGS s) throws IOException
    {
        set=s;
        activeThreads = new Thread [ set.getMaxThreads() ] ;
        for(int i=0;i<set.getMaxThreads();i++){
            activeThreads[i]=null;
        }
        serverSocket = new ServerSocket( set.getPort() );
        try {
            startAccept();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    private void startAccept() throws IOException {
        LOG.write("Ready to accept on "+set.getPort());
        while(true){
            try {
                int firstEmpty=firstEmptySlot();
                if(firstEmpty>=0){
                    activeThreads [ firstEmpty ] = new Thread( new SOCKETTHREAD(firstEmpty, serverSocket.accept() , this, set) ) ;
                    
                }else{
                    LOG.write("Max Socketthreads reched! No more space in Array! Closing socket.",2);
                    serverSocket.accept().close();
                }
            } catch (IOException e) {
                LOG.write("Err while accepting a socket.",1);
                e.printStackTrace();
            }
        }
    }
    
    private int firstEmptySlot(){
        for(int i=0; i<activeThreads.length ; i++){
            if( activeThreads[i] == null || !activeThreads[i].isAlive()  ){
                return i;
            }
        }
        return -1;
    }
    
    public void threadFinished( int position){
        
        try{
            activeThreads [ position ].interrupt();
        }catch(Exception e){;}
        activeThreads [ position ] = null;
        System.gc ();
        LOG.write("Arrayposition "+position+" finished job");
    }
    
}
