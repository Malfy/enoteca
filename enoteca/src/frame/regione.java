package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.casa;
import model.fornitore;
import DAO.CasaDAO;
import DAO.FornitoreDAO;
import DAO.RegioneDAO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class regione extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textregione;
	private JTable table_1;
	private String[] columnsC={"Nome","IdR","IDCASA"};
	private JTextField textCasa;
	private  DefaultTableModel tabModelC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					regione frame = new regione();
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
	public regione() throws Exception {
		setTitle("Regione/casa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(regione.class.getResource("/img/iconaLettura.gif")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 594, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 29, 135, 194);
		contentPane.add(scrollPane);
		final DefaultTableModel tabModel=new DefaultTableModel();
		final String[][] data=riempiRegione();
		final String[] columns={"Nome","Id"};
		tabModel.setDataVector(data, columns);
		table = new JTable(){
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
				int row=table.getSelectedRow();
				int id=0;
				String[][] dataC;
				if(row>=0){			
					try{
					id=Integer.parseInt((String)table.getValueAt(row, 1));
					dataC=riempiCasa(id);
					tabModelC.setDataVector(dataC, columnsC);
					table_1.setModel(tabModelC);
					
					TableColumn col3= table_1.getColumnModel().getColumn(1);
					col3.setMinWidth(0);
					col3.setMaxWidth(0);
					TableColumn col2= table_1.getColumnModel().getColumn(2);
					col2.setMinWidth(0);
					col2.setMaxWidth(0);
					}
					catch (Exception e) {
						JOptionPane.showConfirmDialog(null, e.getMessage());
						// TODO: handle exception
					}

					
				}
			}
		});
		table.setModel(tabModel);
		TableColumn col1= table.getColumnModel().getColumn(1);
		col1.setMinWidth(0);
		col1.setMaxWidth(0);
		scrollPane.setViewportView(table);
		
		JButton btnInserisciR = new JButton("inserisci");
		btnInserisciR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "<html>Inserire nuovo Regione?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					try {
						RegioneDAO.inserisciRegione(textregione.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showConfirmDialog(null, e.getMessage());
					}
				}
			}
		});
		btnInserisciR.setBounds(170, 108, 97, 25);
		contentPane.add(btnInserisciR);
		
		JButton btnEliminaR = new JButton("elimina");
		btnEliminaR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				int id=0;
				if(row>=0){
					if(JOptionPane.showConfirmDialog(null, "<html>Eliminare "+table.getValueAt(row, 0)+"?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
						id=Integer.parseInt( (String) table.getValueAt(row, 1));
						try {
							RegioneDAO.Cancella(id);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
//							JOptionPane.showConfirmDialog(null, e);
						}
					}
					
				}
			}
		});
		btnEliminaR.setBounds(170, 151, 97, 25);
		contentPane.add(btnEliminaR);
		
		JButton btnRefreshR = new JButton("refresh");
		btnRefreshR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[][] data=riempiRegione();
					tabModel.setDataVector(data, columns);
					TableColumn col1= table.getColumnModel().getColumn(1);
					col1.setMinWidth(0);
					col1.setMaxWidth(0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefreshR.setBounds(170, 198, 97, 25);
		contentPane.add(btnRefreshR);
		
		JLabel lblNuovaRegione = new JLabel("nuova regione");
		lblNuovaRegione.setBounds(170, 13, 97, 16);
		contentPane.add(lblNuovaRegione);
		
		textregione = new JTextField();
		textregione.setBounds(170, 42, 116, 22);
		contentPane.add(textregione);
		textregione.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(298, 29, 129, 194);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable(){
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
		tabModelC=new DefaultTableModel();
	
		scrollPane_1.setViewportView(table_1);
		
		JButton btnInserisciC = new JButton("inserisci");
		btnInserisciC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row>=0){
					if(JOptionPane.showConfirmDialog(null, "<html>Inserire nuovo Produttore??</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
						int id=Integer.parseInt((String)table.getValueAt(row, 1));
						try {
							CasaDAO.inserisciCasa(id, textCasa.getText());
						} catch (Exception e1) {
							JOptionPane.showInputDialog(e);
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		btnInserisciC.setBounds(448, 108, 97, 25);
		contentPane.add(btnInserisciC);
		
		JButton btnCancellaC = new JButton("elimina");
		btnCancellaC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table_1.getSelectedRow();
				if(row>=0){
					int id=Integer.parseInt((String) table_1.getValueAt(row, 2));
					try {
						CasaDAO.Cancella(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnCancellaC.setBounds(448, 151, 97, 25);
		contentPane.add(btnCancellaC);
		
		JButton btnRefreshC = new JButton("refresh");
		btnRefreshC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row>=0){
					int id=Integer.parseInt((String)table.getValueAt(row, 1));
					try {
						tabModelC.setDataVector(riempiCasa(id),columnsC);
						TableColumn col3= table_1.getColumnModel().getColumn(1);
						col3.setMinWidth(0);
						col3.setMaxWidth(0);
						TableColumn col2= table_1.getColumnModel().getColumn(2);
						col2.setMinWidth(0);
						col2.setMaxWidth(0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnRefreshC.setBounds(448, 198, 97, 25);
		contentPane.add(btnRefreshC);
		
		JLabel lblNuovaCasa = new JLabel("nuovo Produttore");
		lblNuovaCasa.setBounds(448, 13, 116, 16);
		contentPane.add(lblNuovaCasa);
		
		textCasa = new JTextField();
		textCasa.setColumns(10);
		textCasa.setBounds(448, 42, 116, 22);
		contentPane.add(textCasa);
		
		JLabel lblRegioni = new JLabel("Regioni:");
		lblRegioni.setBounds(25, 13, 56, 16);
		contentPane.add(lblRegioni);
		
		JLabel lblProduttori = new JLabel("Produttori");
		lblProduttori.setBounds(298, 13, 70, 16);
		contentPane.add(lblProduttori);
	}
	
	
	public String[][] riempiCasa(int id) throws Exception{
		ArrayList<casa> listaCasa=CasaDAO.getCasaRegione(id);
		String[][] data=new String[listaCasa.size()][3];
		int a=0;
		for(casa e:listaCasa){
			data[a][0]=e.getNome();
			data[a][2]=String.format("%d", e.getId());
			data[a][1]=String.format("%d",e.getIdRegione());
			a++;		
		}
		
		
		return data;
	}
	public String[][] riempiRegione() throws Exception{
		ArrayList<model.regione> listaRegione=RegioneDAO.getRegioni();
		String[][] data=new String[listaRegione.size()][2];
		int a=0;
		for(model.regione e:listaRegione){
			data[a][0]=e.getNome();
			data[a][1]=String.format("%d", e.getId());
			a++;		
		}
		
		
		return data;
	}
}
