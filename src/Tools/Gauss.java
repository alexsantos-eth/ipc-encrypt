package Tools;

/**
 * Gauss
 */
public class Gauss {

	public static double[] resolve(int[][] gaussMatrix) {
		// GLOBALES
		int rows = gaussMatrix.length;
		int cols = gaussMatrix[0].length;

		// SALIDA
		double[] out = new double[rows];
		double[][] tmpMatrix = Matrix.toDouble(gaussMatrix);

		for (int row = 0; row < rows; row++) {
			// CALCULAR PIVOTES Y REPETICIONES DE 0
			int repeat = row % 2 == 0 ? 2 : 1;
			double pivot = tmpMatrix[row][row];

			// CREAR FILA DE PIVOTE A 1
			for (int col = 0; col < cols; col++)
				tmpMatrix[row][col] = tmpMatrix[row][col] / pivot;

			// RECORRER FILA PARA HACER 0
			for (int times = 0; times < repeat; times++) {
				// CALCULAR POSICION DEL ELEMENTO EN FILA
				int sign = row % 2 == 0 ? 1 : 0;
				int scale = row != 1 ? repeat - sign + (times * (sign - row)) : 2;

				// COEFICIENTE A HACER 0
				double coeficient = tmpMatrix[scale][row];

				// RESTAR FILAS CON COEFICIENTE
				for (int col = 0; col < cols; col++)
					tmpMatrix[scale][col] = tmpMatrix[scale][col] - (tmpMatrix[row][col] * coeficient);

			}
		}

		// ARREGLAR VALOR FLOTANTE
		tmpMatrix[0][3] = tmpMatrix[0][3] - (tmpMatrix[1][3] * tmpMatrix[0][1]);

		// ASIGNAR RESPUESTAS
		for (int res = 0; res < rows; res++)
			out[res] = tmpMatrix[res][3];

		// RETORNAR SALID
		return out;
	}
}