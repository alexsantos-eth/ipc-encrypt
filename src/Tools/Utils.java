package Tools;

/**
 * Utils
 */
public class Utils {

	/**
	 * IMPRIMIR EN CONSOLA
	 * 
	 * @param msg
	 */
	public static void print(String msg) {
		System.out.print(msg);
	}

	/**
	 * Obtener el multiplo de un numero
	 * 
	 * @param length Numero o longitud
	 * @return int
	 */
	public static int getFactor(int length) {
		int unitFactor = 0;
		int[] factors = { 3, 4, 5, 7, 11, 13, 17 };

		// OBTENER FACTOR
		for (int factorIndex = 0; factorIndex < factors.length; factorIndex++)
			if (length % factors[factorIndex] == 0) {
				unitFactor = factors[factorIndex];
				break;
			}

		return unitFactor;
	}

	/**
	 * Limpiar pantalla
	 * 
	 * @return
	 */
	public static void cls() {
		// LIMPIAR PANTALLA
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

}