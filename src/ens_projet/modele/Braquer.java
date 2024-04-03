package ens_projet.modele;

import ens_projet.modele.Action;
import ens_projet.modele.Bandit;

import java.util.ArrayList;
import java.util.Random;

public class Braquer extends Action {
    public Braquer(Personne p, Direction d) {
        super(p,d);
    }
    @Override
    public String executer() {
        String message = null;
        if(auteur instanceof Bandit){
            Random random = new Random();
            ArrayList<Butin> butinsW = auteur.getPosition().butinsPresents();
            if(!butinsW.isEmpty()){
                Butin butinRecupere = butinsW.get(random.nextInt(butinsW.size()));
                ((Bandit) auteur).ajouteButin(butinRecupere);
                auteur.getPosition().retireButin(butinRecupere);
                message = auteur.getNom() + " a récupéré le butin :" + butinRecupere.getNom();
                System.out.println(message);
                return message;
            }
        }
        else{
            message = auteur.getNom() + " n'a trouvé aucun butin à récupérer sur sa position.";
            return message;
        }
        return message;
    }
}
