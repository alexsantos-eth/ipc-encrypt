package Tools;

public class FileMatrix {
    // PROPIEDADES
    public int[][] fileMatrix = new int[0][0];
    public String matrixString = "";

    // INICIALIZAR
    public FileMatrix(int[][] fileMatrix, String matrixString) {
        this.fileMatrix = fileMatrix;
        this.matrixString = matrixString;
    }
}