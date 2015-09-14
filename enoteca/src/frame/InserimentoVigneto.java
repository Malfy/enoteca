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

import DAO.LuvaglioDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class InserimentoVigneto extends JFrame {

	private JPanel contentPane;
	private JTextField textVigneto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserimentoVigneto frame = new InserimentoVigneto();
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
	public InserimentoVigneto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoVigneto.class.getResource("/img/iconaLettura.gif")));
		setTitle("inserimento vigneto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 207, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeVigneto = new JLabel("Nome vigneto");
		lblNomeVigneto.setBounds(53, 13, 83, 16);
		contentPane.add(lblNomeVigneto);
		
		textVigneto = new JTextField();
		textVigneto.setBounds(36, 42, 116, 22);
		contentPane.add(textVigneto);
		textVigneto.setColumns(10);
		
		JButton btnNewButton = new JButton("Salva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(JOptionPane.showConfirmDialog(textVigneto,  "Salvataggio Vigneto "+textVigneto.getText(), "Salvataggio Vigneto", 0)==0)
					{
						if(LuvaglioDAO.inserisciVigneto(textVigneto.getText())){
							inserimentoProdotto.refreshVigneto(textVigneto.getText());
							InserimentoVigneto.this.dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Vigneto gia esistente");
						}
					else{
						InserimentoVigneto.this.setVisible(false);
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(46, 77, 97, 25);
		contentPane.add(btnNewButton);
	}

}
