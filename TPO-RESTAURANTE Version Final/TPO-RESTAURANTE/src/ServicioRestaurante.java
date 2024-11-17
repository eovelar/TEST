import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ServicioRestaurante {
    private Restaurante restaurante;
    private List<MenuCompleto> menus;

    public ServicioRestaurante(Restaurante restaurante, List<MenuCompleto> menus) {
        this.restaurante = restaurante;
        this.menus = menus;
    }

    private static Tipo solicitarTipoMesa(Scanner scanner, Tipo mesaDosPersonas, Tipo mesaCuatroPersonas, Tipo mesaSeisPersonas) {
        try {
            System.out.print("Tipo de mesa (2 personas, 4 personas, 6 personas): ");
            String tipoMesa = scanner.nextLine();

            if (tipoMesa.equalsIgnoreCase("2 personas")) {
                return mesaDosPersonas;
            } else if (tipoMesa.equalsIgnoreCase("4 personas")) {
                return mesaCuatroPersonas;
            } else if (tipoMesa.equalsIgnoreCase("6 personas")) {
                return mesaSeisPersonas;
            } else {
                System.out.println("Tipo de mesa no es válido.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar el tipo de mesa: " + e.getMessage());
            return null;
        }
    }

    public void comerEnRestaurante(Scanner scanner, Tipo mesaDosPersonas, Tipo mesaCuatroPersonas, Tipo mesaSeisPersonas) {
        if (menus.isEmpty()) {
            System.out.println("No hay menús disponibles. Por favor, cree los menús primero en la opción 3.");
            return;
        }
        try {
            Tipo tipoMesa = solicitarTipoMesa(scanner, mesaDosPersonas, mesaCuatroPersonas, mesaSeisPersonas);
            if (tipoMesa == null) {
                return;
            }

            System.out.println("Ha seleccionado una mesa para " + tipoMesa.getNombre() + ".");


            System.out.println("Seleccione el menú que desea pedir:");
            for (int i = 0; i < menus.size(); i++) {
                MenuCompleto menu = menus.get(i);
                System.out.println((i + 1) + ". " + menu.getPlato().getNombre() + " con " + menu.getPostre().getNombre());
            }
            System.out.print("Opción: ");
            int opcionMenu = scanner.nextInt();
            scanner.nextLine();

            if (opcionMenu > 0 && opcionMenu <= menus.size()) {
                MenuCompleto menuSeleccionado = menus.get(opcionMenu - 1);
                System.out.println("Ha seleccionado el siguiente menú:");
                System.out.println("Plato principal: " + menuSeleccionado.getPlato().getNombre());
                System.out.println("Postre: " + menuSeleccionado.getPostre().getNombre());
                System.out.println("Precio total del menú: $" + menuSeleccionado.cualEsTuPrecio());
            } else {
                System.out.println("Opción de menú no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error durante la selección del menú: " + e.getMessage());
        }
    }

    public void hacerReserva(Scanner scanner, Tipo mesaDosPersonas, Tipo mesaCuatroPersonas, Tipo mesaSeisPersonas) {
        System.out.println("Ingrese los datos de la reserva:");
        try {
            Tipo tipo = solicitarTipoMesa(scanner, mesaDosPersonas, mesaCuatroPersonas, mesaSeisPersonas);
            if (tipo == null) {
                return;
            }

            Date fechaInicio = null;
            int intentosFechas = 0;
            boolean fechasValidas = false;

            while (!fechasValidas && intentosFechas < 3) {
                try {
                    System.out.print("Fecha de ingreso (AAAA-MM-DD): ");
                    String fechaInicioStr = scanner.nextLine();
                    fechaInicio = Main.parseFecha(fechaInicioStr); // Llamar al método estático de Main
                    Main.validarFecha(fechaInicio); // Llamar al método estático de Main

                    Mesa mesa = restaurante.obtenerMesaDisponible(tipo, fechaInicio);
                    if (mesa == null) {
                        throw new Exception("No hay mesas " + tipo.getNombre() + " disponibles para las fechas seleccionadas.");
                    }
                    fechasValidas = true;

                    System.out.print("Nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();

                    System.out.print("DNI del cliente: ");
                    String dniCliente = scanner.nextLine();

                    Reserva reserva = restaurante.reservarMesa(new Cliente(nombreCliente, dniCliente), tipo, fechaInicio);
                    System.out.println("\nReserva realizada correctamente:");
                    System.out.println(reserva);

                } catch (Exception e) {
                    System.out.println("Error al realizar la reserva: " + e.getMessage());
                    intentosFechas++;
                }
            }

            if (!fechasValidas) {
                System.out.println("No fue posible hacer la reserva después de varios intentos. Intente con otra fecha o tipo de mesa.");
            }
        } catch (Exception e) {
            System.out.println("Error general en el proceso de reserva: " + e.getMessage());
        }
    }

    public void crearMenuCompleto(Scanner scanner) {

            if (menus.size() >= 2) {
                System.out.println("Ya se han creado los 2 menús permitidos.");
                return;
            }

            System.out.println("Creando un nuevo menú completo...");

            System.out.print("Ingrese el costo base del menú: "); // solo permite precios positivos
            try {
            int costoBase = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            // Crear el plato principal
            System.out.print("Ingrese el nombre del plato principal y su guarnicion : ");
            String nombrePlato = scanner.nextLine();
            PlatoPrincipal plato = new PlatoPrincipal(nombrePlato);

            // Crear el postre, solo permite precios positivos
            System.out.print("Ingrese el nombre del postre: ");
            String nombrePostre = scanner.nextLine();
            System.out.print("Ingrese el costo del postre: ");
            int costoPostre = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Postre postre = new Postre(nombrePostre, costoPostre);

            // Crear el nuevo menú
            MenuCompleto nuevoMenu = new MenuCompleto(costoBase, plato, postre);

            // Verificar si el menú ya existe en la lista
            for (MenuCompleto menu : menus) {
                if (menu.getPlato().getNombre().equalsIgnoreCase(nombrePlato) &&
                        menu.getPostre().getNombre().equalsIgnoreCase(nombrePostre)) {
                    System.out.println("Este menú ya ha sido creado. Intente con otro plato o postre.");
                    return;
                }
            }

            // Agregar el nuevo menú a la lista
            menus.add(nuevoMenu);
            System.out.println("Menú completo creado:");
            System.out.println("Plato principal: " + nuevoMenu.getPlato().getNombre());
            System.out.println("Postre: " + nuevoMenu.getPostre().getNombre());
            System.out.println("Costo total del menú: $" + nuevoMenu.cualEsTuPrecio());
        } catch (Exception e) {
            System.out.println("Error al crear el menú: " + e.getMessage());
        }
    }
}

// esta clase son todos los servicios que brinda el restaurante