package ens_projet.controleur;

import ens_projet.modele.*;
import ens_projet.vue.VueCommandes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControleurBandit implements MouseListener {
    private final Modele modele;
    //private final VueTrain vueT;
    private final VueCommandes vueC;

    public ControleurBandit(Modele m, VueCommandes vC) {
        modele = m;
        //vueT = vT;
        vueC = vC;
        assert vueC != null;
        vueC.addMouseListener(this);
        //vueC.addKeyListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        Bandit banditChoisi;
        Action actionChoisie = null;
        banditChoisi = modele.getTrain().choisirBandit(vueC.getTextField().getText());
        if (banditChoisi != null) {
            if (vueC.getBanditBraquer().contains(mouseX, mouseY)) actionChoisie = new Braquer(modele, banditChoisi);
            else if (vueC.getBanditTirer().contains(mouseX, mouseY)) actionChoisie = new Tirer(modele, banditChoisi);
            else {
                for (Bouton b : vueC.getBanditDeplacer()) {
                    if (b.contains(mouseX, mouseY)) {
                        switch (b.getText()) {
                            case "→":
                                actionChoisie = new Deplacer(modele, banditChoisi, Direction.AVANT);
                                break;
                            case "←":
                                actionChoisie = new Deplacer(modele, banditChoisi, Direction.ARRIERE);
                                break;
                            case "↑":
                                actionChoisie = new Deplacer(modele, banditChoisi, Direction.HAUT);
                                break;
                            case "↓":
                                actionChoisie = new Deplacer(modele, banditChoisi, Direction.BAS);
                                break;
                        }
                        break;
                    }
                }
            }
            if(actionChoisie != null) actionChoisie.executer();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /*public void updateBanditChoisi(String newText) {
        banditChoisi = modele.getTrain().choisirBandit(newText);
    }*/
}
