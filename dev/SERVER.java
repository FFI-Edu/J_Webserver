import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.URLEncoder;

public class SERVER
{
    Socket client;
    String nachricht;
    ServerSocket serverSocket;
    URLEncoder conv;
    
    SETTINGS set;
    
    int port = 1234;
    
    public SERVER(SETTINGS s) throws IOException
    {
        //System.out.println ( conv.encode(":/éäöüß", "UTF-8")+"éäöüß" );
        serverSocket = new ServerSocket(port);
        //System.out.println("#Server started on port: "+port+" !");
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    
   private void test() throws IOException {
        while(true){
            System.out.println("awaiting requwest");
            verarbeiten( serverSocket.accept() );
            System.out.println("socket accepted");
            //schreibeNachricht(client, nachricht);
        }
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
