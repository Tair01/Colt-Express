package ens_projet.modele;

import ens_projet.modele.Bandit;

public abstract class Butin {
    private final int valeur;
    private Bandit possesseur;
    Wagon position;
    private boolean isRamasse;
    private String nom;

    public Butin(int v, Wagon p){
        valeur = v;
        position = p;
        possesseur = null;
    }
    public int getValeur() {return valeur;}

    public String getNom() {
        if(this instanceof Bijou) return "bijou (500$)";
        else if (this instanceof Magot) return "magot (1000$)";
        else return "bourse (" + valeur + "$)";
    }

    /*public ens_projet.modele.Wagon getPositionButin() {return positionButin;}
    public boolean estRamasse(){return ramasse;}

    public void setBandit(ens_projet.modele.Bandit b) { this.b = b;}*/
}

