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
    private Bandit banditChoisi;
    public ControleurBandit(Modele m, VueTrain vT, VueCommandes vC){
        modele = m;
        vueTrain = vT;
        vueCommandes = vC;
    }
    public void setBanditChoisi(Bandit b){
        banditChoisi = b;
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
        String nom = vueCommandes.getTextField().getText();
        banditChoisi = modele.getTrain().choisirPersonne(nom);

        if(banditChoisi == null){
            System.out.println("Aucun Bandit choisi");
            return;
        }
        Action action = null;

        for(Bouton b: vueCommandes.getBanditDeplacer()){
            if(b.contains(mouseX, mouseY)){
                if(b.getText().equals("→")){
                    action = new Deplacer(modele, banditChoisi, Direction.AVANT);
                    action.executer();
                }if(b.getText().equals("←")){
                    action = new Deplacer(modele, banditChoisi, Direction.ARRIERE);
                    action.executer();
                }if(b.getText().equals("↓")){
                    action = new Deplacer(modele,banditChoisi, Direction.BAS);
                    action.executer();
                }if(b.getText().equals("↑")){
                    action = new Deplacer(modele,banditChoisi, Direction.HAUT);
                    action.executer();
                }
            }
        }
        String clickedButtonText = ((Bouton) e.getSource()).getText();
        if (clickedButtonText.equals("Braquer")) {
            action = new Braquer(modele, banditChoisi, Direction.random());
        } else if (clickedButtonText.equals("Tirer")) {
            action = new Tirer(modele, banditChoisi, Direction.random());
        }
        if (action != null) {
            action.executer();
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
    public void updateBanditChoisi(String newText) {
        banditChoisi = modele.getTrain().choisirPersonne(newText);
    }
}
