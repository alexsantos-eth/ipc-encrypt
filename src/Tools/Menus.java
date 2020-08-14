package Tools;

import java.util.Scanner;

/**
 * Menus
 */
public class Menus {

	/**
	 * Imprime en pantalla un menu dentro de lineas
	 * 
	 * @param title Texto del titulo
	 */
	public static void printMenu(String title) {
		// LIMPIAR
		Utils.cls();

		// LINEAS DE SEPARACION
		String[] lines = title.split("\n");
		String divider = "-".repeat(lines[lines.length - 1].length());

		// IMPRIMIR
		Utils.print(divider + "\n" + title + "\n" + divider);
	}

	/**
	 * Obtener un entero como opcion
	 * 
	 * @param options Titulo del prompt (Listado de opciones)
	 * @param name    Nombre del usuario a mostrar (Informacion de entrada)
	 * @param input   Scanner global
	 * @return int
	 */
	public static int getOption(String options, String name, Scanner input) {
		// SALIDA
		int option = 0;

		while (option == 0) {
			// IMPRIMIR MENU
			printMenu(options);

			// FORMATO PARA INGRESAR
			Utils.print("\n" + name + " $ ");

			// RETORNAR ENTERO
			option = input.nextInt();
		}

		return option;
	}

	/**
	 * Obtener un string como opcion
	 * 
	 * @param options  Titulo del prompt (Listado de opciones)
	 * @param name     Nombre del usuario a mostrar (Informacion de entrada)
	 * @param input    Scanner global
	 * @param isString Tratar opcion como texto
	 * @return String
	 */
	public static String getOption(String options, String name, Scanner input, Boolean isString) {
		// SALIDA
		String text = "";

		while (text.length() == 0) {
			// IMPRIMIR MENU
			printMenu(options);

			// FORMATO PARA INGRESAR
			Utils.print("\n" + name + " $ ");

			// ASIGNAR TEXTO
			text = input.nextLine();
		}

		// RETORNAR TEXTO
		return text;
	}
}