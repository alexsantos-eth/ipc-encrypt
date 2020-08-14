package Menus;

import java.util.Scanner;

import Tools.Utils;

/**
 * MainMenu
 */
public class MainMenu {
    // GLOBALES
    boolean breakMenu;
    Scanner input;
    int option;

    public MainMenu() {
        // INICIALIZAR VARIABLES
        input = new Scanner(System.in);
        breakMenu = false;
        option = 0;

        // MOSTRAR MENU
        printMenu();
    }

    private void printMenu() {
        while (!breakMenu) {
            // IMPRIMIR MENU
            Utils.printMenu("| (1) Cifrar | (2) Decifrar | (3) Gauss-Jordan |");

            // LEER OPCION
            option = Utils.getOption("Ingrese una opcion", input);

            switch (option) {
            case (1):
                new Encode();
                break;
            }

            breakMenu = true;
        }
    }
}