package Menus;

import java.util.Scanner;

import Tools.FileMatrix;
import Tools.Matrix;
import Tools.Menus;
import Tools.Utils;

/**
 * Encode
 */
public class Encode {
    Scanner input;
    int[][] encodeMatrix;
    String encodeMaString;

    public Encode(Scanner input) {
        // INICIALIZAR
        this.input = input;

        // IMPRIMIR MENU
        printMenu();
    }

    private void printMenu() {
        // LEER TEXTO DE USUARIO
        String text = Menus.getOption("| Escribe el texto que deseas cifrar: |", "Caracteres alfanumericos", input,
                true);

        // OBTENER MULTIPLO
        int factor = Utils.getFactor(text.length());

        // OBTENER MATRICES
        FileMatrix mAsciiMatrix = Matrix.getAsciiMatrix(text, factor);
        FileMatrix guideMatrix = Matrix.readMatrixFile(input, "ARCHIVO PARA CIFRAR                    |", factor);

        // MULTIPLICAR MATRICES
        encodeMatrix = Matrix.multiplyMatrix(mAsciiMatrix.fileMatrix, guideMatrix.fileMatrix);
        encodeMaString = Matrix.matrixToString(encodeMatrix);

        // IMPRIMIR MATRIZ RESULTANTE
        Utils.print(encodeMaString);
    }
}