package ens_projet.vue;

import ens_projet.modele.Modele;

import javax.swing.*;
import java.awt.*;

public class VueEtat extends JPanel implements Observer {

    private Modele m;

    public VueEtat(Modele m){
        this.m = m;
        m.addObserver(this);
        setPreferredSize(new Dimension(200, 800));
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillRect(0, 0, 200, 800);
        g.setColor(Color.black);
        g.drawRect(0, 0, 200, 800);

    }

    @Override
    public void update(){
        this.repaint();
    }
}
