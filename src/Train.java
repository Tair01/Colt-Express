import java.util.ArrayList;

public class Train {
    private ArrayList<Wagon> wagons;
    private int nombreW;
    public Train(int n){
        nombreW = n;
        wagons = new ArrayList<>();
        for(int i = 0; i < nombreW; i++){
            wagons.add(new Wagon(i, true));
            wagons.add(new Wagon(i, false));
        }
    }

    public ArrayList<Wagon> getWagons() {
        return wagons;
    }

    public int getNombreW(){
        return nombreW;
    }
}
