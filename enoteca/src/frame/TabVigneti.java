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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DAO.LuvaglioDAO;

import model.luvaglio;
import model.tipologia;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabVigneti extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textVigneto;
	private luvaglio appVig=new luvaglio();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabVigneti frame = new TabVigneti();
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
	public TabVigneti() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TabVigneti.class.getResource("/img/iconaLettura.gif")));
		setTitle("Gestione Vigneti");
		setBounds(100, 100, 337, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 149, 146);
		contentPane.add(scrollPane);
		
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
				if(row>=0){
					textVigneto.setText((String) table.getValueAt(row, 0));
					appVig.setNome((String) table.getValueAt(row, 0));
					appVig.setId(Integer.parseInt( (String) table.getValueAt(row, 1)));
				}
			}
		});
		final DefaultTableModel tableModel=new DefaultTableModel();
		final String[] columns={"nome","id"};
		
		tableModel.setDataVector(riempiTab(),columns );
		
		table.setModel(tableModel);
		TableColumn col=new TableColumn();
		col=table.getColumnModel().getColumn(1);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		scrollPane.setViewportView(table);
		
		JLabel lblInserisciNuovoVigneto = new JLabel("Modifica Vigneto");
		lblInserisciNuovoVigneto.setBounds(172, 13, 141, 16);
		contentPane.add(lblInserisciNuovoVigneto);
		
		textVigneto = new JTextField();
		textVigneto.setBounds(173, 46, 132, 22);
		contentPane.add(textVigneto);
		textVigneto.setColumns(10);
		
		JButton btnSalva = new JButton("Salva Modifica");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int row=table.getSelectedRow();
			if(row>=0){
				
				if(JOptionPane.showConfirmDialog(null, "<html>Modifica vigneto?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					
						try {
						appVig.setNome(textVigneto.getText());
						LuvaglioDAO.modVigneto(appVig);
						} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
						TabVigneti.this.dispose();
					}
				}
			}
		});
		btnSalva.setBounds(173, 81, 97, 25);
		contentPane.add(btnSalva);
		
		JButton btnCancella = new JButton("cancella");
		btnCancella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(JOptionPane.showConfirmDialog(null, "<html>Cancella vigneto?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
				try {
					if(row>=0){
						int id=Integer.parseInt((String) table.getValueAt(row, 1));
						LuvaglioDAO.Cancella(id);
					}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnCancella.setBounds(173, 134, 97, 25);
		contentPane.add(btnCancella);
	}
	public static String[][] riempiTab() throws Exception{
		String[][] data = null;
		ArrayList<luvaglio> listaVigneto=LuvaglioDAO.getListaUvaglio();
		int a=0;
		data=new String[listaVigneto.size()][2];
		for(luvaglio e:listaVigneto){
			data[a][0]=e.getNome();
			data[a][1]=String.format("%d", e.getId());
			a++;
		}
		
		return data;
	}
}
