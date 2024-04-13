package ens_projet.vue;

import ens_projet.modele.Modele;

import javax.swing.*;
import java.awt.*;

public class VueCommandes extends JPanel implements Observer {

    private Modele m;

    public VueCommandes(Modele m){
        this.m = m;
        m.addObserver(this);
        setPreferredSize(new Dimension(1000, 200));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.green);
        g.fillRect(0, 0, 1000, 200);
        g.setColor(Color.black);
        g.drawRect(0, 0, 1000, 200);

    }

    @Override
    public void update(){
        this.repaint();
    }
}
