import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Bandit extends Personne {
    private ArrayList<Butin> butins;

    public Bandit(String s, Train t) {
        super(s, true, t);
        position = t.getWagon(0);
        butins = new ArrayList<>();
        balles = NB_BALLES;
    }
    public ArrayList<Butin> getButins(){return butins;}
    public void ajouteButin(Butin b){butins.add(b); }

    // On considère que le bandit ne lâche que le dernier butin qu'il a ramassé
    public void lacheButin(){
        butins.clear();
    }

    public int montantT(){
        int t = 0; // total
        for(Butin b: butins){
            t+=b.getValeur();
        }
        return t;
    }
}
