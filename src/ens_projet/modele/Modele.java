package ens_projet.modele;

import ens_projet.vue.Observable;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Random;

public class Modele extends Observable {
    private final int NB_WAGONS = 5;
    private final int NB_BANDITS = 5;
    private final HashSet<Personne> personnes = new HashSet<>();
    private final Train t;

    // private final FenetreJeu f = new FenetreJeu();
    final static private String[] prenoms = {"Léo", "Gabriel", "Raphaël", "Arthur", "Louis", "Jules", "Adam", "Maël", "Lucas", "Hugo", "Noah", "Liam", "Gabin", "Sacha", "Paul", "Nathan", "Aaron", "Mohamed", "Ethan", "Eden", "Tom", "Léon", "Noé", "Tiago", "Théo", "Isaac", "Marius", "Victor", "Ayden", "Martin", "Naël", "Mathis", "Axel", "Robin", "Timéo", "Enzo", "Marceau", "Eliott", "Nino", "Valentin", "Nolan", "Malo", "Milo", "Antoine", "Samuel", "Augustin", "Amir", "Lyam", "Rayan", "Yanis", "Ibrahim", "Gaspard", "Sohan", "Clément", "Mathéo", "Baptiste", "Simon", "Maxence", "Imran", "Kaïs", "Côme", "Soan", "Evan", "Maxime", "Camille", "Alexandre", "Owen", "Ismaël", "Lenny", "Pablo", "Léandre", "Naïm", "Ilyan", "Thomas", "Joseph", "Oscar", "Elio", "Malone", "Noa", "Diego", "Noam", "Livio", "Charlie", "Charly", "Basile", "Milan", "Ilyes", "Ali", "Anas", "Logan", "Mathys", "Alessio", "William", "Timothée", "Auguste", "Adem", "Ayoub", "Wassim", "Marin", "Youssef"};
    public Modele(){
        t = new Train(this,NB_WAGONS, personnes);
    }
    public void initialiseBandits(){
        Random r = new Random();
        for(int i = 0; i < NB_BANDITS; i++){
            personnes.add(new Bandit(this,prenoms[r.nextInt(prenoms.length)],t));
        }
        personnes.add(new Marshall(this,"Marshall", t));
    }
    public void initialiseWagons(){
        Random r = new Random();
        for(int i = 0; i < NB_WAGONS; i++){
            Wagon w = t.getWagon(i);
            for(int j = 0; j < r.nextInt(3)+1; j++){
                w.ajouterButin(new Bourse(this,r.nextInt(500),w));
            }
        }
        for(int i =0; i < 3; i++){
            int indiceBijou = r.nextInt(NB_WAGONS - 1);
            Wagon w = t.getWagons().get(indiceBijou);
            w.ajouterButin(new Bijou(this,w));
        }
        new Magot(this,t);
    }
    public void initialiseTrain(){
        initialiseBandits();
        initialiseWagons();
    }

    public boolean partieFinie(){
        for(Personne p : personnes){
            if(p instanceof Bandit) {
                if(((Bandit) p).montantT() >= 5000) {
                    System.out.println("Partie finie, bandit gagnant : " + p.getNom());
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
