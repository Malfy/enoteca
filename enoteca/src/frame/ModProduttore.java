package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import DAO.CasaDAO;
import DAO.RegioneDAO;

import model.casa;
import model.regione;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModProduttore extends JFrame {

	private JPanel contentPane;
	private JTable tableProduttore;
	private JTextField textNomeProduttore;
	private JComboBox comboRegione;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModProduttore frame = new ModProduttore();
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
	public ModProduttore() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModProduttore.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		setTitle("Modifica Produttore");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 319, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 40, 136, 202);
		contentPane.add(scrollPane);
		
		tableProduttore = new JTable(){
			public boolean isCellEditable(int data, int columns){
				return false;
			}};
		tableProduttore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=tableProduttore.getSelectedRow();
				if(row>=0)
				{
					try {
						comboRegione.setSelectedItem(RegioneDAO.getCasaRegione(Integer.parseInt((String) tableProduttore.getValueAt(row, 2))).getNome());
						textNomeProduttore.setText((String) tableProduttore.getValueAt(row, 0));
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		String[] columns=new String[] {"nome", "id","idRegione"};
		
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(riempiTabProduttore(), columns);
		tableProduttore.setModel(tableModel);
		TableColumn col=new TableColumn();
		col=tableProduttore.getColumnModel().getColumn(1);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		
		
		TableColumn col1=new TableColumn();
		col1=tableProduttore.getColumnModel().getColumn(2);
		col1.setMinWidth(0);
		col1.setMaxWidth(0);
		scrollPane.setViewportView(tableProduttore);
		
		JLabel lblProduttori = new JLabel("Produttori:");
		lblProduttori.setBounds(12, 13, 115, 16);
		contentPane.add(lblProduttori);
		metodUtils utile=new metodUtils();
		comboRegione = new JComboBox(utile.getComboRegione());
		comboRegione.setBounds(168, 40, 127, 22);
		contentPane.add(comboRegione);
		
		
		JLabel lblRegione = new JLabel("Regione");
		lblRegione.setBounds(168, 13, 56, 16);
		contentPane.add(lblRegione);
		
		JLabel lblNomeProduttore = new JLabel("Nome Produttore:");
		lblNomeProduttore.setBounds(168, 88, 115, 16);
		contentPane.add(lblNomeProduttore);
		
		textNomeProduttore = new JTextField();
		textNomeProduttore.setBounds(168, 117, 127, 22);
		contentPane.add(textNomeProduttore);
		textNomeProduttore.setColumns(10);
		
		JButton btnSalvaModifica = new JButton("Salva modifica");
		btnSalvaModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casa appCasa=new casa();
				int row=tableProduttore.getSelectedRow();
				if(JOptionPane.showConfirmDialog(null, "<html>Modifica Produttore?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
				try {
					if(row>=0){
						appCasa.setId(Integer.parseInt((String) tableProduttore.getValueAt(row, 1)));
						appCasa.setNome(textNomeProduttore.getText());
						appCasa.setIdRegione(RegioneDAO.getId((String) comboRegione.getSelectedItem()));
						
						CasaDAO.modCasa(appCasa);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				ModProduttore.this.dispose();
				}			
			}
		});
		btnSalvaModifica.setBounds(168, 161, 127, 25);
		contentPane.add(btnSalvaModifica);
		
		JButton btnCancella = new JButton("Cancella");
		btnCancella.setBounds(168, 217, 127, 25);
		contentPane.add(btnCancella);
	}
	private String[][] riempiTabProduttore() throws Exception{
		ArrayList<casa> listaCasa=CasaDAO.getCasa();
		int a=0;
		String data[][]=new String[listaCasa.size()][3];
		for(casa e:listaCasa){
			data[a][0]=e.getNome();
			data[a][1]=String.format("%d",e.getId());
			data[a][2]=String.format("%d", e.getIdRegione());
			a++;
			
		}
		
		
		return data;
	}
}
