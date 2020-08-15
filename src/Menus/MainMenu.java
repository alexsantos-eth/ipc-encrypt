package Menus;

import java.util.Scanner;

import Tools.Menus;

/**
 * Menu principal del programa con opciones para cada submenu
 */
public class MainMenu {

	/**
	 * El menu principal es el punto de entrada desde el main
	 * 
	 * @return
	 */
	public MainMenu() {
		// MOSTRAR MENU
		printMenu();
	}

	/**
	 * Imprimir menu principal
	 */
	private void printMenu() {
		// OBTENER INPUT
		Scanner input = new Scanner(System.in);
		boolean breakMenu = false;

		while (!breakMenu) {
			int option = Menus.getOption("| (1) Cifrar | (2) Decifrar | (3) Gauss-Jordan | (4) Salir |", "Ingrese una opcion",
					input);

			// INICIAR CLASE
			switch (option) {
			case (1): {
				new Encode(input);
				break;
			}
			case (2): {
				new Decode(input);
				break;
			}
			case (3): {
				new GaussMenu(input);
				break;
			}
			case (4): {
				breakMenu = true;
				break;
			}
			default: {
				breakMenu = false;
			}
			}
		}

		// CERRAR SCANNER
		input.close();
	}
}