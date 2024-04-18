package ens_projet.modele;

import ens_projet.modele.Personne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Train {
    private final Modele modele;
    private final ArrayList<Wagon> wagons;
    private final HashSet<Personne> personnes;
    private final int NB_WAGONS;
    private Bandit banditChoisi;

    public Train(Modele m, int n, HashSet<Personne> personnes) {
        modele = m;
        NB_WAGONS = n;
        wagons = new ArrayList<>();
        for (int i = 0; i < NB_WAGONS; i++) {
            wagons.add(new Wagon(m,i, this)); // Si l'indice est i - 1, le numero de 1 wagon est -1!
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

    public HashSet<Personne> getPersonnes(){
        return personnes != null ? personnes : new HashSet<>();
    }
    public Bandit choisirPersonne(String nom){
        for(Personne p: personnes) {
            if (p instanceof Bandit && Objects.equals(p.toString(), nom)) {
                banditChoisi = (Bandit) p;
                return (Bandit) p;
            }
        }

        return null;
    }
    public Bandit getBanditChoisi(){ return banditChoisi;}
    public Marshall getMarshall() {
        for (Personne p : personnes) {
            if (p instanceof Marshall) return (Marshall) p;
        }
        return null;
    }
}
