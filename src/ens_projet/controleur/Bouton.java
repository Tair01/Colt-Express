package ens_projet.controleur;

import javax.swing.*;
import java.awt.*;

public class Bouton extends JButton {
    public Bouton(String t, int a, int b, int da, int db){
        setText(t);
        setBounds(a,b,da,db);
        setFocusable(false);
    }
}
