import java.util.ArrayList;
import java.util.HashSet;

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
    public boolean isLocomotive(){return numWag == train.getNombreW();}

    public ArrayList<Bandit> banditsPresents(){
        ArrayList<Bandit> bandits = new ArrayList<>();
        for(Personne p : train.getPersonnes()){
            if(p instanceof Bandit && this.getNumero() == p.getPosition().getNumero()) bandits.add(((Bandit) p));
        }
        return bandits;
}

    public void ajouterButin(Butin b){
        butins.add(b);
    }
    public void retireButin(Butin b){
        butins.remove(b);
    }
    public ArrayList<Butin> getButins(){return butins;}

}
