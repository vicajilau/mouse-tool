package engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sistema {
	 
    public static String DISTRIB_DESCRIPTION = Sistema.cualDistribucionLinux();
    
    /**
     * Comprueba si el SO es admitido o no
     * @return Si es posible ejecutar la aplicación en esta distribución
     */
    public static boolean esSoAdmitido() {
    	String SO1="Debian";
    	String SO2="Linux Mint";
    	String SO3="Ubuntu";
    	
    	boolean esEjecutable = false;
    	if(DISTRIB_DESCRIPTION.contains(SO1)||DISTRIB_DESCRIPTION.contains(SO2)||DISTRIB_DESCRIPTION.contains(SO3)){
    		esEjecutable = true;
    	}
    	return esEjecutable;
    }
    
    private static String cualDistribucionLinux() {
    	 try {
         	String cadenaABuscar= "Description:";
             Boolean encontrado = false;
             
             // Ejecuta un comando sin argumentos
             Process p = Runtime.getRuntime().exec("lsb_release -a");  
             BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));  
             String line = null;  
             while (!encontrado && ((line = in.readLine()) != null)) {  
                 if(line.contains(cadenaABuscar)){
                     encontrado = true;
                     return line.substring(13);
                 }
             }
         } catch (IOException e) {
         }
         return "DESCONOCIDO";
     }
 
}