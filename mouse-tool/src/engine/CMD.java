package engine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMD {
	
	/**
	 * Envía un comando sin parámetros que no devuelve nada
	 * @param comando El comando a introducir
	 */
	public static void enviarComandoSinDevol(String comando) {
		try {
		    Runtime.getRuntime().exec(comando);
		} catch (IOException e) {
		}
	}
	
    /**
     * Comprueba si la linea de los dispositivos de entrada es la del touchad
     * @param linea La línea donde aparece
     * @return
     */
    private static boolean esLineaDelTouchpad(String linea){
     return linea.trim().toLowerCase().contains("touchpad");
    }
	
	/**
	 * Envía un comando sin parámetros que no devuelve nada
	 * @param comando El comando a introducir
	 */
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static String getIDConsola(){

        try {
            Boolean encontrado = false;
            
            // Ejecuta un comando sin argumentos
            Process p = Runtime.getRuntime().exec("xinput list");  
            BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));  
            String line = null;  
            while (!encontrado && ((line = in.readLine()) != null)) {  
                if(esLineaDelTouchpad(line)){
                    encontrado = true;
                }
            }
            if(encontrado){
                return line;
            }
        } catch (IOException e) {
        }
        return null;
    }
}