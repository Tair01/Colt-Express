package ens_projet.modele;

import java.util.Random;

public enum Direction {
    AVANT, ARRIERE, HAUT, BAS;

    public static Direction random(){
        Random rnd = new Random();
        int r = rnd.nextInt(4);
        switch(r){
            case 0: return Direction.AVANT;
            case 1: return Direction.ARRIERE;
            case 2: return Direction.HAUT;
            case 3: return Direction.BAS;
            default: throw new RuntimeException("La direction est inconnue");
        }
    }

}