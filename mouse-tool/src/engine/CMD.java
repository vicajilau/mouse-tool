package engine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import excepciones.ExcepcionObteniendoID;

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
     * Devuelve el ID asociado al touchpad en xinput
     * @return El ID asociado al touchpad
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
    
    /**
     * 
     * @param n
     * @return
     */
    public static String getIDMultiplesDispositivos(int n) throws ExcepcionObteniendoID{
        try {
            Boolean encontrado = false;
            
            // Ejecuta un comando sin argumentos
            Process p = Runtime.getRuntime().exec("xinput list");  
            BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));  
            String line = null;  
            while (!encontrado && ((line = in.readLine()) != null)) {  
                if(esLineaDelTouchpad(line)){
                    enviarComandoSinDevol("xinput disable "+getID(line));
                    int resultado = JOptionPane.showConfirmDialog(null, "Se ha procedido a realizar una prueba, ¿Esto ha desactivado el touchpad? seleccione sí o no", "Ventana de configuración", JOptionPane.YES_NO_OPTION);
                	if(resultado==JOptionPane.YES_OPTION) {
                		encontrado = true;
                		enviarComandoSinDevol("xinput enable "+getID(line));
                	}
                }
            }
            if(encontrado){
                return line;
            }
        } catch (IOException e) {
        }
        throw new ExcepcionObteniendoID("No ha podido obtenerse ningún ID");
    }
    
    /**
     * Obtiene el numero id del touchpad de la linea
     * @param line La linea de la consola donde aparece el ID
     */
    private static String getID(String line){
     int inicio = line.indexOf("=")+1;
     int fin = inicio + 2;
     
     return line.substring(inicio, fin).trim();
    }
    
    /**
     * Comprueba cuantos dispositivos Touchpad hay en el sistema
     * @return El número de dispositivos Touchpad
     */
    public static int getDevicesTouchpad() {
    	int devices = 0;  
    	 try {
             // Ejecuta un comando sin argumentos
             Process p = Runtime.getRuntime().exec("xinput list");  
             BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));  
             
             String line = new String();
             while ((line = in.readLine()) != null) {  
                 if(esLineaDelTouchpad(line)){
                     devices ++;
                 }
             }
         } catch (IOException e) {
         }
         return devices;
    }
    
    /**
     * Envía un comando para saber la dirección en donde está buscando el fichero y es devuelta 
     * como un String
     * @return La ruta en donde se encuentra
     */
    public static String getPWD() {
    	try {
            Boolean encontrado = false;
            
            // Ejecuta un comando sin argumentos
            Process p = Runtime.getRuntime().exec("pwd");  
            BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));  
            String line = null;  
            while (!encontrado && ((line = in.readLine()) != null)) {  
                if(line.contains("/")){
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