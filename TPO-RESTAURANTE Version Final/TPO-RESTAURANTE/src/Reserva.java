import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    private Cliente cliente;
    private Mesa mesa;
    private Date fechaReserva;


    public Reserva (Cliente cliente, Mesa habitacion, Date fechaReserva) {
        this.cliente = cliente;
        this.mesa = habitacion;
        this.fechaReserva = fechaReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }


    // Mostrar datos de la reserva realizada correctamente por consola.
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Cliente: " + cliente.getNombre() + " | " + cliente.getDni() + "\n" +
                "Número Mesa: " + mesa.getNumero() + "\n" +
                "Tipo de Mesa: " + mesa.getTipo().getNombre() + "\n" +
                "Fecha de Reserva: " + dateFormat.format(fechaReserva) + "\n";
    }
}

//Clase para realizar las reservas del hotel.