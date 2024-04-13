package ens_projet;

import ens_projet.vue.Vue;
import ens_projet.modele.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        Modele m = new Modele();
        m.initialise();
        Vue v  = new Vue(m);
        v.afficheTout();
    }
}
