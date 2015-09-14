package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.TipologiaDAO;

import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inserimentoTipologia extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inserimentoTipologia frame = new inserimentoTipologia();
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
	public inserimentoTipologia() {
		setTitle("Inserimento Tipologia");
		setIconImage(Toolkit.getDefaultToolkit().getImage(inserimentoTipologia.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 198, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Inserisci Tipologia Prodotto");
		label.setBounds(10, 10, 160, 22);
		contentPane.add(label);
		
		final TextField textFieldTipologia = new TextField();
		textFieldTipologia.setBounds(10, 38, 160, 22);
		contentPane.add(textFieldTipologia);
		
		Button button = new Button("salva");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "<html>Inserire <font color='green'>"+textFieldTipologia.getText().toString()+"</font> come nuova Tipologia?</html>","Salva",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
					
					try {
						TipologiaDAO.inserisciTipologia(textFieldTipologia.getText().toString());
						inserimentoProdotto.refreshTipologia(textFieldTipologia.getText());
						inserimentoTipologia.this.setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button.setBounds(23, 74, 133, 22);
		contentPane.add(button);
	}
}
