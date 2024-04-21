package ens_projet.modele;

import java.util.ArrayList;
import java.util.HashSet;

public class Train {
    private final Modele modele;
    private final ArrayList<Wagon> wagons;
    private final HashSet<Personne> personnes;
    private final int NB_WAGONS;

    public Train(Modele m, int n, HashSet<Personne> personnes) {
        modele = m;
        NB_WAGONS = n;
        wagons = new ArrayList<>();
        for (int i = 0; i < NB_WAGONS; i++) {
            wagons.add(new Wagon(m, i, this)); // Si l'indice est i - 1, le numero de 1 wagon est -1!
        }
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

    public HashSet<Personne> getPersonnes() {
        return personnes != null ? personnes : new HashSet<>();
    }

}
