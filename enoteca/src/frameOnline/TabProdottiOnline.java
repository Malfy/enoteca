package frameOnline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DAOOnline.FornitoreDAOOnline;
import DAOOnline.ProdottoDAOOnline;

import model.Connessione;
import model.entrate;
import model.prodotto;
import model.uscite;

import java.awt.ScrollPane;
import java.awt.Panel;
import java.util.ArrayList;

public class TabProdottiOnline extends JFrame {

	private prodotto ricercaProdotto;
	private JPanel contentPane;
	private final String[] columns= new String[] {"Vigneto", "Etichetta","Anno", "Produttore", "Regione", "Tipologia", "Entrata", "Uscita","Quantita", "Acquisto", "Vendita","Codice"};
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabProdottiOnline frame = new TabProdottiOnline(new prodotto(),new Connessione());
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
	public TabProdottiOnline(prodotto prodotto,Connessione utente) throws Exception {
		ricercaProdotto=prodotto;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1065, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setBounds(12, 13, 1023, 376);
		contentPane.add(scrollPane);
		
		table = new JTable()
		{
			public boolean isCellEditable(int data, int columns){
				return false;
			}
			public Component prepareRenderer(TableCellRenderer r,int data,int columns){
				Component c = super.prepareRenderer(r, data, columns);
				if(data%2==0)
					c.setBackground(Color.white);
				else
					c.setBackground(Color.lightGray);		
				if(isCellSelected(data, columns))
					c.setBackground(Color.CYAN);
				return c;
				
			}
		};
		DefaultTableModel tableModel=new DefaultTableModel();
		
		tableModel.setDataVector(ricercaProdotto(utente), columns);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
	}
	
	
	
	private  Object[][] ricercaProdotto(Connessione utente) throws Exception{
		Object[][] data;
		ArrayList<prodotto> lista=new ArrayList<prodotto>();
		ProdottoDAOOnline prodottoDao=new ProdottoDAOOnline();
		FornitoreDAOOnline fornitoreDao=new FornitoreDAOOnline();
		lista = prodottoDao.getListaProdotto(ricercaProdotto,true,true,utente);
		data=new String[lista.size()][12];
		try {
			int a=0;	
			for (prodotto e : lista){
				
				data[a][0]=e.getUvaglio().getNome();
				data[a][1]=e.getNome();
				data[a][2]=String.format("%d",e.getAnno());
				data[a][3]=e.getProdCasa().getNome();
				data[a][10]=String.format("%.2f",e.getPrezzo());
				data[a][5]=e.getTipo().getTipo();
				if(e.getProdCasa().getNome()!=null)
					data[a][4]=e.getProdCasa().getRegione().getNome();
				data[a][11]=String.format("%d", e.getIdProdotto());
				data[a][9]=String.format("%.2f",e.getPrezzoInterno());
				int entrata=0;
				if(e.getEntrate().size()!=0){					
					for(entrate E:e.getEntrate()){
						
						entrata=entrata+E.getQuantita();
					}
				}
				
				data[a][6]=String.format("%d", entrata);
				
				int uscita=0;
				if(e.getUscite().size()!=0){
					for(uscite E:e.getUscite()){
						uscita=uscita+E.getQuantita();
					}
				}
				
					data[a][7]=String.format("%d", uscita);
					data[a][8]=String.format("%d", entrata-uscita);
				a++;
			}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return data;
	
	}
	
	
	
}
