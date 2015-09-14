package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NuovoUtenteDb extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuovoUtenteDb frame = new NuovoUtenteDb();
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
	public NuovoUtenteDb() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 206, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(66, 9, 56, 16);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(58, 65, 72, 16);
		contentPane.add(lblPassword);
		
		textUser = new JTextField();
		textUser.setBounds(36, 34, 116, 22);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		textPassword = new JTextField("root");
		textPassword.setBounds(36, 90, 116, 22);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnCreaUtente = new JButton("Crea Utente");
		btnCreaUtente.setBounds(37, 121, 113, 25);
		contentPane.add(btnCreaUtente);
	}
}
