package ens_projet.modele;

import ens_projet.modele.Action;
import ens_projet.modele.Bandit;

import java.util.ArrayList;
import java.util.Random;

public class Braquer extends Action {
    public Braquer(Modele m, Personne p, Direction d) {
        super(m, p, d);
    }

    @Override
    public String executer() {
        String message = ""; // il faudra vérifier à l'issue de l'exécution de cette méthode qu'elle n'a pas renvoyé la chaîne vide (avant d'afficher ceci dans le journal des actions)
        if (auteur instanceof Bandit && auteur.getNbActions() > 0) {
            Random random = new Random();
            ArrayList<Butin> butinsW = auteur.getPosition().getButins();
            ArrayList<CaisseMunitions> caissesW = auteur.getPosition().getCaissesMunitions();
            if (!butinsW.isEmpty()) {
                Butin butinRecupere = butinsW.get(random.nextInt(butinsW.size()));
                ((Bandit) auteur).ajouteButin(butinRecupere);
                auteur.getPosition().retireButin(butinRecupere);
                auteur.utiliseAction();
                message += auteur.getNom() + " a récupéré le butin :" + butinRecupere.getNom() + "\n";
                System.out.println(message);
            }
            if (!caissesW.isEmpty()) {
                CaisseMunitions caisseRamassee = caissesW.get(random.nextInt(caissesW.size()));
                ((Bandit) auteur).ajouteMunitions(caisseRamassee);
                auteur.getPosition().retireButin(caisseRamassee);
                message += auteur.getNom() + " a ramassé une caisse de munitions." + "\n";
            }
        }
        return message;
    }
}
