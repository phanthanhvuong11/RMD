import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	
	public Server(int port) {
		System.out.println("Server is started.");
		try {
		     serverSocket = new ServerSocket(port);
		     while(true) {
		    	 Socket clientSocket = serverSocket.accept();
			     if(clientSocket.isConnected()) {
			    	 ClientConnection connectionThread = new ClientConnection(clientSocket);
			          connectionThread.start();
			        }
			      }
			    } catch (Exception e) {
			      e.printStackTrace();
			    }
	}
	
	public static void main(String[] args) {
		new Server(1235);

	}

}
