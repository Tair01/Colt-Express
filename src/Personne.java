public abstract class Personne {
    private Wagon position;
    private String nom;
    private boolean surLeToit;
    private int nbBalles;
    public Wagon getPosition(){
        return position;
    }
    public boolean isSurLeToit() { return surLeToit; }
    abstract void effectuerAction(Action a);
}
