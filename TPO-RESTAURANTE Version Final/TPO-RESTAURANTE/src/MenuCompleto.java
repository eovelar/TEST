public class MenuCompleto {
    private int costoBase;
    private PlatoPrincipal plato;
    private Postre postre;

    public MenuCompleto(int costoBase, PlatoPrincipal plato, Postre postre) {
        this.costoBase = costoBase;
        this.plato = plato;
        this.postre = postre;
    }

    public int getCostoBase() {
        return costoBase;
    }

    public PlatoPrincipal getPlato() {
        return plato;
    }

    public Postre getPostre() {
        return postre;
    }

    public int cualEsTuPrecio() {
        return (this.postre.getCostoPostre() + this.getCostoBase());
    }
}
