package ens_projet.modele;

import ens_projet.modele.Bandit;
import ens_projet.modele.Butin;
import ens_projet.modele.Personne;
import ens_projet.modele.Train;

import java.util.ArrayList;

public class Wagon {
    private final int numWag;
    protected final Train train;
    private ArrayList<Butin> butins;

    public Wagon(int n, Train t) {
        numWag = n;
        train = t;
        butins = new ArrayList<>();
    }

    public int getNumero() {
        return numWag;
    }
    public boolean isLocomotive(){return numWag == train.getNombreW() - 1;}

    public ArrayList<Bandit> banditsPresents(){
        ArrayList<Bandit> bandits = new ArrayList<>();
        for(Personne p : train.getPersonnes()){
            if(p instanceof Bandit && this.getNumero() == p.getPosition().getNumero()) bandits.add(((Bandit) p));
        }
        return bandits;
    }

    public ArrayList<Butin> butinsPresents(){
        ArrayList<Butin> butins = new ArrayList<>(this.getButins());
        return butins;
    }

    public void ajouterButin(Butin b){
        butins.add(b);
    }
    public void retireButin(Butin b){
        butins.remove(b);
    }
    public ArrayList<Butin> getButins(){return butins;}

}
