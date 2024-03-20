public class Magot extends Butin{
    public static final int VALEUR = 1000;
    public Magot(){
        super(VALEUR);
    }
    public void setPosition(Train train){
        Wagon locomotive = train.getWagonInd(train.getNombreW());
        this.setPositionButin(locomotive);
    }
}
