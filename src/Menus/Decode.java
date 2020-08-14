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
		FileMatrix guideMatrix = Matrix.readMatrixFile(input, "ARCHIVO PARA CIFRAR                    |", 4);
		Utils.print(Matrix.matrixToString(Matrix.matrizInversa(guideMatrix.fileMatrix)));
	}
}