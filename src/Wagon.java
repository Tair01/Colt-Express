import java.util.ArrayList;
import java.util.HashSet;

public class Wagon {
    private int numWag;
    Train train;
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

    public int nbBanditsPresents(){
        int s = 0;
        for(Personne p : train.getPersonnes()){
            if(p instanceof Bandit && this.equals(p.getPosition())) s++;
        }
        return s;
    }

    public void ajouterButin(Butin b){
        butins.add(b);
    }
    public void retireButin(Butin b){
        butins.remove(b);
    }
    public ArrayList<Butin> getButins(){return butins;}
}
