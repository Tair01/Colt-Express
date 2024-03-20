public abstract class Butin {
    private int valeur;
    private Wagon positionButin;
    private boolean ramasse;
    public Butin(int v){
        valeur = v;
    }
    public int getValeur() {return valeur;}
    public Wagon getPositionButin() {return positionButin;}
    public void setPositionButin(Wagon w){ positionButin = w;}
    public boolean estRamasse(){return ramasse;}
}

