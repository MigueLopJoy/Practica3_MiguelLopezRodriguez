package User;
import Gestion.Utils;
import java.time.Year;
import java.util.Scanner;
public class pedirDatos {
	private static Scanner scanner = new Scanner(System.in);

	public static Year pedirAño(String texto) {
		Year año;
		año = Year.of(pedirInt(texto, 1500, 2022));
		return año;
	}

	public static String pedirCodigo(String texto) {
		String codigo;
		do {
			codigo = pedirDatos.pedirString(texto);
			codigo = codigo.trim();
		} while (!Utils.validarCodigo(codigo));
		return codigo;
	}
	public static String pedirNombre(String texto) {
		String nombre;
		do {
			nombre = pedirDatos.pedirString(texto);
			nombre = Utils.convertirPrimeraMayuscula(nombre);
		} while (!Utils.validarNombre(nombre));
		return nombre;
	}

	public static String pedirCorreo(String texto) {
		String correo;
		do {
			correo = pedirDatos.pedirString(texto);
			correo = Utils.convertirMinusculas(correo);
		} while (!Utils.validarCorreo(correo));
		return correo;
	}

	public static String pedirString(String texto) {
		String retorno;
		do {
			System.out.println(texto + "\n");
			retorno = scanner.nextLine();
			if (retorno.isEmpty() || retorno.trim().isEmpty()) {
				System.out.println("Debe introducir algún dato");
			}
		} while (retorno.isEmpty() || retorno.trim().isEmpty());
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

		// Intercambia los valores de los parametros min y max si es necesario
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

	public static boolean confirmacion(String texto) {
		String userInput;
		char confirmacion = ' ';
        boolean confirmado = false;
		do {
			System.out.println(texto);
			if ((userInput = scanner.nextLine()).matches("[a-zA-Z]")){
				confirmacion = userInput.charAt(0);
				if (confirmacion != 's' && confirmacion != 'n') {
					System.out.println("El dato introducido no se corresponde con el solicitado");
				}
			} else {
				System.out.println("Dato no valido");
			}
		} while (confirmacion != 's' && confirmacion != 'n');

		if (confirmacion == 's') {
			confirmado = true;
		}
		return confirmado;
	}
}
