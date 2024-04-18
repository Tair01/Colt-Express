package ens_projet.modele;

import java.util.ArrayList;
import java.util.Random;

public class Tirer extends Action {
    public Tirer(Modele m,Personne p, Direction d) {
        super(m,p,d);
    }
    @Override
    public String executer() {
        Personne personne = getPersonne();
        Train train = personne.getPosition().train;
        Wagon wagonActuelM = train.getMarshall().getPosition();
        Marshall marshall = train.getMarshall();
        int nouvelIndiceM;
        // CAS MARSHALL
        if (personne instanceof Bandit && Math.random() <= Marshall.NERVOSITE_MARSHALL) {
            System.out.println("Marshall se déplace avant l'action du Bandit");
            Random r = new Random();
            Direction directionMarshall = Direction.values()[r.nextInt(2)];
            nouvelIndiceM = (directionMarshall == Direction.ARRIERE) ? Math.max(0, wagonActuelM.getNumero() - 1) : Math.min(wagonActuelM.getNumero() + 1, train.getNombreW() - 1);
            wagonActuelM = train.getWagon(nouvelIndiceM);
            marshall.setPosition(wagonActuelM);
        }
        if (auteur.getNbActions() > 0) {
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
                    return auteur.toString() + " a tiré sur " + chosenBandit.toString() + ".";
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
                    String s = auteur.toString() + " a tiré sur : ";
                    for (Bandit b : banditsTouches) {
                        s += (b.toString() + " ");
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
