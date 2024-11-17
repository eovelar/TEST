import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Restaurante {
    private List<Mesa> mesas = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    public void agregarMesa(Mesa mesa) {
        mesas.add(mesa);
    }

    public Mesa obtenerMesaDisponible(Tipo tipo, Date fechaInicio) {
        for (Mesa mesa : mesas) {
            // Comprobamos si la mesa es del tipo requerido y está disponible en la fecha
            if (mesa.getTipo().equals(tipo) && estaDisponible(mesa, fechaInicio)) {
                return mesa;
            }
        }
        return null; // No hay mesa disponible para el tipo y fecha especificados
    }

    private boolean estaDisponible(Mesa mesa, Date fecha) {
        for (Reserva reserva : reservas) {
            // Si la mesa ya tiene una reserva para la misma fecha, no está disponible
            if (reserva.getMesa().equals(mesa) && reserva.getFechaReserva().equals(fecha)) {
                return false;
            }
        }
        return true; // La mesa está disponible en la fecha especificada
    }

    public Reserva reservarMesa(Cliente cliente, Tipo tipo, Date fechaInicio) throws Exception {
        Mesa mesaDisponible = obtenerMesaDisponible(tipo, fechaInicio);
        if (mesaDisponible != null) {
            Reserva reserva = new Reserva(cliente, mesaDisponible, fechaInicio);
            reservas.add(reserva);
            return reserva;
        } else {
            throw new Exception("No hay mesas disponibles para el tipo " + tipo.getNombre() + " en la fecha seleccionada.");
        }
    }

    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas realizadas.");
        } else {
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        }
    }
}

// Esta clase es la base del programa.
// crea las mesas y  reserva a los clientes.