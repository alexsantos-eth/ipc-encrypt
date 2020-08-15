package Tools;

import java.io.IOException;

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

	public static void promptEnterKey() {
		try {
			Utils.print("\nPresiona \"ENTER\" para continuar... ");
			System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	/**
	 * Redondea los valores de un array double a valores con 3 decimales
	 * 
	 * @param arry Array de dobles
	 * @return String[]
	 */
	public static String[] roundArray(double[] arry) {
		// SALIDA
		String[] out = new String[arry.length];

		// CONVERTIR
		for (int index = 0; index < arry.length; index++) {
			String format = String.format("%.3f", arry[index]);
			out[index] = format.equals("-0.000") ? "0.000" : format;
		}

		// RETORNAR SALIDA
		return out;
	}

}