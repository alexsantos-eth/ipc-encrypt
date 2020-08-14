package Menus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import Tools.Utils;

/**
 * Encode
 */
public class Encode {
    String text, textMaString, fileMaString, encodeMaString;
    Scanner input;
    int[][] matrix;
    int[][] fileMatrix;
    int[][] encodeMatrix;
    int unitFactor, rows, errCode;

    public Encode() {
        // INICIALIZAR
        text = "";
        input = new Scanner(System.in);
        errCode = unitFactor = rows = 0;

        // IMPRIMIR MENU
        printMenu();

        // CERRAR SCANNER
        input.close();
    }

    private void printMenu() {
        // PEDIR TEXTO
        while (text.length() == 0) {
            // IMPRIMIR MENU
            Utils.printMenu("| Escribe el texto que deseas cifrar: |");
            Utils.print("\nCaracteres alfanumericos => ");

            // ASIGNAR TEXTO
            text = input.nextLine();
        }

        // MATRICES
        textMaString = setMatrix();
        fileMaString = readFile();
        encodeMatrix = Utils.multiplyMatrix(matrix, fileMatrix);

        // MATRIZ DE SALIDA
        encodeMaString = Utils.matrixToString(encodeMatrix);
        Utils.print(encodeMaString);
    }

    // ASIGNAR MATRIZ
    private String setMatrix() {
        // INICIALIZAR
        int length = text.length();
        int[] ascii = new int[length];
        int[] factors = { 3, 4, 5, 7, 11, 13, 17 };

        // ARRAY DE INT
        for (int charIndex = 0; charIndex < length; charIndex++)
            ascii[charIndex] = (int) text.charAt(charIndex);

        // OBTENER FACTOR
        for (int factorIndex = 0; factorIndex < factors.length; factorIndex++)
            if (length % factors[factorIndex] == 0) {
                unitFactor = factors[factorIndex];
                break;
            }

        // CREAR MATRIZ
        rows = length / unitFactor;
        matrix = new int[rows][unitFactor];

        // ASIGNAR A MATRIZ
        for (int rowsIndex = 0; rowsIndex < rows; rowsIndex++)
            // RECORRER COLUMNAS
            for (int colsIndex = 0; colsIndex < unitFactor; colsIndex++)
                matrix[rowsIndex][colsIndex] = ascii[colsIndex + (rowsIndex * unitFactor)];

        // DEVOLVER TEXTO
        Utils.print(Utils.matrixToString(matrix));
        return Utils.matrixToString(matrix);
    }

    // LEER ARCHIVO DE TEXTO
    private String readFile() {
        // RUTA
        String pathname = "";
        String eString = errCode == 0 ? "| ARCHIVO PARA CIFRADO"
                : errCode == 2 ? "| Error 02: No es posible leer el archivo"
                        : errCode == 3 ? "| Error 03: Longitud del archivo invalida "
                                : "| Error 01: la ruta no es un  archivo";

        // PEDIR RUTA
        while (pathname.length() == 0) {
            // IMPRIMIR MENU
            Utils.printMenu(eString + "\n| Escribe la ruta del archivo para cifrar : |");
            Utils.print("\nRuta relativa => ");

            // ASIGNAR RUTA
            pathname = input.nextLine();
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
                    // DECLARAR MATRIZ
                    fileMatrix = new int[unitFactor][unitFactor];

                    // CONVERTIR A ENTERO
                    for (int lineIndex = 0; lineIndex < unitFactor; lineIndex++)
                        for (int lineCol = 0; lineCol < unitFactor; lineCol++)
                            fileMatrix[lineIndex][lineCol] = Integer
                                    .parseInt(tmpLine[lineCol + (lineIndex * unitFactor)]);
                } else {
                    errCode = 3;
                    readFile();
                }

                // CERRAR BUFFER
                reader.close();
            } catch (Exception e) {
                errCode = 2;
                readFile();
            }
        } else {
            errCode = 1;
            readFile();
        }

        return Utils.matrixToString(fileMatrix);
    }
}