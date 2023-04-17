package Gestion;

public class Utils {
    public static boolean validarFormatoFecha(String fechaString) {
        String diaString = fechaString.substring(0, 2);
        String mesString = fechaString.substring(3, 5);
        String añoString = fechaString.substring(6);
        int dia;
        int mes;
        int año;
        boolean isValid = false;
        if (fechaString.charAt(2) == '/' && fechaString.charAt(5) == '/') {
            if (isNumeric(diaString) && isNumeric(mesString) && isNumeric(añoString)) {
                año = Integer.parseInt(añoString);
                mes = Integer.parseInt(mesString);
                dia = Integer.parseInt(diaString);
                if (año > 1500 && año < 2022){
                    if (mes > 1 && mes < 12) {
                        if (dia > 1 && dia < calcularDiasMes(mes)) {
                            isValid = true;
                        }
                    }
                }
            }
        }
        return  isValid;
    };
    public static boolean isNumeric(String data) {
        boolean isNumeric = true;
        try {
            Integer.parseInt(data);
        } catch (NumberFormatException e) {
            isNumeric = false;
        }
        return isNumeric;
    }
    public static int convertirEnNumero(String data) {
        int number = 0;
        try {
            number = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }
    private static int calcularDiasMes(int numeroMes) {
        int numDias = 0;

        switch (numeroMes) {
            case 1: {
                numDias = 31;
                break;
            }
            case 2: {
                numDias = 28;
                break;
            }
            case 3: {
                numDias = 31;
                break;
            }
            case 4: {
                numDias = 30;
                break;
            }
            case 5: {
                numDias = 31;
                break;
            }
            case 6: {
                numDias = 30;
                break;
            }
            case 7: {
                numDias = 31;
                break;
            }
            case 8: {
                numDias = 31;
                break;
            }
            case 9: {
                numDias = 30;
                break;
            }
            case 10: {
                numDias = 31;
                break;
            }
            case 11: {
                numDias = 30;
                break;
            }
            case 12: {
                numDias = 31;
                break;
            }
        }
        return numDias;
    }
}
