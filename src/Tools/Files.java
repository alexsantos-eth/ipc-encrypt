package Tools;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase estatica para generar archivos y arhivos de matrices
 */
public class Files {

	/**
	 * Crea un archivo nuevo en la ruta especificada
	 * 
	 * @param content Contenido del archivo
	 * @param path    Ruta del archivo
	 */
	public static void createFile(String content, String path) {
		try {
			// CREAR
			FileWriter file = new FileWriter(path);

			// ESCRIBIR Y CERRAR
			file.write(content);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crear archivo txt con matriz plana
	 * 
	 * @param matrixContent
	 */
	public static void createMatrixFile(String matrixContent) {
		// FECHA DIA / MES / SEGUNDOS
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_ss");
		LocalDateTime now = LocalDateTime.now();

		// CREAR ARCHIVO
		createFile(matrixContent, "../" + dtf.format(now) + ".txt");
	}
}