package ens_projet.modele;

import java.util.ArrayList;
import java.util.Random;

public class Tirer extends Action {
    public Tirer(Modele m,Personne p) {
        super(m,p,null);
    }
    @Override
    public String executer() {
            if (auteur.getBalles() > 0) {
                if (auteur instanceof Bandit && ((Bandit)auteur).getNbActions() > 0) {
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
                    ((Bandit)auteur).utiliseAction();
                    return auteur + " a tiré sur " + chosenBandit + ".";
                } else {
                    ArrayList<Bandit> banditsWagon = auteur.getPosition().banditsPresents();
                    int i = 0;
                    if (direction == Direction.BAS) return null;
                    ArrayList<Bandit> banditsTouches = new ArrayList<>();
                    while (auteur.getBalles() > 0 && i < banditsWagon.size()) {
                        auteur.tire();
                        banditsWagon.get(i).lacheButin();
                        banditsTouches.add(banditsWagon.get(i));
                        i++;
                    }
                    StringBuilder s = new StringBuilder(auteur + " a tiré sur : ");
                    for (Bandit b : banditsTouches) {
                        s.append(b).append(" ");
                        b.setSurLeToit();
                    }
                    return s.toString();
                }
            }
            return auteur + " a essayé de tirer mais est à court de munitions.";
        }
}

