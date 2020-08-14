package Tools;

import java.text.DecimalFormat;

/**
 * PrintMatrix
 */
public class PrintMatrix {

	/**
	 * Convierte una matriz entera a texto
	 * 
	 * @param matrix Matriz NxM
	 * @return String Texto de la matriz
	 */
	public static String toString(int[][] matrix) {
		// PLACEHOLDER
		String out = "<!>\n";

		// FORMATO DE DIGITOS
		DecimalFormat formater = new DecimalFormat("+0000;-0000");

		// RECORRER MATRIZ
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++)
				// ASIGNAR ENTERO CON FORMATO
				out += " | " + formater.format(matrix[row][col]);

			// AGREGAR PLACEHOLDERS
			out += " |\n";
			out += "<!>\n";
		}

		// OBTENER LONGITUD DE LINEA
		String[] lines = out.split("<!>\n");
		int maxLine = lines[lines.length - 1].length();

		// REMPLAZAR PLACEHOLDER POR LINEAS
		out = out.replaceAll("<!>", " " + "-".repeat(maxLine - 2));

		// RETORNAR STRING
		return out;
	}

	/**
	 * Convierte una matriz doble a texto
	 * 
	 * @param matrix Matriz de NxM
	 * @return String Texto de la matriz
	 */
	public static String toString(double[][] matrix) {
		// PLACEHOLDER
		String out = "<!>\n";

		// FORMATO DE DIGITOS
		DecimalFormat formater = new DecimalFormat("+000.000;-000.000");

		// RECORRER MATRIZ
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++)
				// ASIGNAR ENTERO CON FORMATO
				out += " | " + formater.format(matrix[row][col]);

			// AGREGAR PLACEHOLDERS
			out += " |\n";
			out += "<!>\n";
		}

		// OBTENER LONGITUD DE LINEA
		String[] lines = out.split("<!>\n");
		int maxLine = lines[lines.length - 1].length();

		// REMPLAZAR PLACEHOLDER POR LINEAS
		out = out.replaceAll("<!>", " " + "-".repeat(maxLine - 2));

		// RETORNAR STRING
		return out;
	}

	/**
	 * Convierte una matriz long a texto
	 * 
	 * @param matrix Matriz de NxM
	 * @return String Texto de la matriz
	 */
	public static String toString(long[][] matrix) {
		// PLACEHOLDER
		String out = "<!>\n";

		// FORMATO DE DIGITOS
		DecimalFormat formater = new DecimalFormat("+00000;-0000");

		// RECORRER MATRIZ
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++)
				// ASIGNAR ENTERO CON FORMATO
				out += " | " + formater.format(matrix[row][col]);

			// AGREGAR PLACEHOLDERS
			out += " |\n";
			out += "<!>\n";
		}

		// OBTENER LONGITUD DE LINEA
		String[] lines = out.split("<!>\n");
		int maxLine = lines[lines.length - 1].length();

		// REMPLAZAR PLACEHOLDER POR LINEAS
		out = out.replaceAll("<!>", " " + "-".repeat(maxLine - 2));

		// RETORNAR STRING
		return out;
	}
}