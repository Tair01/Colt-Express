package ens_projet.modele;

import java.util.HashSet;

import java.util.Random;

public class Jeu {
    private final int NB_WAGONS = 5;
    private final int NB_ACTIONS = 4;
    private final int NB_BANDITS = 5;
    private final HashSet<Personne> personnes = new HashSet<>();
    private final Train t = new Train(NB_WAGONS, personnes);

    // private final FenetreJeu f = new FenetreJeu();

    final static private String[] prenoms = {"Léo", "Gabriel", "Raphaël", "Arthur", "Louis", "Jules", "Adam", "Maël", "Lucas", "Hugo", "Noah", "Liam", "Gabin", "Sacha", "Paul", "Nathan", "Aaron", "Mohamed", "Ethan", "Eden", "Tom", "Léon", "Noé", "Tiago", "Théo", "Isaac", "Marius", "Victor", "Ayden", "Martin", "Naël", "Mathis", "Axel", "Robin", "Timéo", "Enzo", "Marceau", "Eliott", "Nino", "Valentin", "Nolan", "Malo", "Milo", "Antoine", "Samuel", "Augustin", "Amir", "Lyam", "Rayan", "Yanis", "Ibrahim", "Gaspard", "Sohan", "Clément", "Mathéo", "Baptiste", "Simon", "Maxence", "Imran", "Kaïs", "Côme", "Soan", "Evan", "Maxime", "Camille", "Alexandre", "Owen", "Ismaël", "Lenny", "Pablo", "Léandre", "Naïm", "Ilyan", "Thomas", "Joseph", "Oscar", "Elio", "Malone", "Noa", "Diego", "Noam", "Livio", "Charlie", "Charly", "Basile", "Milan", "Ilyes", "Ali", "Anas", "Logan", "Mathys", "Alessio", "William", "Timothée", "Auguste", "Adem", "Ayoub", "Wassim", "Marin", "Youssef"};

    public void initialiseTrain(){
        Random r = new Random();
        for(int i = 0 ; i < NB_BANDITS ; i++){
            personnes.add(new Bandit(prenoms[r.nextInt(prenoms.length)], t));
        }
        personnes.add(new Marshall("Marshall", t));

        for(int i = 0; i < NB_WAGONS ; i++){
            t.getWagon(i).ajouterButin(new Bourse(r.nextInt(500), t.getWagon(i)));
        }
        for(int i = 0; i < 3; i++){
            int indicebijou = r.nextInt(NB_WAGONS - 1);
            Wagon w = (t.getWagons().get(indicebijou));
            w.ajouterButin(new Bijou(w));
        }
        new Magot(t);
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


    public static void main(String[] args){
        // f = new FenetreJeu();
        Jeu j = new Jeu();
        j.initialiseTrain();
        while (!j.partieFinie()){

        }

    }

}
