package ens_projet.modele;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Bandit extends Personne {
    private ArrayList<Butin> butins;

    public Bandit(String s, Train t) {
        super(s, true, t);
        position = t.getWagon(0);
        butins = new ArrayList<>();
        balles = Personne.NB_BALLES;
    }
    public ArrayList<Butin> getButins(){return butins;}
    public void ajouteButin(Butin b){butins.add(b); }

    // On considère que le bandit ne lâche que le dernier butin qu'il a ramassé
    public void lacheButin(){
        var var =  (!butins.isEmpty()) ? butins.remove(butins.size() - 1) : null;
    }

    public int montantT(){
        int t = 0; // total
        for(Butin b: butins){
            t+=b.getValeur();
        }
        System.out.println(this.getNom() + " a " + t  + "$");
        return t;
    }
}
