package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.FornitoreDAO;
import DAO.FornitoreHasProdottoDAO;
import DAO.ProdottoDAO;

import model.fornitore;
import model.prodotto;
import model.prodottoHasFornitore;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RootPaneContainer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import com.sun.org.apache.bcel.internal.generic.IDIV;

import javax.swing.JSeparator;
import java.awt.Toolkit;

public class tabFornitori extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textRicercaNome;
	private String[] columns={"Nome","Nome agente", "Email", "telefono1", "Telefono2", "Fax", "SitoWeb", "id"};
	private String[] columnsProdotti={"Etichetta", "Vigneto", "Casa", "Fornitore","Codice Prodotto"};
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTable table_1;
	private JTable table_2;
	private JButton buttonLevaProdotto;
	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel2;
	private DefaultTableModel tableModelMod;
	private int idFornitoreSelezionato;
	private JButton buttonInsProd;
	private JLabel lblNewLabel;
	private JLabel lblRestantiProdotti;
	private JLabel lblutilizzandoLeFrecce;
	private JLabel lblNome;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField texttelefono2;
	private JTextField textFax;
	private JTextField textTelefono1;
	private JTextField textSitoWeb;
	private JScrollPane scrollPane_3;
	private JTable tableProdottiMod;
	private JLabel lblPrezzo;
	private JTextField textPrezzo;
	private int idPrFo=0;
	private int idPrRe=0;
	float prezzoProdottoSelezionato=0;
	private JButton btnSalva;
	private JButton btnRefresh;
	private fornitore appoggio=new fornitore();
	private JLabel lblNomeAgente;
	private JTextField textNomeAgente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tabFornitori frame = new tabFornitori();
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
	public tabFornitori() throws Exception {
		setTitle("Aggenzie");
		setIconImage(Toolkit.getDefaultToolkit().getImage(tabFornitori.class.getResource("/img/download.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1001, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		final DefaultTableModel tableModel =new DefaultTableModel();
		String[][] data=riempiTable();
		tableModel.setDataVector(data, columns);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 50, 966, 165);
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
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(table.getSelectedRow()>=0){
					startWaitCursor();
					tableModelMod=new DefaultTableModel();
					tableModel1=new DefaultTableModel();
					tableModel2=new DefaultTableModel();
					
					idFornitoreSelezionato=Integer.parseInt( (String) table.getValueAt(table.getSelectedRow(), 7));
					appoggio.setId(idFornitoreSelezionato) ;
					appoggio.setNome( (String) table.getValueAt(table.getSelectedRow(), 0));
					appoggio.setAgente((String)table.getValueAt(table.getSelectedRow(), 1));
					appoggio.setEmail( (String) table.getValueAt(table.getSelectedRow(), 2));
					appoggio.setTelefono1( (String) table.getValueAt(table.getSelectedRow(), 3));
					appoggio.setTelefono2( (String) table.getValueAt(table.getSelectedRow(), 4));
					appoggio.setFax( (String) table.getValueAt(table.getSelectedRow(), 5));
					appoggio.setWeb( (String) table.getValueAt(table.getSelectedRow(), 6));
					try {
						String[][] data=riempitabProd(appoggio);
						tableModelMod.setDataVector(data, columnsProdotti);
						tableModel1=tableModelMod;
						//.setDataVector(data, columnsProdotti);
						tableModel2.setDataVector(riempitabProdOpposta(appoggio), columnsProdotti);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tableProdottiMod.setModel(tableModelMod);
					table_1.setModel(tableModel1);
					TableColumn col1= table_1.getColumnModel().getColumn(3);
					col1.setMinWidth(0);
					col1.setMaxWidth(0);
					col1= table_1.getColumnModel().getColumn(4);
					col1.setMinWidth(0);
					col1.setMaxWidth(0);
					table_2.setModel(tableModel2);
					TableColumn col2= table_2.getColumnModel().getColumn(3);
					col2.setMinWidth(0);
					col2.setMaxWidth(0);
					col2= table_2.getColumnModel().getColumn(4);
					col2.setMinWidth(0);
					col2.setMaxWidth(0);
					
					//MODIFICA
					textNome.setText(appoggio.getNome());
					textNomeAgente.setText(appoggio.getAgente());
					textEmail.setText(appoggio.getEmail());
					textSitoWeb.setText(appoggio.getWeb());
					textTelefono1.setText(appoggio.getTelefono1());
					texttelefono2.setText(appoggio.getTelefono2());
					textFax.setText(appoggio.getFax());
				}
				stopWaitCursor();
				
			}
		});
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		JLabel lblRicerca = new JLabel("Ricerca:");
		lblRicerca.setBounds(12, 13, 56, 16);
		contentPane.add(lblRicerca);
		
		textRicercaNome = new JTextField();
		textRicercaNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[][] dataR = null;
				try {
					dataR = riempiRicerca(textRicercaNome.getText());
					tableModel.setDataVector(dataR, columns);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textRicercaNome.setBounds(67, 10, 180, 22);
		contentPane.add(textRicercaNome);
		textRicercaNome.setColumns(10);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 228, 966, 252);
		contentPane.add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Prodotti", null, panel, null);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 30, 357, 182);
		panel.add(scrollPane_1);
		
		table_1 = new JTable()
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
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=table_1.getSelectedRow();
				if(row>=0){
					idPrFo=Integer.parseInt((String) table_1.getValueAt(row, 3));
				}
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(594, 30, 357, 182);
		panel.add(scrollPane_2);
		
		
		table_2 = new JTable()
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
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.setSelectionBackground(Color.GREEN);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=table_2.getSelectedRow();
				if(row>=0){
					idPrRe=Integer.parseInt((String) table_2.getValueAt(row, 3));
				}
				
			}
		});
		
		scrollPane_2.setViewportView(table_2);
		
		buttonLevaProdotto = new JButton(">");
		buttonLevaProdotto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table_1.getSelectedRow();
				if(row>=0){
					String[] appoggio={(String) table_1.getValueAt(row, 0),
						(String) table_1.getValueAt(row, 1),(String) table_1.getValueAt(row, 2),
						(String) table_1.getValueAt(row, 3),(String) table_1.getValueAt(row, 4)};
					
					try {
					
					tableModel2.addRow(appoggio);
					ProdottoDAO.deleteFornitore(idPrFo);
					tableModel1.removeRow(row);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		buttonLevaProdotto.setBounds(447, 142, 67, 25);
		panel.add(buttonLevaProdotto);
		
		buttonInsProd = new JButton("<");
		buttonInsProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table_2.getSelectedRow();
				if(row>=0){					
					int tRow=table.getSelectedRow();
					try {
						if(ProdottoDAO.setFornitore(idPrRe, idFornitoreSelezionato)){					
							String[] appoggio={(String) table_2.getValueAt(row, 0),
									(String) table_2.getValueAt(row, 1),(String) table_2.getValueAt(row, 2),
									(String) table_2.getValueAt(row, 3),(String) table_2.getValueAt(row, 4)};
							tableModel1.addRow(appoggio);
							tableModel2.removeRow(row);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		buttonInsProd.setBounds(447, 184, 67, 25);
		panel.add(buttonInsProd);
		
		lblNewLabel = new JLabel("Prodotti dell'aggente selezionato:");
		lblNewLabel.setBounds(12, 0, 355, 17);
		panel.add(lblNewLabel);
		
		lblRestantiProdotti = new JLabel("Prodotti che non hanno assegnato nessun'aggente");
		lblRestantiProdotti.setBounds(596, 7, 355, 17);
		panel.add(lblRestantiProdotti);
		
		lblutilizzandoLeFrecce = new JLabel("<html>Utilizzando le frecce si possono spostare  i prodotti selezionati da una tabella all'altra.</html>");
		lblutilizzandoLeFrecce.setBounds(373, 37, 200, 92);
		panel.add(lblutilizzandoLeFrecce);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("modifica", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 13, 74, 16);
		panel_1.add(lblNome);
		
		label = new JLabel("Email");
		label.setBounds(12, 107, 74, 16);
		panel_1.add(label);
		
		label_1 = new JLabel("Telefono");
		label_1.setBounds(162, 107, 74, 16);
		panel_1.add(label_1);
		
		label_2 = new JLabel("Telefono");
		label_2.setBounds(162, 13, 74, 16);
		panel_1.add(label_2);
		
		label_3 = new JLabel("Fax");
		label_3.setBounds(162, 170, 74, 16);
		panel_1.add(label_3);
		
		label_4 = new JLabel("SitoWeb");
		label_4.setBounds(12, 170, 74, 16);
		panel_1.add(label_4);
		
		textNome = new JTextField();
		textNome.setBounds(14, 35, 116, 22);
		panel_1.add(textNome);
		textNome.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(12, 129, 116, 22);
		panel_1.add(textEmail);
		
		texttelefono2 = new JTextField();
		texttelefono2.setColumns(10);
		texttelefono2.setBounds(162, 129, 116, 22);
		panel_1.add(texttelefono2);
		
		textFax = new JTextField();
		textFax.setColumns(10);
		textFax.setBounds(162, 187, 116, 22);
		panel_1.add(textFax);
		
		textTelefono1 = new JTextField();
		textTelefono1.setColumns(10);
		textTelefono1.setBounds(162, 35, 116, 22);
		panel_1.add(textTelefono1);
		
		textSitoWeb = new JTextField();
		textSitoWeb.setColumns(10);
		textSitoWeb.setBounds(12, 187, 116, 22);
		panel_1.add(textSitoWeb);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(334, 10, 379, 199);
		panel_1.add(scrollPane_3);
		
		tableProdottiMod = new JTable(){
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
		tableProdottiMod.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=tableProdottiMod.getSelectedRow();
				if(row>=0){
					try {
						textPrezzo.setText(String.format("%.2f", ProdottoDAO.getProdotto(Integer.parseInt((String) tableProdottiMod.getValueAt(row, 3))).getPrezzoInterno()));
					} catch ( Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		scrollPane_3.setViewportView(tableProdottiMod);
		
		lblPrezzo = new JLabel("PREZZO AGENZIA");
		lblPrezzo.setBounds(737, 10, 116, 22);
		panel_1.add(lblPrezzo);
		
		textPrezzo = new JTextField();
		textPrezzo.setBounds(737, 47, 116, 22);
		panel_1.add(textPrezzo);
		textPrezzo.setColumns(10);
		
		btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(null, "salvare modifiche?")==JOptionPane.YES_OPTION){
				
				fornitore modFor=new fornitore();
				modFor.setNome(textNome.getText());
				modFor.setEmail(textEmail.getText());
				modFor.setFax(textFax.getText());
				modFor.setAgente(textNomeAgente.getText());
				modFor.setId(idFornitoreSelezionato);
				modFor.setTelefono1(textTelefono1.getText());
				modFor.setTelefono2(texttelefono2.getText());
				modFor.setWeb(textSitoWeb.getText());
				try {
					FornitoreDAO.modificaFornitore(modFor);
					int row=tableProdottiMod.getSelectedRow();
					if(row>=0){
						float prezzo=Float.parseFloat((String)textPrezzo.getText().replace(',','.'));
						if(prezzo!=prezzoProdottoSelezionato){
							ProdottoDAO.setPrezzoInterno(Integer.parseInt((String) tableProdottiMod.getValueAt(row, 3)), prezzo);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnSalva.setBounds(737, 98, 97, 25);
		panel_1.add(btnSalva);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] dataR;
				try {
					dataR = riempiRicerca(textRicercaNome.getText());
					tableModel.setDataVector(dataR, columns);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(852, 184, 97, 25);
		panel_1.add(btnRefresh);
		
		lblNomeAgente = new JLabel("Nome Agente:");
		lblNomeAgente.setBounds(12, 70, 91, 16);
		panel_1.add(lblNomeAgente);
		
		textNomeAgente = new JTextField();
		textNomeAgente.setBounds(115, 70, 161, 25);
		panel_1.add(textNomeAgente);
		textNomeAgente.setColumns(10);
	}
	
	public String[][] riempitabProd(fornitore fornitore) throws Exception{
		ArrayList<prodotto> listaProdotto=ProdottoDAO.getListaProdotto();
		boolean test=true;
		String[][] data=new String[listaProdotto.size()][7];
		int a=0;
		for(prodotto e:listaProdotto){
			if(e.getIdFornitore()==appoggio.getId()){
				data[a][0]=e.getNome();
				data[a][1]=e.getUvaglio().getNome();
				data[a][2]=e.getProdCasa().getNome();
				data[a][3]=String.format("%d", e.getIdProdotto());
				a++;
				
			}	
		}
		return data;
	}
	
	public String[][] riempitabProdOpposta(fornitore fornitore) throws Exception{

		ArrayList<prodotto> listaProdotto=ProdottoDAO.getListaProdotto();
		boolean test=true;
		String[][] data=new String[listaProdotto.size()][7];
		int a=0;
		for(prodotto e:listaProdotto){
			if(e.getIdFornitore()==0){
				data[a][0]=e.getNome();
				data[a][1]=e.getUvaglio().getNome();
				data[a][2]=e.getProdCasa().getNome();
				data[a][3]=String.format("%d", e.getIdProdotto());
				a++;
				
			}	
		}
		return data;
	}
	
	
	public String[][] riempiRicerca(String nome) throws Exception{
		ArrayList<fornitore> listaFornitori=FornitoreDAO.getRicercaFornitore(nome);
		String[][] data=new String[listaFornitori.size()][8];
		int a=0;
		for(fornitore e:listaFornitori){
			data[a][0]=e.getNome();
			data[a][1]=e.getAgente();
			data[a][2]=e.getEmail();
			data[a][3]=e.getTelefono1();
			data[a][4]=e.getTelefono2();
			data[a][5]=e.getFax();
			data[a][6]=e.getWeb();
			data[a][7]=String.format("%d", e.getId());
			a++;		
		}
		
		
		return data;
	}
	
	public String[][] riempiTable() throws Exception{
		ArrayList<fornitore> listaFornitori=FornitoreDAO.getFornitore();
		String[][] data=new String[listaFornitori.size()][8];
		int a=0;
		for(fornitore e:listaFornitori){
			data[a][0]=e.getNome();
			data[a][1]=e.getAgente();
			data[a][2]=e.getEmail();
			data[a][3]=e.getTelefono1();
			data[a][4]=e.getTelefono2();
			data[a][5]=e.getFax();
			data[a][6]=e.getWeb();
			data[a][7]=String.format("%d", e.getId());
			a++;		
		}
		
		
		return data;
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
	
	
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
