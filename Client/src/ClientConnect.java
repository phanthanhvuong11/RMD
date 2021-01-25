import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnect {
	private int id;
	private String password;
	private Socket clientSocket = null;
	
    DataOutputStream dos = null;
    DataInputStream dis = null;
	public ClientConnect(int id, String password) {
		this.id = id;
		this.password = password;
	}
	public void ConnectServer() {
        
        try {
        	clientSocket = new Socket("localhost", 1235);
        	dos = new DataOutputStream(clientSocket.getOutputStream());
            dis = new DataInputStream(clientSocket.getInputStream());
            dos.writeInt(this.id);
	       	dos.flush();
	       	dos.writeUTF(this.password);
	       	dos.flush();		
       }
       catch(Exception e) {
           System.out.print("Connection has been Canceled");
           try {
        	   clientSocket.close();
        	   dis.close();
	           dos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          
           e.printStackTrace();

       }
	}
	public Socket GetSocket() {
		return clientSocket;
	}
	public void closeSocket() {
		try {
			dis.close();
	        dos.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}