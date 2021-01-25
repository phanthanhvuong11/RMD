import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ReceiveAndSendScreen extends Thread {
	InputStream in = null;
	OutputStream out = null;
	Image image1 = null;

	public ReceiveAndSendScreen(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
		start();
	}

	public void run() {
		BufferedImage bu = null;
		while (true) {

			byte[] bytes = new byte[1024 * 1024];
			int count = 0;
			do {
				try {
					count += in.read(bytes, count, bytes.length - count);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));

			try {
				image1 = ImageIO.read(new ByteArrayInputStream(bytes));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bu = (BufferedImage) image1;
			try {
				ImageIO.write(bu, "jpeg", out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
