import java.net.Socket;

public class SocketClient {
	public int ID;
	public Socket socket;
	public String password;
	public boolean isBusy = false;
	public SocketClient(int id, String password, Socket s) {
		this.ID = id;
		this.password = password;
		this.socket = s;
	}
}