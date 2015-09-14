package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import DAO.EntrateDAO;
import DAO.FornitoreDAO;
import DAO.FornitoreHasProdottoDAO;
import DAO.ProdottoDAO;

import model.entrate;
import model.prodotto;
import model.prodottoHasFornitore;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.Calendar;


public class VerificaInserimentoProdotto extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prodotto conferma=null;
					VerificaInserimentoProdotto frame = new VerificaInserimentoProdotto(conferma,new enoteca());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param relazione 
	 */
	public static boolean confermaInserimento(prodotto conferma,enoteca frameEnoteca){
		try {
			VerificaInserimentoProdotto frame = new VerificaInserimentoProdotto(conferma,frameEnoteca);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	public VerificaInserimentoProdotto(final prodotto conferma,final enoteca frameEnoteca) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final metodUtils utile=new metodUtils();
		setBounds(100, 100, 713, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ETICHETTA:");
		lblNewLabel.setBounds(0, 13, 84, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipologia = new JLabel("TIPOLOGIA");
		lblTipologia.setBounds(356, 71, 84, 16);
		contentPane.add(lblTipologia);
		
		JLabel lblVigneto = new JLabel("VIGNETO");
		lblVigneto.setBounds(0, 71, 84, 16);
		contentPane.add(lblVigneto);
		
		JLabel lblProduttore = new JLabel("PRODUTTORE:");
		lblProduttore.setBounds(356, 13, 101, 16);
		contentPane.add(lblProduttore);
		
		final JLabel lblAgenzia = new JLabel("AGENZIA");
		lblAgenzia.setBounds(0, 121, 84, 16);
		contentPane.add(lblAgenzia);
		
		JLabel lblDescrizione = new JLabel("DESCRIZIONE");
		lblDescrizione.setBounds(12, 276, 84, 16);
		contentPane.add(lblDescrizione);
		
		JLabel lblAnnata = new JLabel("ANNATA");
		lblAnnata.setBounds(491, 247, 84, 16);
		contentPane.add(lblAnnata);
		
		JLabel lblPrezzoInterno = new JLabel("PREZZO AL PUBBLICO \"USCITE\"");
		lblPrezzoInterno.setBounds(78, 247, 180, 16);
		contentPane.add(lblPrezzoInterno);
		
		JLabel lblGradazione = new JLabel("GRADAZIONE:");
		lblGradazione.setBounds(599, 247, 84, 16);
		contentPane.add(lblGradazione);
		
		JLabel lblPrezzoAgenziaentrate = new JLabel("PREZZO AGENZIA \"ENTRATE\"");
		lblPrezzoAgenziaentrate.setBounds(283, 247, 174, 16);
		contentPane.add(lblPrezzoAgenziaentrate);
		
		JLabel lblNome = new JLabel("<html><h3><font color='purple'>"+conferma.getNome()+"</html></h3></font>");
		lblNome.setBounds(76, 13, 268, 16);
		contentPane.add(lblNome);
		
		JLabel lblProd = new JLabel("<html><h3><font color='blue'>"+conferma.getProdCasa().getNome()+"</html></h3></font>");
		lblProd.setBounds(445, 13, 238, 16);
		contentPane.add(lblProd);
		
		JLabel lblGrad = new JLabel(String.format("<html><h3><font color='DarkBlue'>"+"%.2f",conferma.getGradi())+"</html></h3></font>");
		lblGrad.setBounds(609, 276, 56, 16);
		contentPane.add(lblGrad);
		
		JLabel lblAnno = new JLabel("<html><h3><font color='lime'>"+String.format("%d",conferma.getAnno())+"</html></h3></font>");
		lblAnno.setBounds(491, 275, 56, 16);
		contentPane.add(lblAnno);
		
		JLabel lblVign = new JLabel("<html><h3><font color='#775500'>"+conferma.getUvaglio().getNome()+"</html></h3></font>");
		lblVign.setBounds(64, 71, 280, 16);
		contentPane.add(lblVign);
		
		JLabel lblTip = new JLabel("<html><h3><font color='#005577'>"+conferma.getTipo().getTipo()+"</html></h3></font>");
		lblTip.setBounds(433, 71, 250, 16);
		contentPane.add(lblTip);
		
		final JLabel lblAge = new JLabel("<html><h3><font color='mocha'>"+conferma.getFornitore().getNome()+"</html></h3></font>");
		lblAge.setBounds(64, 121, 280, 16);
		contentPane.add(lblAge);
		
		JLabel lblUscite = new JLabel("<html><h3><font color='green'>"+String.format("%.2f",conferma.getPrezzo())+"</font></h3></html>");
		lblUscite.setBounds(136, 275, 56, 16);
		contentPane.add(lblUscite);
		
		final JLabel lblEntrate = new JLabel("<html><h3><font color='red'>"+String.format("%.2f",conferma.getPrezzoInterno())+"</font></h3></html>");
		lblEntrate.setBounds(363, 276, 56, 16);
		contentPane.add(lblEntrate);
		
		
		
		
		Calendar c=Calendar.getInstance();
		
		
		
		JLabel lblGiorno = new JLabel("Giorno:");
		lblGiorno.setBounds(356, 121, 56, 16);
		contentPane.add(lblGiorno);
		
		final JLabel labelGiorno = new JLabel(String.format("%d",c.get(Calendar.DAY_OF_MONTH)));
		labelGiorno.setBounds(406, 121, 34, 16);
		contentPane.add(labelGiorno);
		
		final JLabel labelMese = new JLabel(String.format("%d",c.get(Calendar.MONTH)+1));
		labelMese.setBounds(447, 121, 56, 16);
		contentPane.add(labelMese);
		
		final JLabel labelAnno = new JLabel(String.format("%d", c.get(Calendar.YEAR)));
		labelAnno.setBounds(509, 121, 56, 16);
		contentPane.add(labelAnno);
		
		JLabel lblEntrate_1 = new JLabel("Entrate:");
		lblEntrate_1.setBounds(355, 148, 56, 16);
		contentPane.add(lblEntrate_1);
		
		final JLabel labelEntrate = new JLabel(String.format("%d",conferma.getValuta()));
		labelEntrate.setBounds(433, 150, 56, 16);
		contentPane.add(labelEntrate);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 305, 673, 202);
		contentPane.add(scrollPane);
		
		JLabel lblDes = new JLabel(utile.andataACapo(conferma.getDescrizione(), 110));
		scrollPane.setViewportView(lblDes);
		
		JButton btnNewButton = new JButton("Conferma Salvataggio");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					
					if(ProdottoDAO.inserisciProdotto(conferma)){
						
						@SuppressWarnings("deprecation")
						Date data=new Date(Integer.parseInt(labelAnno.getText())-1900, 
								Integer.parseInt(labelMese.getText())-1,Integer.parseInt(labelGiorno.getText()));
						entrate appoggio=new entrate();
						ProdottoDAO pDao=new ProdottoDAO();
						appoggio.setIdProdotto(pDao.getUltimoIdProdotto());
						appoggio.setData(data);
						appoggio.setQuantita(Integer.parseInt(labelEntrate.getText()));
						
						EntrateDAO.setEntrata(appoggio);
						frameEnoteca.addProdotto(conferma);
						if(JOptionPane.showConfirmDialog(null, "Vuoi inserire un nuovo prodotto?","Reset inserimento!",JOptionPane.YES_NO_OPTION)==0){
							inserimentoProdotto.refresh();
						}
						VerificaInserimentoProdotto.this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Risulta gia inserito!");
				}
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(33, 520, 180, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annulla");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VerificaInserimentoProdotto.this.dispose();
			}
		});
		btnNewButton_1.setBounds(327, 520, 171, 25);
		contentPane.add(btnNewButton_1);
		
	
	}
}
