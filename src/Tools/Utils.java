package Tools;

import java.text.DecimalFormat;
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
        // LIMPIAR PANTALLA
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // IMPRIMIR MENU
    public static void printMenu(String title) {
        // LIMPIAR
        cls();

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

    public static int[][] multiplyMatrix(int[][] matrix, int[][] fileMatrix) {
        // VARIABLES DE MATRICES
        int unitFactor = matrix[0].length;
        int[][] outMatrix = new int[matrix.length][unitFactor];

        for (int matrixRows = 0; matrixRows < matrix.length; matrixRows++) {
            // FILA ACTUAL
            int[] currentRow = new int[unitFactor];

            for (int rowIndex = 0; rowIndex < unitFactor; rowIndex++) {
                // UNIDAD DE SUMA
                int unit = 0;

                // OBTENER FACTOR
                for (int matrixCols = 0; matrixCols < matrix[0].length; matrixCols++)
                    unit += matrix[matrixRows][matrixCols] * fileMatrix[matrixCols][rowIndex];

                // ASIGNAR ESPACIO A FILA
                currentRow[rowIndex] = unit;
            }

            // ASIGNAR FILA
            outMatrix[matrixRows] = currentRow;
        }

        // RETORNAR FILA
        return outMatrix;
    }

    // CONVERTIR MATRIZ A TEXTO
    public static String matrixToString(int[][] matrix) {
        // PLACEHOLDER
        String out = "<!>\n";

        // FORMATO DE DIGITOS
        DecimalFormat formater = new DecimalFormat("0000");

        // RECORRER MATRIZ
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++)
                // ASIGNAR ENTERO CON FORMATO
                out += " | " + formater.format(matrix[row][col]);

            // AGREGAR PLACEHOLDERS
            out += " |\n";
            out += "<!>\n";
        }

        // OBTENER LONGITUD DE LINEA
        String[] lines = out.split("<!>\n");
        int maxLine = lines[lines.length - 1].length();

        // REMPLAZAR PLACEHOLDER POR LINEAS
        out = out.replaceAll("<!>", " " + "-".repeat(maxLine - 2));

        // RETORNAR STRING
        return out;
    }
}