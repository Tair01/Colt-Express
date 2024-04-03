package ens_projet.modele;

public abstract class Personne {
    static final int NB_BALLES = 6;
    protected Wagon position;
    Train train;
    private final String nom;
    private boolean surLeToit;
    int balles;
    public Personne(String n, boolean s, Train t){
        // vérification de l'absence du nom choisi dans le train en question
        if(!(checkNomDejaUtilise(t, n))) {
            nom = n;
            surLeToit = s;
            this.train = t;
        }
        else throw new IllegalArgumentException("Une personne portant le même nom se trouve déjà dans le train.");
    }
    // Cette méthode servira à vérifier que l'on ajoute pas deux personnes ayant le même nom dans le même train
    // Cela poserait des problèmes d'unicité
    private static boolean checkNomDejaUtilise(Train t, String nom){
        for(Personne p : t.getPersonnes()){
            if(p.getNom() == nom) return true;
        }
        return false;
    }
    public Wagon getPosition(){
        return position;
    }

    public void setPosition(Wagon w){ position =  w;}

    public int getBalles() {
        return balles;
    }
    public void tire(){
        if(balles > 0) balles--;
    }
    public boolean isSurLeToit() { return surLeToit; }
    public void setSurLeToit(){surLeToit = !(surLeToit);}
    void effectuerAction(Action a){
        if(a.getPersonne().getNom().equals(getNom())) a.executer();
    }
    public String getNom(){
        return nom;
    }
}
