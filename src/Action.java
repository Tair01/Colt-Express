public abstract class Action {
    private Personne personne;
    private Direction direction;
    public Personne getPersonne(){return personne;}
    public Direction getDirection(){return direction;}
    abstract void executer(Direction d);
}
