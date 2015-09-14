package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import model.entrate;
import model.prodotto;
import model.uscite;

import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.FornitoreDAO;
import DAO.ProdottoDAO;

public class DetAnnate extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetAnnate frame = new DetAnnate(new prodotto());
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
	public DetAnnate(prodotto prodotto) throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 794, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 65, 679, 344);
		contentPane.add(scrollPane);
		
		table = new JTable();
		prodotto ricProd=new prodotto();
		ricProd.setNome(prodotto.getNome());
		table.setModel(new DefaultTableModel(
			ricercaProdotto(ricProd),
			new String[] {
				"Anno", "Entrate", "PrezzoAgenzia", "+", "Vendute", "PrezzoVendita", "-", "Rimanenti","id"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel(prodotto.getNome());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Britannic Bold", Font.PLAIN, 37));
		label.setBounds(12, 13, 660, 50);
		contentPane.add(label);
	}
	
	private  Object[][] ricercaProdotto(prodotto ricercaProdotto) throws Exception{
		Object[][] data;
		ArrayList<prodotto> lista=new ArrayList<prodotto>();
		ProdottoDAO prodottoDao=new ProdottoDAO();
		FornitoreDAO fornitoreDao=new FornitoreDAO();
		lista = prodottoDao.getListaProdottoPerEtichetta(ricercaProdotto,true,true);
		data=new String[lista.size()][9];
		try {
			int a=0;	
			for (prodotto e : lista){
				
				data[a][0]=String.format("%d",e.getAnno());
				data[a][5]=String.format("%.2f",e.getPrezzo());
				data[a][2]=String.format("%.2f",e.getPrezzoInterno());
				int entrata=0;
				if(e.getEntrate().size()!=0){					
					for(entrate E:e.getEntrate()){
						
						entrata=entrata+E.getQuantita();
					}
				}
				
				data[a][1]=String.format("%d", entrata);
				
				int uscita=0;
				if(e.getUscite().size()!=0){
					for(uscite E:e.getUscite()){
						uscita=uscita+E.getQuantita();
					}
				}
				
					data[a][4]=String.format("%d", uscita);
					data[a][7]=String.format("%d", entrata-uscita);
					data[a][8]=String.format("%d", e.getIdProdotto());
				a++;
			}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return data;
	
	}
	
	
}
