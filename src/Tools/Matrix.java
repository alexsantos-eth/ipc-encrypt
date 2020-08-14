package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Matrix
 */
public class Matrix {
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

    // LEER ARCHIVO DE TEXTO
    public static FileMatrix readMatrixFile(Scanner input, String title, int unitFactor) {
        // RUTA
        int[][] fileMatrix = new int[unitFactor][unitFactor];
        boolean breakRead = false;
        int errCode = 0;

        while (!breakRead) {
            // RUTA
            String pathname = "";
            String eString = errCode == 3 ? "| Error 03: Longitud de archivo invalida |"
                    : errCode == 2 ? "| Error 02: No se pudo leer el archivo   |"
                            : errCode == 1 ? "| Error 01: La ruta no es un archivo     |" : "| " + title;

            // PEDIR RUTA
            while (pathname.length() == 0) {
                // IMPRIMIR MENU
                Menus.printMenu(eString + "\n| Escribe la ruta del archivo:           |");
                Utils.print("\nRuta relativa => ");

                // ASIGNAR RUTA
                pathname = "../" + input.nextLine();
            }

            // LEER ARCHIVO
            File fileTxt = new File(pathname);

            if (fileTxt.isFile()) {
                try {
                    // BUFFER
                    String line = "";
                    String tmpFileStr = "";
                    BufferedReader reader = new BufferedReader(new FileReader(fileTxt));

                    // LEER ARCHIVO
                    while ((line = reader.readLine()) != null)
                        tmpFileStr += line + "\n";

                    // ARRAY DE STRINGS
                    String[] tmpLine = tmpFileStr.split(",|\n");

                    // VERIFICAR LONGITUD
                    if (tmpLine.length == unitFactor * unitFactor) {
                        // CONVERTIR A ENTERO
                        for (int lineIndex = 0; lineIndex < unitFactor; lineIndex++)
                            for (int lineCol = 0; lineCol < unitFactor; lineCol++)
                                fileMatrix[lineIndex][lineCol] = Integer
                                        .parseInt(tmpLine[lineCol + (lineIndex * unitFactor)]);
                        // SALIR DEL CICLO
                        breakRead = true;
                    } else
                        errCode = 3;

                    // CERRAR BUFFER
                    reader.close();
                } catch (Exception e) {
                    errCode = 2;
                }
            } else
                errCode = 1;
        }

        // RETORNAR MATRIZ
        return new FileMatrix(fileMatrix, matrixToString(fileMatrix));
    }

    // ASIGNAR MATRIZ
    public static FileMatrix getAsciiMatrix(String text, int cols) {
        // INICIALIZAR
        int length = text.length();
        int[] ascii = new int[length];

        // OBTENER FILAS Y COLUMNAS
        int rows = length / cols;

        // CREAR MATRIZ
        int[][] matrix = new int[rows][cols];

        // ARRAY DE INT
        for (int charIndex = 0; charIndex < length; charIndex++)
            ascii[charIndex] = (int) text.charAt(charIndex);

        // ASIGNAR A MATRIZ
        for (int rowsIndex = 0; rowsIndex < rows; rowsIndex++)
            // RECORRER COLUMNAS
            for (int colsIndex = 0; colsIndex < cols; colsIndex++)
                matrix[rowsIndex][colsIndex] = ascii[colsIndex + (rowsIndex * cols)];

        // DEVOLVER TEXTO
        return new FileMatrix(matrix, matrixToString(matrix));
    }
}