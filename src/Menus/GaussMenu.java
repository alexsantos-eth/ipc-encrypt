package Menus;

import java.util.Scanner;

import Tools.FileMatrix;
import Tools.Gauss;
import Tools.Matrix;
import Tools.Utils;

/**
 * Gauss
 */
public class GaussMenu {
	Scanner input;

	public GaussMenu(Scanner input) {
		// INICIALIZAR
		this.input = input;

		// IMPRIMIR MENU
		printMenu();
	}

	private void printMenu() {
		// MANEJAR CICLO
		boolean breakMenu = false;
		int errCode = 0;

		while (!breakMenu) {
			// TEXTO CON ERROR
			String title = errCode == 1 ? "Error 05: la matriz debe ser de 3x4     |"
					: "ARCHIVO CON SISTEMA DE ECUACIONES 3X4  |";

			// LEER MATRIZ DE GAUSS
			FileMatrix guideMatrix = Matrix.readMatrixFile(input, title, 0, 0, true);

			// VERIFICAR MATRIZ
			if (guideMatrix.fileMatrix.length == 3 && guideMatrix.fileMatrix[0].length == 4) {
				// RESOLVER SISTEMA
				double[] results = Gauss.resolve(guideMatrix.fileMatrix);

				Utils.print(results[0] + " " + results[1] + " " + results[2]);

				// SALIR
				breakMenu = true;
			} else
				errCode = 1;
		}
	}
}