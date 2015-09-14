package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import model.prodotto;

import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Frame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.Cursor;

public class DettagliAnnate extends JFrame {
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					DettagliAnnate frame = new DettagliAnnate(new prodotto());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public DettagliAnnate(prodotto prodotto) throws FileNotFoundException, IOException {
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setTitle("Dettagli Annate");
		getContentPane().setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewEtichetta = new JLabel(prodotto.getNome());
		lblNewEtichetta.setLabelFor(this);
		lblNewEtichetta.setFont(new Font("Britannic Bold", Font.PLAIN, 37));
		lblNewEtichetta.setForeground(Color.WHITE);
		lblNewEtichetta.setBounds(12, 13, 660, 50);
		getContentPane().add(lblNewEtichetta);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 65, 102, 344);
		getContentPane().add(scrollPane);
		DefaultTableModel  tableModel=new DefaultTableModel();
		table = new JTable(){
			 boolean [] CanEdit =  new  boolean [] { false };
			public boolean isCellEditable(int data, int columns){
				return CanEdit [ columns];
			}
			
			public Component prepareRenderer(TableCellRenderer r,int data,int columns){
				Component c = super.prepareRenderer(r, data, columns);
				if(data%2==0)
					c.setBackground(Color.white);
				else
					c.setBackground(Color.getHSBColor(15, 3, 1500));
				if(isCellSelected(data, columns))
					c.setBackground(Color.CYAN);
				return c;
			}
			
			
		};
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ANNO"
			}
		));
		scrollPane.setViewportView(table);
		
		
		
		
	}
}
