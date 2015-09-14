package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Frame;
import java.awt.Window.Type;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class ConnectioOnline extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectioOnline frame = new ConnectioOnline();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public ConnectioOnline() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setForeground(Color.DARK_GRAY);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account");
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(102, 13, 116, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(102, 71, 116, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Indirizzo");
		lblNewLabel_2.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(102, 136, 116, 16);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(77, 42, 156, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(77, 100, 156, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(77, 165, 156, 22);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Test");
		btnNewButton.setBounds(12, 210, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Connettiti");
		btnNewButton_1.setBounds(121, 210, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Esci");
		btnNewButton_2.setBounds(230, 210, 97, 25);
		contentPane.add(btnNewButton_2);
		
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon(ConnectioOnline.class.getResource("/img/internet.jpg")));
		label.setBounds(12, 13, 315, 326);
		contentPane.add(label);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, lblNewLabel_1, lblNewLabel_2, textField, textField_1, textField_2}));
	}
}
