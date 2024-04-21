package ens_projet.vue;

import ens_projet.modele.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class VueEtat extends JPanel implements Observer {

    private final Modele m;

    public VueEtat(Modele m) {
        this.m = m;
        m.addObserver(this);
        setPreferredSize(new Dimension(200, 750));
        setBackground(Color.DARK_GRAY);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 20;
        int taille = 12;
        Font police = new Font("Tahoma", Font.BOLD, taille);
        g.setFont(police);
        for(Bandit b : m.getBandits()){
            try {
                if (Objects.equals(b.toString(), m.getBanditEnAction().toString())) g.setColor(Color.GREEN);
            } catch (NullPointerException e){
                m.setBanditEnAction(m.getBandits().get(0));
            }
            if (b.toString().equals(m.getBanditEnAction().toString())) g.setColor(Color.GREEN);
            else g.setColor(Color.WHITE);
            g.setFont(police.deriveFont(Font.BOLD));
            g.drawString(b + " :", 5, y);
            g.setColor(Color.WHITE);
            g.setFont(police.deriveFont(Font.PLAIN));
            g.drawString("Argent : " + b.montantT() + " $", 15, y + taille + 2);
            g.drawString("Nombre de balles : " + b.getBalles(), 15, y + 2*(taille + 2));
            y = y + 3*(taille + 2);
            g.drawString("Nombre d'actions : " + b.getNbActions(), 15, y);
            y += 20;
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
