public class Marshall extends Personne {
    protected static final double NERVOSITE_MARSHALL = 0.3;
    public Marshall(String n, Train t){
        super(n,false, t);
        position = t.getWagon(t.getNombreW() - 1);
        balles = NB_BALLES;
    }
}
