import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientControl {
	public Socket clientSocket;
	private DataOutputStream dos;
	private DataInputStream dis;
	public ClientControl(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public void ReceiveScreenAndSendEvent() {
		CreateFrame crame =  new CreateFrame(this.clientSocket, "1366", "768");
		crame.drawGUI();
		crame.GetAndDrawImage();
		crame.SendEvent(this.clientSocket);
		
	}
	public int checkPass(int id, String password) {
		try {
			dos = new DataOutputStream(clientSocket.getOutputStream());
			dis = new DataInputStream(clientSocket.getInputStream());
			dos.writeInt(id);
			dos.writeUTF(password);
			dos.flush();
			//while((dis.readInt())!=1) {}
			while(true) {
				int a = dis.readInt();
				if(a==1) break;
				if(a==2 ) return 2;
				if(a==3 ) return 3;
			}
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
}