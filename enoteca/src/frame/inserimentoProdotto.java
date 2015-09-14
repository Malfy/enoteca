package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;
import javax.swing.BoxLayout;
import java.awt.TextArea;
import java.awt.List;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;

import DAO.CasaDAO;
import DAO.FornitoreDAO;
import DAO.FornitoreHasProdottoDAO;
import DAO.ProdottoDAO;
import DAO.RegioneDAO;
import DAO.TipologiaDAO;

import model.casa;
import model.fornitore;
import model.prodotto;
import model.prodottoHasFornitore;
import model.regione;
import model.tipologia;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JComboBox;
import javax.swing.JButton;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.xml.internal.ws.util.UtilException;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.Toolkit;

public class inserimentoProdotto extends JFrame {

	private JPanel contentPane;
	private static JTextField textNome;
	private static JTextField textPrezzoFornitore;
	private static JTextField textFieldPrezzoCliente;
	private static JTextField textFieldGradi;
	private static JTextField textFieldAnno;
	private static metodUtils utile=new metodUtils();
	private static JComboBox comboBoxVigneto;
	private static JComboBox comboBoxTipologia;
	private static JComboBox comboBoxCasa;
	private static JComboBox comboBoxFornitore;
	private static JEditorPane editorDescrizione = new JEditorPane();
	private static JTextField textEntrate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inserimentoProdotto frame = new inserimentoProdotto(new enoteca());
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
	public inserimentoProdotto(final enoteca frameEnoteca) throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(inserimentoProdotto.class.getResource("/img/iconaLettura.gif")));
		
		setTitle("Inserimento nuovo prodotto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 979, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textNome = new JTextField();
		textNome.setBackground(SystemColor.controlHighlight);
		textNome.setBounds(50, 37, 280, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Etichetta");
		lblNome.setBounds(50, 11, 70, 18);
		contentPane.add(lblNome);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(342, 11, 106, 14);
		contentPane.add(lblDescrizione);
		
	
		
		Label label = new Label("Produttore");
		label.setBounds(50, 124, 62, 22);
		contentPane.add(label);
		
		comboBoxCasa = new JComboBox();
		
		comboBoxCasa.setBounds(50, 152, 138, 20);
		contentPane.add(comboBoxCasa);
		
		Button buttonCasa = new Button("Nuovo Produttore");
		buttonCasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inserimentoCasa insCasa=new inserimentoCasa();
				insCasa.setVisible(true);		
			}
		});
		buttonCasa.setBounds(224, 152, 106, 22);
		contentPane.add(buttonCasa);
		
		JLabel lblTipologia = new JLabel("Tipologia");
		lblTipologia.setBounds(50, 183, 70, 14);
		contentPane.add(lblTipologia);
		
		comboBoxTipologia = new JComboBox();
		comboBoxTipologia.setBounds(50, 208, 138, 20);
		contentPane.add(comboBoxTipologia);
		
		JLabel lblFornitore = new JLabel("Fornitore:");
		lblFornitore.setBounds(50, 239, 62, 14);
		contentPane.add(lblFornitore);
		
		comboBoxFornitore = new JComboBox();

		comboBoxFornitore.setBounds(50, 264, 138, 20);
		contentPane.add(comboBoxFornitore);
		
		Button button = new Button("Nuova Agenzia");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InsAgenzia inFo=new InsAgenzia();
				inFo.setVisible(true);
			}
		});
		button.setBounds(224, 264, 106, 22);
		contentPane.add(button);
		
		Button button_1 = new Button("Nuova tipologia");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserimentoTipologia inTi=new inserimentoTipologia();
				inTi.setVisible(true);
			}
		});
		button_1.setBounds(224, 206, 106, 22);
		contentPane.add(button_1);
		
		JLabel lblPrezzoFornitore = new JLabel("Prezzo Agenzia");
		lblPrezzoFornitore.setBounds(50, 295, 138, 14);
		contentPane.add(lblPrezzoFornitore);
		
		JLabel lblPrezzoCliente = new JLabel("Prezzo Cliente");
		lblPrezzoCliente.setBounds(224, 295, 106, 14);
		contentPane.add(lblPrezzoCliente);
		
		textPrezzoFornitore = new JTextField();
		textPrezzoFornitore.setBackground(new Color(255, 255, 224));
		textPrezzoFornitore.setColumns(10);
		textPrezzoFornitore.setBounds(50, 320, 138, 20);
		contentPane.add(textPrezzoFornitore);
		
		textFieldPrezzoCliente = new JTextField();
		textFieldPrezzoCliente.setBackground(new Color(224, 255, 255));
		textFieldPrezzoCliente.setColumns(10);
		textFieldPrezzoCliente.setBounds(224, 320, 106, 20);
		contentPane.add(textFieldPrezzoCliente);
		
		JLabel lblGradi = new JLabel("Gradi");
		lblGradi.setBounds(50, 351, 46, 14);
		contentPane.add(lblGradi);
		
		textFieldGradi = new JTextField();
		textFieldGradi.setBackground(new Color(245, 245, 220));
		textFieldGradi.setColumns(10);
		textFieldGradi.setBounds(50, 376, 138, 20);
		contentPane.add(textFieldGradi);
		
		JLabel lblAnno = new JLabel("Anno");
		lblAnno.setBounds(50, 407, 46, 14);
		contentPane.add(lblAnno);
		
		textFieldAnno = new JTextField();
		textFieldAnno.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAnno.setColumns(10);
		textFieldAnno.setBackground(new Color(245, 245, 220));
		textFieldAnno.setBounds(50, 432, 138, 20);
		contentPane.add(textFieldAnno);
		
		//qui riempio combo fornitore
		final FornitoreDAO FornitoreDAO=new FornitoreDAO();
		ArrayList<fornitore> listaFornitore = new ArrayList<fornitore>();
		try {
			listaFornitore = FornitoreDAO.getFornitore();
			int y=0;
			comboBoxFornitore.addItem("Nessuna selezione");
			while(listaFornitore.size()>y)
			{
				
				comboBoxFornitore.addItem(listaFornitore.get(y).getNome());
				y++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//QUI RIEMPIO L COMBO CASA
		CasaDAO casaDao=new CasaDAO();
		ArrayList<casa> lista = new ArrayList<casa>();
		try {
			lista = casaDao.getCasa();
			int y=0;
			comboBoxCasa.addItem("Nessuna selezione");
			while(lista.size()>y)
			{
				
				comboBoxCasa.addItem(lista.get(y).getNome());
				y++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TipologiaDAO tipologiaDao=new TipologiaDAO();
		ArrayList<tipologia> listaTipologia = new ArrayList<tipologia>();
		try {
			listaTipologia = tipologiaDao.getListaTipo();
			int y=0;
			comboBoxTipologia.addItem("Nessuna selezione");
			while(listaTipologia.size()>y)
			{
				
				comboBoxTipologia.addItem(listaTipologia.get(y).getTipo());
				y++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TASTO SALVA DA COMPLETARE
		Button btnSalva = new Button("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prodotto appoggio=new prodotto();
				metodUtils utile=new metodUtils();
				if(!textNome.getText().toString().equals(""))
					appoggio.setNome(textNome.getText().toString());
				
				if(!editorDescrizione.getText().toString().equals(""))
					appoggio.setDescrizione(editorDescrizione.getText().toString());
				if(!textFieldAnno.getText().toString().equals("") && utile.isNumber(textFieldAnno.getText().toString()))
					try{
						appoggio.setAnno(Integer.parseInt( textFieldAnno.getText().toString()));
						
					}catch(NumberFormatException e){
						System.out.println(e.getMessage());
					}
				
				if(!textEntrate.getText().toString().equals("") && utile.isNumber(textEntrate.getText().toString())){
					appoggio.setValuta(Integer.parseInt(textEntrate.getText().toString()));
				}
				
				if(!textFieldGradi.getText().toString().equals("") && utile.isNumber(textFieldGradi.getText().toString().replace(',','.')))
					appoggio.setGradi(Float.parseFloat(utile.convertStringFloat(textFieldGradi.getText().toString().replace(',','.'))));
				if(!textFieldPrezzoCliente.getText().toString().equals("") && utile.isNumber(textFieldPrezzoCliente.getText().toString().replace(',','.'))){
					appoggio.setPrezzo(Float.parseFloat(textFieldPrezzoCliente.getText().toString().replace(',','.')));
				}
				if(comboBoxCasa.getSelectedIndex()>=0){
					appoggio.getProdCasa().setNome(comboBoxCasa.getSelectedItem().toString());
				}
				if(comboBoxVigneto.getSelectedIndex()>=0)
					appoggio.getUvaglio().setNome(comboBoxVigneto.getSelectedItem().toString());
				if(comboBoxTipologia.getSelectedIndex()>=0)
					appoggio.getTipo().setTipo(comboBoxTipologia.getSelectedItem().toString());
				
				if(!textPrezzoFornitore.getText().toString().equals("") && utile.isNumber(textPrezzoFornitore.getText().toString().replace(',','.'))){
					appoggio.setPrezzoInterno(Float.parseFloat(textPrezzoFornitore.getText().toString().replace(',','.')));
				}
				appoggio.setFornitore(new fornitore());
				if(comboBoxFornitore.getSelectedIndex()>=0){
					appoggio.getFornitore().setNome(comboBoxFornitore.getSelectedItem().toString());
				}
				
				VerificaInserimentoProdotto.confermaInserimento(appoggio,frameEnoteca);
				
				
			}
		});
		btnSalva.setBounds(50, 479, 70, 22);
		contentPane.add(btnSalva);
		
		
		editorDescrizione.setText((String) null);
		editorDescrizione.setFont(new Font("Tahoma", Font.BOLD, 13));
		editorDescrizione.setBounds(336, 37, 613, 415);
		contentPane.add(editorDescrizione);
		
		JLabel lblVigneto = new JLabel("Vigneto");
		lblVigneto.setBounds(50, 70, 56, 16);
		contentPane.add(lblVigneto);
		
		final metodUtils utile = new metodUtils();
		comboBoxVigneto = new JComboBox(utile.getComboVigneto());
		comboBoxVigneto.setBounds(50, 99, 138, 22);
		contentPane.add(comboBoxVigneto);
		
//		JButton btnRefresh = new JButton("REFRESH");
//		btnRefresh.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					comboBoxVigneto.removeAllItems();
//					String[] vignetoData=utile.getComboVigneto();
//					for(int a=0;a<vignetoData.length;a++){
//						comboBoxVigneto.addItem(vignetoData[a]);
//					}
//					comboBoxCasa.removeAllItems();
//					String[] casaData=utile.getComboCasa();
//					for(int a=1;a<casaData.length;a++){
//						comboBoxCasa.addItem(casaData[a]);
//					}
//					comboBoxFornitore.removeAllItems();
//					String[] fornitoreData=utile.getComboFornitore();
//					for(int a=1;a<fornitoreData.length;a++){
//						comboBoxFornitore.addItem(fornitoreData[a]);
//					}
//					comboBoxTipologia.removeAllItems();
//					String[] tipoData=utile.getComboTipologia();
//					for(int a=1;a<tipoData.length;a++){
//						comboBoxTipologia.addItem(tipoData[a]);
//					}
//					
//					
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//		btnRefresh.setBounds(233, 555, 97, 25);
//		contentPane.add(btnRefresh);
		
		Button button_2 = new Button("Nuovo Vigneto");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserimentoVigneto inVi=new InserimentoVigneto();
				inVi.setVisible(true);
			}
		});
		button_2.setBounds(224, 99, 106, 22);
		contentPane.add(button_2);
		
		JLabel lblEntrate = new JLabel("Entrate");
		lblEntrate.setBounds(224, 351, 107, 14);
		contentPane.add(lblEntrate);
		
		textEntrate = new JTextField();
		textEntrate.setHorizontalAlignment(SwingConstants.CENTER);
		textEntrate.setColumns(10);
		textEntrate.setBackground(new Color(245, 245, 220));
		textEntrate.setBounds(224, 376, 107, 20);
		contentPane.add(textEntrate);
				
	}
	
	
	public static void refresh(){
		textNome.setText("");
		textFieldAnno.setText("");
		editorDescrizione.setText("");
		textFieldGradi.setText("");
		textPrezzoFornitore.setText("");
		textFieldPrezzoCliente.setText("");
		textEntrate.setText("");
		comboBoxCasa.setSelectedIndex(0);
		comboBoxFornitore.setSelectedIndex(0);
		comboBoxTipologia.setSelectedIndex(0);
		comboBoxVigneto.setSelectedIndex(0);
		
	}
	
	
	static void refreshTipologia(String select) throws Exception{
		comboBoxTipologia.addItem(select);
		comboBoxTipologia.setSelectedItem(select);
	}
	
	static void refreshProduttore(String select) throws Exception{
		comboBoxCasa.addItem(select);
		comboBoxCasa.setSelectedItem(select);
	}
	
	static void refreshVigneto(String select) throws Exception{
		comboBoxVigneto.addItem(select);
		comboBoxVigneto.setSelectedItem(select);
		
	}
	
	static void refreshAgenzia(String select) throws Exception{
		comboBoxFornitore.addItem(select);
		comboBoxFornitore.setSelectedItem(select);
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
