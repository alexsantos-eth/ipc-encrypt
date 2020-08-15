package Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase estatica con herramientas generales en todo el proyecto
 */
public class Utils {

	/**
	 * Imprimir en consola System.out.print()
	 * 
	 * @param msg
	 */
	public static void print(String msg) {
		System.out.print(msg);
	}

	public static void promptEnterKey() {
		try {
			Utils.print("\nPresiona \"ENTER\" para continuar... ");
			System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtener el multiplo de un numero
	 * 
	 * @param length Numero o longitud
	 * @return int
	 */
	public static int getFactor(int length) {
		int unitFactor = 0;
		int[] factors = { 3, 4, 5, 7, 11, 13, 17 };

		// OBTENER FACTOR
		for (int factorIndex = 0; factorIndex < factors.length; factorIndex++)
			if (length % factors[factorIndex] == 0) {
				unitFactor = factors[factorIndex];
				break;
			}

		return unitFactor;
	}

	/**
	 * Limpiar pantalla
	 * 
	 * @return
	 */
	public static void cls() {
		// LIMPIAR PANTALLA
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * Redondea los valores de un array double a valores con 3 decimales
	 * 
	 * @param arry Array de dobles
	 * @return String[]
	 */
	public static String[] roundArray(double[] arry) {
		// SALIDA
		String[] out = new String[arry.length];
		String log = "";

		// CONVERTIR
		for (int index = 0; index < arry.length; index++) {
			String format = String.format("%.3f", arry[index]);
			out[index] = format.equals("-0.000") ? "0.000" : format;
			log += "[" + out[index] + "] ";
		}

		// GUARDAR
		Utils.log("ROUND ARRAY", log);

		// RETORNAR SALIDA
		return out;
	}

	/**
	 * Genera logs de todos los procesos en un archivo process.log
	 * 
	 * @param title   Titulo del log
	 * @param content Contenido del log
	 */
	public static void log(String title, String content) {
		try {
			// BUFFER
			String line = "";
			String tmpFileStr = "";
			BufferedReader reader = new BufferedReader(new FileReader("../process.log"));

			// LEER ARCHIVO
			while ((line = reader.readLine()) != null)
				tmpFileStr += line + "\n";

			// CREAR FECHA
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM hh:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			// ASIGNAR TEXTO
			tmpFileStr += dtf.format(now) + " INFO " + title + "\n" + content + "\n";

			// CREAR ARCHIVO
			Files.createFile(tmpFileStr, "../process.log");

			// CERRAR READER
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}