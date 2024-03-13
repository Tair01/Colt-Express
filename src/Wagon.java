import java.util.ArrayList;

public class Wagon {
    private int numero;
    private boolean locomotive; // true si est un loco et false sinon
    private ArrayList<Bandit> listBandits;

    public Wagon(int n,boolean l) {
        numero = n;
        locomotive = l;
    }

    public int getNumero() {
        return numero;
    }
    public boolean isLocomotive(){return locomotive;}

    public void ajouterBandit(Bandit b){
        listBandits.add(b);
    }
    public void enleverBandit(Bandit b){
        listBandits.remove(b);
    }
    public boolean estPlein(){
        return !listBandits.isEmpty();
    }

    public ArrayList<Bandit> getListBandits() {
        return listBandits;
    }
}
