package Tools;

/**
 * Clase estatica con operaciones para sistemas de ecuaciones 3x3
 */
public class Gauss {

	/**
	 * Resuelve un sistema de 3x3 utilizando Gauss-Jordan
	 * 
	 * @param gaussMatrix Sistemas de ecuaciones 3x3 expandida
	 * @return double[]
	 */
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

			Utils.log("CONVERT PIVOT " + (row + 1) + " TO 1", Matrix.toString(tmpMatrix, false));

			// RECORRER FILA PARA HACER 0
			for (int times = 0; times < repeat; times++) {
				// CALCULAR POSICION DEL ELEMENTO EN FILA
				int sign = row % 2 == 0 ? 1 : 0;
				int scale = row != 1 ? repeat - sign + (times * (sign - row)) : 2;

				// COEFICIENTE A HACER 0
				double coefficient = tmpMatrix[scale][row];

				// RESTAR FILAS CON COEFICIENTE
				for (int col = 0; col < cols; col++) {
					tmpMatrix[scale][col] = tmpMatrix[scale][col] - (tmpMatrix[row][col] * coefficient);
					Utils.log("GET DIFFERENCE BETWEEN PIVOT " + (row + 1) + " AND [" + scale + "][" + col + "] COEFFICIENT",
							Matrix.toString(tmpMatrix, false));
				}
			}
		}

		// ARREGLAR VALOR FLOTANTE
		tmpMatrix[0][3] = tmpMatrix[0][3] - (tmpMatrix[1][3] * tmpMatrix[0][1]);
		tmpMatrix[0][1] = 0;

		// GUARDAR
		Utils.log("RESOLVE X VALUE OF ROW 1", Matrix.toString(tmpMatrix, false));

		// ASIGNAR RESPUESTAS
		for (int res = 0; res < rows; res++)
			out[res] = tmpMatrix[res][3];

		// RETORNAR SALID
		return out;
	}
}