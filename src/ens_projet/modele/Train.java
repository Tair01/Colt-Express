package ens_projet.modele;

import ens_projet.modele.Personne;

import java.util.ArrayList;
import java.util.HashSet;

public class Train {
    private final ArrayList<Wagon> wagons;
    private final HashSet<Personne> personnes;
    private final int NB_WAGONS;

    public Train(int n, HashSet<Personne> personnes) {
        NB_WAGONS = n;
        wagons = new ArrayList<>();
        // on ajoute NB_WAGONS - 1 wagons au train
        for (int i = 1; i < NB_WAGONS; i++) {
            wagons.add(new Wagon(i - 1, this));
        }
        // dernier wagon : c'est une locomotive
        wagons.add(new Wagon(NB_WAGONS - 1, this));
        this.personnes = personnes != null ? personnes : new HashSet<>();
    }

    public ArrayList<Wagon> getWagons() {
        return wagons;
    }

    public Wagon getWagon(int i) {
        return wagons.get(i);
    }

    public int getNombreW() {
        return NB_WAGONS;
    }

    public HashSet<Personne> getPersonnes(){
        return personnes != null ? personnes : new HashSet<>();
    }
}

