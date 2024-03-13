import java.util.ArrayList;

public class Train {
    private ArrayList<Wagon> wagons;
    private int nombreW;
    public Train(int n){
        nombreW = n;
        wagons = new ArrayList<>();

        //Premier wagon est un Locomotive
        wagons.add(new Wagon(0,true));
        for(int i = 0; i < nombreW; i++){
            wagons.add(new Wagon(i, false));
            wagons.add(new Wagon(i, false));
        }
    }

    public ArrayList<Wagon> getWagons() {
        return wagons;
    }

    public int getNombreW(){
        return nombreW;
    }

    public Wagon getDernierWagon(){
        return wagons.get(nombreW - 1);
    }

    public Wagon getPremierWagon(){
        return wagons.get(0);
    }

    public Wagon getWagonSuivant(Wagon actuel) {
        int index = wagons.indexOf(actuel);
        // Faut faire les cas des exceptions pour l'index
        return wagons.get(index + 1);
    }

    public Wagon getWagonPrecedente(Wagon actuel){
        int index = wagons.indexOf(actuel);
        return wagons.get(index - 1);
    }
}
