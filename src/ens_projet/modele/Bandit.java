package ens_projet.modele;

import java.util.ArrayList;

public class
Bandit extends Personne {
    public static int NB_ACTIONS = 4;

    int actions;
    private ArrayList<Butin> butins;

    public Bandit(Modele m, String s, Train t) {
        super(m, s, true, t);
        position = t.getWagon(0);
        butins = new ArrayList<>();
        balles = Personne.NB_BALLES;
        actions = NB_ACTIONS;
    }

    public ArrayList<Butin> getButins() {
        return butins;
    }

    public void ajouteButin(Butin b) {
        butins.add(b);
    }

    // On considère que le bandit ne lâche que le dernier butin qu'il a ramassé
    public void lacheButin() {
        if ((!butins.isEmpty())) {
            butins.remove(butins.size() - 1);
        }
    }

    public int montantT() {
        int t = 0; // total
        for (Butin b : butins) {
            t += b.getValeur();
        }
        return t;
    }

    public void ajouteMunitions(CaisseMunitions c) {
        balles += c.getNbMunitions();
    }

    public void utiliseAction() {
        if (actions > 0) actions--;
    }

    public int getNbActions() {
        return actions;
    }
    public void resetNbActions() {
        this.actions = NB_ACTIONS;
    }

}
