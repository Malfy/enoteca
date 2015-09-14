package frame;
//import frame.Connecctor;
import frame.ConnectioOnline;
import frame.ConnessioneOnline;
import frame.InserisciFornitore;
import frame.SplashScreen;
import frame.TabVigneti;
import frame.inserimentoProdotto;
import frame.inserimentoTipologia;
import frame.regione;
import frame.tabFornitori;
import frame.tabProdotti;
import frame.tabTipologie;

import impl.implProdotto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JTable;

import model.entrate;
import model.prodotto;
import model.uscite;
//import net.miginfocom.swing.MigLayout;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.SystemColor;
import java.awt.Component;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;

import DAO.Export;
import DAO.FornitoreDAO;
import DAO.ProdottoDAO;
import DAO.TestConnessione;

import javax.swing.JTextPane;
import java.awt.TextField;
import java.awt.List;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import javax.swing.RootPaneContainer;

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;
import com.sun.corba.se.impl.ior.ObjectKeyFactoryImpl;

import backup.Home;
import java.awt.Button;
import java.awt.Rectangle;


public class enoteca extends JFrame {
	

	private JPanel contentPane;
	private JTable table;
	private final Action action = new SwingAction();
	private JButton btnInserisciprodotto;
	private static enoteca frame;
	private static Object[][] data;
	private JButton btnVisualizzaProdotto;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new enoteca();
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
	public enoteca() throws Exception {
//		data=tableRiempi();
		setTitle("GestionEnotecaV1.8.2");
		setIconImage(Toolkit.getDefaultToolkit().getImage(enoteca.class.getResource("/img/download.jpg")));
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tabbedPane, 38, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tabbedPane, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tabbedPane, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tabbedPane, -15, SpringLayout.EAST, contentPane);
		contentPane.add(tabbedPane);
		
		JPanel panelProdotto = new JPanel();
		tabbedPane.addTab("Prodotto", null, panelProdotto, null);
		panelProdotto.setLayout(null);
		
		btnInserisciprodotto = new JButton("Inserisci Prodotto");
		btnInserisciprodotto.setBounds(12, 12, 173, 26);
		panelProdotto.add(btnInserisciprodotto);
		btnInserisciprodotto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inserimentoProdotto frameInsProd = null;
				try {
					frameInsProd = new inserimentoProdotto(frame);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frameInsProd.setVisible(true);
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnInserisciprodotto, 430, SpringLayout.WEST, contentPane);
		data=tableRiempi();
		btnVisualizzaProdotto = new JButton("Visualizza Prodotto");
		btnVisualizzaProdotto.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {			
				try {
					startWaitCursor();
					tabProdotti vP=new tabProdotti(data,frame);	
					vP.setVisible(true);
					stopWaitCursor();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		});
		btnVisualizzaProdotto.setBounds(12, 78, 173, 26);
		panelProdotto.add(btnVisualizzaProdotto);
		
		JLabel lblQuiInserisciUn = new JLabel("Qui inserisci un nuovo prodotto");
		lblQuiInserisciUn.setBounds(12, 50, 243, 16);
		panelProdotto.add(lblQuiInserisciUn);
		
