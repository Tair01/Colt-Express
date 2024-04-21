package ens_projet.controleur;

import ens_projet.modele.*;
import ens_projet.modele.Action;
import ens_projet.vue.VueCommandes;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Controleur implements MouseListener {
    private final Modele modele;
    private  VueCommandes vueC;

    // 0 : mode planification, 1 : mode action !
    boolean mode;

    public Action[][] actions;

    Bandit banditEnCours;

    public void setBanditEnCours(Bandit banditEnCours) {
        this.banditEnCours = banditEnCours;
    }

    public void setIndiceBanditEnCours(int indiceBanditEnCours) {
        this.indiceBanditEnCours = indiceBanditEnCours;
    }

    public int compteurI, compteurJ;

    int indiceBanditEnCours;


    public Controleur(Modele m, VueCommandes vueC) {
        this.vueC = vueC;
        assert vueC != null;
        vueC.addMouseListener(this);
        modele = m;
        actions = new Action[modele.getBandits().size()][Bandit.NB_ACTIONS];
    }


    public String getModeStr(){
        return mode ? "Planification" : "Action !";
    }

    public void setMode(boolean mode) {
        this.mode = mode;
        // Afficher l'état actuel dans la vue commandes
        //vueC.setModeLabel(getModeStr());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        if(mode) { // Si en mode planification
            Action newAction = determineAction(mouseX, mouseY);
            if(newAction != null) {
                int i = 0;
                while(i < Bandit.NB_ACTIONS && actions[indiceBanditEnCours][i] != null) i++;
                if(i < Bandit.NB_ACTIONS) actions[indiceBanditEnCours][i] = newAction;
            }
        } else { // Si en mode action !
            if(vueC.getBanditAction().contains(mouseX, mouseY) && compteurI < modele.getBandits().size() && compteurJ < Bandit.NB_ACTIONS) {
                Action actionToExecute = actions[compteurI][compteurJ];
                if (actionToExecute != null) {
                    String msg = actionToExecute.executer();
                    System.out.println((msg != null && !msg.isEmpty()) ? msg : "Action impossible, non-effectuée");
                }
                compteurJ++;
                if(compteurJ >= Bandit.NB_ACTIONS) {
                    compteurJ = 0;
                    compteurI++;
                    if(compteurI >= modele.getBandits().size()) {
                        System.out.println("Toutes les actions ont été effectuées !");
                        compteurI = 0;  // Réinitialiser pour le prochain tour
                    }
                }
            }
        }
    }

    private Action determineAction(int mouseX, int mouseY) {
        // Retourne l'action correspondante selon le bouton cliqué
        Action newAction = null;
        if(vueC.getBanditDeplacer(Direction.HAUT).contains(mouseX, mouseY)) {
            newAction = new Deplacer(modele, banditEnCours, Direction.HAUT);
        }
        else if(vueC.getBanditDeplacer(Direction.BAS).contains(mouseX, mouseY)) {
            newAction = new Deplacer(modele, banditEnCours, Direction.BAS);
        }
        else if(vueC.getBanditDeplacer(Direction.AVANT).contains(mouseX, mouseY)) {
            newAction = new Deplacer(modele, banditEnCours, Direction.AVANT);
        }
        else if(vueC.getBanditDeplacer(Direction.ARRIERE).contains(mouseX, mouseY)) {
            newAction = new Deplacer(modele, banditEnCours, Direction.ARRIERE);
        }
        else if(vueC.getBanditBraquer().contains(mouseX, mouseY)) {
            newAction = new Braquer(modele, banditEnCours);
        }
        else if(vueC.getBanditTirer().contains(mouseX, mouseY)) {
            newAction = new Tirer(modele, banditEnCours);
        }
        return newAction;
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

}
