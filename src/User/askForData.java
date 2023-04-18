package User;

import Biblioteca.Autor;
import Biblioteca.Libro;
import Gestion.Utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
public class askForData {
	private static Scanner scanner = new Scanner(System.in);
	public static LocalDate pedirFecha(String texto) {
		LocalDate fecha;
		String fechaString;
		Year año;
		int mes;
		int dia;


	}
	public static Year pedirAño(String texto) {
		Year año;
		System.out.println(texto);
		año = Year.of(pedirInt("Introduzca el año (AAAA)", 0, 9999));
		return año;
	}
	public static Month pedirMes(String texto) {
		Month mes;
		System.out.println(texto);
		mes = Month.of(pedirInt("Introduzca el mes (MM)", 1, 12));
		return mes;
	}
	public static MonthDay pedirDia(String texto, Month mes){
		MonthDay dia;
		System.out.println(texto);
		dia = MonthDay.of(mes, pedirInt("Introduzca el dia (DD)", 1, Utils.calcularDiasMes(mes.getValue())));
		return  dia;
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

	public static int pedirInt(int min, int max) {
		int retorno;
		int corrector;

		if (min > max) {
			corrector = max;
			max = Math.max(max, min);
			min = Math.min(corrector, min);
		}
		do {
			while (!scanner.hasNextInt()) {
				System.out.println("Debe introducir un numero");
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

	public static int pedirInt(ArrayList<Integer> numsGroup, int max) {
		int retorno = 0;

		do {
			while (!scanner.hasNextInt()) {
				System.out.println("Debe introducir un numero");
				scanner.next();
			}
			retorno = scanner.nextInt();
			scanner.nextLine();

			if (!numsGroup.contains(retorno)) {
				if (retorno < 1 || retorno > max) {
					System.out.println("Numero fuera de rango");
				} else {
					System.out.println("El numero introducido no es una opcion valida");
					retorno = 0;
				}
			}
			if (retorno < 1 || retorno > max || !numsGroup.contains(retorno)) {
				System.out.println("Introduzca un nuevo numero");
			}
		} while (retorno < 1 || retorno > max || !numsGroup.contains(retorno));
		return retorno;
	}

	public static double pedirDouble(String texto) {
		double retorno;

		System.out.print(texto + "\n");
		while (!scanner.hasNextInt()) {
			System.out.println("Debe introducir un numero");
			System.out.print(texto + "\n");
			scanner.next();
		}
		retorno = scanner.nextDouble();
		scanner.nextLine();
		return retorno;
	}

	public static double pedirDouble(String texto, double min, double max) {
		double retorno;
		double corrector;

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
			retorno = scanner.nextDouble();
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
