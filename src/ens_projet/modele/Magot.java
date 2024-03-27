package ens_projet.modele;

import ens_projet.modele.Butin;

public class Magot extends Butin {
    public Magot(Train t){
        super(1000, t.getWagon(t.getNombreW() - 1), "ens_projet.modele.Magot") ;
    }
}
