import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static List<MenuCompleto> menus = new ArrayList<>();

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);

        // Crear los tipos de mesa
        Tipo mesaDosPersonas = new Tipo("2 personas", 1000);
        Tipo mesaCuatroPersonas = new Tipo("4 personas", 2000);
        Tipo mesaSeisPersonas = new Tipo("6 personas", 3000);

        // Agregar mesas
        restaurante.agregarMesa(new Mesa(mesaDosPersonas));
        restaurante.agregarMesa(new Mesa(mesaDosPersonas));
        restaurante.agregarMesa(new Mesa(mesaDosPersonas));
        restaurante.agregarMesa(new Mesa(mesaCuatroPersonas));
        restaurante.agregarMesa(new Mesa(mesaSeisPersonas));

        // Instanciar el servicio del restaurante
        ServicioRestaurante restauranteService = new ServicioRestaurante(restaurante, menus);

        boolean ejecutando = true;

        while (ejecutando) {
            try{
            // Menú principal
            System.out.println("Seleccione una opción:");
            System.out.println("1. Comer en el restaurante");
            System.out.println("2. Hacer una reserva");
            System.out.println("3. Crear menú para pedir en el restaurante");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    restauranteService.comerEnRestaurante(scanner, mesaDosPersonas, mesaCuatroPersonas, mesaSeisPersonas);
                    break;
                case 2:
                    restauranteService.hacerReserva(scanner, mesaDosPersonas, mesaCuatroPersonas, mesaSeisPersonas);
                    break;
                case 3:
                    restauranteService.crearMenuCompleto(scanner);
                    break;
                case 4:
                    ejecutando = false;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer en caso de error
            }
        }

        // Mostrar todas las reservas realizadas al finalizar
        System.out.println("\nReservas realizadas con éxito en esta sesión:");
        restaurante.mostrarReservas();
    }



    // metodo para validar el formato de la hora ingresada
    public static Date parseFecha(String fechaStr) throws ParseException {
        return dateFormat.parse(fechaStr);
    }
    public static void validarFecha(Date fecha) throws Exception {
        Date hoy = new Date();
        if (fecha.before(hoy)) {
            throw new Exception("La fecha ingresada es pasada. Por favor, ingrese una fecha futura.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        int year = calendar.get(Calendar.YEAR);
        if (year < 2024 || year > 2030) {
            throw new Exception("La fecha ingresada no es válida.");
        }
    }
}