package ens_projet.vue;

import ens_projet.controleur.Bouton;
import ens_projet.controleur.Controleur;
import ens_projet.modele.Bandit;
import ens_projet.modele.Direction;
import ens_projet.modele.Modele;

import javax.swing.*;
import java.awt.*;


// VueCommande est une vue indépendante qui initie des processus
// grâce à des entrées utilisateur (texte, souris). Par conséquent, elle n'a pas besoin
// d'implémenter l'interface Observer

public class VueCommandes extends JPanel {
    private final Modele m;
    private Bouton banditDeplacerHaut, banditDeplacerBas, banditDeplacerAvant, banditDeplacerArriere;
    private Bouton banditBraquer, banditTirer, banditAction;

    public VueCommandes(Modele m) {
        this.m = m;
        setPreferredSize(new Dimension(1000, 200));
        setLayout(null);
        initBoutons();
    }

    public void initBoutons() {
        int size = 60;

        // ce carré central permet de positionner automatiquement les six boutons
        // en effet, la forme groupée de des boutons de déplacement forme un carré au milieu, donc pour les positionner, nous pouvons l'exploiter
        // et nous pouvons nous servir de cela pour positionner également les boutons d'action
        // size, X, Y
        int[] centralSquare = {60, 200, 75};

        // Positions et dimensions des boutons de déplacement

        banditDeplacerAvant = new Bouton("→", centralSquare[1] + centralSquare[0], centralSquare[2], size, size);
        banditDeplacerArriere = new Bouton("←", centralSquare[1] - centralSquare[0], centralSquare[2], size, size);
        banditDeplacerBas = new Bouton("↓", centralSquare[1], centralSquare[2] + centralSquare[0], size, size);
        banditDeplacerHaut = new Bouton("↑", centralSquare[1], centralSquare[2] - centralSquare[0], size, size);
        add(banditDeplacerAvant);
        add(banditDeplacerArriere);
        add(banditDeplacerBas);
        add(banditDeplacerHaut);


        // Position et dimension des boutons "Braquer" et "Tirer"
        banditBraquer = new Bouton("Braquer", centralSquare[1] + centralSquare[0] + size + 50, centralSquare[2] - size/2, size * 3, size * 2);
        banditTirer = new Bouton("Tirer", centralSquare[1] + centralSquare[0] + size*4 + 50, centralSquare[2] - size/2, size * 3, size * 2);
        banditAction = new Bouton("Action !", centralSquare[1] + centralSquare[0] + size*7 + 50, centralSquare[2] - size/2, size*3, size*2);

        add(banditAction);
        add(banditBraquer);
        add(banditTirer);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Bouton getBanditDeplacer(Direction d) {
        switch(d){
            case HAUT: return banditDeplacerHaut;
            case BAS : return banditDeplacerBas;
            case AVANT : return banditDeplacerAvant;
            case ARRIERE: return banditDeplacerArriere;
            default: return null;
        }
    }

    public Bouton getBanditBraquer() {
        return banditBraquer;
    }

    public Bouton getBanditTirer() {
        return banditTirer;
    }

    public Bouton getBanditAction() {
        return banditAction;
    }

}
