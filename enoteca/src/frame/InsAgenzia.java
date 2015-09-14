package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import DAO.FornitoreDAO;

import model.fornitore;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class InsAgenzia extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textWeb;
	private JTextField textTel1;
	private JTextField textTel2;
	private JTextField textFax;
	private JTextField textNomeAgente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsAgenzia frame = new InsAgenzia();
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
	public InsAgenzia() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InsAgenzia.class.getResource("/img/download.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 328, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(12, 13, 56, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Email:");
		label_1.setBounds(12, 77, 56, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Sito Web");
		label_2.setBounds(12, 141, 56, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Telefono1");
		label_3.setBounds(170, 77, 70, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Telefono2");
		label_4.setBounds(170, 141, 70, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Fax");
		label_5.setBounds(12, 206, 56, 16);
		contentPane.add(label_5);
		
		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(12, 42, 116, 22);
		contentPane.add(textNome);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(12, 106, 116, 22);
		contentPane.add(textEmail);
		
		textWeb = new JTextField();
		textWeb.setColumns(10);
		textWeb.setBounds(12, 170, 116, 22);
		contentPane.add(textWeb);
		
		textTel1 = new JTextField();
		textTel1.setColumns(10);
		textTel1.setBounds(170, 106, 116, 22);
		contentPane.add(textTel1);
		
		textTel2 = new JTextField();
		textTel2.setColumns(10);
		textTel2.setBounds(170, 170, 116, 22);
		contentPane.add(textTel2);
		
		textFax = new JTextField();
		textFax.setColumns(10);
		textFax.setBounds(12, 235, 116, 22);
		contentPane.add(textFax);
		
		JButton button = new JButton("Salva");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,  "Salvare questa AGENZIA?", "Salvataggio Vigneto", 0)==0)
				{
					
					fornitore appoggio=new fornitore();
					if(!textEmail.getText().toString().equals(""))
						appoggio.setEmail(textEmail.getText());
					if(!textFax.getText().toString().equals(""))
						appoggio.setFax(textFax.getText().toString());
					if(!textTel1.getText().toString().equals(""))
						appoggio.setTelefono1(textTel1.getText().toString());
					if(!textTel2.getText().toString().equals(""))
						appoggio.setTelefono2(textTel2.getText().toString());
					if(!textNome.getText().toString().equals(""))
						appoggio.setNome(textNome.getText().toString());
					else
						appoggio.setNome("");
					if(!textNomeAgente.getText().equals("")){
						appoggio.setAgente(textNomeAgente.getText());
					}
					if(!textWeb.getText().toString().equals(""))
						appoggio.setWeb(textWeb.getText().toString());
					try {
						FornitoreDAO.inserisciFornitore(appoggio);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				try {
					inserimentoProdotto.refreshAgenzia(textNome.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					inserimentoProdotto.refreshAgenzia(textNome.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				InsAgenzia.this.dispose();
				
				}
			}
		});
		button.setBounds(12, 270, 97, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("esci");
		button_1.setBounds(218, 270, 87, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Reset");
		button_2.setBounds(121, 270, 81, 25);
		contentPane.add(button_2);
		
		JLabel label_6 = new JLabel("Nome Agente");
		label_6.setBounds(170, 13, 116, 16);
		contentPane.add(label_6);
		
		textNomeAgente = new JTextField();
		textNomeAgente.setColumns(10);
		textNomeAgente.setBounds(170, 42, 116, 22);
		contentPane.add(textNomeAgente);
	}
}
