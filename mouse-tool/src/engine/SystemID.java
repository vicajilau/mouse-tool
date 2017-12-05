package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import excepciones.ExcepcionObteniendoID;

public class SystemID {
	
	private String id;
	
	/**
	 * Constructor de clase
	 * @throws ExcepcionObteniendoID Puede no obtener el ID, se debe tratar en la declaración
	 * @throws IOException 
	 */
	public SystemID() throws ExcepcionObteniendoID, IOException {
		id = getIDTouchpad();
	}
	
	/**
	 * Obtiene el ID asociado al Touchpad en este sistema
	 * @return El ID en forma de cadena
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * En base a la configuración del sistema, calcula el id del Touchpad y lo devuelve
	 * @return El ID del dispositivo
	 * @throws ExcepcionObteniendoID Puede no obtener el ID, se debe tratar en la declaración
	 * @throws IOException 
	 */
	public String getIDTouchpad() throws ExcepcionObteniendoID, IOException{
		int devices = getDevicesTouchpad();
    	if(devices > 1) {
    		// Hay más de 1 dispositivo (Se debe resolver posibles conflictos en múltiples ids en el futuro)
    		FileConfiguracion fc = new FileConfiguracion();
    		if(!fc.existeFicheroConfiguracion()) {
    			fc.creaFicheros(getIDMultiplesDispositivos());
    		}else {
    			FileReader fr = new FileReader( fc.getFile());
    			BufferedReader br = new BufferedReader(fr);
    			String linea = br.readLine();
    			br.close();
    			return linea.trim();
    		}
    		try {
				return getIDMultiplesDispositivos();
			} catch (ExcepcionObteniendoID e) {
				// Algo ha salido mal al obtener el ID (Se debería generar excepcion en el futuro)
	    		throw new ExcepcionObteniendoID("No ha encontrado nigún ID");
			}
    	}else if(devices==1) {
    		return getIDConsola();
    	}else {
    		// Algo ha salido mal al obtener el ID (Se debería generar excepcion en el futuro)
    		throw new ExcepcionObteniendoID("No ha encontrado nigún ID");
    	}
	}
	
    /**
     * Comprueba si la linea de los dispositivos de entrada es la del touchad
     * @param linea La línea donde aparece
     * @return
     */
    private boolean esLineaDelTouchpad(String linea){
     return linea.trim().toLowerCase().contains("touchpad");
    }
	
	
    /**
     * Devuelve el ID asociado al touchpad en xinput
     * @return El ID asociado al touchpad
     */
    private String getIDConsola(){
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
                return getID(line);
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
    private String getIDMultiplesDispositivos() throws ExcepcionObteniendoID{
        try {
            Boolean encontrado = false;
            
            // Ejecuta un comando sin argumentos
            Process p = Runtime.getRuntime().exec("xinput list");  
            BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));  
            String line = null;  
            while (!encontrado && ((line = in.readLine()) != null)) {  
                if(esLineaDelTouchpad(line)){
                    Terminal.enviarComandoSinDevol("xinput disable "+getID(line));
                    int resultado = JOptionPane.showConfirmDialog(null, "Se ha detectado varios dispositivos Touchpad y por ello hemos procedido a realizar una prueba, ¿Esto ha desactivado el touchpad? seleccione sí o no", "Asistente de configuración", JOptionPane.YES_NO_OPTION);
                	if(resultado==JOptionPane.YES_OPTION) {
                		encontrado = true;
                		Terminal.enviarComandoSinDevol("xinput enable "+getID(line));
                	}
                }
            }
            if(encontrado){
                return getID(line);
            }
        } catch (IOException e) {
        }
        throw new ExcepcionObteniendoID("No ha podido obtenerse ningún ID");
    }
    
    /**
     * Obtiene el numero id del touchpad de la linea
     * @param line La linea de la consola donde aparece el ID
     */
    private String getID(String line){
     int inicio = line.indexOf("=")+1;
     int fin = inicio + 2;
     
     return line.substring(inicio, fin).trim();
    }
    
    /**
     * Comprueba cuantos dispositivos Touchpad hay en el sistema
     * @return El número de dispositivos Touchpad
     */
    private int getDevicesTouchpad() {
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
}
