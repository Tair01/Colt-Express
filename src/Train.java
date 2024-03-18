import java.util.ArrayList;
import java.util.HashSet;

public class Train {
    private  ArrayList<Wagon> wagons;
    private HashSet<Personne> personnes;
    private int nombreW;

    public Train(int n) {
        nombreW = n;
        wagons = new ArrayList<>();
        for (int i = 0; i < nombreW; i++) {
            wagons.add(new Wagon(i,this));
        }
        //Dernière wagon est un Locomotive
        wagons.add(new Wagon(nombreW,this));
        personnes = new HashSet<>();
    }

    public ArrayList<Wagon> getWagons() {
        return wagons;
    }

    public Wagon getWagonInd(int i) {
        return wagons.get(i);
    }

    public int getNombreW() {
        return nombreW;
    }


    public HashSet<Personne> getPersonnes(){
        return personnes;
    }


    public void ajoutePersonne(Personne p){
        if(p != null) personnes.add(p);
    }
    public void retirerPersonne(Personne p){
        if(p != null) personnes.remove(p);
    }

    public void nouveauPosition(Personne p, Wagon newW){
        this.retirerPersonne(p);
        p.setPosition(newW);
        ajoutePersonne(p);
    }
}

