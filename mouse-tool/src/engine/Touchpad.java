package engine;

import excepciones.ExcepcionObteniendoID;

public class Touchpad{
    private int id;
    /**
     * Constructor de objetos de la clase Touchpad
     * @throws ExcepcionObteniendoID 
     * @throws NumberFormatException 
     */
    public Touchpad() throws NumberFormatException, ExcepcionObteniendoID{
        id = Integer.parseInt(obtenElIdDelSistema());
    }
    
    /**
     * En función del numero de IDs encontrados devuelve el ID apropiado para cada situación
     * @return Un único ID en forma de cadena
     */
    private String obtenElIdDelSistema() throws ExcepcionObteniendoID{
    	int devices = CMD.getDevicesTouchpad();
    	if(devices > 1) {
    		// Hay más de 1 dispositivo (Se debe resolver posibles conflictos en múltiples ids en el futuro)
    		return getID(CMD.getIDConsola());
    	}else if(devices==1) {
    		return getID(CMD.getIDConsola());
    	}else {
    		// Algo ha salido mal al obtener el ID (Se debería generar excepcion en el futuro)
    		throw new ExcepcionObteniendoID("No ha encontrado nigún ID");
    	}
    }
    
    /**
     * Devuelve el ID del Touchpad
     * @return el ID
     */
    public int getID() {
    	return id;
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
     * Activa el touchpad
     */
    public void activarTouchpad() {
    	String comando = "xinput enable " + getID();
    	CMD.enviarComandoSinDevol(comando);
    }
    
    /**
     * Desactiva el touchpad
     */
    public void desactivarTouchpad() {
    	String comando = "xinput disable " + getID();
    	CMD.enviarComandoSinDevol(comando);
    }
}
