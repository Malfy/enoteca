package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;

import model.regione;

import DAO.RegioneDAO;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModRegione extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String[] columns=new String[]{"nome","id"};
	private JTextField textRegione;
	private regione appReg=new regione();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModRegione frame = new ModRegione();
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
	public ModRegione() throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 326, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 41, 116, 147);
		contentPane.add(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int data, int columns){
				return false;
			}};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=table.getSelectedRow();
				
				if(row>=0){
					appReg.setId(Integer.parseInt((String) table.getValueAt(row, 1)));
					appReg.setNome((String) table.getValueAt(row, 0));
					textRegione.setText(appReg.getNome());
				}
			}
		});
		DefaultTableModel tableModel=new DefaultTableModel();
		tableModel.setDataVector(riempiTable(), columns);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		TableColumn col=new TableColumn();
		col=table.getColumnModel().getColumn(1);
		col.setMinWidth(0);
		col.setMaxWidth(0);

		JLabel lblRegioni = new JLabel("Regioni:");
		lblRegioni.setBounds(12, 13, 56, 16);
		contentPane.add(lblRegioni);
		
		textRegione = new JTextField();
		textRegione.setBounds(150, 38, 131, 22);
		contentPane.add(textRegione);
		textRegione.setColumns(10);
		
		JLabel lblRegioneSelezionata = new JLabel("Regione selezionata:");
		lblRegioneSelezionata.setBounds(152, 13, 129, 16);
		contentPane.add(lblRegioneSelezionata);
		
		JButton btnSalvaModifica = new JButton("Salva modifica");
		btnSalvaModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row>=0){
					appReg.setNome(textRegione.getText());
					if(JOptionPane.showConfirmDialog(null, "<html>Modifica Regione?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					try {
						RegioneDAO.modRegione(appReg);
						ModRegione.this.dispose();
					} catch ( Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
				
			}
		});
		btnSalvaModifica.setBounds(149, 83, 132, 25);
		contentPane.add(btnSalvaModifica);
		
		JButton btnCanc = new JButton("cancella");
		btnCanc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow()>=0){
					
				if(JOptionPane.showConfirmDialog(null, "<html>Eliminare Regione <font color='red'>"+appReg.getNome()+"</font>?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					try {
						RegioneDAO.Cancella(appReg.getId());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			}
		});
		btnCanc.setBounds(150, 160, 131, 25);
		contentPane.add(btnCanc);
	}
	private String[][] riempiTable() throws Exception{
		String[][] data = null;
		ArrayList<model.regione> listaRegioni= RegioneDAO.getRegioni();
		data=new String[listaRegioni.size()][2];
		int a=0;
		for(regione e:listaRegioni){
			data[a][0]=e.getNome();
			data[a][1]=String.format("%d",e.getId());
			a++;
		}
		
		
		return data;
	}

}
