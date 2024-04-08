package ens_projet.modele;

import ens_projet.modele.Action;
import ens_projet.modele.Bandit;
import ens_projet.modele.Direction;
import ens_projet.modele.Personne;

import java.util.ArrayList;
import java.util.Random;

public class Tirer extends Action {
    public Tirer(Modele m,Personne p, Direction d) {
        super(m,p,d);
    }
    @Override
    public String executer() {
        if (auteur.actions > 0) {
            if (auteur.getBalles() > 0) {
                if (auteur instanceof Bandit) {
                    Random random = new Random();
                    ArrayList<Bandit> banditsWagon = auteur.getPosition().banditsPresents();
                    Bandit chosenBandit = null; // Initialisation pour éviter une référence non assignée
                    int index;
                    boolean[] indexExplores = new boolean[banditsWagon.size()];
                    int nbIndicesExplores = 0; // Compteur pour le nombre d'indices explorés

                    if (!banditsWagon.isEmpty() && (direction == Direction.AVANT || direction == Direction.ARRIERE)) {
                        do {
                            index = random.nextInt(banditsWagon.size());
                            // Vérifie si l'indice n'a pas déjà été exploré
                            if (!indexExplores[index]) {
                                indexExplores[index] = true; // Marque l'indice comme exploré
                                nbIndicesExplores++; // Incrémente le compteur d'indices explorés
                                chosenBandit = banditsWagon.get(index);
                            }
                        } while (!(chosenBandit.isSurLeToit() == auteur.isSurLeToit() && nbIndicesExplores == banditsWagon.size()));
                    } else {
                        do {
                            index = random.nextInt(banditsWagon.size());
                            // Vérifie si l'indice n'a pas déjà été exploré
                            if (!indexExplores[index]) {
                                indexExplores[index] = true; // Marque l'indice comme exploré
                                nbIndicesExplores++; // Incrémente le compteur d'indices explorés
                                chosenBandit = banditsWagon.get(index);
                            }
                        } while (chosenBandit.isSurLeToit() == auteur.isSurLeToit() && nbIndicesExplores == banditsWagon.size());
                    }
                    chosenBandit.lacheButin();
                    auteur.tire();
                    auteur.utiliseAction();
                    return auteur.getNom() + " a tiré sur " + chosenBandit.getNom() + ".";
                } else {
                    ArrayList<Bandit> banditsWagon = auteur.getPosition().banditsPresents();
                    int i = 0;
                    if (direction == Direction.BAS) return null;
                    ArrayList<Bandit> banditsTouches = new ArrayList<>();
                    while (auteur.getBalles() > 0 && i < banditsWagon.size()) {
                        auteur.tire();
                        auteur.utiliseAction();
                        banditsWagon.get(i).lacheButin();
                        banditsTouches.add(banditsWagon.get(i));
                        i++;
                    }
                    String s = auteur.getNom() + " a tiré sur : ";
                    for (Bandit b : banditsTouches) {
                        s += (b.getNom() + " ");
                        b.setSurLeToit();
                    }
                    return s;
                }
            }
            return "Le nombre de balles est égal à 0";
        }
        return "Le nombre d'actions est égal à 0"; // Plus d'actions disponibles
    }
}
