import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ReceiveScreen extends Thread{
	private JPanel cPanel = null;
	InputStream in = null;
	Image image1 = null;
	public ReceiveScreen(InputStream in,JPanel p){
		this.in = in;
		this.cPanel = p;
		start();
	}

	public void run(){
		try{
			while(true){
				byte[] bytes = new byte[1024*1024];
				int count = 0;
				do{
					count+=in.read(bytes,count,bytes.length-count);
				}while(!(count>4 && bytes[count-2]==(byte)-1 && bytes[count-1]==(byte)-39));
				
				image1 = ImageIO.read(new ByteArrayInputStream(bytes));
//				/System.out.println(image1);
				image1 = image1.getScaledInstance(cPanel.getWidth(),cPanel.getHeight(),Image.SCALE_FAST);

				Graphics graphics = cPanel.getGraphics();
				graphics.drawImage(image1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
				
				
			}

		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}