package graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import engine.Sistema;

public class PanelActivar extends JPanel {
	
	public PanelActivar () {
		this.setSize(350,374);
	}
	/**
	 * @override
	 */
	public void paintComponent(Graphics g) {
		Dimension tamanio = getSize();
		
		// Ajusta la imagen del panel en función de la resolución
		ImageIcon imagenFondo;
		if(Sistema.SCREEN_WIDTH<1920) {
			this.setSize(200, 213);
			imagenFondo = new ImageIcon(getClass().getResource("/images/touchpad-mHD-activa-icon.png"));
		}else {
			imagenFondo = new ImageIcon(getClass().getResource("/images/touchpad-activa-icon.png"));
		}
		g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
