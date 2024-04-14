package ens_projet.controleur;

import ens_projet.modele.*;
import ens_projet.vue.VueCommandes;
import ens_projet.vue.VueTrain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControleurBandit implements MouseListener, KeyListener {
    private final Modele modele;
    private final VueTrain vueTrain;
    private final VueCommandes vueCommandes;
    public ControleurBandit(Modele m, VueTrain vT, VueCommandes vC){
        modele = m;
        vueTrain = vT;
        vueCommandes = vC;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        // Faut changer, ca doit etre un bandit choisi il doit avoir des conditions pour le nb actions et ect..
        Bandit bandit = modele.getBandits().get(0);
        Action action;

        for(Bouton b: vueCommandes.getBanditDeplacer()){
            if(b.contains(mouseX, mouseY)){
                if(b.getText().equals("→")){
                    action = new Deplacer(modele, bandit, Direction.AVANT);
                    action.executer();
                }if(b.getText().equals("←")){
                    action = new Deplacer(modele, bandit, Direction.ARRIERE);
                    action.executer();
                }if(b.getText().equals("↓")){
                    action = new Deplacer(modele,bandit, Direction.BAS);
                    action.executer();
                }if(b.getText().equals("↑")){
                    action = new Deplacer(modele,bandit, Direction.HAUT);
                    action.executer();
                }if(b.getText().equals("Braquer")){
                    action = new Braquer(modele, bandit, Direction.random());
                    action.executer();
                }if(b.getText().equals("Tirer")){
                    action = new Tirer(modele, bandit, Direction.random());
                    action.executer();
                }
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
