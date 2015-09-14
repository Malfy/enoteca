package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import DAO.LuvaglioDAO;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsVigneto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsVigneto frame = new InsVigneto();
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
	public InsVigneto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InsVigneto.class.getResource("/img/download.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 186, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Nome vigneto");
		label.setBounds(29, 13, 83, 16);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(12, 42, 116, 22);
		contentPane.add(textField);
		
		JButton button = new JButton("Salva");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(JOptionPane.showConfirmDialog(textField,  "Salvataggio Vigneto "+textField.getText(), "Salvataggio Vigneto", 0)==0)
					{
						if(LuvaglioDAO.inserisciVigneto(textField.getText())){
							tabProdotti.refreshVigneto();
							InsVigneto.this.dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Vigneto gia esistente");
						}
					else{
						InsVigneto.this.setVisible(false);
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(22, 77, 97, 25);
		contentPane.add(button);
	}
}
