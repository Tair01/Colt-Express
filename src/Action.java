public abstract class Action {
    private Personne personne;
    private Direction direction;
    public Action(Personne p, Direction d){
        personne = p;
        direction = d;
    }
    public Personne getPersonne(){return personne;}
    public Direction getDirection(){return direction;}
    abstract void executer();
}
