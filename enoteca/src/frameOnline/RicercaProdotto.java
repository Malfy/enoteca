package frameOnline;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import model.Connessione;
import model.prodotto;
import model.regione;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComboBox;

import DAOOnline.RegioneDAOOnline;

public class RicercaProdotto extends JFrame {

	private JPanel contentPane;
	private JTextField textEtichetta;
	private JTextField textProduttore;
	private JTextField textVigneto;
	private JTextField textTipologia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaProdotto frame = new RicercaProdotto(new Connessione());
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
	public RicercaProdotto(final Connessione utente) throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Etichetta");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setBounds(12, 13, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblProduttore = new JLabel("Produttore");
		lblProduttore.setForeground(Color.RED);
		lblProduttore.setBounds(12, 78, 79, 16);
		contentPane.add(lblProduttore);
		
		JLabel lblVigneto = new JLabel("Vigneto");
		lblVigneto.setForeground(Color.RED);
		lblVigneto.setBounds(12, 139, 56, 16);
		contentPane.add(lblVigneto);
		
		JLabel lblRegione = new JLabel("Regione");
		lblRegione.setForeground(Color.RED);
		lblRegione.setBounds(12, 201, 56, 16);
		contentPane.add(lblRegione);
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setForeground(Color.RED);
		lblTipologia.setBounds(12, 268, 56, 16);
		contentPane.add(lblTipologia);
		
		textEtichetta = new JTextField();
		textEtichetta.setBounds(12, 43, 116, 22);
		contentPane.add(textEtichetta);
		textEtichetta.setColumns(10);
		
		textProduttore = new JTextField();
		textProduttore.setColumns(10);
		textProduttore.setBounds(12, 107, 116, 22);
		contentPane.add(textProduttore);
		
		textVigneto = new JTextField();
		textVigneto.setColumns(10);
		textVigneto.setBounds(12, 166, 116, 22);
		contentPane.add(textVigneto);
		
		textTipologia = new JTextField();
		textTipologia.setColumns(10);
		textTipologia.setBounds(12, 297, 116, 22);
		contentPane.add(textTipologia);
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					prodotto prodotto=new prodotto();
					prodotto.setNome(textEtichetta.getText());
					prodotto.getUvaglio().setNome(textVigneto.getText());
					prodotto.getProdCasa().setNome(textProduttore.getText());
					prodotto.getTipo().setTipo(textTipologia.getText());
					prodotto.setCibi("");
					
					TabProdottiOnline tabProOn=new TabProdottiOnline(prodotto, utente);
					tabProOn.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCerca.setBounds(206, 232, 116, 52);
		contentPane.add(btnCerca);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEtichetta.setText("");
				textProduttore.setText("");
				textTipologia.setText("");
				textVigneto.setText("");
			}
		});
		btnReset.setBounds(206, 296, 116, 25);
		contentPane.add(btnReset);
		
		JComboBox comboRegione = new JComboBox();
		ArrayList<regione> regioni=RegioneDAOOnline.getRegioni(utente);
		for(regione app:regioni){
			comboRegione.addItem(app.getNome());
		}
		comboRegione.setBounds(12, 233, 116, 22);
		contentPane.add(comboRegione);
	}
}
