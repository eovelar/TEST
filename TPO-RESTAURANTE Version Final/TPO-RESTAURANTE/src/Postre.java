public class Postre {
    private String nombre;
    private int costoPostre;

    public Postre(String nombre, int costoPostre) {
        this.nombre = nombre;
        this.costoPostre = costoPostre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCostoPostre() {
        return costoPostre;
    }
}