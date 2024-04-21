package ens_projet.modele;

import ens_projet.controleur.Controleur;
import ens_projet.vue.Observable;
import ens_projet.vue.Vue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Modele extends Observable {
    public static final int NB_WAGONS = 5;
    public static final int NB_JOUEURS = 2;
    private Bandit banditEnAction;
    private final HashSet<Personne> personnes = new HashSet<>();
    private final Train t;
    private boolean enAction;
    private List<Action>[] actionsPlanifiees;  // Actions planifiées pour chaque bandit

    public Modele(boolean auto) {
        t = new Train(this, NB_WAGONS, personnes);
        actionsPlanifiees = new List[NB_JOUEURS];  // Suppose un nombre égal de bandits et de joueurs
        for (int i = 0; i < NB_JOUEURS; i++) {
            actionsPlanifiees[i] = new ArrayList<>();
        }
        initialise(auto);
    }

    public void toggleMode() {
        enAction = !enAction;  // Bascule entre les modes planification et action
        notifyObservers();
    }

    public void executeActions() {
        // Exécute les actions dans l'ordre pour chaque joueur
        for (List<Action> actionList : actionsPlanifiees) {
            for (Action action : actionList) {
                if (action != null) {
                    String result = action.executer();
                    System.out.println(result);
                }
            }
            actionList.clear();  // Nettoie la liste après exécution
        }
        toggleMode();  // Retourne en mode planification après exécution
    }

    // Méthode pour ajouter des actions dans la liste
    public void planAction(int joueurIndex, Action action) {
        if (!enAction && joueurIndex < actionsPlanifiees.length) {
            actionsPlanifiees[joueurIndex].add(action);
        }
    }

    private void initialisePersonnes(boolean auto) {
        Scanner scanner = new Scanner(System.in);
        String nomBandit;
        for (int i = 0; i < NB_JOUEURS; i++) {
            if(auto) {
                nomBandit = "Bandit" + i;
            }
            else{
                System.out.println("Entrez le nom du bandit n°" + (i + 1) + " : ");
                nomBandit = scanner.nextLine().trim();
                while (nomBandit.isEmpty()) {
                    System.out.println("Merci d'entrer un nom non vide et qui n'a pas déjà été utilisé.");
                    nomBandit = scanner.nextLine().trim();
                }
            }
            personnes.add(new Bandit(this, nomBandit, t));
        }
        personnes.add(new Marshall(this, "Marshall", t));
        notifyObservers();
    }

    private void initialiseWagons() {
        Random r = new Random();
        // dans chaque wagon...
        for (int i = 0; i < NB_WAGONS; i++) {
            Wagon w = t.getWagon(i);
            // on place aléatoirement entre 1 et 3...
            for (int j = 0; j < r.nextInt(3); j++) {
                // ... bourses contenant entre 100 et 500$...
                w.ajouterButin(new Bourse(this, 100 + r.nextInt(400), w));
                // ... et caisses de munitions contenant entre 1 et 3 balles
                w.ajouterButin(new CaisseMunitions(this, w, r.nextInt(3)));
            }
        }
        for (int i = 0; i < 3; i++) {
            int indiceBijou = r.nextInt(NB_WAGONS - 1);
            Wagon w = t.getWagons().get(indiceBijou);
            w.ajouterButin(new Bijou(this, w));
        }
        new Magot(this, t); // il sera placé automatiquement selon la logique de code
        notifyObservers();
    }

    private void initialise(boolean auto) {
        initialisePersonnes(auto);
        initialiseWagons();
    }

    // renvoie null si la partie n'est pas encore fini, le nom du gagnant sinon
    public String partieFinie() {
        for (Personne p : personnes) {
            if (p instanceof Bandit) {
                // la partie se termine si l'un des bandits présents a accumulé au moins 5000$ de butin
                if (((Bandit) p).montantT() >= 5000) {
                    System.out.println("Partie finie, joueur gagnant : " + p);
                    return p.toString();
                }
            }
        }
        return null;
    }

    public ArrayList<Bandit> getBandits() {
        ArrayList<Bandit> bandits = new ArrayList<>();
        for (Personne personne : personnes) {
            if (personne instanceof Bandit) {
                bandits.add((Bandit) personne);
            }
        }
        return bandits;
    }

    public Train getTrain() {
        return t;
    }

    public boolean isActionMode() {
        return enAction;
    }

    public Bandit getBanditEnAction() {
        return banditEnAction;
    }

    public void setBanditEnAction(Bandit banditEnAction) {
        this.banditEnAction = banditEnAction;
        notifyObservers();
    }

    public static void jeu() {
        Modele modele = new Modele(false);
        Vue vue = new Vue(modele);
        Controleur controleur = new Controleur(modele, vue.getVueCommandes());

        while(modele.partieFinie() == null) {
            controleur.setBanditEnCours(modele.getBandits().get(0));
            controleur.setMode(false);
            while(!modele.isActionMode()) {
            }
            modele.executeActions();
        }
    }
}
