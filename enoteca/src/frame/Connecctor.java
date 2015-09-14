//package frame;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Properties;
//import java.util.ResourceBundle;
//import java.util.jar.JarException;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//
//import com.mysql.jdbc.Connection;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//import dbconnection.ConnectionManager;
//
//public class Connecctor extends JFrame {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//
//	private JPanel contentPane;
//	private JTextField textAccount;
//	private JTextField textPassword;
//	private JTextField textIndirizzo;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Connecctor frame = new Connecctor();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 * @throws IOException 
//	 * @throws FileNotFoundException 
//	 */
//	public Connecctor() throws FileNotFoundException, IOException {
//		Properties pr = new Properties();
//		pr.load(new FileInputStream("src/db.properties"));
//	
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 248, 316);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JLabel lblNewLabel = new JLabel("Account");
//		lblNewLabel.setBounds(79, 13, 94, 16);
//		contentPane.add(lblNewLabel);
//		
//		JLabel lblNewLabel_1 = new JLabel("Password");
//		lblNewLabel_1.setBounds(79, 79, 94, 16);
//		contentPane.add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_2 = new JLabel("Indirizzo");
//		lblNewLabel_2.setBounds(79, 149, 94, 16);
//		contentPane.add(lblNewLabel_2);
//		
//		textAccount = new JTextField(pr.getProperty("connection.direct.user"));
//		textAccount.setBounds(49, 42, 124, 22);
//		contentPane.add(textAccount);
//		textAccount.setColumns(10);
//		
//		textPassword = new JTextField(pr.getProperty("connection.direct.password"));
//		textPassword.setBounds(49, 108, 124, 22);
//		contentPane.add(textPassword);
//		textPassword.setColumns(10);
//		String url=pr.getProperty("connection.direct.url");
//		
//		textIndirizzo = new JTextField(pr.getProperty("connection.direct.url"));
//		textIndirizzo.setBounds(30, 174, 169, 22);
//		contentPane.add(textIndirizzo);
//		textIndirizzo.setColumns(10);
//		
//		JButton btnSalva = new JButton("Salva");
//		btnSalva.setBounds(12, 209, 63, 25);
//		contentPane.add(btnSalva);
//		
//		JButton btnReset = new JButton("reset");
//		btnReset.setBounds(87, 209, 61, 25);
//		contentPane.add(btnReset);
//		
//		JButton btnTest = new JButton("test");
//		btnTest.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				ConnectionManager conn=new ConnectionManager();
//				try {
//					Connection con= (Connection) conn.getConnectionTest(textIndirizzo.getText(),textAccount.getText(),textPassword.getText());
//				} catch ( Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		btnTest.setBounds(160, 209, 58, 25);
//		contentPane.add(btnTest);
//	}
//
//	
//}
