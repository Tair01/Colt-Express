package ens_projet.modele;

public abstract class Action {
    final Personne auteur;
    final Direction direction;
    public Action(Personne a, Direction d){
        auteur = a;
        direction = d;
    }
    public Personne getPersonne(){return auteur;}
    public Direction getDirection(){return direction;}
    public abstract String executer();

}

// TODO : ajouter des munitions dans les wagons -> 06/04 ☑
// TODO : modifier les classes pour MVC -> 07/04
// TODO : coder la classe FenetreJeu -> 14/04
// TODO : vérifier/améliorer les tests -> 15/04
// TODO : rajouter de la documentation et améliorer le code si possible (optimisation, suppression variables/méthodes inutilisées...etc.) -> 15/04
// TODO : remplir fichier README -> 15/04

// soutenance : 22/04