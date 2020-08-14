package Menus;

import java.util.Scanner;

import Tools.FileMatrix;
import Tools.Matrix;
import Tools.Menus;
import Tools.PrintMatrix;
import Tools.Utils;

/**
 * Encode
 */
public class Encode {
	Scanner input;
	int[][] encodeMatrix;
	String encodeMaString;

	/**
	 * Clase para cifrar mensaje
	 * 
	 * @param input Scanner global
	 * @return
	 */
	public Encode(Scanner input) {
		// INICIALIZAR
		this.input = input;

		// IMPRIMIR MENU
		printMenu();
	}

	/**
	 * Imprimir menu para cifrar
	 */
	private void printMenu() {
		// LEER TEXTO DE USUARIO
		String text = Menus.getOption("| Escribe el texto que deseas cifrar: |", "Caracteres alfanumericos", input, true);

		// OBTENER MULTIPLO
		int factor = Utils.getFactor(text.length());

		// OBTENER MATRICES
		FileMatrix mAsciiMatrix = Matrix.getAsciiMatrix(text, factor);
		FileMatrix guideMatrix = Matrix.readMatrixFile(input, "ARCHIVO PARA CIFRAR                    |", factor, factor,
				false);

		// MULTIPLICAR MATRICES
		encodeMatrix = Matrix.multiplyMatrix(mAsciiMatrix.fileMatrix, guideMatrix.fileMatrix);
		encodeMaString = PrintMatrix.toString(encodeMatrix);

		// IMPRIMIR MATRIZ RESULTANTE
		Utils.print("\nCifrado completo!, este es tu mensaje secreto: \n\n" + encodeMaString);
		Utils.promptEnterKey();
	}
}