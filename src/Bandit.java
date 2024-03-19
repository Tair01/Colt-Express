import java.util.ArrayList;
import java.util.HashSet;

public class Bandit extends Personne {
    private HashSet<Butin> butins;
    public Bandit(String n, boolean s) {
        super(n, s);
        butins = new HashSet<>();
    }
    public HashSet<Butin> getButins(){return butins;}
    public void ajouteButin(Butin b){butins.add(b); }
    public void lacheButin(){butins.remove(butins.size());}
    public int montantT(){
        int t = 0; // total
        for(Butin b: butins){
            if(b instanceof Bourse bourse) t += bourse.getValeur();
            if(b instanceof Bijou) t += Bijou.VALEUR;
            if(b instanceof Magot) t+= Magot.VALEUR;
        }
        return t;
    }

    @Override
    void effectuerAction(Action a, Direction direction) {
        Personne personne = a.getPersonne();
        if (personne instanceof Bandit) {
            Bandit bandit = (Bandit) personne;
            if (a instanceof Deplacer) {
                Deplacer d = (Deplacer) a;
                if (bandit.getPosition() != null) {
                    d.executer();
                }
            } else if (a instanceof Tirer) {
                Tirer t = (Tirer) a;
                t.executer();
            } else if (a instanceof Braquer) {
                Braquer b = (Braquer) a;
                b.executer();
            } else {
                throw new IllegalArgumentException("Action non prise en charge pour le bandit.");
            }
        } else {
            throw new IllegalArgumentException("Action associée à une personne invalide.");
        }
    }
}
