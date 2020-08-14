package Tools;

import java.util.Scanner;

/**
 * Menus
 */
public class Menus {

    // IMPRIMIR MENU
    public static void printMenu(String title) {
        // LIMPIAR
        Utils.cls();

        // LINEAS DE SEPARACION
        String lines = "-".repeat(title.length());

        // IMPRIMIR
        System.out.print(lines + "\n" + title + "\n" + lines);
    }

    // OPTENER OPCION DE CUALQUIER MENU COMO NUMERO
    public static int getOption(String options, String name, Scanner input) {
        // SALIDA
        int option = 0;

        while (option == 0) {
            // IMPRIMIR MENU
            printMenu(options);

            // FORMATO PARA INGRESAR
            System.out.print("\n" + name + " => ");

            // RETORNAR ENTERO
            option = input.nextInt();
        }

        return option;
    }

    // OPTENER OPCION DE CUALQUIER MENU COMO STRING
    public static String getOption(String options, String name, Scanner input, Boolean string) {
        // SALIDA
        String text = "";

        while (text.length() == 0) {
            // IMPRIMIR MENU
            printMenu(options);

            // FORMATO PARA INGRESAR
            System.out.print("\n" + name + " => ");

            // ASIGNAR TEXTO
            text = input.nextLine();
        }

        // RETORNAR TEXTO
        return text;
    }
}