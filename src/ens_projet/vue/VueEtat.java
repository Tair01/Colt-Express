package ens_projet.vue;

import ens_projet.modele.*;

import javax.swing.*;
import java.awt.*;

public class VueEtat extends JPanel implements Observer {

    private final Modele m;

    public VueEtat(Modele m) {
        this.m = m;
        m.addObserver(this);
        setPreferredSize(new Dimension(200, 750));
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int k = 1;
        //FontMetrics fm = g.getFontMetrics();
        Font nom = new Font("Tahoma", Font.BOLD, (getHeight()/Modele.NB_BANDITS))/3
        for(Bandit b : m.getBandits()){
            g.drawString();
        }

    }

    @Override
    public void update() {
        this.repaint();
    }
}
