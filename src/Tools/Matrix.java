package Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;

/**
 * Clase estatica con operaciones entre matrices
 */
public class Matrix {

	/**
	 * Multiplica dos matrices cuando sea posible
	 * 
	 * @param matrix     matriz A de NxM
	 * @param fileMatrix matriz B de NxM
	 * @return int[][] matriz resultante de NxM
	 */
	public static int[][] multiplyMatrix(int[][] matrix, int[][] fileMatrix) {
		// VARIABLES DE MATRICES
		int cols = matrix[0].length;
		int[][] outMatrix = new int[matrix.length][cols];

		for (int matrixRows = 0; matrixRows < matrix.length; matrixRows++) {
			// FILA ACTUAL
			int[] currentRow = new int[cols];

			for (int rowIndex = 0; rowIndex < cols; rowIndex++) {
				// UNIDAD DE SUMA
				int unit = 0;

				// OBTENER FACTOR
				for (int matrixCols = 0; matrixCols < matrix[0].length; matrixCols++)
					unit += matrix[matrixRows][matrixCols] * fileMatrix[matrixCols][rowIndex];

				// ASIGNAR ESPACIO A FILA
				currentRow[rowIndex] = unit;
			}

			// ASIGNAR FILA
			outMatrix[matrixRows] = currentRow;
		}

		// GUARDAR MATRIZ
		Utils.log("MULTIPLICAR MATRICES", toString(outMatrix, false));

		// RETORNAR FILA
		return outMatrix;
	}

	/**
	 * Multiplica dos matrices cuando sea posible
	 * 
	 * @param matrix     matriz doble A de NxM
	 * @param fileMatrix matriz entera B de NxM
	 * @return int[][] matriz resultante de NxM
	 */
	public static long[][] multiplyMatrix(int[][] matrix, double[][] fileMatrix) {
		// VARIABLES DE MATRICES
		int cols = matrix[0].length;
		long[][] outMatrix = new long[matrix.length][cols];

		for (int matrixRows = 0; matrixRows < matrix.length; matrixRows++) {
			// FILA ACTUAL
			long[] currentRow = new long[cols];

			for (int rowIndex = 0; rowIndex < cols; rowIndex++) {
				// UNIDAD DE SUMA
				Double unit = 0.0;

				// OBTENER FACTOR
				for (int matrixCols = 0; matrixCols < matrix[0].length; matrixCols++) {
					Double product = (double) (matrix[matrixRows][matrixCols] * fileMatrix[matrixCols][rowIndex]);
					unit += product;
				}

				// ASIGNAR ESPACIO A FILA
				currentRow[rowIndex] = Math.round(unit);
			}

			// ASIGNAR FILA
			outMatrix[matrixRows] = currentRow;
		}

		// GUARDAR MATRIZ
		Utils.log("MULTIPLICAR MATRICES", toString(outMatrix, false));

		// RETORNAR FILA
		return outMatrix;
	}

