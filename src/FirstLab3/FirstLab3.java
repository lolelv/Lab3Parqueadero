/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstLab3;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class FirstLab3 {

    /**
     * @param args the command line arguments
     */
    static int[] parking = new int[87];
    static String[] placas = new String[87];
    static double [] hora = new double[87];
    static double fracHora = 5000;
    static double auxPlata = 0;

    public static void Parking() {
        for (int i = 0; i < 87; i++) {
            parking[i] = i + 1;
        }
    }

    public static void ImprimirParking() {
        System.out.println("Los parqueaderos ocupados están simbolizados con un cero (0).\n");
        for (int i = 0; i < 24; i++) {
            System.out.print(parking[i] + "|");
        }
        System.out.print("\n");
        for (int i = 24; i < 45; i++) {
            System.out.print(parking[i] + "|");

        }
        System.out.print("\n");
        for (int i = 45; i < 66; i++) {
            System.out.print(parking[i] + "|");
        }
        System.out.print("\n");
        for (int i = 66; i < 87; i++) {
            System.out.print(parking[i] + "|");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    public static void Asignacion(int parqueadero, String placa, double hora1) {
        for (int i = 0; i < 87; i++) {
            if (parking[parqueadero - 1] == 1) {
                System.out.println("Espacio ocupado, ingrese otro: ");
                break;
            }            
            if (hora1 < 6.0 || hora1 > 20.0) {
                System.out.println("Ha ingresado una hora fuera de nuestro horario de atención, \n ingrese de nuevo: ");
                break;
            } else {
                parking[parqueadero - 1] = 0;
                hora[parqueadero - 1] = hora1;
                placas[parqueadero - 1] = placa;
                break;
            }
        }
    }

    public static int EspaciosVacios() {
        int auxCupos = 0;
        for (int i = 0; i < 87; i++) {
            if (parking[i] != 0) {
                auxCupos = auxCupos + 1;
            }
        }
        return auxCupos;
    }

    public static double Valor(String placa, double salida) {
        double plata = 0;
        for (int i = 0; i < 87; i++) {
            if (placa.equals(placas[i])) {
                plata = plata + ((salida - hora[i]) * fracHora);
                parking[i] = i + 1;
                auxPlata = auxPlata + plata;
                break;
            }
        }
        return plata;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner (System.in);
        int opc;
        Parking();
        do {
            System.out.print(" 1. Visualizar el parqueadero. \n 2. Asignar puesto para parquear. \n 3. Precio a pagar del parqueadero");
            System.out.println("\n 4. Dinero recogido en el día. \n 5. Cupos libres. \n 6. Cambiar fracción por hora. \n 7. Salir.");
            System.out.println(" ");
            opc = read.nextInt();
            switch (opc) {
                case 1:
                    ImprimirParking();
                    break;
                case 2:
                    System.out.println("Ingrese el número del parqueadero:");
                    int parqueadero = read.nextInt();
                    while (parqueadero > 87 || parqueadero < 1){
                        System.out.println("Ha ingresado un espacio incorrecto, recuerde que tenemos una capacidad máxima de 87 cupos. ");
                        parqueadero = 0;
                        System.out.println("Ingrese de nuevo el número:");
                        parqueadero = read.nextInt();
                    }
                    System.out.println("Ingrese la placa del carro (ej: abc123) :");
                    String placa = read.next();
                    System.out.println("Ingrese la hora de ingreso (ej:6.30, donde 6 representa la hora y 30 los minutos) :");
                    double hora1 = read.nextDouble();
                    Asignacion(parqueadero, placa, hora1);
                    break;
                case 3:
                    System.out.println("Ingrese la placa del carro: ");
                    placa = read.next();
                    System.out.println("Ingrese la hora de sálida: ");
                    double salida = read.nextDouble();
                    double precio2 = Valor(placa, salida);
                    System.out.println("El total neto a pagar es: $" + precio2);
                    break;
                case 4:
                    System.out.println("Se ha recogido un total de: $" + auxPlata);
                    break;
                case 5:
                    int cupos = EspaciosVacios();
                    System.out.println("Quedan " + cupos + " cupos disponibles.");
                    break;
                case 6:
                    System.out.println("Ingrese el nuevo valor de la fracción por hora: ");
                    fracHora = read.nextDouble();
                    System.out.println("Ha ingresado como nueva fracción por hora: $"+fracHora);
                    System.out.println("");
                    break;
                case 7:
                    System.out.println("¡Gracias por usar nuestro servicio de Parqueadero! ");
                    break;
                default:
                    System.out.println("La opción que ha ingresado es incorecta, intente de nuevo: ");
                    System.out.println(" ");
                    break;
            }
        } while (opc != 7);
    }

}
