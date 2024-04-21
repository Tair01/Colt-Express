package ens_projet.modele;

import java.util.ArrayList;

public class Wagon {
    protected final Train train;
    private final Modele modele;
    private final int numWag;
    private final ArrayList<Butin> butins;
    private final ArrayList<CaisseMunitions> caisses;

    public Wagon(Modele m, int n, Train t) {
        modele = m;
        numWag = n;
        train = t;
        butins = new ArrayList<>();
        caisses = new ArrayList<>();
    }

    public int getNumero() {
        return numWag;
    }

    public boolean isLocomotive() {
        return numWag == train.getNombreW() - 1;
    }

    public ArrayList<Bandit> banditsPresents() {
        ArrayList<Bandit> bandits = new ArrayList<>();
        for (Personne p : train.getPersonnes()) {
            if (p instanceof Bandit && this.getNumero() == p.getPosition().getNumero()) bandits.add(((Bandit) p));
        }
        return bandits;
    }

    public ArrayList<Personne> getPersonnesW() {
        ArrayList<Personne> personnes = new ArrayList<>();
        for (Personne p : train.getPersonnes()) {
            if (this.getNumero() == p.getPosition().getNumero()) personnes.add(p);
        }
        return personnes;
    }

    public Train getTrain() {
        return train;
    }

    public void ajouterButin(Butin b) {
        butins.add(b);
    }

    public void retireButin(Butin b) {
        butins.remove(b);
    }

    public ArrayList<Butin> getButins() {
        return butins;
    }

    public ArrayList<CaisseMunitions> getCaissesMunitions() {
        return caisses;
    }

}
