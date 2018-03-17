package engine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {
	
	private String comandoActivarTouchpad;
	private String comandoDesactivarTouchpad;
	
	public Terminal(String id) {
		comandoActivarTouchpad="xinput enable "+ id;
		comandoDesactivarTouchpad="xinput disable " + id;
	}
	
	/**
	 * Envía un comando sin parámetros que no devuelve nada
	 * @param comando El comando a introducir
	 */
	public static void enviarComandoSinDevol(String comando) {
		try {
			System.out.println("Se va a enviar a la línea de comandos: " + comando);
		    Runtime.getRuntime().exec(comando);
		} catch (IOException e) {
		}
	}
	

	/**
	 * Envia el comando para activar el touchpad
	 */
	public void enviarComandoActivarTouchpad() {
		enviarComandoSinDevol(comandoActivarTouchpad);
	}
	
	/**
	 * Envia el comando para desactivar el touchpad
	 */
	public void enviarComandoDesactivarTouchpad() {
		enviarComandoSinDevol(comandoDesactivarTouchpad);
	}
	
    
    /**
     * Envía un comando para saber la dirección en donde está buscando el fichero y es devuelta 
     * como un String
     * @return La ruta en donde se encuentra
     */
    public String getPWD() {
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