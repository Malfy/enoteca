package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DAO.FornitoreHasProdottoDAO;

import model.prodottoHasFornitore;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inserisicPrezzo extends JFrame {

	private JPanel contentPane;
	private JTextField textPrezzo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prodottoHasFornitore a=new prodottoHasFornitore();
					inserisicPrezzo frame = new inserisicPrezzo(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static void prezzo(prodottoHasFornitore relazione) {
				try {
				
					inserisicPrezzo frame = new inserisicPrezzo(relazione);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
	}
	/**
	 * Create the frame.
	 */
	public inserisicPrezzo(final prodottoHasFornitore relazione) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 371, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInserirePrezzoProdotto = new JLabel("Inserire prezzo prodotto relativo al fornitore selezionato");
		lblInserirePrezzoProdotto.setBounds(5, 5, 336, 47);
		contentPane.add(lblInserirePrezzoProdotto);
		
		textPrezzo = new JTextField();
		textPrezzo.setBounds(118, 65, 116, 22);
		contentPane.add(textPrezzo);
		textPrezzo.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textPrezzo.getText().equals(""))
					relazione.setPrezzo(Float.parseFloat(textPrezzo.getText()));
				try {
					FornitoreHasProdottoDAO.inserisciRelazioneProdForn(relazione);
					inserisicPrezzo.this.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnOk.setBounds(5, 121, 97, 25);
		contentPane.add(btnOk);
		
		JButton btnCanc = new JButton("Canc");
		btnCanc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserisicPrezzo.this.setVisible(false);
			}
		});
		btnCanc.setBounds(233, 121, 97, 25);
		contentPane.add(btnCanc);
	}
}
