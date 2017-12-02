package engine;

import javax.swing.JOptionPane;

public class Principal {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Los parámetros introducidos son: " );
		for (int i=0; i<args.length; i++) {
			System.out.print(args[i] + " ");
		}
		System.out.println();
		
		if(Sistema.esSoAdmitido()) {
			GestorScripts gestorFile = new GestorScripts();
			Touchpad touchpad = new Touchpad();
			// Si los script está desconfigurado deben configurarse
			if(gestorFile.esConfigPorDefecto(GestorScripts.direccionScript1, GestorScripts.direccionScript2)) {
				System.out.println("Se debe proceder a configurar los Scripts");
				gestorFile.configuraScripts(GestorScripts.direccionScript1, GestorScripts.direccionScript2, touchpad.getID());
			}
			
			// Comprueba que el parámetro es un parámetro valido
			if (PValidador.esParamValido(args)) {
				if(args[0].equals(PValidador.parametroActivar)) {
					// Es el parámetro para activar
					CMD.enviarComandoSinDevol(GestorScripts.direccionScript1);
					
				}else if(args[0].equals(PValidador.parametroDesactivar)) {
					// Es el parámetro para desactivar
					CMD.enviarComandoSinDevol(GestorScripts.direccionScript2);
				}
				
			} else {
				// No es un parámetro válido
				System.out.println("No es un parámetro válido");
			}
		}else {
			JOptionPane.showMessageDialog(null, "La distribución GNU/Linux " + Sistema.DISTRIB_DESCRIPTION+ " no es compatible con esta aplicación", "Distribución no compatible", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}