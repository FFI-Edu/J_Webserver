import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.URLEncoder;
import java.lang.*;

public class SERVER
{
    Socket client;
    String nachricht;
    ServerSocket serverSocket;
    URLEncoder conv;
    
    SETTINGS set;
    
    Thread [] activeThreads;
    
    int port = 1234;
    
    public SERVER(SETTINGS s) throws IOException
    {
        activeThreads = new Thread [ set.getMaxThreads() ] ;
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
                accept ( serverSocket.accept() );
            } catch (IOException e) {
                LOG.write("Err while accepting a socket.",1);
                e.printStackTrace();
            }
        }
    }
    
    private void accept(Socket socket) throws IOException {
        activeThreads [ firstEmptySlot() ] = new Thread( new SOCKETTHREAD(firstEmptySlot(), socket, this, set) );
    }
    
    private int firstEmptySlot(){
        return 0;
    }
    
    private String verarbeiten(Socket socket) throws IOException {
        int buffersize = socket.getReceiveBufferSize();
        //System.out.println("Buffersize: "+buffersize+"; ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        /*char[] buffer = new char[buffersize];
        int anzahlZeichen = bufferedReader.read(buffer, 0, buffersize);
        String nachricht = new String(buffer, 0, anzahlZeichen);*/
        String nachricht="";
        String inputLine;
        int dg=0;        
        while ((inputLine = bufferedReader.readLine()) != null){
            //System.out.println(dg+"");
            nachricht += (inputLine+"\n");
            //System.out.println(dg+"1");
            dg++;
            if(dg==15){break;}
        }   
        System.out.println("out");        
        schreibeNachricht(socket);
        bufferedReader.close();
        socket.close();        
        System.out.println(nachricht);
        return nachricht;
    }
    
    private void schreibeNachricht(Socket socket) throws IOException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        /*try {
           BufferedReader in = new BufferedReader(new FileReader("test.html"));
           String zeile = null;
           int n = 0;
          while ((zeile = in.readLine()) != null) {
            //System.out.println("Gelesene Zeile: " + zeile);
            printWriter.println(zeile);
          }
        } catch (IOException e) {
             e.printStackTrace();
        }*/
          
        printWriter.println("Hello,l1");
        printWriter.println("Bye,l2");
        printWriter.flush();
        
        
       
        //System.out.println("#Client abgeshclossen ");
        
        
    }
    
}
