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

// TODO : 5. retirer tous les imports de modules, méthodes, attributs et variables inutilisés
// TODO : 4. intégrer le patron de conception MVC
// TODO : 3. vérifier les tests, les changer et faire en sorte à ce qu'ils passent
// TODO : 2. arranger/supprimer/remplacer les méthodes executer() dans ens_projet.modele.Deplacer, random() dans ens_projet.modele.Direction,
// TODO : 1. compléter les classes ens_projet.modele.Marshall, ens_projet.modele.Braquer, ens_projet.modele.Tirer et ens_projet.modele.Direction
// TODO : 0. revoir la logique du jeu et faire les modifications nécessaires (notamment : est-ce que le code permet aux bandits de lâcher un butin sans se faire tirer dessus ?)