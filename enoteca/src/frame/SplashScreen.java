package frame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class SplashScreen {
	private JFrame frame = new JFrame();
	private JProgressBar progress = new JProgressBar();
	
	public SplashScreen(String message, int steps) {
		//finestra senza barra del titolo e bordo
		frame.setUndecorated(true);
		JComponent c = (JComponent)frame.getContentPane();
		c.setLayout(new BorderLayout());
		progress.setMinimum(0);
		progress.setMaximum(steps);
		progress.setValue(0);
		progress.setOrientation(JProgressBar.HORIZONTAL);
		progress.setString(message);
		progress.setStringPainted(true);
		progress.setBorder(new CompoundBorder(
			new EmptyBorder(4, 4, 4, 4),
			progress.getBorder()));
		c.add(progress, BorderLayout.SOUTH);
		JLabel splash = new JLabel(new ImageIcon(getClass().getResource(
			"/img/logoo.gif")));
		splash.setBackground(Color.LIGHT_GRAY);
		splash.setBorder(new CompoundBorder(
			new EmptyBorder(4, 4, 4, 4),
			new EtchedBorder()));
		c.add(splash, BorderLayout.CENTER);
		c.setBackground(Color.WHITE);
		c.setBorder((Border) new LineBorder(Color.BLACK, 1));
	}
	
	/** AWT-Safe */
	public void show() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.pack();
				//al centro dello schermo
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	/** AWT-Safe */
	public void hide() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.dispose();
			}
		});
	}

	/** AWT-Safe */
	public void advance(final String message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//imposta la stringa message se diversa da null
				progress.setString(message == null ? "" : message);
				//avanza di 1
				progress.setValue(progress.getValue() + 1);
			}
		});
	}

}
