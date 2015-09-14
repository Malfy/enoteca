package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

import frameOnline.RicercaProdotto;

import model.Connessione;

import DAOOnline.TestConnessioneOnline;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnessioneOnline extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Connessione utente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnessioneOnline frame = new ConnessioneOnline();
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
	public ConnessioneOnline() {
		utente=new Connessione();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Account");
		label.setForeground(Color.RED);
		label.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		label.setBounds(102, 13, 116, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		label_1.setBounds(102, 71, 116, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Indirizzo");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		label_2.setBounds(102, 136, 116, 16);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(77, 42, 156, 22);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(77, 100, 156, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(77, 165, 156, 22);
		contentPane.add(textField_2);
		
		JButton button = new JButton("Test");
	
		button.setBounds(12, 210, 97, 25);
		contentPane.add(button);
		
		final JButton button_1 = new JButton("Connettiti");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RicercaProdotto ricerca;
				try {
					ricerca = new RicercaProdotto(utente);
					ricerca.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(121, 210, 97, 25);
		button_1.setEnabled(false);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Esci");
		button_2.setBounds(230, 210, 97, 25);
		contentPane.add(button_2);
		
		final JLabel lblConnessioneTest = new JLabel("");
		lblConnessioneTest.setBounds(12, 319, 56, 16);
		contentPane.add(lblConnessioneTest);
		
		JLabel label_3 = new JLabel(" ");
		label_3.setIcon(new ImageIcon(ConnectioOnline.class.getResource("/img/internet.ico")));
		label_3.setBounds(12, 13, 315, 326);
		contentPane.add(label_3);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				utente.setLink("jdbc:mysql://"+textField_2.getText()+"/enoteca");
				utente.setNome(textField.getText());
				utente.setPassword(textField_1.getText());
				try {
					if(!TestConnessioneOnline.test(utente)){
						button_1.setEnabled(true);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
	}
}
