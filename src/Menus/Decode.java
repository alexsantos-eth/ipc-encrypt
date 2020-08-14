package Menus;

import java.util.Scanner;

import Tools.FileMatrix;
import Tools.Matrix;
import Tools.Utils;

/**
 * Decode
 */
public class Decode {
	Scanner input;

	/**
	 * @param input
	 * @return
	 */
	public Decode(Scanner input) {
		// INICIALIZAR
		this.input = input;

		// IMPRIMIR MENU
		printMenu();
	}

	private void printMenu() {
		// PEDIR MATRICES
		FileMatrix textMatrix = Matrix.readMatrixFile(input, "ARCHIVO CIFRADO                        |", 0, 0, true);
		FileMatrix guideMatrix = Matrix.readMatrixFile(input, "ARCHIVO PARA DECIFRAR                  |", 0, 0, true);

		// VERIFICAR SI ES POSIBLE MULTIPLICAR
		if (textMatrix.fileMatrix[0].length == guideMatrix.fileMatrix.length) {
			// CALCULAR MATRIZ INVERSA DE NXN
			double[][] inverted = Matrix.invertedMatrix(guideMatrix.fileMatrix);
			long[][] decoded = Matrix.multiplyMatrix(textMatrix.fileMatrix, inverted);

			Utils.print(
					"\nDecifrado completo!, Este es tu mensaje original: \n\n" + Matrix.asciiMatrixToString(decoded) + "\n");
			Utils.promptEnterKey();
		}
	}
}