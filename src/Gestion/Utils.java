package Gestion;

public class Utils {
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
    public static int calcularDiasMes(int numeroMes) {
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
