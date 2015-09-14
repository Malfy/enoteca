package frame;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.jar.JarException;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultRowSorter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;


import model.casa;
import model.entrate;
import model.prodotto;
import model.prodottoHasFornitore;
import model.uscite;


import DAO.CasaDAO;
import DAO.EntrateDAO;
import DAO.FornitoreDAO;
import DAO.FornitoreHasProdottoDAO;
import DAO.LuvaglioDAO;
import DAO.ProdottoDAO;
import DAO.RegioneDAO;
import DAO.TipologiaDAO;
import DAO.UsciteDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import sun.awt.WindowClosingListener;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;
import com.toedter.calendar.JCalendar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.toedter.calendar.JDateChooser;

public class tabProdotti extends JFrame{
// 1785 DA CONTROLLARE verifica il funzionamento da modifica e salva modifica tabella! ;-) che bel casino il bel
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private enoteca enoteca;
	private boolean controlloSalvataggio=false;
	private int[] idMod;
	private JLabel lblNumEtic;
	private boolean ricAvaCont=true;
	private JPanel contentPane;
	private JLabel lblDetEtic;
	private  JLabel labelDetAnno;
	private JLabel labelDetVendita;
	private JLabel labelDetAcqui;
	private JLabel labelDetVign;
	private JLabel labeldetProduttore;
	private JLabel labelDetReg;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JScrollPane scrollPane;
	private static JTable tableApp=new JTable();
	private static JTable tableCos=new JTable();
	private static JTable table;
	private prodotto ricercaProdotto=new prodotto();
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField textModNome;
	private static metodUtils utile=new metodUtils();
	private JTextField textModCibi;
	private JTextField textModAcquisto;
	private JTextField textModVendita;
	private float prezzo=0;
	private final JComboBox comboModFornitore=new JComboBox(utile.getComboFornitore());
	private prodotto app;
	private JComboBox comboModVigneto;
	private JComboBox comboModTipo;
	private final DefaultTableModel tableModelApp;
	private JComboBox comboModCasa;
	private JLabel labelId;
	final JLabel lblModErrore = new JLabel("");
	private JTable tableEntrate;
	private JTable tableUscite;
	private JTextField textQuantitaEntrate;
	private JTextField textQuantitaUscite;
	private JLabel lblSomma;
	private DefaultTableModel  tableModelCon=new DefaultTableModel();
	private DefaultTableModel tableModel =new DefaultTableModel();
	private static JComboBox tabComboProduttore=new JComboBox();
	private static JComboBox tabComboVigneto=new JComboBox();
	private static JComboBox tabComboTipologia=new JComboBox();
	private JTextField textRicercaVeloceNome;
	private JTextField textId;
	private JTextField textModAnno;
	private JTextField textModGradi;
	private JTextField textField;
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

			
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					tabProdotti frame = new tabProdotti(new Object[10][10],new enoteca());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
		});
	}
		
