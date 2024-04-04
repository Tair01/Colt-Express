package ens_projet.modele;

import java.util.Random;

public enum Direction {
    AVANT, ARRIERE, HAUT, BAS;

    public static Direction random() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}