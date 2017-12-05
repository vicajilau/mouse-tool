package engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileConfiguracion {
	private String rutaCarpeta;
	private String archivo;
	File af;

	public FileConfiguracion() {
		rutaCarpeta="./.mouse-tool/";
		archivo="config-id";
		af = new File(rutaCarpeta+archivo);
	}
	
	public boolean existeFicheroConfiguracion() {
		return af.exists() && af.isFile();
	}
	
	public File getFile() {
		return af;
	}
	/**
	 * Crea los ficheros de configuración y su carpeta
	 * @throws IOException Excepcion de creacion y 
	 */
	public void creaFicheros(String id) throws IOException{
		File crea_carpeta = new File(rutaCarpeta);
		File crea_archivo = new File(rutaCarpeta+archivo);
		if(crea_archivo.exists()) {
			JOptionPane.showMessageDialog(null, "El archivo " + crea_archivo.getAbsolutePath() + " ya existe");
		}else {
			JOptionPane.showMessageDialog(null, "El archivo " + crea_archivo.getAbsolutePath() + " no existe pero se creará");
			try {
				crea_carpeta.mkdirs();
					if(crea_archivo.createNewFile()) {
						BufferedWriter bw = new BufferedWriter(new FileWriter(crea_archivo));
				        bw.write(id);
				        bw.close();
						JOptionPane.showMessageDialog(null, "Archivos creados");
					}else {
						JOptionPane.showMessageDialog(null, "Archivos no creados");
					}
				
			}catch(IOException ex) {
				JOptionPane.showMessageDialog(null, "La carpeta " + ex.getMessage() + " no ha podido crearse", "Error al crear carpeta", JOptionPane.ERROR_MESSAGE);
				throw new IOException(ex.getCause());
			}
		}
	}
}
