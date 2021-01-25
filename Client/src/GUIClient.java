import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class GUIClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtMyID;
	private JTextField txtMyPassword;
	private JTextField txtFriendId;
	private JTextField txtFriendPassword;
	private ClientConnect cCon;
	ClientControl clientCon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIClient frame = new GUIClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 551);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMyID = new JTextField();
		txtMyID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMyID.setBounds(173, 93, 196, 46);
		contentPane.add(txtMyID);
		txtMyID.setColumns(10);
		
		txtMyPassword = new JTextField();
		txtMyPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMyPassword.setBounds(173, 172, 196, 46);
		contentPane.add(txtMyPassword);
		txtMyPassword.setColumns(10);
		
		txtFriendId = new JTextField();
		txtFriendId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFriendId.setBounds(584, 93, 200, 46);
		contentPane.add(txtFriendId);
		txtFriendId.setColumns(10);
		
		txtFriendPassword = new JTextField();
		txtFriendPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFriendPassword.setBounds(584, 172, 200, 46);
		contentPane.add(txtFriendPassword);
		txtFriendPassword.setColumns(10);
		//Connect server
		JButton btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConnect.setBackground(new Color(30, 144, 255));
		btnConnect.setBounds(12, 337, 139, 41);
		contentPane.add(btnConnect);
		
		JButton btnCancel = new JButton("Reset");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtMyID.setText("");
				txtMyPassword.setText("");
			}
		});
		btnCancel.setBackground(new Color(30, 144, 255));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(201, 337, 132, 41);
		contentPane.add(btnCancel);
		//Connect Server to control friend computer
		JButton btnFrConnect = new JButton("Connect");
		btnFrConnect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFrConnect.setBackground(new Color(30, 144, 255));
		btnFrConnect.setBounds(468, 338, 139, 41);
		contentPane.add(btnFrConnect);
		
		JButton btnFrCancel = new JButton("Reset");
		btnFrCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFriendId.setText("");
				txtFriendPassword.setText("");
			}
		});
		btnFrCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFrCancel.setBackground(new Color(30, 144, 255));
		btnFrCancel.setBounds(671, 338, 113, 40);
		contentPane.add(btnFrCancel);
		
		JLabel lbMyID = new JLabel("My ID");
		lbMyID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbMyID.setBackground(Color.BLUE);
		lbMyID.setBounds(12, 92, 78, 46);
		contentPane.add(lbMyID);
		
		JLabel lbMyPassword = new JLabel("My Password");
		lbMyPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbMyPassword.setBounds(12, 178, 139, 32);
		contentPane.add(lbMyPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Friend ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(430, 93, 132, 42);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fr Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(430, 172, 142, 46);
		contentPane.add(lblNewLabel_3);
		
//		JButton btnChooseFile = new JButton("Choose File");
//		btnChooseFile.setBackground(new Color(30, 144, 255));
//		btnChooseFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		btnChooseFile.setEnabled(false);
//		btnChooseFile.setBounds(105, 406, 157, 46);
//		contentPane.add(btnChooseFile);
		
		
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosed(WindowEvent e) {
		        JOptionPane.showConfirmDialog(null, "xxxx",
		                null, JOptionPane.YES_NO_OPTION);
		    }
		    public void windowClosing(WindowEvent e) {
		        int hoi = JOptionPane.showConfirmDialog(null, "Do you want to close this app? ",
		                null, JOptionPane.YES_NO_OPTION);
		        if (hoi == JOptionPane.YES_OPTION) {     
		            System.exit(0);
		        }
		    }
		});
		
		//Add event for button
		//btFrnConnect
		btnFrConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtMyID.getText().trim().equals(txtFriendId.getText().trim())) {
					JOptionPane.showMessageDialog(null, "Don't control myself");
					return;
				}
				if(!txtMyID.getText().trim().equals("") && !txtMyPassword.getText().trim().equals("")
						&& !txtFriendId.getText().trim().equals("") && !txtFriendPassword.getText().trim().equals("")){
					cCon = new ClientConnect(Integer.parseInt(txtMyID.getText()),txtMyPassword.getText());
					cCon.ConnectServer();
					clientCon = new ClientControl(cCon.GetSocket());
					int x = clientCon.checkPass(Integer.parseInt(txtFriendId.getText()), txtFriendPassword.getText());
					if( x== 1) {
						clientCon.ReceiveScreenAndSendEvent();	
						//btnChooseFile.setEnabled(true);
						btnFrConnect.setEnabled(false);
						btnConnect.setEnabled(false);
					}
					else if(x == 2) {
						
						JOptionPane.showMessageDialog(null, "Incorrect password or id");
					}
						else if(x == 3) {
						
							JOptionPane.showMessageDialog(null, "da cos thiet bij ket noi toi" + txtFriendId.getText());
						}	
						
				}
				else JOptionPane.showMessageDialog(null, "Please FullFill a Textbox");
			}
		});
		
		//btnConnect
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtMyID.getText().trim().equals("") && !txtMyPassword.getText().trim().equals("")) {
					btnFrConnect.setEnabled(false);
					btnConnect.setEnabled(false);
					cCon = new ClientConnect(Integer.parseInt(txtMyID.getText()),txtMyPassword.getText());
					cCon.ConnectServer();
					new ClientBeControl(cCon.GetSocket()).SendScreenAndReceiveEvent();			
				}
				else JOptionPane.showMessageDialog(null, "Please FullFill a Textbox");
			}
		});
		
//		btnChooseFile.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				JFileChooser fc = new JFileChooser();
//				fc.setCurrentDirectory(new java.io.File("D:\\"));
//				fc.setDialogTitle("Choose File");
//				//fc.setFileFilter(new FileNameExtensionFilter("txt file","txt"));
//				fc.showOpenDialog(btnChooseFile);
//				String fileName = fc.getSelectedFile().getName();
//				String path = fc.getSelectedFile().getAbsolutePath();
//				new SendFile(clientCon.clientSocket, path, fileName);
//			}
//		});
	}
}