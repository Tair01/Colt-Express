package ens_projet.modele;

public class CaisseMunitions extends Butin {
    int nbBalles;

    public CaisseMunitions(Modele m, Wagon p, int b) {
        super(m, 0, p);
        nbBalles = b;
    }

    public int getNbMunitions() {
        return nbBalles;
    }
}
