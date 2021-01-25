import java.awt.Robot;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReceiveEventAndFile extends Thread{
		private Socket socket= null;
		private Robot robot = null;
		private DataInputStream dis = null;
		public ReceiveEventAndFile(Socket socket, Robot robot){
			this.socket = socket;
			this.robot = robot;
			start(); 
		}

		public void run(){
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				while(true){
					int command = dis.readInt();
					switch(command){
						case-1:
							robot.mousePress(dis.readInt());
							break;
						case-2:
							robot.mouseRelease(dis.readInt());
							break;
						case-3:
							robot.keyPress(dis.readInt());
							break;
						case-4:
							robot.keyRelease(dis.readInt());
							break;
						case-5:
							robot.mouseMove(dis.readInt(),dis.readInt());
							break;
						case -6:{
							String path = dis.readUTF();
							int bytesRead;
							byte[] buffer = new byte[1024];
							OutputStream output = null;
							try {
								output = new FileOutputStream("E:\\"+path);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							InputStream in = null;
							in = this.socket.getInputStream();
							while (true) {
					        	bytesRead = in.read(buffer);
								output.write(buffer, 0, bytesRead);
								if(bytesRead<1024) break;
							}
							output.close();
							break;
						}
							
					}
		
				}
			}catch(IOException ex){
					ex.printStackTrace();
			}
	}
			
}



