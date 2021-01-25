import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Hashtable;

public class ClientConnection extends Thread {
	private Socket clientSocket;
    private DataInputStream dis;
    BufferedImage image;
    private static Hashtable<Integer, SocketClient> connectionList 
    = new Hashtable<Integer, SocketClient>();
	 public ClientConnection(Socket clientSocket) {
		this.clientSocket = clientSocket;
		//System.out.println(connectionList.size());
	 }

	 public void run() {
		 try {
		        dis = new DataInputStream(clientSocket.getInputStream());
		        int id = dis.readInt();
		        String password = dis.readUTF();
		        connectionList.put(id, new SocketClient(id, password, this.clientSocket));
		        Socket sCon;
		        while(connectionList.size()<2) {}
		        while(true) {
		        	int idcon=0;
		        	String passwordCon="";
		        	if(dis!=null) {
		        		idcon = dis.readInt();
			        	passwordCon = dis.readUTF();
		        	}
		        	
		        	if(idcon == 1000) return;
		        	if((sCon = getSocketByIDAndPassword(idcon, passwordCon)) != null) {
		        		new DataOutputStream(sCon.getOutputStream()).writeInt((1));
		        		new DataOutputStream(clientSocket.getOutputStream()).writeInt((1));
		        		setBusyClientById(id);
		        		setBusyClientById(idcon);
		        		break;
		        	}
		        	if(getBusyClientById(idcon)) {
		        		new DataOutputStream(clientSocket.getOutputStream()).writeInt((3));
		        	}
		        	if(passwordCon !="") {
		        		new DataOutputStream(clientSocket.getOutputStream()).writeInt((2));
		        		passwordCon="";
		        	}
		        	
		   
		        	
		        }
	        	new ReceiveAndSendScreen(sCon.getInputStream(),clientSocket.getOutputStream());
	        	new ReceiveAndSendEventFile(clientSocket.getInputStream(),sCon.getOutputStream());
		       
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
	 }
	  
	 public Socket getSocketByIDAndPassword(int id, String password) {
		 for (Integer key : connectionList.keySet()) {
			 SocketClient value = connectionList.get(key);
			 if(value.ID == id && value.password.trim().equals(password.trim()) && !value.isBusy) {
				 return value.socket;
			 }
		 }
		 return null;
     }
	 
	 public void setBusyClientById(int id) {
		 for (Integer key : connectionList.keySet()) {
			 SocketClient value = connectionList.get(key);
			 if(value.ID == id) {
				 value.isBusy = true;
				 break;
			 }
		 }
	 }
	 public boolean getBusyClientById(int id) {
		 for (Integer key : connectionList.keySet()) {
			 SocketClient value = connectionList.get(key);
			 if(value.ID == id && value.isBusy) {
				 return true;
			 }
		 }
		 return false;
	 }
	
}
