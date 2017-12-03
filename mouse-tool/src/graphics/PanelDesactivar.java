package graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelDesactivar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PanelDesactivar () {
		this.setSize(400,427);
	}
	/**
	 * @override
	 */
	public void paintComponent(Graphics g) {
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/images/touchpad-desactiva-icon.png"));
		g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
