package Menus;

import java.util.Scanner;

import Tools.Menus;

/**
 * MainMenu
 */
public class MainMenu {

    /**
     * @return
     */
    public MainMenu() {
        // MOSTRAR MENU
        printMenu();
    }

    private void printMenu() {
        // OBTENER INPUT
        Scanner input = new Scanner(System.in);
        int option = Menus.getOption("| (1) Cifrar | (2) Decifrar | (3) Gauss-Jordan |", "Ingrese una opcion", input);

        // INICIAR CLASE
        switch (option) {
        case (1): {
            new Encode(input);
            break;
        }
        }

        // CERRAR SCANNER
        input.close();
    }
}