//	public void open() throws InterruptedException{
//		SplashScreen splash = new SplashScreen("Caricamento...", 5);
//		splash.show();
//		Thread.sleep(1000);
//	splash.advance("Connessione al database...");
//	Thread.sleep(1000);//simulazione tempo di connessione
//	splash.advance("Ricezione dati...");
//	Thread.sleep(1000);//simulazione tempo di ricezione
//	splash.advance("Caricamento ricezione...");
//	Thread.sleep(1000);//simulazione tempo di caricamento immagini
//	splash.advance("Caricamento interfaccia...");
//	Thread.sleep(1000);//simulazione tempo di caricamento interfaccia
//	splash.advance("Fatto.");
//	Thread.sleep(1000);//pausa prima di chiudere lo splash screen
//	splash.hide();
//	}

	public tabProdotti(Object[][] Data,final enoteca frame) throws Exception {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		enoteca=frame;
	
		
		tabComboProduttore=new JComboBox(utile.getComboCasa());
		tabComboTipologia=new JComboBox(utile.getComboTipologia());
		tabComboVigneto=new JComboBox(utile.getComboVigneto());
		setTitle("Gestione Prodotti");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\malfy\\workspace\\enoteca\\img\\download.jpg"));
		final JTextField textNome;
		
	   
		final String[] columns= new String[] {  "Regione", "Produttore", "Etichetta", "Vigneto", "Tipologia", "Anno", "Entrata", "Uscita","Quantita", "Acquisto", "Vendita","Codice"};
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1317, 672);
		
		tabProdotti.this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				int s = JOptionPane.showConfirmDialog(null,"Salvare modifiche?");
				if(s == JOptionPane.YES_OPTION){
					try {
						salvaModificaTab();
						enoteca.refresh(idMod);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tabProdotti.this.dispose();
				}
				else if(s == JOptionPane.NO_OPTION){
					if(controlloSalvataggio)
						try {
							enoteca.refresh(idMod);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					tabProdotti.this.dispose();	
					
				}
			}	
		});
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			try {
					
					int s = JOptionPane.showConfirmDialog(null,"Salvare modifiche?");
						
					if(s == JOptionPane.YES_OPTION){
						try {
							salvaModificaTab();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						tabProdotti.this.dispose();
					}
					else if(s == JOptionPane.NO_OPTION)
						tabProdotti.this.dispose();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//		        else if(s == JOptionPane.CANCEL_OPTION)
//		        else if(s == JOptionPane.CLOSED_OPTION)
			}
		});
		
		JMenuItem mntmSalva = new JMenuItem("Salva");
		mntmSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvaModificaTab();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmSalva);
		mnFile.add(mntmExit);
		
		JMenu mnInserisci = new JMenu("Inserisci");
		menuBar.add(mnInserisci);
		
		JMenuItem mntmInserisciNuovoProdotto = new JMenuItem("Inserisci nuovo Prodotto");
		mntmInserisciNuovoProdotto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					inserimentoProdotto insProdotto=new inserimentoProdotto(frame);
					insProdotto.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnInserisci.add(mntmInserisciNuovoProdotto);
		
		JMenuItem mntmNuovaAgenzia = new JMenuItem("Nuova Agenzia");
		mntmNuovaAgenzia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciFornitore insAgenzia=new InserisciFornitore();
				insAgenzia.setVisible(true);
			}
		});
		mnInserisci.add(mntmNuovaAgenzia);
		
		JMenuItem mntmNuovaTipologia = new JMenuItem("Nuova Tipologia");
		mntmNuovaTipologia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsTipologia insTipo=new InsTipologia();
				insTipo.setVisible(true);
			}
		});
		mnInserisci.add(mntmNuovaTipologia);
		
		JMenuItem mntmNuovaRegione = new JMenuItem("Nuova Regione");
		mntmNuovaRegione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserimentoRegione insRegione=new inserimentoRegione();
				insRegione.setVisible(true);
			}
		});
		mnInserisci.add(mntmNuovaRegione);
		
		JMenuItem mntmNuovoProduttore = new JMenuItem("Nuovo Produttore");
		mntmNuovoProduttore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserimentoProduttore insCasa;
				try {
					insCasa = new InserimentoProduttore();
					insCasa.setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnInserisci.add(mntmNuovoProduttore);
		
		JMenuItem mntmNuovoVigneto = new JMenuItem("Nuovo Vigneto");
		mntmNuovoVigneto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsVigneto inVi=new InsVigneto();
				inVi.setVisible(true);
				
			}
		});
		mnInserisci.add(mntmNuovoVigneto);
		
		JMenu mnModifica = new JMenu("Modifica");
		menuBar.add(mnModifica);
		
		JMenuItem mntmModificaAgenzia = new JMenuItem("Modifica Agenzia");
		mntmModificaAgenzia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tabFornitori tabFornitori=new tabFornitori();
					tabFornitori.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnModifica.add(mntmModificaAgenzia);
		
		JMenuItem mntmModificaProduttore = new JMenuItem("Modifica Produttore");
		mntmModificaProduttore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModProduttore modProd;
				try {
					modProd = new ModProduttore();
					modProd.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnModifica.add(mntmModificaProduttore);
		
		JMenuItem mntmModificaRegione = new JMenuItem("Modifica Regione");
		mntmModificaRegione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModRegione modR;
				try {
					modR = new ModRegione();
					modR.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnModifica.add(mntmModificaRegione);
		
		JMenuItem mntmModificaTipologia = new JMenuItem("Modifica Tipologia");
		mntmModificaTipologia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabTipologie tabTip;
				try {
					tabTip = new tabTipologie();
					tabTip.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnModifica.add(mntmModificaTipologia);
		
		JMenuItem mntmModificaVigneto = new JMenuItem("Modifica Vigneto");
		mntmModificaVigneto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabVigneti tabVigne;
				
				try {
					tabVigne = new TabVigneti();
					tabVigne.setVisible(true);				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			
		});
		mnModifica.add(mntmModificaVigneto);
		
		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		JMenuItem mntmHelp = new JMenuItem("help!");
		mntmHelp.setPressedIcon(new ImageIcon(tabProdotti.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		mnInfo.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setBounds(12, 48, 1275, 279);
		contentPane.add(scrollPane);
//		Object[][] appoggio = tableRiempi();
		table = new JTable(){
			 boolean [] CanEdit =  new  boolean [] {  false	,  true , true, true ,  true ,  false , false , false ,  false,true,true,false };
			public boolean isCellEditable(int data, int columns){
				return CanEdit [ columns];
			}
			
			public Component prepareRenderer(TableCellRenderer r,int data,int columns){
				Component c = super.prepareRenderer(r, data, columns);
				if(data%2==0)
					c.setBackground(Color.white);
				else
					c.setBackground(new Color(239,247,255)/*Color.getHSBColor(0.857f, 0.800f, 0.480f)*/);
				if(isCellSelected(data, columns))
					c.setBackground(Color.CYAN);
				return c;
			}
			
			
		};
		
		
	
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=table.getSelectedRow();
				if(row>=0 && Integer.parseInt(  (String) table.getValueAt(row, 11))!=0){
					int id=Integer.parseInt(  (String) table.getValueAt(row, 11));
					
					tabComboProduttore.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								if(tabComboProduttore.getToolTipText().toString()!=null){
									table.setValueAt(RegioneDAO.getCasaRegione(CasaDAO.getId(tabComboProduttore.getToolTipText().toString())), table.getSelectedRow(),4);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				
				
				try {
					
					dettaglioProdotto(id);
					app = ProdottoDAO.getProdotto(id);
					labelId.setText(String.format("%d", app.getIdProdotto()));
					textModGradi.setText(String.format("%.2f",app.getGradi()));
					textModAnno.setText(String.format("%d", app.getAnno()));
					textModNome.setText(app.getNome());
					textModCibi.setText(app.getCibi());
					textModVendita.setText(String.format("%.2f", app.getPrezzo()));
					textModAcquisto.setText(String.format("%.2f", app.getPrezzoInterno()));
					//tabella Entrate
					String[] columns= new String[] {"Entrate", "Data"};
					DefaultTableModel tModel =new DefaultTableModel();	
					String [][] data=popolaEntrateTab(id);
					tModel.setDataVector(data, columns);
					tableEntrate.setModel(tModel);
								
					
					//tabella USCITE
					String []column=new String[]{"Uscite","Data"};
					String [][]dat=popolaUsciteTab(id);
					tableUscite.setModel(new DefaultTableModel(dat,column));
					
					comboModCasa.setSelectedItem(app.getProdCasa().getNome());
					comboModTipo.setSelectedItem(app.getTipo().getTipo());
					comboModVigneto.setSelectedItem(app.getUvaglio().getNome());
					 lblModErrore.setText("");
					if(app.getFornitore().getNome()!=null)
						comboModFornitore.setSelectedItem(app.getFornitore().getNome());
					else
						comboModFornitore.setSelectedIndex(0);			
					
					int entrata=calcolaEntrate(EntrateDAO.getEntrate(id));
					int uscite=calcolaUscite(UsciteDAO.getUscite(id));
					float prezzoEn=0;
					float prezzoUs=0;
					
					String lbl="<html>En: "+entrata+" Prezzo: ";
					boolean testNull=false;
					
					if(comboModFornitore.getSelectedItem().equals("Nessuna selezione")){
						lbl=lbl+" nullo";
						testNull=true;
					}
					if(!textModAcquisto.getText().toString().equals("")){
						prezzoEn=Float.parseFloat(textModAcquisto.getText().toString().replace(",", "."));
						lbl=lbl+ String.format("%.2f Eur", prezzoEn)+"   tot: "+String.format("%.2f", prezzoEn*entrata);
						
					}				
					lbl=lbl+"<BR/>Us: "+uscite+" Prezzo: ";
					if(!textModVendita.getText().equals("") || !textModVendita.getText().equals("0,00")){
						prezzoUs=Float.parseFloat(textModVendita.getText().toString().replace(",", "."));
						lbl=lbl+ String.format("%.2f Eur", prezzoUs)+"   tot: "+String.format("%.2f", prezzoUs*uscite);
						
					}	
					else
						testNull=true;
					
					lbl=lbl+"<BR/> guadagno totale: ";
					if(testNull){
						lbl+="nullo";
					}
					else{
						if((prezzoUs*uscite)-(prezzoEn*entrata)<0)
							lbl+="<font color='#DC143C'>"+String.format("%.2f", (prezzoUs*uscite)-(prezzoEn*entrata))+"</font>";
						else
							lbl+="<font color='#0000FF'>"+String.format("%.2f", (prezzoUs*uscite)-(prezzoEn*entrata))+"</font>";
					}
					lbl+="Quantita deposito:  "+String.format("%d",entrata-uscite);
					lbl+="</html>";
				lblSomma.setText(lbl);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				}
			}
		});
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		tableModelApp=new DefaultTableModel();
		tableModelCon=new DefaultTableModel();
		tableModel=new DefaultTableModel();
		tableModelCon.setDataVector(Data, columns);
		tableModel.setDataVector(Data, columns);
		tableModelApp.setDataVector(new Object[0][11], columns);
		tableCos.setModel(tableModelCon);
		tableApp.setModel(tableModelApp);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setModel(tableModel);
		
		
		
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(tabComboVigneto));
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(tabComboProduttore));
		table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(tabComboTipologia));
		scrollPane.setViewportView(table);
		table.setRowSorter(new TableRowSorter<>(tableModel));
		
		TableColumn col0=new TableColumn();
		col0=table.getColumnModel().getColumn(0);
		col0.setMinWidth(130);
		col0.setMaxWidth(0);
		
		TableColumn col4=new TableColumn();
		col4=table.getColumnModel().getColumn(4);
		col4.setMinWidth(130);
		col4.setMaxWidth(0);
		
		
		TableColumn col11=new TableColumn();
		col11=table.getColumnModel().getColumn(11);
		col11.setMinWidth(35);
		col11.setMaxWidth(0);
		
		TableColumn col10=new TableColumn();
		col10=table.getColumnModel().getColumn(10);
		col10.setMinWidth(35);
		col10.setMaxWidth(0);
		
		TableColumn col8=new TableColumn();
		col8=table.getColumnModel().getColumn(8);
		col8.setMinWidth(45);
		col8.setMaxWidth(0);
		
		TableColumn col7=new TableColumn();
		col7=table.getColumnModel().getColumn(7);
		col7.setMinWidth(45);
		col7.setMaxWidth(0);
		
		TableColumn col6=new TableColumn();
		col6=table.getColumnModel().getColumn(6);
		col6.setMinWidth(45);
		col6.setMaxWidth(0);
		
		TableColumn col5=new TableColumn();
		col5=table.getColumnModel().getColumn(5);
		col5.setMinWidth(45);
		col5.setMaxWidth(0);
		
		TableColumn col9=new TableColumn();
		col9=table.getColumnModel().getColumn(9);
		col9.setMinWidth(45);
		col9.setMaxWidth(0);
		
		
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		tabbedPane.setBounds(12, 330, 1275, 269);
		contentPane.add(tabbedPane);
		
		
		
		panel = new JPanel();
		tabbedPane.addTab("Ricerca Avanzata", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Etichetta:");
		lblNewLabel.setBounds(46, 45, 74, 14);
		panel.add(lblNewLabel);
		
		textNome = new JTextField();
		textNome.setBounds(46, 71, 203, 20);
		panel.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblVigneto = new JLabel("Vigneto");
		lblVigneto.setBounds(46, 102, 46, 14);
		panel.add(lblVigneto);
		
		final JComboBox comboBoxVigneto = new JComboBox(utile.getComboVigneto());
	
		comboBoxVigneto.setBounds(46, 127, 203, 20);
		panel.add(comboBoxVigneto);
		
		panel.add(comboBoxVigneto);
		final JComboBox comboBoxCasa = new JComboBox(utile.getComboCasa());
		comboBoxCasa.setBounds(46, 183, 203, 20);
		panel.add(comboBoxCasa);
		
				
				lblNewLabel_1 = new JLabel("Produttore");
				lblNewLabel_1.setBounds(46, 158, 74, 14);
				panel.add(lblNewLabel_1);
				
				JLabel lblRegione = new JLabel("Regione:");
				lblRegione.setBounds(314, 102, 59, 14);
				panel.add(lblRegione);
				
				final JComboBox comboRegione = new JComboBox(utile.getComboRegione());
				comboRegione.setBounds(314, 127, 233, 20);
				panel.add(comboRegione);
				
				JLabel lblTipologia = new JLabel("Tipologia:");
				lblTipologia.setBounds(314, 158, 59, 14);
				panel.add(lblTipologia);
				
				final JComboBox comboBoxTipologia = new JComboBox(utile.getComboTipologia());
				comboBoxTipologia.setBounds(314, 183, 233, 20);
				panel.add(comboBoxTipologia);
				
				JButton btnNewButton = new JButton("RESET");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						textNome.setText("");
						textId.setText("");
						comboBoxCasa.setSelectedIndex(0);
						comboBoxTipologia.setSelectedIndex(0);
						comboBoxVigneto.setSelectedIndex(0);
						comboRegione.setSelectedIndex(0);
					}
				});
				btnNewButton.setBounds(759, 154, 116, 49);
				panel.add(btnNewButton);
				
				final JButton btnCerca = new JButton("Cerca");
				btnCerca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						ricAvaCont=true;
						if(!textId.getText().equals("")){
							ricercaAvanzata(textId.getText(),11);
						}
						
						if(!textNome.getText().equals("")){
							ricercaAvanzata(textNome.getText(),2);
						}
												
						if(!comboBoxTipologia.getSelectedItem().toString().equals("Nessuna selezione")){
							ricercaAvanzata(comboBoxTipologia.getSelectedItem().toString(),4);
						}
						
						if(!comboBoxVigneto.getSelectedItem().toString().equals("Nessuna selezione")){
							ricercaAvanzata(comboBoxVigneto.getSelectedItem().toString(),3);
						}
						
						if(!comboRegione.getSelectedItem().toString().equals("Nessuna selezione")){
							ricercaAvanzata(comboRegione.getSelectedItem().toString(),0);
						}
						
						if(!comboBoxCasa.getSelectedItem().toString().equals("Nessuna selezione")){
							ricercaAvanzata(comboBoxCasa.getSelectedItem().toString(),1);
						}
						
						

					}
				});
				btnCerca.setBounds(575, 154, 174, 49);
				panel.add(btnCerca);
				
				JLabel lblId_1 = new JLabel("ID");
				lblId_1.setBounds(317, 45, 56, 16);
				panel.add(lblId_1);
				
				textId = new JTextField();
				textId.setBounds(314, 71, 59, 22);
				panel.add(textId);
				textId.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Modifica", null, panel_2, null);
		panel_2.setLayout(null);
		
		
		
		lblModErrore.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 13));
		lblModErrore.setForeground(Color.RED);
		lblModErrore.setBounds(10, 180, 489, 14);
		panel_2.add(lblModErrore);
		
		
		JButton btnModifica = new JButton("Salva Modifiche");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(app!=null){
				if(!textModNome.getText().equals(""))
					app.setNome(textModNome.getText());
				if(!textModCibi.getText().equals(""))
					app.setCibi(textModCibi.getText());
				if(!textModVendita.getText().equals(""))
					app.setPrezzo(Float.parseFloat(metodUtils.convertStringFloat( textModVendita.getText())));
				if(comboModFornitore.getSelectedIndex()>=0){
					app.getFornitore().setNome(comboModFornitore.getSelectedItem().toString());	
				}
				if(!textModAcquisto.getText().equals(""))
					app.setPrezzoInterno(Float.parseFloat(metodUtils.convertStringFloat(textModAcquisto.getText())));
				
				if(!textModAnno.getText().equals("")&&utile.isNumber(textModAnno.getText())){
					app.setAnno(Integer.parseInt(textModAnno.getText()));
				}
				if(!textModGradi.getText().equals("")&&utile.isNumber(textModGradi.getText())){
					app.setGradi(Float.parseFloat(textModGradi.getText()));
				}
				
				if(comboModCasa.getSelectedIndex()>0)
					app.getProdCasa().setNome(comboModCasa.getSelectedItem().toString());
				if(comboModTipo.getSelectedIndex()>0){
					app.getTipo().setTipo(comboModTipo.getSelectedItem().toString());
				}
				if(comboModVigneto.getSelectedIndex()>0)
					app.getUvaglio().setNome(comboModVigneto.getSelectedItem().toString());
				
				app.setIdProdotto(Integer.parseInt(labelId.getText()));
				try {
					if(ProdottoDAO.modificaProdotto(app))
						lblModErrore.setText("Modifica Salvata");
					else
						lblModErrore.setText("ho un problema nel salvataggio");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else
				lblModErrore.setText("SELEZIONARE UN PRODOTTO DALLA TABELLA");
				
			}
		});
		btnModifica.setBounds(672, 36, 162, 23);
		panel_2.add(btnModifica);
		
		JLabel lblNome = new JLabel("Eichetta:");
		lblNome.setBounds(10, 11, 59, 14);
		panel_2.add(lblNome);
		
		textModNome = new JTextField();
		textModNome.setBounds(10, 37, 197, 20);
		panel_2.add(textModNome);
		textModNome.setColumns(10);
		
		JLabel lblCasa = new JLabel("Produttore:");
		lblCasa.setBounds(10, 68, 99, 14);
		panel_2.add(lblCasa);
		
		comboModCasa = new JComboBox(utile.getComboCasa());
		comboModCasa.setBounds(10, 89, 197, 20);
		panel_2.add(comboModCasa);
		
		JLabel lblTipologia_1 = new JLabel("Tipologia");
		lblTipologia_1.setBounds(10, 120, 79, 14);
		panel_2.add(lblTipologia_1);
		
		comboModTipo = new JComboBox(utile.getComboTipologia());
		comboModTipo.setBounds(10, 138, 197, 20);
		panel_2.add(comboModTipo);
		
		JLabel lblCibi_1 = new JLabel("Cibi:");
		lblCibi_1.setBounds(241, 11, 46, 14);
		panel_2.add(lblCibi_1);
		
		textModCibi = new JTextField();
		textModCibi.setBounds(241, 37, 258, 20);
		panel_2.add(textModCibi);
		textModCibi.setColumns(10);
		
		JLabel lblAcquisto = new JLabel("Acquisto:");
		lblAcquisto.setBounds(417, 120, 66, 14);
		panel_2.add(lblAcquisto);
		
		JLabel lblVendita = new JLabel("Vendita:");
		lblVendita.setBounds(415, 71, 68, 14);
		panel_2.add(lblVendita);
		
		textModAcquisto = new JTextField();
		textModAcquisto.setBounds(415, 141, 86, 20);
		panel_2.add(textModAcquisto);
		textModAcquisto.setColumns(10);
		
		textModVendita = new JTextField();
		textModVendita.setColumns(10);
		textModVendita.setBounds(413, 89, 86, 20);
		panel_2.add(textModVendita);
		
		JLabel lblVigneto_1 = new JLabel("Vigneto:");
		lblVigneto_1.setBounds(241, 68, 110, 14);
		panel_2.add(lblVigneto_1);
		
		 comboModVigneto = new JComboBox(utile.getComboVigneto());
		comboModVigneto.setBounds(241, 89, 162, 20);
		panel_2.add(comboModVigneto);
		
		JLabel lblFornitore = new JLabel("Agenzia:");
		lblFornitore.setBounds(241, 120, 86, 14);
		panel_2.add(lblFornitore);
		
		 
		comboModFornitore.setBounds(241, 138, 162, 20);
		panel_2.add(comboModFornitore);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(672, 11, 46, 14);
		panel_2.add(lblId);
		
		labelId = new JLabel("...");
		labelId.setBounds(690, 11, 46, 14);
		panel_2.add(labelId);
		
		JButton btnCancella = new JButton("cancella");
		btnCancella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(app!=null){
					int row=table.getSelectedRow();
					try {
						
						 int response = JOptionPane.showConfirmDialog(null, "vuoi cancellare  "+app.getNome()+"?", "cancella",
						     JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						 if (response == JOptionPane.NO_OPTION) {
						   
						 } else if (response == JOptionPane.YES_OPTION) {
							 ProdottoDAO.eliminaProdotto(app);
								tableModel.removeRow(row);	
						 } else if (response == JOptionPane.CLOSED_OPTION) {
							
						 }
						
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
				else
				{
					lblModErrore.setText("Selezionare un prodotto dalla Tabella!");
				}
			}
		});
		btnCancella.setForeground(Color.RED);
		btnCancella.setBounds(672, 137, 162, 23);
		panel_2.add(btnCancella);
		
		JButton btnModificaAltro = new JButton("modifica altro");
		btnModificaAltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modProd mP;
				
				if(app!=null)
					try {
						mP=new modProd(app.getIdProdotto());
						mP.setVisible(true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(textNome, "errore", e.getMessage(), 0);
						e.printStackTrace();
					}
				else
					lblModErrore.setText("Selezionare un prodotto dalla tabella");
			}
		});
		btnModificaAltro.setBounds(672, 88, 162, 23);
		panel_2.add(btnModificaAltro);
		
		JButton btnSalvaTutto = new JButton("Salva modifiche Tabella");
		btnSalvaTutto.setBounds(846, 35, 203, 25);
		panel_2.add(btnSalvaTutto);
		
		JLabel lblAnno = new JLabel("Anno:");
		lblAnno.setBounds(527, 10, 56, 16);
		panel_2.add(lblAnno);
		
		JLabel lblGradi = new JLabel("Gradi:");
		lblGradi.setBounds(527, 67, 56, 16);
		panel_2.add(lblGradi);
		
		textModAnno = new JTextField();
		textModAnno.setBounds(527, 37, 116, 22);
		panel_2.add(textModAnno);
		textModAnno.setColumns(10);
		
		textModGradi = new JTextField();
		textModGradi.setColumns(10);
		textModGradi.setBounds(527, 88, 116, 22);
		panel_2.add(textModGradi);
		btnSalvaTutto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				
						salvaModificaTab();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Entrate/Uscite", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.scrollbar);
		panel_5.setBounds(816, 13, 442, 223);
		panel_3.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblDettagli = new JLabel("Dettagli");
		lblDettagli.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblDettagli.setBounds(210, 13, 99, 16);
		panel_5.add(lblDettagli);
		
		JButton btnGrafico = new JButton("Grafico prodotto");
		btnGrafico.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						GraficoProdotto.visGrafico(app.getIdProdotto());	
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					JOptionPane.showMessageDialog(null, "il grafico non è al momento disponibile");
				
			}
		});
		btnGrafico.setBounds(3, 39, 131, 25);
		panel_5.add(btnGrafico);
		
		lblSomma = new JLabel("");
		lblSomma.setBounds(146, 41, 281, 139);
		panel_5.add(lblSomma);
		
		JButton btnDettagli = new JButton("Dettagli prodotto");
		btnDettagli.setBounds(3, 70, 131, 25);
		panel_5.add(btnDettagli);
		
		JButton btnDettagliAnnate = new JButton("Dettagli Annate");
		btnDettagliAnnate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(app!=null){
					int row=table.getSelectedRow();
					if(row>0){
							DetAnnate d;
							try {
								d = new DetAnnate(app);
								d.setVisible(true);	
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
											
					}
				}
				
				
			}
		});
		btnDettagliAnnate.setBounds(3, 101, 131, 25);
		panel_5.add(btnDettagliAnnate);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.scrollbar);
		panel_6.setBounds(12, 13, 386, 237);
		panel_3.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblEntrate = new JLabel("ENTRATE");
		lblEntrate.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblEntrate.setBounds(12, 13, 72, 16);
		panel_6.add(lblEntrate);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 34, 158, 150);
		panel_6.add(scrollPane_3);
		
		tableEntrate = new JTable(){
		public boolean isCellEditable(int data, int columns){
			return false;
		}};
		tableEntrate.setFillsViewportHeight(true);
