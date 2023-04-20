package User;
import Gestion.Utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
public class pedirDatos {
	private static Scanner scanner = new Scanner(System.in);
	public static Year pedirAño(String texto) {
		Year año;
		año = Year.of(pedirInt(texto, 1500, 2022));
		return año;
	}
	public static String pedirNombre(String texto) {
		String nombre;
		do {
			nombre = pedirDatos.pedirString(texto);
			if (nombre != "") {
				nombre = Utils.convertirMayuscula(nombre);
			}
		} while (!Utils.validarNombre(nombre));
		return nombre;
	}
	public static String pedirString(String texto) {
		System.out.println(texto + "\n");
		String retorno = scanner.nextLine();
		return retorno;
	}
	public static int pedirInt(String texto) {
		int retorno;
		System.out.print(texto + "\n");
		while (!scanner.hasNextInt()) {
			System.out.println("Debe introducir un numero");
			System.out.print(texto + "\n");
			scanner.next();
		}
		retorno = scanner.nextInt();
		scanner.nextLine();
		return retorno;
	}
	public static int pedirInt(String texto, int min, int max) {
		int retorno;
		int corrector;

		if (min > max) {
			corrector = max;
			max = Math.max(max, min);
			min = Math.min(corrector, min);
		}

		do {
			System.out.print(texto + "\n");
			while (!scanner.hasNextInt()) {
				System.out.println("Debe introducir un numero");
				System.out.print(texto + "\n");
				scanner.next();
			}
			retorno = scanner.nextInt();
			scanner.nextLine();
			if (retorno < min || retorno > max) {
				System.out.println("Numero fuera de rango");
			}
		} while (retorno < min || retorno > max);
		return retorno;
	}
	public static boolean confirmacion(String texto) {
		char confirmacion;
        boolean confirmado = false;
		do {
			System.out.println(texto);
			confirmacion = scanner.nextLine().charAt(0);
		} while (confirmacion != 's' && confirmacion != 'n');

		if (confirmacion == 's') {
			confirmado = true;
		}
		return confirmado;
	}
}
