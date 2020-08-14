package Tools;

import Interfaces.OutputMatrix;

public class FileMatrix implements OutputMatrix {
    // PROPIEDADES
    public int[][] fileMatrix = new int[0][0];
    public String matrixString = "";

    /**
     * @param fileMatrix
     * @param matrixString
     * @return
     */
    // INICIALIZAR
    public FileMatrix(int[][] fileMatrix, String matrixString) {
        this.fileMatrix = fileMatrix;
        this.matrixString = matrixString;
    }
}