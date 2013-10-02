import java.io.*;
import java.net.*;

public class TESTCLIENT {

		private TESTCLIENT[] clients;

		public TESTCLIENT(int numberOfClients, int port) {
                clients = new TESTCLIENT[numberOfClients];
                for(int i=0; i<=numberOfClients; i++) {
                        clients[i] = new TESTCLIENT(numberOfClients, port);
                }
				//todo: dont connect all at the same time
				for(TESTCLIENT tc : clients) {
						tc.connect(port);
				}
		}

		private void connect(int port) {
                Socket socket;
                PrintWriter out;

				//todo: follow 301 
				try{
						socket = new Socket(InetAddress.getLocalHost(), port);
						out = new PrintWriter (new OutputStreamWriter(socket.getOutputStream()));
				}
				catch( Exception e) {
						e.printStackTrace();
				}

}
}
