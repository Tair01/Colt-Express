public abstract class Butin {
    private final int valeur;
    private Bandit possesseur;
    Wagon position;
    private boolean isRamasse;

    public Butin(int v, Wagon position){
        valeur = v;
        position = position;
        possesseur = null;
    }
    public int getValeur() {return valeur;}

    /*public Wagon getPositionButin() {return positionButin;}
    public boolean estRamasse(){return ramasse;}

    public void setBandit(Bandit b) { this.b = b;}*/
}

