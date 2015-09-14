package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.FornitoreDAO;
import DAO.FornitoreHasProdottoDAO;
import DAO.ProdottoDAO;

import model.fornitore;
import model.prodotto;
import model.prodottoHasFornitore;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class modProd extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCibi;
	private JTextField textAnno;
	private JTextField textGradi;
	private JTextField textInterno;
	private JTextField textPublico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final int a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modProd frame = new modProd(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public modProd(final int id) throws JarException, SQLException, Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(modProd.class.getResource("/img/iconaLettura.gif")));
		setTitle("Modifica Prodotto");
		final metodUtils utile=new metodUtils();
		final prodotto prodotto=ProdottoDAO.getProdotto(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 949, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Etichetta:");
		lblNewLabel.setBounds(10, 11, 96, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Produttore:");
		lblNewLabel_1.setBounds(10, 219, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTipologia = new JLabel("Tipologia:");
		lblTipologia.setBounds(10, 269, 72, 14);
		contentPane.add(lblTipologia);
		
		JLabel lblVigneto = new JLabel("Vigneto:");
		lblVigneto.setBounds(10, 325, 72, 14);
		contentPane.add(lblVigneto);
		
		textNome = new JTextField();
		textNome.setBounds(10, 26, 224, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		textNome.setText(prodotto.getNome());
		
		final JComboBox comboCasa = new JComboBox(utile.getComboCasa());
		comboCasa.setBounds(10, 244, 224, 20);
		contentPane.add(comboCasa);
		comboCasa.setSelectedItem(prodotto.getProdCasa().getNome());
		
		
		
		final JComboBox comboTipologia = new JComboBox(utile.getComboTipologia());
		comboTipologia.setBounds(10, 294, 224, 20);
		contentPane.add(comboTipologia);
		comboTipologia.setSelectedItem(prodotto.getTipo().getTipo());
		
		final JComboBox comboVigneto = new JComboBox(utile.getComboVigneto());
		comboVigneto.setBounds(10, 350, 224, 20);
		contentPane.add(comboVigneto);
		comboVigneto.setSelectedItem(prodotto.getUvaglio().getNome());
		
		final JEditorPane editorDescrizione = new JEditorPane();
		editorDescrizione.setFont(new Font("Tahoma", Font.BOLD, 13));
		editorDescrizione.setBounds(301, 26, 622, 489);
		contentPane.add(editorDescrizione);
		editorDescrizione.setText(prodotto.getDescrizione());
		
		JLabel lblDescrizione = new JLabel("Descrizione:");
		lblDescrizione.setBounds(301, 11, 86, 14);
		contentPane.add(lblDescrizione);
		
		JLabel lblCibi = new JLabel("Cibi:");
		lblCibi.setBounds(10, 57, 46, 14);
		contentPane.add(lblCibi);
		
		textCibi = new JTextField();
		textCibi.setBounds(10, 82, 224, 77);
		contentPane.add(textCibi);
		textCibi.setColumns(10);
		textCibi.setText(prodotto.getCibi());
		
		JLabel lblFornitore = new JLabel("Agenzia:");
		lblFornitore.setBounds(10, 381, 72, 14);
		contentPane.add(lblFornitore);
		
		final JComboBox comboFornitore = new JComboBox(utile.getComboFornitore());
		comboFornitore.setBounds(10, 406, 224, 20);
		contentPane.add(comboFornitore);
		comboFornitore.setSelectedItem(prodotto.getFornitore().getNome());
		
		JLabel lblAnno = new JLabel("Anno:");
		lblAnno.setBounds(10, 170, 46, 14);
		contentPane.add(lblAnno);
		
		textAnno = new JTextField();
		textAnno.setBounds(10, 195, 46, 20);
		contentPane.add(textAnno);
		textAnno.setColumns(10);
		textAnno.setText(String.format("%d", prodotto.getAnno()));
		
		JLabel lblGradi = new JLabel("Gradi:");
		lblGradi.setBounds(66, 170, 40, 14);
		contentPane.add(lblGradi);
		
		
		textGradi = new JTextField();
		textGradi.setBounds(66, 195, 40, 20);
		contentPane.add(textGradi);
		textGradi.setColumns(10);
		textGradi.setText(String.format("%.2f", prodotto.getGradi()));
		
		JLabel lblPrezzoInterno = new JLabel("Prezzo Agente:");
		lblPrezzoInterno.setBounds(10, 437, 82, 14);
		contentPane.add(lblPrezzoInterno);
		
		textInterno = new JTextField();
		textInterno.setBounds(6, 457, 86, 20);
		contentPane.add(textInterno);
		textInterno.setColumns(10);
		textInterno.setText(String.format("%.2f",prodotto.getPrezzoInterno()));
		
		

		
		JLabel lblPrezzoPublico = new JLabel("Prezzo Publico:");
		lblPrezzoPublico.setBounds(114, 437, 82, 14);
		contentPane.add(lblPrezzoPublico);
		
		textPublico = new JTextField();
		textPublico.setBounds(102, 457, 86, 20);
		contentPane.add(textPublico);
		textPublico.setColumns(10);
		textPublico.setText(String.format("%.2f",prodotto.getPrezzo()));
		
		JButton btnFornitori = new JButton("Modifica Fornitori");
		btnFornitori.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnFornitori.setBounds(202, 455, 87, 25);
		contentPane.add(btnFornitori);
		
		JButton btnSalva = new JButton("salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			prodotto appoggio=new prodotto();
			appoggio.setIdProdotto(id);
			
			if(!textNome.getText().toString().equals(""))
				appoggio.setNome(textNome.getText().toString());
			
			if(!editorDescrizione.getText().toString().equals(""))
				appoggio.setDescrizione(editorDescrizione.getText().toString());
			if(!textAnno.getText().toString().equals("")){
				if(utile.isDoubleOrInt(textAnno.getText())==0){
					System.out.println(textAnno.getText());
					appoggio.setAnno(Integer.parseInt( textAnno.getText().toString()));
				}
				else
					JOptionPane.showInternalConfirmDialog(textAnno, "inserimento anno sbagliato");
			}
			if(!textCibi.getText().toString().equals(""))
				appoggio.setCibi(textCibi.getText().toString());
			if(!textGradi.getText().toString().equals(""))
				appoggio.setGradi(Float.parseFloat(utile.convertStringFloat(textGradi.getText().toString())));
			if(!textInterno.getText().toString().equals("")){
				String prezzo1=textInterno.getText().toString().replace(".", "");
				appoggio.setPrezzoInterno(Float.parseFloat(prezzo1.replace(',', '.')));
			}
			if(!textPublico.getText().toString().equals("")){
				String prezzo2=textPublico.getText().toString().replace(".", "");
				appoggio.setPrezzo(Float.parseFloat(prezzo2.replace(',', '.')));
			}
			if(comboCasa.getSelectedIndex()>0)
				appoggio.getProdCasa().setNome(comboCasa.getSelectedItem().toString());
			if(comboVigneto.getSelectedIndex()>0)
				appoggio.getUvaglio().setNome(comboVigneto.getSelectedItem().toString());
			if(comboTipologia.getSelectedIndex()>0)
				appoggio.getTipo().setTipo(comboTipologia.getSelectedItem().toString());
			appoggio.setFornitore(new fornitore());
			if(comboFornitore.getSelectedIndex()>0)
				appoggio.getFornitore().setNome(comboFornitore.getSelectedItem().toString());
			
			try {
				if(ProdottoDAO.modificaProdotto(appoggio))
					System.out.println("salvato");
			} catch ( Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			modProd.this.setVisible(false);
			}
		});
		btnSalva.setBounds(9, 490, 97, 25);
		contentPane.add(btnSalva);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modProd.this.setVisible(false);
			}
		});
		btnEsci.setBounds(137, 490, 97, 25);
		contentPane.add(btnEsci);
		
//		prodotto=ProdottoDAO.getProdotto(id);
		
		
	}
}
