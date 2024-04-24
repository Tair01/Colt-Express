package ens_projet.controleur;

import ens_projet.modele.*;
import ens_projet.modele.Action;
import ens_projet.vue.VueCommandes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Controleur implements ActionListener {
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
        vueC.getBanditDeplacer(Direction.HAUT).addActionListener(this);
        vueC.getBanditDeplacer(Direction.BAS).addActionListener(this);
        vueC.getBanditDeplacer(Direction.AVANT).addActionListener(this);
        vueC.getBanditDeplacer(Direction.ARRIERE).addActionListener(this);
        vueC.getBanditAction().addActionListener(this);
        vueC.getBanditTirer().addActionListener(this);
        vueC.getBanditBraquer().addActionListener(this);

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

    /*@Override
    public void mouseClicked(ActionEvent  e) {
        if(mode) { // Si en mode planification
            Action newAction = determineAction(mouseX, mouseY);
            if(newAction != null) {
                int i = 0;
                while(i < Bandit.NB_ACTIONS && actions[indiceBanditEnCours][i] != null) i++;
                if(i < Bandit.NB_ACTIONS) actions[indiceBanditEnCours][i] = newAction;
                System.out.println("vcffffff");
            }
        } else { // Si en mode action !
            if(vueC.getBanditAction() && compteurI < modele.getBandits().size() && compteurJ < Bandit.NB_ACTIONS) {
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
    }*/

    private Action determineAction(ActionEvent e) {
        // Retourne l'action correspondante selon le bouton cliqué
        Action newAction = null;
        if(e.getSource()  == vueC.getBanditDeplacer(Direction.HAUT)) {
            newAction = new Deplacer(modele, banditEnCours, Direction.HAUT);
        }
        else if(e.getSource()  ==  vueC.getBanditDeplacer(Direction.BAS)) {
            newAction = new Deplacer(modele, banditEnCours, Direction.BAS);
        }
        else if(e.getSource()  ==  vueC.getBanditDeplacer(Direction.AVANT)) {
            newAction = new Deplacer(modele, banditEnCours, Direction.AVANT);
        }
        else if(e.getSource()  ==  vueC.getBanditDeplacer(Direction.ARRIERE)) {
            newAction = new Deplacer(modele, banditEnCours, Direction.ARRIERE);
        }
        else if(e.getSource()  ==  vueC.getBanditBraquer()) {
            newAction = new Braquer(modele, banditEnCours);
        }
        else if(e.getSource()  ==  vueC.getBanditTirer()) {
            newAction = new Tirer(modele, banditEnCours);
        }
        return newAction;
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("vcffffff");
        int i = 0;

        if(!mode) { // Si en mode planification
            //jhk
            // System.out.println("vcffffff");
            Action newAction = determineAction(e);
            if(newAction != null) {
                while(i < Bandit.NB_ACTIONS && indiceBanditEnCours < Modele.NB_JOUEURS && actions[indiceBanditEnCours][i] != null) i++;
                if(i < Bandit.NB_ACTIONS && indiceBanditEnCours < Modele.NB_JOUEURS) actions[indiceBanditEnCours][i] = newAction;
                else {
                    if(indiceBanditEnCours < Modele.NB_JOUEURS) indiceBanditEnCours++;
                    else mode = !mode;
                }

            }
        } else { // Si en mode action !

            if(e.getSource() == vueC.getBanditAction() && compteurI < modele.getBandits().size() && compteurJ < Bandit.NB_ACTIONS) {
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
}
