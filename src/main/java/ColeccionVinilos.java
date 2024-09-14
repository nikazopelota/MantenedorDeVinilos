import java.util.InputMismatchException;
import java.util.Scanner;

public class ColeccionVinilos {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String[][] vinilos = new String[100][3];

    public static void main(String[] args) {
        iniciarPrograma();
    }

    public static void menu() {
        System.out.println("\nMenu\n");
        System.out.println("1.- Agregar un vinilo");
        System.out.println("2.- Mostrar colección completa");
        System.out.println("3.- Mostrar el número total de vinilos");
        System.out.println("4.- Mostrar el número de espacios disponibles");
        System.out.println("5.- Buscar un vinilo por artista y álbum");
        System.out.println("6.- Eliminar un vinilo");
        System.out.println("7.- Salir\n");
    }

    public static void iniciarPrograma() {
        int opcion;
        do {
            menu();
            opcion = solicitarEntero("Ingrese su opción: ");
            procesarOpcion(opcion);
        } while (opcion != 7);
        scanner.close();
    }

    public static int solicitarEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int numero = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número entero válido");
                scanner.nextLine();
            }
        }
    }

    public static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                opcion1();
                break;
            case 2:
                opcion2();
                break;
            case 3:
                opcion3();
                break;
            case 4:
                opcion4();
                break;
            case 5:
                opcion5();
                break;
            case 6:
                opcion6();
                break;
            case 7:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }

    public static void agregarVinilo(String banda, String album, String anio) {
        for (int i = 0; i < vinilos.length; i++) {
            if (vinilos[i][0] == null) { // Encuentra el primer espacio vacío
                vinilos[i][0] = banda;
                vinilos[i][1] = album;
                vinilos[i][2] = anio;
                System.out.println("Vinilo agregado: " + banda + " - " + album + " (" + anio + ")");
                return;
            }
        }
        System.out.println("La colección está llena, no se puede agregar más vinilos.");
    }

    public static String pedirBanda(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public static String pedirAlbum(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public static String pedirAnio(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public static void opcion1() {
        String banda = pedirBanda("Escriba el nombre de la banda: ");
        String album = pedirAlbum("Escriba el nombre del álbum: ");
        String anio = pedirAnio("Escriba el año de lanzamiento: ");
        agregarVinilo(banda, album, anio);
    }

    public static void opcion2() {
        mostrarColeccion();
    }

    public static void opcion3() {
        int total = contarVinilos();
        System.out.println("Total de vinilos en la colección: " + total);
    }

    public static void opcion4() {
        int disponibles = contarEspaciosDisponibles();
        System.out.println("Espacios disponibles en la colección: " + disponibles);
    }

    public static void opcion5() {
        String banda = pedirBanda("Escriba el nombre de la banda a buscar: ");
        String album = pedirAlbum("Escriba el nombre del álbum a buscar: ");
        boolean encontrado = buscarVinilo(banda, album);
        if (encontrado) {
            System.out.println("El vinilo '" + album + "' de " + banda + " está en la colección.");
        } else {
            System.out.println("El vinilo '" + album + "' de " + banda + " no se encontró en la colección.");
        }
    }

    public static void opcion6() {
        String banda = pedirBanda("Escriba el nombre de la banda del vinilo a eliminar: ");
        String album = pedirAlbum("Escriba el nombre del álbum a eliminar: ");
        boolean eliminado = eliminarVinilo(banda, album);
        if (eliminado) {
            System.out.println("El vinilo '" + album + "' de " + banda + " ha sido eliminado.");
        } else {
            System.out.println("El vinilo '" + album + "' de " + banda + " no se encontró en la colección.");
        }
    }

    public static boolean eliminarVinilo(String banda, String album) {
        for (int i = 0; i < vinilos.length; i++) {
            if (vinilos[i][0] != null && vinilos[i][0].equals(banda) && vinilos[i][1].equals(album)) {
                vinilos[i][0] = null;
                vinilos[i][1] = null;
                vinilos[i][2] = null;
                reorganizarVinilos(i); // Opcional: Reorganizar después de eliminar
                return true;
            }
        }
        return false;
    }

    public static void reorganizarVinilos(int index) {
        for (int i = index; i < vinilos.length - 1; i++) {
            vinilos[i][0] = vinilos[i + 1][0];
            vinilos[i][1] = vinilos[i + 1][1];
            vinilos[i][2] = vinilos[i + 1][2];
        }
        // Limpiar la última fila después de la reorganización
        vinilos[vinilos.length - 1][0] = null;
        vinilos[vinilos.length - 1][1] = null;
        vinilos[vinilos.length - 1][2] = null;
    }

    public static void mostrarColeccion() {
        for (String[] vinilo : vinilos) {
            if (vinilo[0] != null) {
                System.out.println("Banda: " + vinilo[0] + ", Álbum: " + vinilo[1] + ", Año: " + vinilo[2]);
            }
        }
    }

    public static int contarVinilos() {
        int contador = 0;
        for (String[] vinilo : vinilos) {
            if (vinilo[0] != null) {
                contador++;
            }
        }
        return contador;
    }

    public static int contarEspaciosDisponibles() {
        return vinilos.length - contarVinilos();
    }

    public static boolean buscarVinilo(String banda, String album) {
        for (String[] vinilo : vinilos) {
            if (vinilo[0] != null && vinilo[0].equals(banda) && vinilo[1].equals(album)) {
                return true;
            }
        }
        return false;
    }
}
