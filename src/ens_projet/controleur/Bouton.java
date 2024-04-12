package ens_projet.controleur;

import javax.swing.*;

public abstract class Bouton extends JButton {
    public Bouton(String t, int a, int b, int da, int db){
        setText(t);
        setLocation(a,b);
        setSize(da,db);
        setFocusable(false);
    }
}
