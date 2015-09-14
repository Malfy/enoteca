package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import DAO.ProdottoDAO;

import model.prodotto;

import java.awt.Color;
import java.sql.SQLException;
import java.util.jar.JarException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class confermaEliminazione extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args,final int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					confermaEliminazione frame = new confermaEliminazione(id);
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
	 * @throws SQLException 
	 * @throws JarException 
	 */
	public  confermaEliminazione(int id) throws JarException, SQLException, Exception {
		boolean itorno=false;
		setIconImage(Toolkit.getDefaultToolkit().getImage(confermaEliminazione.class.getResource("/img/iconaLettura.gif")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 275, 130);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEliminareIlProdotto = new JLabel("Eliminare il prodotto selezionato dalla tabella?");
		lblEliminareIlProdotto.setBounds(62, 11, 165, 14);
		contentPane.add(lblEliminareIlProdotto);
		
		JLabel lblNomeProd = new JLabel("...");
		lblNomeProd.setBounds(10, 36, 241, 14);
		contentPane.add(lblNomeProd);
		final prodotto prodotto=ProdottoDAO.getProdotto(id);
		lblNomeProd.setText(prodotto.getNome());
		
		JButton btnSi = new JButton("SI");
		btnSi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					ProdottoDAO.eliminaProdotto(prodotto);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				close();
			}
		});
		btnSi.setForeground(new Color(0, 128, 0));
		btnSi.setBounds(10, 58, 89, 23);
		contentPane.add(btnSi);
		
		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		btnNo.setForeground(new Color(255, 0, 0));
		btnNo.setBounds(162, 58, 89, 23);
		contentPane.add(btnNo);
		
		
		lblEliminareIlProdotto.setText("eliminare questo prodotto?");
		
	}
	
	public void close(){
		confermaEliminazione.this.setVisible(false);
	}
}
