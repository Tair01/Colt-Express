package ens_projet.vue;

import ens_projet.controleur.ControleurBandit;
import ens_projet.modele.Modele;

import javax.swing.*;
import java.awt.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Vue {
    private final Modele model;
    private final JFrame frame;
    private final VueCommandes vueC;
    private final VueEtat vueE;
    private final VueTrain vueT;

    // TODO - réfléchir : faut-il rajouter une classe VueWagon et transformer VueTrain en JFrame dont le Layout est Flow ou Grid ? VueWagon pourra aussi être un JFrame en FlowLayout de deux autres JFrames en FlowLayout (intérieur et toit) (qui eux-mêmes peuvent être deux JFrames flowLayout : l'un montrant les personnes et l'autre montrant les butins)
    // https://javatutorial.net/wp-content/uploads/2017/07/gridLayout.png

    public Vue(Modele m){
        model = m;
        frame = new JFrame("Colt Express");
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        vueC = new VueCommandes(m);
        vueE = new VueEtat(m);
        vueT = new VueTrain(m);
        frame.add(vueT, BorderLayout.CENTER);
        frame.add(vueC, BorderLayout.SOUTH);
        frame.add(vueE, BorderLayout.WEST);
        ControleurBandit controleurBandit = new ControleurBandit(model, vueT, vueC);
        addMouseListener(controleurBandit);
        addKeyListener(controleurBandit);
        frame.pack();
        frame.setVisible(true);
    }

    public void afficheTout(){
        vueT.repaint();
        vueE.repaint();
        vueC.repaint();
    }

}
