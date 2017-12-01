package engine;

import javax.swing.JOptionPane;

public class Principal {
	
	private static String script1 = "/opt/mouse-tool/config/activa-raton";
	private static String script2 = "/opt/mouse-tool/config/desactiva-raton";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Los parámetros introducidos son: " );
		for (int i=0; i<args.length; i++) {
			System.out.print(args[i] + " ");
		}
		System.out.println();
		
		if(Sistema.esSoAdmitido()) {
			GestorFichero gestorFile = new GestorFichero();
			Touchpad touchpad = new Touchpad();
			// Si los script está desconfigurado deben configurarse
			if(gestorFile.esConfigPorDefecto(script1, script2)) {
				System.out.println("Se debe proceder a configurar los Scripts");
				gestorFile.configuraScripts(script1, script2, touchpad.getID());
			}
			
			// Comprueba que el parámetro es un parámetro valido
			if (PValidador.esParamValido(args)) {
				if(args[0].equals(PValidador.parametroActivar)) {
					// Es el parámetro para activar
					CMD.enviarComandoSinDevol(script1);
					
				}else if(args[0].equals(PValidador.parametroDesactivar)) {
					// Es el parámetro para desactivar
					CMD.enviarComandoSinDevol(script2);
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