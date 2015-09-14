package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import model.regione;

import DAO.CasaDAO;
import DAO.RegioneDAO;
import javax.swing.JTextField;
import javax.swing.JButton;

public class inserimentoCasa extends JFrame {

	private JPanel contentPane;
	private JTextField textCasa;
	private static JComboBox comboBoxRegione;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inserimentoCasa frame = new inserimentoCasa();
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
	public inserimentoCasa() {
		setTitle("Inserimento Produttore");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 356, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button button = new Button("inserisci nuova regione");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inserimentoRegione insReg=new inserimentoRegione();
				insReg.setVisible(true);
				
			}
		});
		button.setBounds(193, 40, 138, 22);
		contentPane.add(button);
		
		comboBoxRegione = new JComboBox();
		comboBoxRegione.setBounds(23, 40, 145, 20);
		contentPane.add(comboBoxRegione);
		
		//Riempio comboBox
		
		RegioneDAO regioneDao=new RegioneDAO();
		ArrayList<regione> lista = new ArrayList<regione>();
		try {
			lista = regioneDao.getRegioni();
			int y=0;
			while(lista.size()>y)
			{
				comboBoxRegione.addItem(lista.get(y).getNome());
				y++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		JLabel lblRegione = new JLabel("Regione:");
		lblRegione.setBounds(23, 13, 65, 14);
		contentPane.add(lblRegione);
		
		textCasa = new JTextField();
		textCasa.setBounds(23, 97, 116, 22);
		contentPane.add(textCasa);
		textCasa.setColumns(10);
		
		JLabel lblCasa = new JLabel("Produttore:");
		lblCasa.setBounds(23, 73, 98, 16);
		contentPane.add(lblCasa);
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "<html>Inserire "+textCasa.getText().toString()+" come un nuovo produttore?</html>","Modifica",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					try {
						int id=RegioneDAO.getId(comboBoxRegione.getSelectedItem().toString());
						CasaDAO.inserisciCasa(id, textCasa.getText().toString());
						inserimentoProdotto.refreshProduttore(textCasa.getText());
						inserimentoCasa.this.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalva.setBounds(193, 96, 138, 25);
		contentPane.add(btnSalva);
	}
	
	public static void refresh(){
		comboBoxRegione.removeAllItems();
		RegioneDAO regioneDao=new RegioneDAO();
		ArrayList<regione> lista = new ArrayList<regione>();
		try {
			lista = regioneDao.getRegioni();
			int y=0;
			while(lista.size()>y)
			{
				comboBoxRegione.addItem(lista.get(y).getNome());
				y++;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
