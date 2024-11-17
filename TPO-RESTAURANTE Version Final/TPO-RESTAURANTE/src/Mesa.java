public class Mesa {
    private int numero;
    private Tipo tipo;
    private int contadorMesaPara2 = 10;
    private int contadorMesaPara4 = 20;
    private int contadorMesaPara6 = 30;

    public Mesa(Tipo tipo) {
        this.numero = asignarNumero(tipo);
        this.tipo = tipo;
    }

    // Asignar número a las mesas (2 personas: desde 10, 4 personas: desde 20, 6 personas: desde 30)
    private int asignarNumero(Tipo tipo) {
        switch (tipo.getNombre()) {
            case "2 personas":
                return contadorMesaPara2++;
            case "4 personas":
                return contadorMesaPara4++;
            case "6 personas":
                return contadorMesaPara6++;
            default:
                throw new IllegalArgumentException("Tipo de habitación desconocido: " + tipo.getNombre());
        }
    }

    public int getNumero() {
        return numero;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
