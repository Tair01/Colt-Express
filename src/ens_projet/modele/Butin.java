package ens_projet.modele;

public abstract class Butin {
    private final Modele modele;
    private final int valeur;
    private Bandit possesseur;
    Wagon position;
    private boolean isRamasse, surLeToit;
    //private String nom;

    public Butin(Modele m, int v, Wagon p) {
        modele = m;
        valeur = v;
        position = p;
        possesseur = null;
        surLeToit = false;
    }

    public int getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        if (this instanceof Bijou) return "bijou (500$)";
        else if (this instanceof Magot) return "magot (1000$)";
        else return "bourse (" + valeur + "$)";
    }

    public boolean isSurLeToit() {
        return surLeToit;
    }

    /*public ens_projet.modele.Wagon getPositionButin() {return positionButin;}
    public boolean estRamasse(){return ramasse;}

    public void setBandit(ens_projet.modele.Bandit b) { this.b = b;}*/
}

