package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.JTextField;

import DAO.CasaDAO;
import DAO.RegioneDAO;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class InserimentoProduttore extends JFrame {

	private JPanel contentPane;
	private JTextField textProduttore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserimentoProduttore frame = new InserimentoProduttore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public InserimentoProduttore() throws Exception {
		metodUtils utile=new metodUtils();
		final JComboBox comboBoxRegione = new JComboBox(utile.getComboRegione());
		setTitle("Nuovo Produttore");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoProduttore.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 213, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProduttore = new JLabel("Produttore:");
		lblProduttore.setBounds(60, 66, 77, 16);
		contentPane.add(lblProduttore);
		
		textProduttore = new JTextField();
		textProduttore.setBounds(12, 87, 172, 22);
		contentPane.add(textProduttore);
		textProduttore.setColumns(10);
		
		Button btnSalva = new Button("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "<html>Inserire "+textProduttore.getText().toString()+" come un nuovo produttore?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					try {
						CasaDAO.inserisciCasa(RegioneDAO.getId(comboBoxRegione.getSelectedItem().toString()), textProduttore.getText());
						tabProdotti.refreshProduttore();
						InserimentoProduttore.this.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalva.setBounds(59, 115, 79, 24);
		contentPane.add(btnSalva);
		
		JLabel lblRegione = new JLabel("Regione:");
		lblRegione.setBounds(69, 0, 56, 16);
		contentPane.add(lblRegione);
	
		comboBoxRegione.setBounds(12, 31, 172, 22);
		contentPane.add(comboBoxRegione);
	}
}
