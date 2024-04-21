package ens_projet.vue;

import ens_projet.controleur.ControleurBandit;
import ens_projet.modele.Modele;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private final Modele model;
    final JFrame frame;
    private final VueCommandes vueC;
    private final VueEtat vueE;
    private final VueTrain vueT;

    // TODO - réfléchir : faut-il rajouter une classe VueWagon et transformer VueTrain en JFrame dont le Layout est Flow ou Grid ? VueWagon pourra aussi être un JFrame en FlowLayout de deux autres JFrames en FlowLayout (intérieur et toit) (qui eux-mêmes peuvent être deux JFrames flowLayout : l'un montrant les personnes et l'autre montrant les butins)
    // https://javatutorial.net/wp-content/uploads/2017/07/gridLayout.png

    public Vue(Modele m) {
        model = m;
        frame = new JFrame("Colt Express");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        vueC = new VueCommandes(m);
        vueE = new VueEtat(m);
        vueT = new VueTrain(m);
        frame.add(vueT, BorderLayout.CENTER);
        frame.add(vueC, BorderLayout.SOUTH);
        frame.add(vueE, BorderLayout.WEST);
        ControleurBandit controleurBandit = new ControleurBandit(model, vueC);
        frame.addMouseListener(controleurBandit);
        //frame.addKeyListener(controleurBandit);
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
