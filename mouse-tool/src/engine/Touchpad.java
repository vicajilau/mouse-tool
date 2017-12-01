package engine;

public class Touchpad{
    private int id;
    /**
     * Constructor de objetos de la clase Touchpad
     */
    public Touchpad(){
        String idText=getID(CMD.getIDConsola());
        if (idText!=null){
            this.id = Integer.parseInt(idText);
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
    	String comando = "xinput set-prop " + getID() + " \"Device Enabled\" 0";
    	CMD.enviarComandoSinDevol(comando);
    }
    
    /**
     * Desactiva el touchpad
     */
    public void desactivarTouchpad() {
    	String comando = "xinput set-prop " + getID() + " \"Device Enabled\" 0";
    	CMD.enviarComandoSinDevol(comando);
    }
}