//		tableEntrate.se
		tableEntrate.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Entrate", "data"
			}
		));
		tableEntrate.getColumnModel().getColumn(0).setPreferredWidth(45);
		tableEntrate.getColumnModel().getColumn(1).setPreferredWidth(105);
		scrollPane_3.setViewportView(tableEntrate);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(258, 14, 56, 16);
		panel_6.add(lblData);
		
		JLabel lblQuantita = new JLabel("quantita");
		lblQuantita.setBounds(245, 121, 56, 16);
		panel_6.add(lblQuantita);
		
		textQuantitaEntrate = new JTextField();
		textQuantitaEntrate.setBounds(251, 134, 30, 22);
		panel_6.add(textQuantitaEntrate);
		textQuantitaEntrate.setColumns(10);
		
		textQuantitaEntrate.setText("0");
		JButton btnIncreEn = new JButton("Incrementa");
		btnIncreEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a=Integer.parseInt(textQuantitaEntrate.getText())+1;
				textQuantitaEntrate.setText(String.format("%d",a));
				
			}
		});
		btnIncreEn.setBounds(221, 169, 117, 25);
		panel_6.add(btnIncreEn);
		
		JButton btnDecreEn = new JButton("Decrementa");
		btnDecreEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int a=Integer.parseInt(textQuantitaEntrate.getText());
			if(a>0)
				textQuantitaEntrate.setText(String.format("%d",a-1));
			
			}
		});
		btnDecreEn.setBounds(221, 197, 117, 25);
		panel_6.add(btnDecreEn);

		final JCheckBox chckEntSomma = new JCheckBox("Somma");
		chckEntSomma.setBounds(171, 133, 72, 25);
		panel_6.add(chckEntSomma);
		
		final JLabel lblErroreEntrate = new JLabel("");
		lblErroreEntrate.setForeground(Color.RED);
		lblErroreEntrate.setBounds(112, 222, 102, 16);
		panel_6.add(lblErroreEntrate);
		
		JButton btnSalvaEn = new JButton("Salva");
		btnSalvaEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(app==null){
					lblErroreEntrate.setText("Selziona prodotto!");
				}
				else{
					@SuppressWarnings("deprecation")
					Date data=new Date(dateChooser.getDate().getYear(), 
							dateChooser.getDate().getMonth(),dateChooser.getDate().getDate());
					entrate appoggio=new entrate();
					appoggio.setIdProdotto(app.getIdProdotto());
					appoggio.setData(data);
					appoggio.setQuantita(Integer.parseInt(textQuantitaEntrate.getText()));
					try {
						if(EntrateDAO.controlloData(appoggio))
							EntrateDAO.aggiornaEntrata(appoggio,chckEntSomma.isSelected());
						else
							EntrateDAO.setEntrata(appoggio);
						//tabella Entrate
						String[] columns= new String[] {"Entrate", "Data"};
						DefaultTableModel tModel =new DefaultTableModel();	
						String [][] data0=popolaEntrateTab(app.getIdProdotto());
						tModel.setDataVector(data0, columns);
						tableEntrate.setModel(tModel);
									
						
						//tabella USCITE
						String []column=new String[]{"Uscite","Data"};
						String [][]dat=popolaUsciteTab(app.getIdProdotto());
						tableUscite.setModel(new DefaultTableModel(dat,column));
						
					} catch ( Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnSalvaEn.setBounds(121, 198, 83, 22);
		panel_6.add(btnSalvaEn);
		
		JButton btnCancEn = new JButton("Cancella");
		btnCancEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row=tableEntrate.getSelectedRow();
				entrate appoggio=new entrate();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				appoggio.setIdProdotto(app.getIdProdotto());
				String appDat=tableEntrate.getValueAt(row, 1).toString();
				  java.util.Date utilDate = new java.util.Date();
				try {
					utilDate=formatter.parse(appDat);
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					appoggio.setData(sqlDate);
					EntrateDAO.eliminaEntrateProdotto(appoggio);
					
					//tabella Entrate
					String[] columns= new String[] {"Entrate", "Data"};
					DefaultTableModel tModel =new DefaultTableModel();	
					String [][] data=popolaEntrateTab(app.getIdProdotto());
					tModel.setDataVector(data, columns);
					tableEntrate.setModel(tModel);
								
					
					//tabella USCITE
					String []column=new String[]{"Uscite","Data"};
					String [][]dat=popolaUsciteTab(app.getIdProdotto());
					tableUscite.setModel(new DefaultTableModel(dat,column));
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnCancEn.setBounds(12, 197, 97, 25);
		panel_6.add(btnCancEn);
		Calendar c=Calendar.getInstance();
		
		textField = new JTextField();
		textField.setBounds(317, 134, 39, 22);
		panel_6.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Scontistica:");
		lblNewLabel_2.setBounds(308, 121, 66, 16);
		panel_6.add(lblNewLabel_2);
		
		JLabel label = new JLabel("%");
		label.setBounds(362, 129, 24, 33);
		panel_6.add(label);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("d-MM-yyyy");
		dateChooser.setCalendar( c.getInstance());
		dateChooser.setBounds(221, 47, 133, 25);
		panel_6.add(dateChooser);
		
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.scrollbar);
		panel_4.setBounds(410, 13, 394, 253);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblUscite = new JLabel("USCITE");
		lblUscite.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblUscite.setBounds(12, 13, 56, 16);
		panel_4.add(lblUscite);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setEnabled(false);
		scrollPane_4.setBounds(12, 34, 147, 150);
		panel_4.add(scrollPane_4);
		
		tableUscite = new JTable(){
			public boolean isCellEditable(int data, int columns){
				return false;
			}
		};
		tableUscite.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUscite.setFillsViewportHeight(true);
		tableUscite.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Uscite", "Data"
			}
		));
		scrollPane_4.setViewportView(tableUscite);
		
		JLabel label_1 = new JLabel("Data");
		label_1.setBounds(272, 14, 56, 16);
		panel_4.add(label_1);
		
		JLabel label_3 = new JLabel("quantita");
		label_3.setBounds(293, 105, 56, 16);
		panel_4.add(label_3);
		
		textQuantitaUscite = new JTextField();
		textQuantitaUscite.setColumns(10);
		textQuantitaUscite.setBounds(293, 122, 56, 22);
		panel_4.add(textQuantitaUscite);
		textQuantitaUscite.setText("0");
		JButton btnCancUs = new JButton("Cancella");
		btnCancUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=tableUscite.getSelectedRow();
				uscite appoggio=new uscite();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				appoggio.setIdProdotto(app.getIdProdotto());
				String appDat=tableUscite.getValueAt(row, 1).toString();
				  java.util.Date utilDate = new java.util.Date();
				try {
					utilDate=formatter.parse(appDat);
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
					appoggio.setData(sqlDate);
					UsciteDAO.eliminaUsciteProdotto(appoggio);
					
					//tabella Entrate
					String[] columns= new String[] {"Entrate", "Data"};
					DefaultTableModel tModel =new DefaultTableModel();	
					String [][] data=popolaEntrateTab(app.getIdProdotto());
					tModel.setDataVector(data, columns);
					tableEntrate.setModel(tModel);
								
					
					//tabella USCITE
					String []column=new String[]{"Uscite","Data"};
					String [][]dat=popolaUsciteTab(app.getIdProdotto());
					tableUscite.setModel(new DefaultTableModel(dat,column));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		btnCancUs.setBounds(12, 197, 97, 25);
		panel_4.add(btnCancUs);
		
		JButton btnIncreUs = new JButton("Incrementa");
		btnIncreUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=Integer.parseInt(textQuantitaUscite.getText())+1;
				textQuantitaUscite.setText(String.format("%d", a));
			}
		});
		btnIncreUs.setBounds(232, 157, 117, 25);
		panel_4.add(btnIncreUs);

		final JLabel lblErroreUscite = new JLabel("");
		lblErroreUscite.setForeground(Color.RED);
		lblErroreUscite.setBounds(115, 219, 108, 16);
		
		panel_4.add(lblErroreUscite);	
		
		JButton btnDecreUs = new JButton("Decrementa");
		btnDecreUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=Integer.parseInt(textQuantitaUscite.getText());
				if(a>0)
					textQuantitaUscite.setText(String.format("%d", a-1));
			}
		});
		btnDecreUs.setBounds(232, 195, 117, 25);
		panel_4.add(btnDecreUs);
		
		final JCheckBox checkUscSomma = new JCheckBox("Somma");
		checkUscSomma.setBounds(202, 121, 83, 25);
		panel_4.add(checkUscSomma);
		
		JButton btnSalvaUs = new JButton("Salva");
		btnSalvaUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(app==null){
					lblErroreUscite.setText("Seleziona prodotto");
				}
				else{
					@SuppressWarnings("deprecation")
					
					Date data=new Date(dateChooser_1.getDate().getYear(), 
							dateChooser_1.getDate().getMonth(),dateChooser_1.getDate().getDate());
					uscite appoggio=new uscite();
					appoggio.setIdProdotto(app.getIdProdotto());
					appoggio.setData(data);
					appoggio.setQuantita(Integer.parseInt(textQuantitaUscite.getText()));
					try {
						if(UsciteDAO.controlloData(appoggio))
							UsciteDAO.aggiornaUscite(appoggio,checkUscSomma.isSelected());
						else
							UsciteDAO.setUscita(appoggio);
						//tabella Entrate
						String[] columns= new String[] {"Entrate", "Data"};
						DefaultTableModel tModel =new DefaultTableModel();	
						String [][] data0=popolaEntrateTab(app.getIdProdotto());
						tModel.setDataVector(data0, columns);
						tableEntrate.setModel(tModel);
									
						
						//tabella USCITE
						String []column=new String[]{"Uscite","Data"};
						String [][]dat=popolaUsciteTab(app.getIdProdotto());
						tableUscite.setModel(new DefaultTableModel(dat,column));
					} catch ( Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnSalvaUs.setBounds(121, 198, 83, 22);
		panel_4.add(btnSalvaUs);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("d-MM-yyyy");
		dateChooser_1.setCalendar(c.getInstance());
		dateChooser_1.setBounds(221, 47, 128, 25);
		panel_4.add(dateChooser_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Dettagli Prodotto", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBounds(12, 13, 436, 216);
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblEtichetta = new JLabel("Etichetta:");
		lblEtichetta.setBounds(12, 13, 78, 16);
		panel_7.add(lblEtichetta);
		
		JLabel labelVigneto = new JLabel("Vigneto:");
		labelVigneto.setBounds(12, 63, 78, 16);
		panel_7.add(labelVigneto);
		
		JLabel lblProduttore = new JLabel("Produttore:");
		lblProduttore.setBounds(12, 116, 78, 16);
		panel_7.add(lblProduttore);
		
		JLabel lblRegione_1 = new JLabel("Regione:");
		lblRegione_1.setBounds(12, 168, 78, 16);
		panel_7.add(lblRegione_1);
		
		lblDetEtic = new JLabel("");
		lblDetEtic.setBounds(12, 37, 413, 16);
		panel_7.add(lblDetEtic);
		
		 labelDetVign = new JLabel("");
		 labelDetVign.setBounds(12, 85, 413, 16);
		 panel_7.add(labelDetVign);
		 
		  labeldetProduttore = new JLabel("");
		  labeldetProduttore.setBounds(12, 138, 413, 16);
		  panel_7.add(labeldetProduttore);
		  
		   labelDetReg = new JLabel("");
		   labelDetReg.setBounds(12, 190, 413, 16);
		   panel_7.add(labelDetReg);
		   
		   JPanel panel_8 = new JPanel();
		   panel_8.setBackground(SystemColor.info);
		   panel_8.setBounds(470, 13, 500, 213);
		   panel_1.add(panel_8);
		   panel_8.setLayout(null);
		   
		   JPanel panel_9 = new JPanel();
		   panel_9.setBackground(SystemColor.text);
		   panel_9.setBounds(12, 13, 85, 61);
		   panel_8.add(panel_9);
		   panel_9.setLayout(null);
		   
		   JLabel lblAnno_1 = new JLabel("Anno:");
		   lblAnno_1.setBounds(12, 13, 54, 16);
		   panel_9.add(lblAnno_1);
		   
		   labelDetAnno = new JLabel("");
		   labelDetAnno.setBounds(12, 35, 54, 16);
		   panel_9.add(labelDetAnno);
		   
		   JPanel panel_10 = new JPanel();
		   panel_10.setLayout(null);
		   panel_10.setBackground(new Color(255, 182, 193));
		   panel_10.setBounds(12, 80, 85, 61);
		   panel_8.add(panel_10);
		   
		   JLabel lblAcquisto_1 = new JLabel("Acquisto");
		   lblAcquisto_1.setBounds(10, 10, 54, 16);
		   panel_10.add(lblAcquisto_1);
		    labelDetAcqui = new JLabel("");
		   labelDetAcqui.setBounds(10, 32, 54, 16);
		   panel_10.add(labelDetAcqui);
		   
		   JPanel panel_11 = new JPanel();
		   panel_11.setLayout(null);
		   panel_11.setBackground(new Color(240, 255, 240));
		   panel_11.setBounds(12, 142, 85, 61);
		   panel_8.add(panel_11);
		   
		   JLabel lblVendita_1 = new JLabel("Vendita");
		   lblVendita_1.setBounds(10, 10, 54, 16);
		   panel_11.add(lblVendita_1);
		   
		   labelDetVendita = new JLabel("");
		   labelDetVendita.setBounds(10, 32, 54, 16);
		   panel_11.add(labelDetVendita);
		   
		   JLabel lblDetProdDescr = new JLabel("");
		   lblDetProdDescr.setBackground(Color.WHITE);
		   lblDetProdDescr.setBounds(118, 13, 370, 190);
		   panel_8.add(lblDetProdDescr);
		
		textRicercaVeloceNome = new JTextField();
		textRicercaVeloceNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
//				System.out.println(table.getRowCount());
			if(!textRicercaVeloceNome.getText().equals(""))
				ricerca(textRicercaVeloceNome.getText());
			}
		});
		textRicercaVeloceNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ricerca(textRicercaVeloceNome.getText());
			}
		});
		textRicercaVeloceNome.setBounds(140, 16, 149, 22);
		contentPane.add(textRicercaVeloceNome);
		textRicercaVeloceNome.setColumns(10);
		
		JLabel lblRicercaVeloce = new JLabel("Ricerca generale:");
		lblRicercaVeloce.setBounds(12, 19, 116, 16);
		contentPane.add(lblRicercaVeloce);
		
		lblNumEtic = new JLabel("Ci sono  "+tableModel.getRowCount()+"  etichette!");
		lblNumEtic.setBounds(1021, 14, 266, 26);
		contentPane.add(lblNumEtic);
		
	
		
		
	}
	
	
	public static void refreshTipologie() throws Exception{
		tabComboTipologia=new JComboBox(utile.getComboTipologia());
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(tabComboTipologia));
	}
	
	public static void refreshVigneto() throws Exception{
		tabComboVigneto=new JComboBox(utile.getComboVigneto());
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(tabComboVigneto));
	}
	
	public static void refreshProduttore() throws Exception{
		tabComboProduttore=new JComboBox(utile.getComboCasa());
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(tabComboProduttore));
	}
	
	private static Object[][] tableRiempi() throws Exception{
		final ProdottoDAO prodottoDao=new ProdottoDAO();
		FornitoreDAO fornitoreDao=new FornitoreDAO();
		ArrayList<prodotto> lista= prodottoDao.getListaProdotto();
		/// hahahahahahahaha
		Collections.sort(lista);
		Object[][] data=new String[lista.size()][12];
		try {
			int a=0;	
			for (prodotto e : lista){
				
				data[a][3]=e.getUvaglio().getNome();
				data[a][2]=e.getNome();
				data[a][5]=String.format("%d", e.getAnno());
				data[a][1]=e.getProdCasa().getNome();
				data[a][10]=String.format("%.2f",e.getPrezzo());
				data[a][4]=e.getTipo().getTipo();
				if(e.getProdCasa().getNome()!=null)
					data[a][0]=e.getProdCasa().getRegione().getNome();
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
	
	
	private  String[][] popolaEntrateTab(int id) throws Exception{
		String[][] data ;
		ArrayList<entrate> lista=EntrateDAO.getEntrate(id);
		data=new String[lista.size()][2];
		
		try {
			int a=0;	
			for (entrate e : lista){
				data[a][0]=String.format("%d", e.getQuantita());
				if(e.getData()!=null){
					Format formatter=new SimpleDateFormat("dd-MM-yyyy");
					data[a][1]=formatter.format(e.getData());				
				}
				else{
					data[a][1]="";					
				}
				a++;
			}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return data;
	}
	
	private  String[][] popolaUsciteTab(int id) throws Exception{
		String[][] data ;
		ArrayList<uscite> lista=UsciteDAO.getUscite(id);
		data=new String[lista.size()][2];
		
		try {
			int a=0;	
			for (uscite e : lista){
				data[a][0]=String.format("%d", e.getQuantita());
				if(e.getData()!=null){
					Format formatter=new SimpleDateFormat("dd-MM-yyyy");
					data[a][1]=formatter.format(e.getData());					
				}
				else{
					data[a][1]="";					
				}
				a++;
			}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return data;
	}
	
	
	
	
	
	
	
	private  Object[][] ricercaProdotto() throws Exception{
		Object[][] data;
		ArrayList<prodotto> lista=new ArrayList<prodotto>();
		ProdottoDAO prodottoDao=new ProdottoDAO();
		FornitoreDAO fornitoreDao=new FornitoreDAO();
		lista = prodottoDao.getListaProdotto(ricercaProdotto,true,true);
		data=new String[lista.size()][12];
		try {
			int a=0;	
			for (prodotto e : lista){
				
				data[a][3]=e.getUvaglio().getNome();
				data[a][2]=e.getNome();
				data[a][5]=String.format("%d",e.getAnno());
				data[a][1]=e.getProdCasa().getNome();
				data[a][10]=String.format("%.2f",e.getPrezzo());
				data[a][4]=e.getTipo().getTipo();
				if(e.getProdCasa().getNome()!=null)
					data[a][0]=e.getProdCasa().getRegione().getNome();
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
	
	
	private boolean salvaModificaTab() throws Exception{
		boolean ritorno=false;
		int n=0;
		int f=tableModelCon.getRowCount();
		int idModificati[]=new int[f];
		for(int row=0;row<tableModelCon.getRowCount();row++){
			 
			for(int ROW=0;ROW<tableModelApp.getRowCount();ROW++)
			{
				 ritorno=false;
				if(tableModelCon.getValueAt(row, 11).equals(tableModelApp.getValueAt(ROW, 11))){
					if (tableModelApp.getValueAt(ROW, 3)!=null && tableModelCon.getValueAt(row, 3)!=null){
						if(!tableModelCon.getValueAt(row, 3).equals(tableModelApp.getValueAt(ROW, 3)))
							ritorno= true;						
					}
					if(tableModelCon.getValueAt(row, 1)!=null){
						if(tableModelApp.getValueAt(ROW, 1)!=null){
							if(!tableModelCon.getValueAt(row, 1).equals(tableModelApp.getValueAt(ROW, 1)))
								ritorno =true;													
						}
						else
							ritorno=true;
					}
					else
					{
						if(tableModelApp.getValueAt(ROW, 1)!=null){
							ritorno=true;
						}
					}
					if(tableModelCon.getValueAt(row, 2)!=null&&tableModelApp.getValueAt(ROW, 2)!=null){						
						if(!tableModelCon.getValueAt(row, 2).equals(tableModelApp.getValueAt(ROW, 2)))
							ritorno =true;
					}
					if(tableModelCon.getValueAt(row, 4)!=null){
						if(tableModelApp.getValueAt(ROW, 4)!=null){
							if(!tableModelCon.getValueAt(row, 4).equals(tableModelApp.getValueAt(ROW, 4)))
								ritorno =true;													
						}
						else
							ritorno=true;
					}
					else
					{
						if(tableModelApp.getValueAt(ROW, 4)!=null){
							ritorno=true;
						}
					}
					if(tableModelCon.getValueAt(row, 10)!=null && tableModelApp.getValueAt(ROW, 10)!=null){
						if(!tableModelCon.getValueAt(row, 10).equals(tableModelApp.getValueAt(ROW, 10)))
							ritorno =true;
					}
					if(tableModelCon.getValueAt(row, 9)!=null && tableModelApp.getValueAt(ROW, 9)!=null){
						if(!tableModelCon.getValueAt(row, 9).equals(tableModelApp.getValueAt(ROW, 9)))
							ritorno =true;						
					}
				
					if(ritorno){
						prodotto salMod=new prodotto();
						
						if(tableModelApp.getValueAt(ROW, 11)!=null){
							salMod.setIdProdotto(Integer.parseInt((String)tableModelApp.getValueAt(ROW, 11)));
							idModificati[n]=Integer.parseInt((String)tableModelApp.getValueAt(ROW, 11));
							n++;							
						}
						if(tableModelApp.getValueAt(ROW, 2)!=null)
							salMod.setNome((String)tableModelApp.getValueAt(ROW, 2));
						if(tableModelApp.getValueAt(ROW, 1)!=null)
							salMod.getProdCasa().setNome((String)tableModelApp.getValueAt(ROW, 1));
						if(tableModelApp.getValueAt(ROW, 3)!=null)
							salMod.getUvaglio().setNome((String)tableModelApp.getValueAt(ROW, 3));
						if(tableModelApp.getValueAt(ROW, 4)!=null)
							salMod.getTipo().setTipo((String)tableModelApp.getValueAt(ROW, 4));
						if(tableModelApp.getValueAt(ROW, 9)!=null)
							salMod.setPrezzoInterno(Float.parseFloat( (String) tableModelApp.getValueAt(ROW, 9).toString().replace(',', '.')));
						if(tableModelApp.getValueAt(ROW, 10)!=null)
							salMod.setPrezzo(Float.parseFloat( (String) tableModelApp.getValueAt(ROW, 10).toString().replace(',', '.')));
						ProdottoDAO.modificaProdottoTab(salMod);
					}
				}
			}
			for(int ROW=0;ROW<tableModel.getRowCount();ROW++)
			{
				 ritorno=false;
				if(tableModelCon.getValueAt(row, 11).equals(tableModel.getValueAt(ROW, 11))){
					if(tableModelCon.getValueAt(row, 1)!=null){
						if(tableModel.getValueAt(ROW, 1)!=null){
							if(!tableModelCon.getValueAt(row, 1).equals(tableModel.getValueAt(ROW, 1)))
								ritorno =true;													
						}
						else
							ritorno=true;
					}
					else
					{
						if(tableModel.getValueAt(ROW, 1)!=null){
							ritorno=true;
						}
					}
					if(tableModelCon.getValueAt(row, 2)!=null && tableModel.getValueAt(ROW, 2)!=null){						
						if(!tableModelCon.getValueAt(row, 2).equals(tableModel.getValueAt(ROW, 2)))
							ritorno =true;
					}
					if(tableModelCon.getValueAt(row, 3)!=null && tableModel.getValueAt(ROW, 3)!=null){
						if(!tableModelCon.getValueAt(row, 3).equals(tableModel.getValueAt(ROW, 3)))
							ritorno =true;						
					}
					if(tableModelCon.getValueAt(row, 4)!=null){
						if(tableModel.getValueAt(ROW, 4)!=null){
							if(!tableModelCon.getValueAt(row, 4).equals(tableModel.getValueAt(ROW, 4)))
								ritorno =true;													
						}
						else
							ritorno=true;
					}
					else
					{
						if(tableModel.getValueAt(ROW, 4)!=null){
							ritorno=true;
						}
					}

					if(tableModelCon.getValueAt(row, 9)!=null && tableModel.getValueAt(ROW, 9)!=null){
						if(!tableModelCon.getValueAt(row, 9).equals(tableModel.getValueAt(ROW, 9)))
							ritorno =true;						
					}
					
					if((tableModelCon.getValueAt(row, 10)!=null && tableModel.getValueAt(ROW, 10)!=null)){
						if(!tableModelCon.getValueAt(row, 10).equals(tableModel.getValueAt(ROW, 10)))
							ritorno =true;
					}
					if(ritorno){
						prodotto salMod=new prodotto();
						if(tableModel.getValueAt(ROW, 11)!=null){
							salMod.setIdProdotto(Integer.parseInt((String)tableModel.getValueAt(ROW, 11)));
							idModificati[n]=Integer.parseInt((String)tableModel.getValueAt(ROW, 11));
							n++;
							
						}
						if(tableModel.getValueAt(ROW, 2)!=null)
							salMod.setNome((String)tableModel.getValueAt(ROW, 2));
						if(tableModel.getValueAt(ROW, 4)!=null)
							salMod.getTipo().setTipo((String)tableModel.getValueAt(ROW, 4));
						if(tableModel.getValueAt(ROW, 1)!=null)
							salMod.getProdCasa().setNome((String)tableModel.getValueAt(ROW, 1));
						if(tableModel.getValueAt(ROW, 3)!=null)
							salMod.getUvaglio().setNome((String)tableModel.getValueAt(ROW, 3));
						if(tableModel.getValueAt(ROW, 9)!=null)
							salMod.setPrezzoInterno(Float.parseFloat( (String) tableModel.getValueAt(ROW, 9).toString().replace(',', '.')));
						if(tableModel.getValueAt(ROW, 10)!=null)
							salMod.setPrezzo(Float.parseFloat( (String) tableModel.getValueAt(ROW, 10).toString().replace(',', '.')));
						ProdottoDAO.modificaProdottoTab(salMod);
					}
				}
		}
		
	}
//	enoteca.refresh();
	idMod=idModificati;
	controlloSalvataggio=true;
	return ritorno;
}
	

	
	private void preRicerca(){
		if(tableModelApp.getRowCount()>0){				
			for(int row=tableModelApp.getRowCount()-1;row>=0;row--){
				String[] appoggio={(String) tableApp.getValueAt(row, 0),
					(String) tableApp.getValueAt(row, 1),(String) tableApp.getValueAt(row, 2),
					(String) tableApp.getValueAt(row, 3),(String) tableApp.getValueAt(row, 4),
					(String) tableApp.getValueAt(row, 5),(String) tableApp.getValueAt(row, 6),
					(String) tableApp.getValueAt(row, 7),(String) tableApp.getValueAt(row, 8),
					(String) tableApp.getValueAt(row, 9),(String) tableApp.getValueAt(row, 10),
					(String) tableApp.getValueAt(row, 11)
				};
				boolean controllo=false;
				for(int a=tableModel.getRowCount()-1;a>=0;a--){
					if(tableModel.getValueAt(a, 11).equals(appoggio[11])){
						controllo=true;					
					}
				}
				if(!controllo)
					tableModel.addRow(appoggio);
				tableModelApp.removeRow(row);
			}
		}
	}
	
	
	public void ricercaAvanzata(String nome,int index){
		if(tableModelApp.getRowCount()>0 && ricAvaCont){				
			for(int row=tableModelApp.getRowCount()-1;row>=0;row--){
				String[] appoggio={(String) tableApp.getValueAt(row, 0),
					(String) tableApp.getValueAt(row, 1),(String) tableApp.getValueAt(row, 2),
					(String) tableApp.getValueAt(row, 3),(String) tableApp.getValueAt(row, 4),
					(String) tableApp.getValueAt(row, 5),(String) tableApp.getValueAt(row, 6),
					(String) tableApp.getValueAt(row, 7),(String) tableApp.getValueAt(row, 8),
					(String) tableApp.getValueAt(row, 9),(String) tableApp.getValueAt(row, 10)							
				};
				boolean controllo=false;
				for(int a=tableModel.getRowCount()-1;a>=0;a--){
					if(tableModel.getValueAt(a, 10).equals(appoggio[10])){
						controllo=true;					
					}
				}
				if(!controllo)
					tableModel.addRow(appoggio);
				tableModelApp.removeRow(row);
			}
		}
		ricAvaCont=false;
			String ricerca=new String();
			ricerca=nome;
			if(tableModel.getRowCount()>0){
				int a=0;
			for(int row=table.getRowCount()-1;row>=0;row--){
				boolean test=true;
				
//				for(int col=0;col<table.getColumnCount();col++){
					if(test){
						
						String next=new String();
						if(tableModel.getValueAt(row, index)!=null){
							next=(String) tableModel.getValueAt(row, index);
						}
						//che cazzo ho fatto? bho! non funziona un cazzo! non entra dentro al ciclo controllare la condizione!
						if(next.toLowerCase().indexOf(ricerca.toLowerCase())==-1 /*&& (index!=10 ||next.equals(ricerca) )*/){
							a++;
							String[] appoggio={(String) tableModel.getValueAt(row, 0),
								(String) tableModel.getValueAt(row, 1),(String) tableModel.getValueAt(row, 2),
								(String) tableModel.getValueAt(row, 3),(String) tableModel.getValueAt(row, 4),
								(String) tableModel.getValueAt(row, 5),(String) tableModel.getValueAt(row, 6),
								(String) tableModel.getValueAt(row, 7),(String) tableModel.getValueAt(row, 8),
								(String) tableModel.getValueAt(row, 9),(String) tableModel.getValueAt(row, 10),
								(String) tableModel.getValueAt(row, 11)
							};
							
							tableModelApp.addRow(appoggio);
							tableModel.removeRow(row);
							test=false;	
						}
					}
//				}
			}
			}
		
	}
	
	
	
	
	public void ricerca(String nome){
		if(tableModelApp.getRowCount()>0){				
			for(int row=tableModelApp.getRowCount()-1;row>=0;row--){
				String[] appoggio={(String) tableApp.getValueAt(row, 0),
					(String) tableApp.getValueAt(row, 1),(String) tableApp.getValueAt(row, 2),
					(String) tableApp.getValueAt(row, 3),(String) tableApp.getValueAt(row, 4),
					(String) tableApp.getValueAt(row, 5),(String) tableApp.getValueAt(row, 6),
					(String) tableApp.getValueAt(row, 7),(String) tableApp.getValueAt(row, 8),
					(String) tableApp.getValueAt(row, 9),(String) tableApp.getValueAt(row, 10),
					(String) tableApp.getValueAt(row, 11)
				};
				boolean controllo=false;
				for(int a=tableModel.getRowCount()-1;a>=0;a--){
					if(tableModel.getValueAt(a, 11).equals(appoggio[11])){
						controllo=true;					
					}
				}
				if(!controllo){
					tableModel.addRow(appoggio);
				}
				
				tableModelApp.removeRow(row);
			}
		} 
			
			
			
			String ricerca=new String();
			ricerca=nome;
			DefaultTableModel tbMd=new DefaultTableModel();
			if(tableModel.getRowCount()>0){
//				System.out.println(table.getRowCount());
			for(int row=tableModel.getRowCount()-1;row>=0;row--){
				boolean test=true;
				int a=0;
//				System.out.println(table.getColumnCount());
				for(int col=0;col<tableModel.getColumnCount();col++){
					if(test){
						
						String next=new String();
						if(tableModel.getValueAt(row, col)!=null){
							next=(String) tableModel.getValueAt(row, col);
						}
//						System.out.println(next.indexOf(ricerca));
						//entra solo se lo trova.....
						if(next.toLowerCase().indexOf(ricerca.toLowerCase())==-1){
							a++;
						}
						if(a==12)//a==12 funziona ma non so per quale assurdo motivo!ilbello è che non ho copiato niente!
						{
							String[] appoggio={(String) tableModel.getValueAt(row, 0),
								(String) tableModel.getValueAt(row, 1),(String) tableModel.getValueAt(row, 2),
								(String) tableModel.getValueAt(row, 3),(String) tableModel.getValueAt(row, 4),
								(String) tableModel.getValueAt(row, 5),(String) tableModel.getValueAt(row, 6),
								(String) tableModel.getValueAt(row, 7),(String) tableModel.getValueAt(row, 8),
								(String) tableModel.getValueAt(row, 9),(String) tableModel.getValueAt(row, 10),
								(String) tableModel.getValueAt(row, 11)
							};
							
							tableModelApp.addRow(appoggio);
							tableModel.removeRow(row);
							test=false;	
						}
					}
				}
			}
			}
		
		lblNumEtic.setText("Ci sono  "+tableModel.getRowCount()+"  etichette!");
	}
	
	private void dettaglioProdotto(int id) throws JarException, SQLException, Exception{
		prodotto prodottoDettaglio=ProdottoDAO.getProdotto(id);
		labeldetProduttore.setText(prodottoDettaglio.getProdCasa().getNome());
		if(!prodottoDettaglio.getProdCasa().getRegione().getNome().equals(null))
			labelDetReg.setText(prodottoDettaglio.getProdCasa().getRegione().getNome());
		labelDetVign.setText(prodottoDettaglio.getUvaglio().getNome());
		lblDetEtic.setText(prodottoDettaglio.getNome());
		labelDetAnno.setText(String.format("%d", prodottoDettaglio.getAnno()));
		labelDetAcqui.setText(String.format("%.2f", prodottoDettaglio.getPrezzoInterno()));
		labelDetVendita.setText(String.format("%.2f", prodottoDettaglio.getPrezzo()));
		
		
	}
	
	private int calcolaUscite(ArrayList<uscite> lista){
		int a=0;
		for(uscite e:lista){
			a=a+e.getQuantita();
			
		}
		return a;
	}
	
	private int calcolaEntrate(ArrayList<entrate> lista){
		int a=0;
		for(entrate e:lista)
		{
			a=a+e.getQuantita();
		}
		return a;
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
