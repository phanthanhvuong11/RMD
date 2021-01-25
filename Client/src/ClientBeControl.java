import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class ClientBeControl {
	private Socket clientSocket;
	private DataInputStream dis;
	private DataOutputStream dos;
	public ClientBeControl(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public void SendScreenAndReceiveEvent()  {
		try {
			dis = new DataInputStream(this.clientSocket.getInputStream());
			dos = new DataOutputStream(this.clientSocket.getOutputStream());
			dos.writeInt(1000);
			dos.writeUTF("xxx");
			dos.flush();
			while(dis.readInt() != 1) {}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        Robot robot = null;
		Rectangle rectangle = null;
		
		GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
    	 	
    	Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();	
    	rectangle=new Rectangle(dim);
    	try {
			robot=new Robot(gDev);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	new SendScreen(this.clientSocket,robot,rectangle);
    	new ReceiveEventAndFile(this.clientSocket, robot);
	}
}