		JLabel lblQuiPuoiVisualizzare = new JLabel("Qui puoi visualizzare e gestire i prodotti");
		lblQuiPuoiVisualizzare.setBounds(12, 116, 463, 16);
		panelProdotto.add(lblQuiPuoiVisualizzare);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(enoteca.class.getResource("/img/logoo.gif")));
		label.setBounds(268, 0, 250, 268);
		panelProdotto.add(label);
		
		JPanel panelFornitore = new JPanel();
		tabbedPane.addTab("Fornitore", null, panelFornitore, null);
		panelFornitore.setLayout(null);
		
		JLabel lblFornitore = new JLabel("Inserisci un nuovo Agenzia");
		lblFornitore.setBounds(12, 12, 453, 16);
		panelFornitore.add(lblFornitore);
		
		JButton btnInserisciFornitore = new JButton("Inserisci Agenzia");
		btnInserisciFornitore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciFornitore inFo=new InserisciFornitore();
				inFo.setVisible(true);
			}
		});
		btnInserisciFornitore.setBounds(12, 40, 143, 26);
		panelFornitore.add(btnInserisciFornitore);
		
		JButton btnVisualizzaFornitore = new JButton("Visualizza Agenzia");
		btnVisualizzaFornitore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabFornitori tFornitori;
				try {
					tFornitori = new tabFornitori();
					tFornitori.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVisualizzaFornitore.setBounds(12, 106, 143, 26);
		panelFornitore.add(btnVisualizzaFornitore);
		
		JLabel lblQuiVisualizziE = new JLabel("Qui visualizzi e gestisci i Agenzia");
		lblQuiVisualizziE.setBounds(12, 78, 453, 16);
		panelFornitore.add(lblQuiVisualizziE);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(enoteca.class.getResource("/img/logoo.gif")));
		label_1.setBounds(268, 0, 250, 268);
		panelFornitore.add(label_1);
		
		JPanel panelAltro = new JPanel();
		tabbedPane.addTab("Altro", null, panelAltro, null);
		panelAltro.setLayout(null);
		
		JButton btnRegioni = new JButton("Regione/Produttore");
		btnRegioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regione reg;
				try {
					reg = new regione();
					reg.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRegioni.setBounds(12, 40, 155, 26);
		panelAltro.add(btnRegioni);
		
		JButton btnVigneti = new JButton("Vigneti");
		btnVigneti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TabVigneti tabVig=new TabVigneti();
					tabVig.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnVigneti.setBounds(12, 106, 98, 26);
		panelAltro.add(btnVigneti);
		
		JButton btnTipologie = new JButton("Tipologie");
		btnTipologie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabTipologie taTi;
				try {
					taTi = new tabTipologie();
					taTi.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnTipologie.setBounds(12, 172, 98, 26);
		panelAltro.add(btnTipologie);
		
		JLabel lblQuiPuoiRimuovere = new JLabel("Qui puoi CANCELLARE o INSERIRE  Regione/Produttore");
		lblQuiPuoiRimuovere.setBounds(12, 12, 332, 16);
		panelAltro.add(lblQuiPuoiRimuovere);
		
		JLabel lblQuiPuoiRimuovere_1 = new JLabel("Qui puoi MODIFICARE o CANCELLARE  Vigneti");
		lblQuiPuoiRimuovere_1.setBounds(12, 78, 283, 16);
		panelAltro.add(lblQuiPuoiRimuovere_1);
		
		JLabel lblQuiPuoiRimuovere_2 = new JLabel("<html>Qui puoi MODIFICARE o CANCELLARE  le Tipolog<font color='#ffffff'>ie</font></html>");
		lblQuiPuoiRimuovere_2.setBounds(12, 144, 283, 16);
		panelAltro.add(lblQuiPuoiRimuovere_2);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(enoteca.class.getResource("/img/logoo.gif")));
		label_2.setBounds(268, 0, 250, 268);
		panelAltro.add(label_2);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Impostazioni", null, panel, null);
		panel.setLayout(null);
		
		JButton btnConnessione = new JButton("Connessione");
		btnConnessione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				try {
////					Connecctor c=new Connecctor();
////					c.setVisible(true);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		});
		btnConnessione.setBounds(12, 12, 132, 26);
		panel.add(btnConnessione);
		
		JButton btnUtenti = new JButton("Crea Utente");
		btnUtenti.setBounds(12, 50, 132, 26);
		panel.add(btnUtenti);
		
		JButton btnBackup = new JButton("Backup");
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Home h=new Home();
				h.setVisible(true);
				
			}
		});
