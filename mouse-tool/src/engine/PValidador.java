package engine;

public class PValidador {
	
	private static int numeroDeParametros = 1;
	public static String parametroActivar = "-a";
	public static String parametroDesactivar = "-d";
	
	/**
	 * Indica si el número de parámetros introducidos es válido
	 * @param datosConsola Los parámetros recibidos en la consola
	 * @return Si acepta el número de parámetros o no
	 */
	private static boolean numParamValido(String[] datosConsola) {
		if(datosConsola.length==numeroDeParametros) {
			return true;
		}
		return false;
	}
	
	/**
	 * Comrprueba si el/los parámetros introducidos son válidos
	 * @param datosConsola Los datos (parámetros) introducidos por consola
	 * @return Si son correctos los parámetros
	 */
	private static boolean parametroValido(String[] datosConsola) {
		boolean correcto = true;
		for(String comando : datosConsola) {
			if(!comando.equalsIgnoreCase(parametroActivar) && !comando.equalsIgnoreCase(parametroDesactivar)) {
				// Si no se da que no es uno ni otro
				correcto = false;
			}
		}
		return correcto;
	}
	
	/**
	 * Comprueba si los datos pasados a la consola son admitibles en el programa o no
	 * @param datosConsola Los parámetros introducidos
	 * @return Si son válidos o no.
	 */
	public static boolean esParamValido(String[] datosConsola) {
		return(numParamValido(datosConsola) && parametroValido(datosConsola));
	}
}