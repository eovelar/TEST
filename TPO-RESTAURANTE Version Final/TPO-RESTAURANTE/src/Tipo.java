public class Tipo {
    private String nombre;
    private float precio;

    public Tipo(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }
}

// Clase para identificar los tipos de mesa (para 2 personas, 4 personas o 6 personas)
// El precio seria el cobro de cubiertos
