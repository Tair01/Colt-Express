package ens_projet;

import ens_projet.modele.Modele;
import ens_projet.vue.Vue;

public class Main {
    public static void main(String[] args) {
        Modele m = new Modele();
        m.initialise();
        Vue v = new Vue(m);
        v.afficheTout();
    }
}
