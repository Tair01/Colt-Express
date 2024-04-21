package ens_projet.modele;

public abstract class Action {
    private final Modele modele;
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

    //public Direction getDirection(){return direction;}
    public abstract String executer();

    @Override
    public String toString(){
        return executer();
    }

}

// TODO : ajouter des munitions dans les wagons ☑
// TODO : modifier les classes pour MVC ☑
// TODO : coder les vues du jeu
// TODO : coder le contrôleur
// TODO : corriger tous les endroits où la vérification de la présence du butin sur le toit ou à l'intérieur est nécessaire
// TODO : coder la logique du jeu
// TODO : vérifier/améliorer les tests
// TODO : mettre à jour le diagramme
// TODO : rajouter de la documentation et améliorer le code si possible (optimisation, suppression variables/méthodes inutilisées...etc.)
// TODO : remplir fichier README
// TODO : rajouter des éléments optionnels

// soutenance : 24/04