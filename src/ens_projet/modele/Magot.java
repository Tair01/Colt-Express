package ens_projet.modele;

import ens_projet.modele.Butin;

public class Magot extends Butin {
    public Magot(Modele m,Train t){
        super(m,1000, t.getWagon(t.getNombreW() - 1)) ;
    }
}
