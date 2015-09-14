package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ImpostaTabProdotto extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImpostaTabProdotto frame = new ImpostaTabProdotto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImpostaTabProdotto() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 289, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 245, 341);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome Colonna", "Posizione"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		scrollPane.setViewportView(table);
		
		Properties pr = new Properties();
		try {
			pr.load(new FileInputStream(""));
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(pr.getProperty("colums.vigneto"));
		pr.setProperty("colums.vigneto", "wewe");
//		pr.l
		OutputStream out = new OutputStream() {
			
			@Override
			public void write(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
		String comments = "";
		pr.save(out, comments);
		System.out.println(pr.getProperty("colums.vigneto"));
		
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.setBounds(100, 364, 63, 25);
		contentPane.add(btnSalva);
	}
}
