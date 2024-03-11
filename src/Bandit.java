public class Bandit {
    public String nom;
    public Wagon wagon;
    public boolean surToit;

    public Bandit(String n, Wagon w, boolean s){
        nom = n;
        wagon = w;
        surToit = s;
    }

    public void positionInit(){
        // Initialiser le bandit sur le toit du dernier wagon de train
    }

    public void deplace(Direction d){
        /** Deplacement de bandit avec des conditions:
         1) Si il est sur le toit du dernier wagon, alors il peut pas avancer vers l'arriere
         2) Si il est sur le toit de locomotive, alors il peut pas avancer vers l'avant
         3)
         */

    }
}
