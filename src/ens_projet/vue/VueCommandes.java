package ens_projet.vue;

import ens_projet.controleur.Bouton;
import ens_projet.modele.Modele;

import javax.swing.*;
import java.awt.*;

public class VueCommandes extends JPanel implements Observer {
    private Modele m;
    private Bouton[] banditDeplacer;
    private Bouton banditBraquer, banditTirer;

    public VueCommandes(Modele m){
        this.m = m;
        m.addObserver(this);
        setPreferredSize(new Dimension(1000, 200));
        initBoutons();
    }

    public void initBoutons(){
        setLayout(null);
        int largeurBouton = 50;
        int hauteurBouton = 50;

        // Positions et dimensions des boutons de déplacement
        banditDeplacer = new Bouton[4];
        banditDeplacer[0] = new Bouton("→", 570, 80, largeurBouton, hauteurBouton);
        banditDeplacer[1] = new Bouton("←", 420, 80, largeurBouton, hauteurBouton);
        banditDeplacer[2] = new Bouton("↓", 500, 150, largeurBouton, hauteurBouton);
        banditDeplacer[3] = new Bouton("↑", 500, 10, largeurBouton, hauteurBouton);

        // Haut: 500, 10
        // Bas: 500, 150
        // Gauche: 420, 80
        // Droite: 570, 80
        for(Bouton b: banditDeplacer){
            this.add(b);
        }

        // Position et dimension des boutons "Braquer" et "Tirer"
        banditBraquer = new Bouton("Braquer", 700, 50, largeurBouton * 2, hauteurBouton);
        banditTirer = new Bouton("Tirer", 700, 120, largeurBouton * 2, hauteurBouton);

        this.add(banditBraquer);
        this.add(banditTirer);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Bouton b: banditDeplacer) b.paintComponents(g);
        banditBraquer.paintComponents(g);
        banditTirer.paintComponents(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        //g.drawString("Actions restantes:" + m.getNbActions(),10, getHeight() - 10);
    }

    @Override
    public void update(){
        this.repaint();
    }
    public Bouton[] getBanditDeplacer() {
        return banditDeplacer;
    }
}