	/**
	 * Leer una matriz de un archivo de texto y retornar un objeto FileMatrix
	 * 
	 * @param input      Instancia del Scanner global
	 * @param title      Titulo para el prompt del archivo
	 * @param unitFactor Factor a evaluar matriz cuadrada NxN
	 * @return FileMatrix
	 */
	public static FileMatrix readMatrixFile(Scanner input, String title, int rows, int cols, boolean inferSize) {
		// RUTA
		int[][] fileMatrix = new int[rows][cols];
		boolean breakRead = false;
		int errCode = 0;

		while (!breakRead) {
			// RUTA
			String pathname = "";
			String eString = errCode == 3 ? "| Error 03: Longitud de archivo invalida |"
					: errCode == 2 ? "| Error 02: No se pudo leer el archivo   |"
							: errCode == 1 ? "| Error 01: La ruta no es un archivo     |" : "| " + title;

			// PEDIR RUTA
			while (pathname.length() == 0) {
				// IMPRIMIR MENU
				Menus.printMenu(eString + "\n| Escribe la ruta del archivo:           |");
				Utils.print(
						"\nRaiz: " + Paths.get("").toAbsolutePath().getParent() + "/\n" + "Escribir direccion (LFS o UNIX): ");

				if (input.hasNext())
					// ASIGNAR RUTA
					pathname = "../" + input.next();
			}

			// LEER ARCHIVO
			File fileTxt = new File(pathname);

			if (fileTxt.isFile()) {
				try {
					// BUFFER
					String line = "";
					String tmpFileStr = "";
					BufferedReader reader = new BufferedReader(new FileReader(fileTxt));

					// LEER ARCHIVO
					while ((line = reader.readLine()) != null)
						tmpFileStr += line + "\n";

					// ARRAY DE STRINGS
					String[] lineRows = tmpFileStr.split("\n");
					String[] lineCols = lineRows[0].split(",");
					String[] tmpLine = tmpFileStr.split(",|\n");

					// ASIGNAR DIMENSION DINAMICA
					if (inferSize)
						fileMatrix = new int[lineRows.length][lineCols.length];

					// VERIFICAR LONGITUD
					if (tmpLine.length == (inferSize ? lineRows.length * lineCols.length : rows * cols)) {
						// CONVERTIR A ENTERO
						for (int lineIndex = 0; lineIndex < (inferSize ? lineRows.length : rows); lineIndex++)
							for (int lineCol = 0; lineCol < (inferSize ? lineCols.length : cols); lineCol++)
								fileMatrix[lineIndex][lineCol] = Integer
										.parseInt(tmpLine[lineCol + (lineIndex * (inferSize ? lineCols.length : cols))]);

						// SALIR DEL CICLO
						breakRead = true;
					} else
						errCode = 3;

					// CERRAR BUFFER
					reader.close();
				} catch (Exception e) {
					errCode = 2;
				}
			} else
				errCode = 1;
		}

		// RETORNAR MATRIZ
		FileMatrix respMatrix = new FileMatrix(fileMatrix, toString(fileMatrix, false));
		Utils.log("LEER MATRIZ CON CLAVES DE ARCHIVO", respMatrix.matrixString);

		return respMatrix;
	}

	/**
	 * Genera una matriz con valores enteros ASCII de un texto
	 * 
	 * @param text Texto a mapear valores ASCII
	 * @param cols Numero de columnas del texto o factor
	 * @return FileMatrix
	 */
	public static FileMatrix getAsciiMatrix(String text, int cols) {
		// INICIALIZAR
		int length = text.length();
		int[] ascii = new int[length];

		// OBTENER FILAS Y COLUMNAS
		int rows = length / cols;

		// CREAR MATRIZ
		String[][] tmpMatrix = new String[rows][cols];
		int[][] matrix = new int[rows][cols];

		// ARRAY DE STRING
		for (int charIndex = 0; charIndex < length; charIndex++)
			ascii[charIndex] = (int) text.charAt(charIndex);

		// ARRAY DE INT
		for (int charIndex = 0; charIndex < length; charIndex++)
			ascii[charIndex] = (int) text.charAt(charIndex);

		// ASIGNAR A MATRIZ
		for (int rowsIndex = 0; rowsIndex < rows; rowsIndex++)
			// RECORRER COLUMNAS
			for (int colsIndex = 0; colsIndex < cols; colsIndex++) {
				tmpMatrix[rowsIndex][colsIndex] = Character.toString((char) ascii[colsIndex + (rowsIndex * cols)]);
				matrix[rowsIndex][colsIndex] = ascii[colsIndex + (rowsIndex * cols)];
			}

		// GUARDAR MATRIZ DE TEXTO
		Utils.log("OBTENER CARACTERES", toString(tmpMatrix));
		Utils.log("CONVERTIR A CODIGO ASCII", toString(matrix, false));

		// DEVOLVER TEXTO
		return new FileMatrix(matrix, toString(matrix, false));
	}

	/**
	 * Calcula la matriz transpuesta de una matriz entera
	 * 
	 * @param matrix Matriz NxN
	 * @return int[][]
	 */
	public static int[][] transposedMatrix(int[][] matrix) {
		// MATRIZ DE SALIDA
		int[][] out = new int[matrix[0].length][matrix.length];

		// INVERTIR ORDEN
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix.length; j++)
				out[i][j] = matrix[j][i];

