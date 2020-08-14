package Tools;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Files
 */
public class Files {
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

	public static void createMatrixFile(String matrixContent) {
		// FECHA DIA / MES / MINUTOS
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_ss");
		LocalDateTime now = LocalDateTime.now();

		// CREAR ARCHIVO
		createFile(matrixContent, "../" + dtf.format(now) + ".txt");
	}
}