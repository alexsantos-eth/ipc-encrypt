package Tools;

import Interfaces.OutputMatrix;

/**
 * Tipo de dato necesario en el programa para guardar matrices y generar logs
 */
public class FileMatrix implements OutputMatrix {
	// PROPIEDADES
	public int[][] fileMatrix = new int[0][0];
	public String matrixString = "";

	/**
	 * Constructor: retornar una matriz de enteros y una matriz como texto
	 * 
	 * @param fileMatrix   Matriz de enteros
	 * @param matrixString Matriz como texto
	 * @return
	 */
	public FileMatrix(int[][] fileMatrix, String matrixString) {
		this.fileMatrix = fileMatrix;
		this.matrixString = matrixString;
	}
}