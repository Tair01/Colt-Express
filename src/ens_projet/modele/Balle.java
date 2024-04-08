package ens_projet.modele;

public class Balle  extends Butin{
    private static  final int PUISSANCE  = 1;
    public Balle(Modele m,Wagon p){
        super(m,0, p);
    }

    public static int getPUISSANCE() {
        return PUISSANCE;
    }

    public String getNom(){
        return "balle (puissance " + getPUISSANCE() + ")";
    }
}
