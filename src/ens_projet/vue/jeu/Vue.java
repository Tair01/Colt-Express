package ens_projet.vue.jeu;

import javax.swing.*;

public class Vue extends JFrame {
    public Vue(){
        super("Colt Express");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        setVisible(true);
    }
}
