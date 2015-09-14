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
import java.sql.SQLException;
import java.util.jar.JarException;
import java.awt.Toolkit;

public class InserisciFornitore extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textSitoWeb;
	private JTextField textTelefono1;
	private JTextField textTelefono2;
	private JTextField textFax;
	private JTextField textNomeAgente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserisciFornitore frame = new InserisciFornitore();
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
	public InserisciFornitore() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserisciFornitore.class.getResource("/img/iconaLettura.gif")));
		setTitle("Inserisci Fornitore");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 13, 56, 16);
		contentPane.add(lblNome);
		
		JLabel lblEmai = new JLabel("Email:");
		lblEmai.setBounds(12, 77, 56, 16);
		contentPane.add(lblEmai);
		
		JLabel lblSitoWeb = new JLabel("Sito Web");
		lblSitoWeb.setBounds(12, 141, 56, 16);
		contentPane.add(lblSitoWeb);
		
		JLabel lblTelefono = new JLabel("Telefono1");
		lblTelefono.setBounds(170, 77, 70, 16);
		contentPane.add(lblTelefono);
		
		JLabel lblTelefono_1 = new JLabel("Telefono2");
		lblTelefono_1.setBounds(170, 141, 70, 16);
		contentPane.add(lblTelefono_1);
		
		JLabel lblFax = new JLabel("Fax");
		lblFax.setBounds(12, 206, 56, 16);
		contentPane.add(lblFax);
		
		textNome = new JTextField();
		textNome.setBounds(12, 42, 116, 22);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(12, 106, 116, 22);
		contentPane.add(textEmail);
		
		textSitoWeb = new JTextField();
		textSitoWeb.setColumns(10);
		textSitoWeb.setBounds(12, 170, 116, 22);
		contentPane.add(textSitoWeb);
		
		textTelefono1 = new JTextField();
		textTelefono1.setColumns(10);
		textTelefono1.setBounds(170, 106, 116, 22);
		contentPane.add(textTelefono1);
		
		textTelefono2 = new JTextField();
		textTelefono2.setColumns(10);
		textTelefono2.setBounds(170, 170, 116, 22);
		contentPane.add(textTelefono2);
		
		textFax = new JTextField();
		textFax.setColumns(10);
		textFax.setBounds(12, 235, 116, 22);
		contentPane.add(textFax);
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,  "Salvare questa AGENZIA?", "Salvataggio Vigneto", 0)==0)
				{
					
					fornitore appoggio=new fornitore();
					if(!textEmail.getText().toString().equals(""))
						appoggio.setEmail(textEmail.getText());
					if(!textFax.getText().toString().equals(""))
						appoggio.setFax(textFax.getText().toString());
					if(!textTelefono1.getText().toString().equals(""))
						appoggio.setTelefono1(textTelefono1.getText().toString());
					if(!textTelefono2.getText().toString().equals(""))
						appoggio.setTelefono2(textTelefono2.getText().toString());
					if(!textNome.getText().toString().equals(""))
						appoggio.setNome(textNome.getText().toString());
					else
						appoggio.setNome("");
					if(!textNomeAgente.getText().equals("")){
						appoggio.setAgente(textNomeAgente.getText());
					}
					if(!textSitoWeb.getText().toString().equals(""))
						appoggio.setWeb(textSitoWeb.getText().toString());
					try {
						FornitoreDAO.inserisciFornitore(appoggio);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				try {
//					inserimentoProdotto.refreshAgenzia(textNome.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				InserisciFornitore.this.dispose();
				}
			}
		});
		btnSalva.setBounds(12, 270, 97, 25);
		contentPane.add(btnSalva);
		
		JButton btnEsci = new JButton("esci");
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserisciFornitore.this.setVisible(false);
			}
		});
		btnEsci.setBounds(218, 270, 87, 25);
		contentPane.add(btnEsci);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textEmail.setText("");
				textNome.setText("");
				textFax.setText("");
				textTelefono1.setText("");
				textTelefono2.setText("");
				textSitoWeb.setText("");
			}
		});
		btnReset.setBounds(121, 270, 81, 25);
		contentPane.add(btnReset);
		
		JLabel lblNomeAgente = new JLabel("Nome Agente");
		lblNomeAgente.setBounds(170, 13, 116, 16);
		contentPane.add(lblNomeAgente);
		
		textNomeAgente = new JTextField();
		textNomeAgente.setColumns(10);
		textNomeAgente.setBounds(170, 42, 116, 22);
		contentPane.add(textNomeAgente);
	}
}
