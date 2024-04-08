package ens_projet.modele;

import ens_projet.modele.Action;
import ens_projet.modele.Bandit;

import java.util.ArrayList;
import java.util.Random;

public class Braquer extends Action {
    public Braquer(Modele m,Personne p, Direction d) {
        super(m,p,d);
    }
    @Override
    public String executer() {
        String message = null;
        if (auteur.actions > 0) {
            if (auteur instanceof Bandit) {
                Random random = new Random();
                ArrayList<Butin> butinsW = auteur.getPosition().butinsPresents();
                ArrayList<Balle> ballesW = auteur.getPosition().ballesPresents();
                if (!butinsW.isEmpty()) {
                    Butin butinRecupere = butinsW.get(random.nextInt(butinsW.size()));
                    ((Bandit) auteur).ajouteButin(butinRecupere);
                    auteur.getPosition().retireButin(butinRecupere);
                    auteur.utiliseAction();
                    message = auteur.getNom() + " a récupéré le butin :" + butinRecupere.getNom();
                    System.out.println(message);
                }
                if (!ballesW.isEmpty()) {
                    Balle balleRamassee = ballesW.get(random.nextInt(ballesW.size()));
                    ((Bandit) auteur).ajouteBalle();
                    auteur.getPosition().retireBalle(balleRamassee);
                    message = auteur.getNom() + " a ramassé une balle.";
                }
            } else {
                message = auteur.getNom() + " n'a trouvé aucun butin à récupérer sur sa position.";
            }
        } else {
            message = "Le nombre d'actions est égal à 0";
        }
        return message;
    }

}
