import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class CreateFrame {
	String width="", height="";
	private JFrame frame = new JFrame();

	//JDesktopPane represents the main container that will contain all connected clients' screens

	private JDesktopPane desktop = new JDesktopPane();
	private Socket cSocket = null;
	private JInternalFrame interFrame = new JInternalFrame("Server Screen", true, true, true);
	private JPanel cPanel = new JPanel();
	public CreateFrame(Socket sk,String width, String height){
		this.cSocket = sk;
		this.width = width;
		this.height = height;
		//start();
	}
	public void drawGUI() {
		frame.add(desktop, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Show thr frame in maximized state
	
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);		//CHECK THIS LINE
		frame.setVisible(true);
		//frame.setPreferredSize(new Dimension(400, 300));
		interFrame.setLayout(new BorderLayout());
		interFrame.getContentPane().add(cPanel, BorderLayout.CENTER);
		interFrame.setSize(100,100);
		desktop.add(interFrame);

		try {
			//Initially show the internal frame maximized
			interFrame.setMaximum(true);
			}catch (PropertyVetoException ex) { 
				ex.printStackTrace();
		}

		//This allows to handle KeyListener events
		cPanel.setFocusable(true);
		interFrame.setVisible(true);
	}

	
	public void GetAndDrawImage(){
		InputStream in = null;
		try {
			in = 	cSocket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ReceiveScreen(in,cPanel);
	}
	
	public void SendEvent(Socket socket) {
		new SendEvent(socket, cPanel, width, height);
	}
}