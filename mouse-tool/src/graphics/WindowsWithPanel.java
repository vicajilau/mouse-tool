package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class WindowsWithPanel extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Genera la opacidad
	 */
	public void generarOpacidad() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		boolean isUniformTranslucencySupported = gd.isWindowTranslucencySupported(TRANSLUCENT);
		
		
		if(!isUniformTranslucencySupported) {
			// No soporta transicion
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			// Si la soporta, aquí debería hacerla en proximas versiones
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	
	/**
	 * Create the frame.
	 */
	public WindowsWithPanel(JPanel panel) {
		setType(Type.POPUP);
		setOpacity(1.0f);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 427);
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		setLocationRelativeTo(null);
		contentPane = panel;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		generarOpacidad();
	}
}
