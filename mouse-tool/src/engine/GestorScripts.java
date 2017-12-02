package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class GestorScripts {
	public static String textoPorDefectActivaRaton = "xinput set-prop XX \"Device Enabled\" 1";
	public static String textoPorDefectDesactivaRaton = "xinput set-prop XX \"Device Enabled\" 0";
	public static String direccionScript1 = "/opt/mouse-tool/config/activa-raton";
	public static String direccionScript2 = "/opt/mouse-tool/config/desactiva-raton";
	
	/**
	 * Configura los scripts por defecto a la configuración del Sistema Operativo
	 * @param file1 La dirección del 1º script para activar
	 * @param file2 La dirección del 2º script para desactivar
	 * @param id El ID del Touchpad segun el Sistema Operativo
	 */
	public void configuraScripts(String file1, String file2, int id) {
		
		String textoParaActivar = "xinput set-prop " + id +" \"Device Enabled\" 1";
		String textoParaDesactivar = "xinput set-prop " + id +" \"Device Enabled\" 0";
		
        try {
            File inFile = new File(file1);
            if (!inFile.isFile()) {
            	JOptionPane.showMessageDialog(null, "El fichero de configuración: " + file1 +" no ha sido encontrado", "Ups, algo ha ido mal", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Construct the new file that will later be renamed to the original filename. 
            BufferedReader br = new BufferedReader(new FileReader(file1));
            PrintWriter pw = new PrintWriter(new FileWriter(file1));
            String line ;
            //Read from the original file and write to the new 
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.trim().contains(textoPorDefectActivaRaton)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.println("#!/bin/bash");
            pw.println("# Script de bash");
            pw.println(textoParaActivar); // Agrega la línea de configuracion con el ID
            pw.close();
            br.close();
            
            // Ahora para el script 2
            inFile = new File(file2);
            if (!inFile.isFile()) {
            	JOptionPane.showMessageDialog(null, "El fichero de configuración: " + file2 +" no ha sido encontrado", "Ups, algo ha ido mal", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            br = new BufferedReader(new FileReader(file2));
            pw = new PrintWriter(new FileWriter(file2));
            
            //Read from the original file and write to the new 
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.trim().contains(textoPorDefectDesactivaRaton)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.println("#!/bin/bash");
            pw.println("# Script de bash");
            pw.println(textoParaDesactivar);
            pw.close();
            br.close();
 
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El fichero de configuración no ha sido encontrado", "Ups, algo ha ido mal", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
        	 JOptionPane.showMessageDialog(null, "Se ha encontrado un error al leer o escribir en los ficheros de configuración", "Ups, algo ha ido mal", JOptionPane.ERROR_MESSAGE);
        }
        
    }
	
	/**
	 * Comprueba si los Scripts tienen configuración por defecto 
	 * @param file1 El primer script para Activar el Touchpad
	 * @param file2 El segundo Script para desactivar el Touchpad
	 * @return Si tienen configuración por defecto o no.
	 */
	public boolean esConfigPorDefecto(String file1, String file2) {
		boolean esPorDefectoFile1 = false;
		boolean esPorDefectoFile2 = false;
		
		try {
			// Se comprueba si el 1º fichero tiene configuración por defecto
			File inFile1 = new File(file1);
	        if (!inFile1.isFile()) {
	        	JOptionPane.showMessageDialog(null, "El fichero de configuración: " + file1 +" no ha sido encontrado", "Ups, algo ha ido mal", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	        
	        BufferedReader br = new BufferedReader(new FileReader(file1));
	        String line ;
	        //Read from the original file and write to the new 
	        //unless content matches data to be removed.
	        while ((line = br.readLine()) != null) {
	            if (line.trim().equals(textoPorDefectActivaRaton)) {
	                esPorDefectoFile1 = true;
	            }
	        }
	        br.close();
	        
	        
	     // Se comprueba si el 1º fichero tiene configuración por defecto
	        File inFile2 = new File(file2);
			if (!inFile2.isFile()) {
				JOptionPane.showMessageDialog(null, "El fichero de configuración: " + file2 + " no ha sido encontrado",
						"Ups, algo ha ido mal", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			br = new BufferedReader(new FileReader(file2));
			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {
				if (line.trim().equals(textoPorDefectDesactivaRaton)) {
					esPorDefectoFile2 = true;
				}
			}
			br.close();
	        
		}catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return(esPorDefectoFile1 && esPorDefectoFile2);
        
	}
}
