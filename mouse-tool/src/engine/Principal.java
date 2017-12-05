package engine;

import java.io.IOException;

import javax.swing.JOptionPane;

import excepciones.ExcepcionObteniendoID;
import graphics.PanelActivar;
import graphics.PanelDesactivar;
import graphics.WindowsWithPanel;;

public class Principal {
	
	
	/**
	 * Entrada al programa
	 * @param args
	 */

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		System.out.print("Los parámetros introducidos son: " );
		for (int i=0; i<args.length; i++) {
			System.out.print(args[i] + " ");
		}
		System.out.println();
		
		if(Sistema.esSoAdmitido()) {
			try {
				SystemID sysID = new SystemID();
				System.out.println(sysID.getID());
			
				if(PValidador.esParamValido(args)) {
					Terminal terminal = new Terminal(sysID.getID());
					// Comprueba que el parámetro es un parámetro valido
						if(args[0].equals(PValidador.parametroActivar)) {
							// Es el parámetro para activar
							terminal.enviarComandoActivarTouchpad();
							WindowsWithPanel ventanaActivar = new WindowsWithPanel(new PanelActivar());
							ventanaActivar.dispose();
							
						}else if(args[0].equals(PValidador.parametroDesactivar)) {
							// Es el parámetro para desactivar
							terminal.enviarComandoDesactivarTouchpad();
							WindowsWithPanel ventanaDesactivar = new WindowsWithPanel(new PanelDesactivar());
							ventanaDesactivar.dispose();
						}
						
					} else {
						// No es un parámetro válido
						System.out.println("No es un parámetro válido");
					}
					
				} catch (ExcepcionObteniendoID e) {
					JOptionPane.showMessageDialog(null, "No se ha podido obtener el ID asociado al touchpad, compruebe que su equipo dispone de xinput", "Ups, algo ha salido mal", JOptionPane.ERROR_MESSAGE);
				}
			
		}else {
			JOptionPane.showMessageDialog(null, "La distribución GNU/Linux " + Sistema.DISTRIB_DESCRIPTION+ " no es compatible con esta aplicación", "Distribución no compatible", JOptionPane.INFORMATION_MESSAGE );
		}
	}

}