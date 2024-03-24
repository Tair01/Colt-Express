public class Magot extends Butin{
    public Magot(Train t){
        super(1000, t.getWagon(t.getNombreW() - 1)) ;
    }
}
