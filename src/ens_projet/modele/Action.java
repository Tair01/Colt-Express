package ens_projet.modele;

public abstract class Action {
    final Modele modele;
    final Personne auteur;
    final Direction direction;

    public Action(Modele m, Personne a, Direction d) {
        modele = m;
        auteur = a;
        direction = d;
    }

    public Personne getPersonne() {
        return auteur;
    }

    public abstract String executer();

    @Override
    public String toString(){
        return executer();
    }

}