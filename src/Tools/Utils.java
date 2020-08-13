package Tools;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utils
 */
public class Utils {
    // IMPRIMIR EN CONSOLA
    public static void print(String msg) {
        System.out.print(msg);
    }

    // DAR FORMATO A LOS MENUS
    public static void cls() {
        // EJECUTAR COMANDO CLS O CLEAR EN MAC/LINUX
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        // MOSTRAR LA EXCEPCION
        catch (IOException | InterruptedException ex) {
            print(ex.toString());
        }
    }

    // IMPRIMIR MENU
    public static void printMenu(String title) {
        // LINEAS DE SEPARACION
        String lines = "-".repeat(title.length());

        // IMPRIMIR
        System.out.print(lines + "\n" + title + "\n" + lines);
    }

    // OPTENER OPCION DE CUALQUIER MENU COMO NUMERO
    public static int getOption(String name, Scanner input) {
        // FORMATO PARA INGRESAR
        System.out.print("\n" + name + " => ");

        // DETECTAR SI INGRESO CARACTER DIFERENTE DE ENTERO
        try {
            return input.nextInt();
        }

        // CONTINUAR LEYENDO Y RETORNAR ERROR 101
        catch (InputMismatchException ex) {
            input.nextLine();
            return 101;
        }
    }
}