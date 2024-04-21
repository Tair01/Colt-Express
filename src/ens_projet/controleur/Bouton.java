package ens_projet.controleur;

import javax.swing.*;

public class Bouton extends JButton {
    public Bouton(String texte, int x, int y, int largeur, int hauteur) {
        setText(texte);
        setBounds(x, y, largeur, hauteur);
        setFocusable(false);
    }
}