		// RETORNAR MATRIZ
		return out;
	}

	/**
	 * Calcula la matriz de cofactores de una matriz entera
	 * 
	 * @param matrix Matriz NxN
	 * @return int[][]
	 */
	public static int[][] cofactorMatrix(int[][] matrix) {
		// SALIDA
		int[][] outMatrix = new int[matrix.length][matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				// MATRIZ PARA DETERMINANTE
				int[][] det = new int[matrix.length - 1][matrix.length - 1];
				int detValue;

				for (int k = 0; k < matrix.length; k++)
					// VERIFICAR POSICION DE COFACTOR
					if (k != i)
						for (int l = 0; l < matrix.length; l++)
							// CAMBIAR SIGNO
							if (l != j) {
								int indice1 = k < i ? k : k - 1;
								int indice2 = l < j ? l : l - 1;
								det[indice1][indice2] = matrix[k][l];
							}

				// CALCULAR DETERMINANTE DE SUB MATRIZ
				detValue = determinant(det);

				// ASIGNAR VALOR DETERMINANTE * (SIGNO)^(I+J)
				outMatrix[i][j] = detValue * (int) Math.pow(-1, i + j + 2);
			}
		}

		// RETORNAR SALIDA
		return outMatrix;
	}

	/**
	 * Calcula una matriz adjunta de una matriz entera con A(T)(Adj)
	 * 
	 * @param matrix Matriz NxN
	 * @return int[][]
	 */
	public static int[][] attachedMatrix(int[][] matrix) {
		return transposedMatrix(cofactorMatrix(matrix));
	}

	/**
	 * Calcula el determinante de una matriz entera
	 * 
	 * @param matrix Matriz NxN
	 * @return int
	 */
	public static int determinant(int[][] matrix) {
		// CALCULAR PARA 2X2
		if (matrix.length == 2)
			return (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);

		// CALCULAR PARA NXN
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			// DEFINIR MATRIZ
			int[][] nm = new int[matrix.length - 1][matrix.length - 1];

			// RECORRER MATRIZ
			for (int j = 0; j < matrix.length; j++)
				// ENCONTRAR PARAMETRO I+J
				if (j != i)
					// RECORRER FILAS
					for (int k = 1; k < matrix.length; k++) {
						// INVERVALOS NEGATIVOS
						int indice = -1;

						// POSITIVOS
						if (j < i)
							indice = j;

						// NEGATIVOS
						else if (j > i)
							indice = j - 1;

						// CALCULAR COFACTOR
						nm[indice][k - 1] = matrix[j][k];
					}

			// RECURSION
			if (i % 2 == 0)
				sum += matrix[i][0] * determinant(nm);
			else
				sum -= matrix[i][0] * determinant(nm);
		}

		// RETORNAR SUMA RECURSIVA
		return sum;
	}

	/**
	 * Multiplica una matriz por una constante
	 * 
	 * @param n      Constante a multiplicar por matrix K * A
	 * @param matrix Matriz NxN
	 * @return double[][]
	 */
	public static double[][] multiplyMatrixFactor(double n, int[][] matrix) {
		// MATRIZ DE SALIDA
		double[][] outMatrix = new double[matrix.length][matrix[0].length];

		for (int row = 0; row < matrix.length; row++)
			for (int column = 0; column < matrix.length; column++)
				// MULTIPLICAR POR FACTOR
				outMatrix[row][column] = (double) matrix[row][column] * n;

		// RETORNAR MATRIZ
		return outMatrix;
	}

	/**
	 * Calcula la inversa de una matriz
	 * 
	 * @param matrix Matriz NxN
	 * @return double[][]
	 */
	public static double[][] invertedMatrix(int[][] matrix) {
		// DETERMINANTE Y ADJUNTA
		double det = (double) 1 / determinant(matrix);
		int[][] nMatrix = attachedMatrix(matrix);

		// MULTIPLICAR POR DETERMINANTE
		double[][] inverted = multiplyMatrixFactor(det, nMatrix);

		// GUARDAR MATRIZ
		Utils.log("INVERTIR MATRIZ", toString(inverted, false));

		// RETORNAR INVERSA
		return inverted;
	}

	/**
	 * Convierte una matriz ASCII a string
	 * 
	 * @param asciiMatrix Matriz Long NxM
	 * @return String
	 */
	public static String asciiMatrixToString(long[][] asciiMatrix) {
		// SALIDA
		String out = "";

		for (int row = 0; row < asciiMatrix.length; row++)
			for (int col = 0; col < asciiMatrix[0].length; col++) {
				// ASIGNAR CARACTER
				int currentChar = (int) asciiMatrix[row][col];
				out += Character.toString((char) currentChar);
			}

		// GUARDAR
		Utils.log("CONVERTIR DE CODIGO ASCII A TEXTO", "-".repeat(out.length()) +"\n| " + out + " |\n"+"-".repeat(out.length()));

		// RETORNAR SALIDA
		return out;
	}

	/**
	 * Convierte una matriz entera a texto
	 * 
	 * @param matrix Matriz NxM
	 * @return String Texto de la matriz
	 */
	public static String toString(int[][] matrix, boolean plain) {
		// PLACEHOLDER
		String out = plain ? "" : "<!>\n";

		// FORMATO DE DIGITOS
		String format = plain ? "0000;-0000" : "+0000;-0000";
		DecimalFormat formater = new DecimalFormat(format);

		// RECORRER MATRIZ
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++)
				// ASIGNAR ENTERO CON FORMATO
				out += (plain ? "" : " | ") + formater.format(matrix[row][col]) + (plain ? "," : "");

			// AGREGAR PLACEHOLDERS
			out += plain ? "\n" : " |\n";

			if (!plain)
				out += "<!>\n";
			else
				out = out.replaceAll(",\n", "\n");
		}

		if (!plain) {
			// OBTENER LONGITUD DE LINEA
			String[] lines = out.split("<!>\n");
			int maxLine = lines[lines.length - 1].length();

			// REMPLAZAR PLACEHOLDER POR LINEAS
			out = out.replaceAll("<!>", " " + "-".repeat(maxLine - 2));
		}

		// RETORNAR STRING
		return out;
	}

	/**
	 * Convierte una matriz doble a texto
	 * 
	 * @param matrix Matriz NxM
	 * @return String Texto de la matriz
	 */
	public static String toString(double[][] matrix, boolean plain) {
		// PLACEHOLDER
		String out = plain ? "" : "<!>\n";

		// FORMATO DE DIGITOS
		String format = plain ? "000.000;-000.000" : "+000.000;-000.000";
		DecimalFormat formater = new DecimalFormat(format);

		// RECORRER MATRIZ
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++)
				// ASIGNAR ENTERO CON FORMATO
				out += (plain ? "" : " | ") + formater.format(matrix[row][col]) + (plain ? "," : "");

			// AGREGAR PLACEHOLDERS
			out += plain ? "\n" : " |\n";

			if (!plain)
				out += "<!>\n";
			else
				out = out.replaceAll(",\n", "\n");
		}

		if (!plain) {
			// OBTENER LONGITUD DE LINEA
			String[] lines = out.split("<!>\n");
			int maxLine = lines[lines.length - 1].length();

			// REMPLAZAR PLACEHOLDER POR LINEAS
			out = out.replaceAll("<!>", " " + "-".repeat(maxLine - 2));
		}

		// RETORNAR STRING
		return out;
	}

	/**
	 * Convierte una matriz long a texto
	 * 
	 * @param matrix Matriz NxM
	 * @return String Texto de la matriz
	 */
	public static String toString(long[][] matrix, boolean plain) {
		// PLACEHOLDER
		String out = plain ? "" : "<!>\n";

		// FORMATO DE DIGITOS
		String format = plain ? "0000;-0000" : "+0000;-0000";
		DecimalFormat formater = new DecimalFormat(format);

		// RECORRER MATRIZ
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++)
				// ASIGNAR ENTERO CON FORMATO
				out += (plain ? "" : " | ") + formater.format(matrix[row][col]) + (plain ? "," : "");

			// AGREGAR PLACEHOLDERS
			out += plain ? "\n" : " |\n";

			if (!plain)
				out += "<!>\n";
			else
				out = out.replaceAll(",\n", "\n");
		}

		if (!plain) {
			// OBTENER LONGITUD DE LINEA
			String[] lines = out.split("<!>\n");
			int maxLine = lines[lines.length - 1].length();

			// REMPLAZAR PLACEHOLDER POR LINEAS
			out = out.replaceAll("<!>", " " + "-".repeat(maxLine - 2));
		}

		// RETORNAR STRING
		return out;
	}

	/**
	 * Convierte una matriz string a texto como tabla
	 * 
	 * @param matrix Matriz NxM
	 * @return String Texto de la matriz
	 */
	public static String toString(String[][] matrix) {
		// PLACEHOLDER
		String out = "<!>\n";

		// RECORRER MATRIZ
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++)
				// ASIGNAR ENTERO CON FORMATO
				out += " | " + matrix[row][col];

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
	 * Convierte una matriz de enteros a matriz de dobles
	 * 
	 * @param matrix Matriz entera NxM
	 * @return double[][]
	 */
	public static double[][] toDouble(int[][] matrix) {
		// GLOBALES
		int rows = matrix.length;
		int cols = matrix[0].length;

		// SALIDA
		double[][] tmpMatrix = new double[rows][cols];

		// CONVERTIR COEFICIENTES DOUBLE
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++)
				tmpMatrix[row][col] = (double) matrix[row][col];

		// RETORNAR SALIDA
		return tmpMatrix;
	}
}