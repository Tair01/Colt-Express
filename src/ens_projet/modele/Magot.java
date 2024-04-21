package ens_projet.modele;

public class Magot extends Butin {
    public Magot(Modele m, Train t) {
        super(m, 1000, m.getTrain().getWagon(t.getNombreW() - 1));
    }
}
