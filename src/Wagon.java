public class Wagon {
    private int numero;
    private boolean estInterieur; // false si sur le toit

    public Wagon(int n, boolean estInt) {
        numero = n;
        estInterieur = estInt;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isEstInterieur() {
        return estInterieur;
    }
}