//		btnBackup.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				Export ex=new Export();
//				try {
//					ex.exportDb();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
		btnBackup.setBounds(12, 90, 98, 26);
		panel.add(btnBackup);
		
		JLabel lblConnect = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblConnect, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblConnect, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblConnect);
		
		Button button = new Button("Connessione");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startWaitCursor();
				ConnessioneOnline cOn=new ConnessioneOnline();
				cOn.setVisible(true);
				stopWaitCursor();
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBackground(new Color(255, 102, 102));
		sl_contentPane.putConstraint(SpringLayout.NORTH, button, 4, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, button, -138, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, button, -6, SpringLayout.NORTH, tabbedPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, button, -22, SpringLayout.EAST, contentPane);
		contentPane.add(button);
		if(!TestConnessione.test())
			lblConnect.setText("<html> <font color='green'>connessione stabile</font></html>");
		else
			lblConnect.setText("<html><font color='red'>connessione instabile</font></html>");		
	}
	
	void refresh(int idMod[]) throws Exception{
		// deve rientrare un array di int che rappresentano gli id dei prodotti modificati!
		for(int a=0;a<idMod.length;a++){
			if(idMod[a]!=0){
				for(int x=0;x<data.length;x++){
					if(idMod[a]==Integer.parseInt((String) data[x][11]))
					{
						//richiedere al db il prodotto per questo id---> idMod[a]
						prodotto prodottoAggiorna=new prodotto();
						prodottoAggiorna=ProdottoDAO.getProdotto(idMod[a]);
						data[x][3]=prodottoAggiorna.getUvaglio().getNome();
						data[x][2]=prodottoAggiorna.getNome();
						data[x][5]=String.format("%d", prodottoAggiorna.getAnno());
						data[x][1]=prodottoAggiorna.getProdCasa().getNome();
						data[x][10]=String.format("%.2f",prodottoAggiorna.getPrezzo());
						data[x][4]=prodottoAggiorna.getTipo().getTipo();
						if(prodottoAggiorna.getProdCasa().getNome()!=null)
							data[x][0]=prodottoAggiorna.getProdCasa().getRegione().getNome();
						data[x][11]=String.format("%d", prodottoAggiorna.getIdProdotto());
						data[x][9]=String.format("%.2f",prodottoAggiorna.getPrezzoInterno());
						int entrata=0;
						if(prodottoAggiorna.getEntrate().size()!=0){					
							for(entrate E:prodottoAggiorna.getEntrate()){
								
								entrata=entrata+E.getQuantita();
							}
						}
						
						data[x][6]=String.format("%d", entrata);
						
						int uscita=0;
						if(prodottoAggiorna.getUscite().size()!=0){
							for(uscite E:prodottoAggiorna.getUscite()){
								uscita=uscita+E.getQuantita();
							}
						}
						
							data[x][7]=String.format("%d", uscita);
							data[x][8]=String.format("%d", entrata-uscita);
						
						
						
						
						
						
						
					}
				}
			}
		}
		
//		System.out.println("refresh");
//		enoteca enoteca=new enoteca();
//		enoteca.setVisible(true);
//		this.dispose();
	}
	
	private static Object[][] tableRiempi() throws Exception{
		final ProdottoDAO prodottoDao=new ProdottoDAO();
		FornitoreDAO fornitoreDao=new FornitoreDAO();
		ArrayList<prodotto> lista= prodottoDao.getListaProdotto();
		/// hahahahahahahaha
		Collections.sort(lista);
		Object[][] dataMet=new String[lista.size()][12];
		try {
			int a=0;	
			for (prodotto e : lista){
				
				dataMet[a][3]=e.getUvaglio().getNome();
				dataMet[a][2]=e.getNome();
				dataMet[a][5]=String.format("%d", e.getAnno());
				dataMet[a][1]=e.getProdCasa().getNome();
				dataMet[a][10]=String.format("%.2f",e.getPrezzo());
				dataMet[a][4]=e.getTipo().getTipo();
				if(e.getProdCasa().getNome()!=null)
					dataMet[a][0]=e.getProdCasa().getRegione().getNome();
				dataMet[a][11]=String.format("%d", e.getIdProdotto());
				dataMet[a][9]=String.format("%.2f",e.getPrezzoInterno());
				int entrata=0;
				if(e.getEntrate().size()!=0){					
					for(entrate E:e.getEntrate()){
						
						entrata=entrata+E.getQuantita();
					}
				}
				
				dataMet[a][6]=String.format("%d", entrata);
				
				int uscita=0;
				if(e.getUscite().size()!=0){
					for(uscite E:e.getUscite()){
						uscita=uscita+E.getQuantita();
					}
				}
				
					dataMet[a][7]=String.format("%d", uscita);
					dataMet[a][8]=String.format("%d", entrata-uscita);
				a++;
			}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		data=dataMet;
		return dataMet;
	
	
	}
	

	private class SwingAction extends AbstractAction {
		public class DettagliAnnate {

		}
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	
	public void startWaitCursor() {
	    RootPaneContainer root = (RootPaneContainer) this.getRootPane()
	            .getTopLevelAncestor();
	    root.getGlassPane().setCursor(
	            Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	    root.getGlassPane().setVisible(true);
	}
	 
	public void stopWaitCursor() {
	    RootPaneContainer root = (RootPaneContainer) this.getRootPane()
	            .getTopLevelAncestor();
	    root.getGlassPane().setCursor(
	            Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    root.getGlassPane().setVisible(false);
	}



	public void addProdotto(prodotto prodottoAggiorna) {
		int x=data.length;
		System.out.println(x);
		Object[][] dataMod=new Object[x+1][12];
		for(int y=0;y<data.length;y++){
			dataMod[y][0]=data[y][0];
			dataMod[y][1]=data[y][1];
			dataMod[y][2]=data[y][2];
			dataMod[y][3]=data[y][3];
			dataMod[y][4]=data[y][4];
			dataMod[y][5]=data[y][5];
			dataMod[y][6]=data[y][6];
			dataMod[y][7]=data[y][7];
			dataMod[y][8]=data[y][8];
			dataMod[y][9]=data[y][9];
			dataMod[y][10]=data[y][10];
			dataMod[y][11]=data[y][11];			
		}
		
		dataMod[x][3]=prodottoAggiorna.getUvaglio().getNome();
		dataMod[x][2]=prodottoAggiorna.getNome();
		dataMod[x][5]=String.format("%d", prodottoAggiorna.getAnno());
		dataMod[x][1]=prodottoAggiorna.getProdCasa().getNome();
		dataMod[x][10]=String.format("%.2f",prodottoAggiorna.getPrezzo());
		dataMod[x][4]=prodottoAggiorna.getTipo().getTipo();
		dataMod[x][0]="";//prodottoAggiorna.getProdCasa().getRegione().getNome();
		dataMod[x][11]=String.format("%d", prodottoAggiorna.getIdProdotto());
		dataMod[x][9]=String.format("%.2f",prodottoAggiorna.getPrezzoInterno());
		int entrata=0;
		if(prodottoAggiorna.getEntrate().size()!=0){					
			for(entrate E:prodottoAggiorna.getEntrate()){
				
				entrata=entrata+E.getQuantita();
			}
		}
		
		dataMod[x][6]=String.format("%d", entrata);
		
		int uscita=0;
		if(prodottoAggiorna.getUscite().size()!=0){
			for(uscite E:prodottoAggiorna.getUscite()){
				uscita=uscita+E.getQuantita();
			}
		}
		
		dataMod[x][7]=String.format("%d", uscita);
		dataMod[x][8]=String.format("%d", entrata-uscita);
		
		data=new Object[data.length+1][12];
		data=dataMod;
		
	}
}
