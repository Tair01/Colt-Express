package ens_projet.modele;

import ens_projet.vue.Observable;

import java.util.Objects;

public abstract class Personne {
    private final Modele modele;
    final static int NB_BALLES = 6;
    private final String nom;
    protected Wagon position;
    Train train;
    int balles;
    private boolean surLeToit;

    public Personne(Modele m, String n, boolean s, Train t) {
        // vérification de l'absence du nom choisi dans le train en question
        if (!(checkNomDejaUtilise(t, n))) {
            modele = m;
            nom = n;
            surLeToit = s;
            this.train = t;
        } else throw new IllegalArgumentException("Une personne portant le même nom se trouve déjà dans le train.");
    }

    // Cette méthode servira à vérifier que l'on ajoute pas deux personnes ayant le même nom dans le même train
    // Cela poserait des problèmes d'unicité
    private static boolean checkNomDejaUtilise(Train t, String nom) {
        for (Personne p : t.getPersonnes()) {
            if (Objects.equals(p.toString(), nom)) return true;
        }
        return false;
    }

    public Wagon getPosition() {
        return position;
    }

    public void setPosition(Wagon w) {
        position = w;
    }

    public int getBalles() {
        return balles;
    }

    public void tire() {
        if (balles > 0) balles--;
    }

    public boolean isSurLeToit() {
        return surLeToit;
    }

    public void setSurLeToit() {
        surLeToit = !(surLeToit);
    }

    @Override
    public String toString() {
        return nom;
    }
}
