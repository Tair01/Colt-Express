package ens_projet.vue;

import ens_projet.controleur.Controleur;
import ens_projet.modele.Modele;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private final Modele model;
    final JFrame frame;
    private final VueCommandes vueC;

    public VueCommandes getVueCommandes(){
        return vueC;
    }
    private final VueEtat vueE;
    private final VueTrain vueT;
    public Controleur controleur;

    public Vue(Modele m) {
        model = m;
        frame = new JFrame("Colt Express");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        vueE = new VueEtat(m);
        vueT = new VueTrain(m);
        vueC = new VueCommandes(m);
        frame.add(vueT, BorderLayout.CENTER);
        frame.add(vueE, BorderLayout.WEST);
        controleur = new Controleur(model, vueC);
        frame.add(vueC, BorderLayout.SOUTH);
        frame.addMouseListener(controleur);
        frame.pack();
        frame.setVisible(true);
        vueC.repaint();
        vueE.repaint();
        vueT.repaint();
    }

    public void refresh(){
        vueE.repaint();
        vueT.repaint();
        vueC.repaint();
    }
}
