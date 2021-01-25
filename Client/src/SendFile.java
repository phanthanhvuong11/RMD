import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendFile extends Thread {
	private Socket clientSocket;
	private String filePath;
	private String fileName;
	private PrintWriter writer = null;
	public SendFile(Socket socket, String path, String fileName) {
		this.clientSocket = socket;
		this.filePath = path;
		this.fileName = fileName;
		try {
			writer = new PrintWriter(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start();
	}
	
	public void run() {
		writer.println(-6);
		writer.println(this.fileName);
		writer.flush();
		File myFile = new File(this.filePath);
        byte[] mybytearray = new byte[(int) myFile.length()];
         
        FileInputStream fis = null;
		try {
			fis = new FileInputStream(myFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedInputStream bis = new BufferedInputStream(fis);
        try {
			bis.read(mybytearray, 0, mybytearray.length);
			OutputStream os = this.clientSocket.getOutputStream();
	         
	        os.write(mybytearray, 0, mybytearray.length);
	        os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
}