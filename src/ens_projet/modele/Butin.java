package ens_projet.modele;

import ens_projet.modele.Bandit;

public abstract class Butin {
    private final int valeur;
    private Bandit possesseur;
    Wagon position;
    private boolean isRamasse;
    private String nom;

    public Butin(int v, Wagon position, String n){
        valeur = v;
        position = position;
        possesseur = null;
        nom = n;
    }
    public int getValeur() {return valeur;}

    public String getNom() { return nom; }

    /*public ens_projet.modele.Wagon getPositionButin() {return positionButin;}
    public boolean estRamasse(){return ramasse;}

    public void setBandit(ens_projet.modele.Bandit b) { this.b = b;}*/
}

