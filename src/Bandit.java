public class Bandit extends Personne {
    public String nom;
    public Train train;
    public boolean surToit;
    public Wagon wagonActuel; // Le wagon actuel de bandit

    public Bandit(String n,boolean s){
        nom = n;
        surToit = s;
    }

    // Initialise le bandit à la position de départ
    public void positionInit(){
        if(train.getWagons().isEmpty()){
            throw new RuntimeException("Aucun wagon dans le train");
        }
        if(train.getDernierWagon().estPlein()){
            throw new RuntimeException("Le dernier wagon est plein");
        }
        Wagon dernierW = train.getDernierWagon();
        dernierW.ajouterBandit(this);
        surToit = true;
        wagonActuel = dernierW;
    }

    public void deplace(Direction d){
        /** Deplacement de bandit avec des conditions:
         1) Si il est sur le dernier wagon, alors il peut pas avancer vers l'arriere
         2) Si il est sur le locomotive, alors il peut pas avancer vers l'avant
         */
        if(d == Direction.AVANT && wagonActuel.isLocomotive()){
            throw new RuntimeException("Ne peut pas avancer vers l'avant");
        }
        if(d == Direction.ARRIERE && train.getDernierWagon().equals(this)){
            throw  new RuntimeException("Ne peut pas avancer vers l'arriere");
        }
        if (d == Direction.BAS && !surToit) {
            throw new RuntimeException("Ne peut pas descendre vers le bas, déjà à l'intérieur du wagon.");
        }
        if (d == Direction.HAUT && surToit) {
            throw new RuntimeException("Ne peut pas grimper vers le haut, déjà sur le toit du wagon.");
        }
        if(surToit){
            if (d == Direction.AVANT || d == Direction.ARRIERE) {
                wagonActuel.enleverBandit(this);
                wagonActuel = (d == Direction.AVANT) ? train.getWagonSuivant(wagonActuel) : train.getWagonPrecedente(wagonActuel);
                wagonActuel.ajouterBandit(this);
                surToit = true;
            }
            if(d == Direction.BAS){
                wagonActuel.enleverBandit(this);
                surToit = false; // Car l'interieur du wagon
            }
        }else{
            if (d == Direction.AVANT || d == Direction.ARRIERE) {
                wagonActuel.enleverBandit(this);
                wagonActuel = (d == Direction.AVANT) ? train.getWagonSuivant(wagonActuel) : train.getWagonPrecedente(wagonActuel);
                wagonActuel.ajouterBandit(this);
                surToit = false;
            } if (d == Direction.HAUT) {
                wagonActuel.enleverBandit(this);
                surToit = true; // Car sur le toit du wagon
            }
        }
    }
}

