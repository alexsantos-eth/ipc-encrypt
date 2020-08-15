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
		// CONTROLAR CICLO
		boolean breakMenu = false;
		int errCode = 0;

		while (!breakMenu) {
			// MENSAJE DE ERROR
			String title = errCode == 1 ? "Error 04: no es posible decifrar       |"
					: "ARCHIVO CIFRADO                        |";

			// PEDIR MATRICES
			FileMatrix textMatrix = Matrix.readMatrixFile(input, title, 0, 0, true);
			FileMatrix guideMatrix = Matrix.readMatrixFile(input, "ARCHIVO PARA DECIFRAR                  |", 0, 0, true);

			// VERIFICAR SI ES POSIBLE MULTIPLICAR
			if (textMatrix.fileMatrix[0].length == guideMatrix.fileMatrix.length) {
				// CALCULAR MATRIZ INVERSA DE NXN
				double[][] inverted = Matrix.invertedMatrix(guideMatrix.fileMatrix);
				long[][] decoded = Matrix.multiplyMatrix(textMatrix.fileMatrix, inverted);

				// TEXTO DE MATRIZ
				String decodeString = Matrix.asciiMatrixToString(decoded);

				// MOSTRAR MATRIZ
				Utils.print("\nDecifrado completo!, Este es tu mensaje original: \n\n" + decodeString + "\n");
				Utils.promptEnterKey();

				// SALIR
				breakMenu = true;
			} else
				errCode = 1;
		}
	}
}