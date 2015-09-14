package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.luvaglio;
import model.tipologia;

import DAO.LuvaglioDAO;
import DAO.TipologiaDAO;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class tabTipologie extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JButton btnSalva;
	private JButton btnCancella;
	private int id=0;
	private JTextField textField;
	private final static String[] columns={"nome","id"};
	private JLabel lblNome;
	private static DefaultTableModel tableModel;
	private static DefaultTableModel tableModelModificato;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tabTipologie frame = new tabTipologie();
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
	public tabTipologie() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(tabTipologie.class.getResource("/img/iconaLettura.gif")));
		setTitle("Modifica Tipologia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 344, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 134, 141);
		contentPane.add(scrollPane);
		
		
		
		table = new JTable(){
			
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
				if(row>=0){
					textField.setText((String) table.getValueAt(row, 0));
					id=Integer.parseInt((String) table.getValueAt(row, 1));
				}
			}
		});
		tableModelModificato=new DefaultTableModel()
		{
			public boolean isCellEditable(int data, int columns){
				return true;
			}
			
		};
		tableModel=new DefaultTableModel()
		{
			public boolean isCellEditable(int data, int columns){
				return false;
			}
			
		};
		TableColumn col1=new TableColumn();
		tableModel.setDataVector(riempiTipologia(), columns);
		tableModelModificato.setDataVector(riempiTipologia(), columns);
		
		table.setModel(tableModel);
		col1= table.getColumnModel().getColumn(1);
		col1.setMinWidth(0);
		col1.setMaxWidth(0);
		scrollPane.setViewportView(table);
		
		btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(JOptionPane.showConfirmDialog(textField,  "Salvataggio Tipologia  "+textField.getText(), "Conferma salvataggio", 0)==0)
						if(TipologiaDAO.inserisciTipologia(textField.getText()))
							refresh();
						else
							JOptionPane.showMessageDialog(null, "Gia inserito cerca nella tabella");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalva.setBounds(159, 179, 97, 25);http://www.disi.unige.it/person/MagilloP/INTERF03/LEZJAVA/dialog.gif
		contentPane.add(btnSalva);
		
		btnCancella = new JButton("cancella");
		btnCancella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				int id=0;
				if(row>=0){
					id=Integer.parseInt((String) tableModel.getValueAt(row, 1));
					String nome=(String) tableModel.getValueAt(row, 0);
					
					try {
						System.out.println(JOptionPane.showConfirmDialog(textField,  "Eliminare tipologia "+nome, "Conferma eliminazione", 0));
						if(JOptionPane.showConfirmDialog(textField,  "Eliminare tipologia "+nome, "Conferma eliminazione", 0)==0)
							TipologiaDAO.Cancella(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(textField,"Seleziona tipologia nella Tabella" ,"Errore" , 0);
				}
			}
		});
		btnCancella.setBounds(158, 129, 97, 25);
		contentPane.add(btnCancella);
		
		textField = new JTextField();
		textField.setBounds(158, 76, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNome = new JLabel("Tipologia");
		lblNome.setBounds(158, 47, 56, 16);
		contentPane.add(lblNome);
		
		JButton btnSalvaModifiche = new JButton("Salva Modifiche");
		btnSalvaModifiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipologia tipologia=new tipologia();
				tipologia.setTipo(textField.getText());
				tipologia.setId(id);
				if(JOptionPane.showConfirmDialog(textField, "<html>Modifica Tipologia?</html>","salva",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					//salva
					try {
						TipologiaDAO.modificaTipologia(tipologia);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					}
					
				
				try {
					tableModel.setDataVector(riempiTipologia(), columns);
					table.setModel(tableModel);
					TableColumn col=new TableColumn();
					col=table.getColumnModel().getColumn(1);
					col.setMinWidth(0);
					col.setMaxWidth(0);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSalvaModifiche.setBounds(158, 9, 158, 25);
		contentPane.add(btnSalvaModifiche);
		
		
	}
	public static void refresh(){
		try {
			tableModel.setDataVector(riempiTipologia(), columns);
			TableColumn col=new TableColumn();
			col= table.getColumnModel().getColumn(1);
			col.setMinWidth(0);
			col.setMaxWidth(0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static String[][] riempiTipologia() throws Exception{
		String data[][]=null;
		ArrayList<tipologia> listaTipo=TipologiaDAO.getListaTipo();
		int a=0;
		data=new String[listaTipo.size()][2];
		for(tipologia e:listaTipo){
			data[a][0]=e.getTipo();
			data[a][1]=String.format("%d", e.getId());
		
			a++;
		}
		return data;
	}
}
