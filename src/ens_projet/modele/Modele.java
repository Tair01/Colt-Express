package ens_projet.modele;

import ens_projet.vue.Observable;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Random;
import java.util.Scanner;


public class Modele extends Observable {
    private final int NB_WAGONS = 5;
    private final int NB_BANDITS = 2;
    private final HashSet<Personne> personnes = new HashSet<>();
    private final Train t;

    // private final FenetreJeu f = new FenetreJeu();
    public Modele(){
        t = new Train(this,NB_WAGONS, personnes);
    }
    public void initialiseBandits(){
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < NB_BANDITS; i++){
            System.out.println("Entrez le nom du bandit n°" + i);
            personnes.add(new Bandit(this,scanner.nextLine(), t));
        }
        personnes.add(new Marshall(this,"Marshall", t));
    }
    public void initialiseWagons(){
        Random r = new Random();
        // dans chaque wagon...
        for(int i = 0; i < NB_WAGONS; i++){
            Wagon w = t.getWagon(i);
            // on place aléatoirement entre 1 et 3...
            for(int j = 0; j < r.nextInt(3); j++){
                // ... bourses contenant entre 100 et 500$...
                w.ajouterButin(new Bourse(this,r.nextInt(400) + 100,w));
                // ... et caisses de munitions contenant entre 1 et 3 balles
                w.ajouterButin(new CaisseMunitions(this, w, r.nextInt(3)));
            }
        }
        for(int i =0; i < 3; i++){
            int indiceBijou = r.nextInt(NB_WAGONS - 1);
            Wagon w = t.getWagons().get(indiceBijou);
            w.ajouterButin(new Bijou(this,w));
        }
        new Magot(this,t); // il sera placé automatiquement selon la logique de code
    }
    public void initialiseTrain(){
        initialiseBandits();
        initialiseWagons();
    }

    public boolean partieFinie(){
        for(Personne p : personnes){
            if(p instanceof Bandit) {
                // la partie se termine si l'un des bandits présents a accumulé au moins 5000$ de butin
                if(((Bandit) p).montantT() >= 5000) {
                    System.out.println("Partie finie, joueur gagnant : " + p.getNom());
                    // f.finalScreen();
                    return true;
                }
            }
        }
        return false;
    }
    public ArrayList<Bandit> getBandits(){
        ArrayList<Bandit> bandits = new ArrayList<>();
        for(Personne personne: personnes){
            if(personne instanceof Bandit){
                bandits.add((Bandit) personne);
            }
        }
        return bandits;
    }

    public static void main(String[] args){
        // f = new FenetreJeu();
        Modele j = new Modele();
        j.initialiseTrain();
        ArrayList<Bandit> bandits = j.getBandits();
        while (!j.partieFinie()){
            for(Bandit b: bandits){

            }
        }

    }

}
