public abstract class Personne {
    private Wagon position;
    private String nom;
    private boolean surLeToit;
    private int nbBalles;
    public Personne(String n, boolean s){
        nom = n;
        surLeToit = s;
    }
    public Wagon getPosition(){
        return position;
    }
    public void setPosition(Wagon newPosition){position = newPosition;}
    public boolean isSurLeToit() { return surLeToit; }
    public void setSurLeToit(boolean s){surLeToit = s;}
    abstract void effectuerAction(Action a,Direction direction);

}
