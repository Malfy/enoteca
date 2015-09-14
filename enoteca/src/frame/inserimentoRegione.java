package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.TextField;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import DAO.RegioneDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Closeable;
import java.util.jar.JarException;

public class inserimentoRegione extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRegione;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inserimentoRegione frame = new inserimentoRegione();
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
	public inserimentoRegione() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 225, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome regione");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(59, 11, 88, 29);
		contentPane.add(lblNewLabel);
		
		textFieldRegione = new JTextField();
		textFieldRegione.setBounds(39, 51, 129, 20);
		contentPane.add(textFieldRegione);
		textFieldRegione.setColumns(10);
		
		JButton btnSalva = new JButton("salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(JOptionPane.showConfirmDialog(textFieldRegione,"salvare questa regione: "+textFieldRegione.getText(), "salvataggio regione", 0)==0){
						
						if(RegioneDAO.inserisciRegione(textFieldRegione.getText())){
							inserimentoCasa.refresh();
							inserimentoRegione.this.setVisible(false);							
						}
						else{
							JOptionPane.showInputDialog(textFieldRegione.getText(), textFieldRegione.getText()+" questa regione è gia inserita");
						}
					}
					else{
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inserimentoRegione.this.setVisible(false);
				
			}
		});
		btnSalva.setBounds(59, 82, 89, 23);
		contentPane.add(btnSalva);
	}
}
