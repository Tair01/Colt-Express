package ens_projet.modele;

import java.util.Random;

public class Deplacer extends Action {
    public Deplacer(Modele m, Personne p, Direction d) {
        super(m, p, d);
    }

    @Override
    public String executer() {
        Personne personne = getPersonne();
        Wagon wagonActuel = personne.getPosition();
        Train train = personne.getPosition().getTrain();
        int nouvelIndice;
        String description = null;

        // CAS GÉNÉRAL : vérification des conditions triviales de déplacement
        if ((direction == Direction.HAUT && personne.isSurLeToit())
                || (direction == Direction.BAS && !personne.isSurLeToit())
                || (direction == Direction.AVANT && wagonActuel.isLocomotive())
                || (direction == Direction.ARRIERE && wagonActuel.equals(train.getWagon(0)))
        ) return description;

        // CAS MARSHALL
        if (personne instanceof Marshall) {
            Direction directionMarshall;
            if (Math.random() <= Marshall.NERVOSITE_MARSHALL) {
                Random r = new Random();
                directionMarshall = Direction.values()[r.nextInt(2)];
                nouvelIndice = (directionMarshall == Direction.ARRIERE) ? Math.max(0, wagonActuel.getNumero() - 1) : Math.min(wagonActuel.getNumero() + 1, train.getNombreW() - 1);
                wagonActuel = train.getWagon(nouvelIndice);
                personne.setPosition(wagonActuel);
                description = personne + " se déplace du wagon " + wagonActuel.getNumero() + " vers le wagon " + wagonActuel;
            }
        }

        // CAS BANDIT
        if (((Bandit)personne).getNbActions() > 0) {
            if (direction == Direction.AVANT || direction == Direction.ARRIERE) {
                nouvelIndice = (direction == Direction.ARRIERE) ? Math.max(0, wagonActuel.getNumero() - 1) : Math.min(wagonActuel.getNumero() + 1, train.getNombreW() - 1);
                wagonActuel = train.getWagon(nouvelIndice);
                personne.setSurLeToit();
                personne.setPosition(wagonActuel);
                ((Bandit)personne).utiliseAction();
                description = personne + " se déplace du wagon " + wagonActuel.getNumero() + " vers le wagon " + wagonActuel;
            } else if (direction == Direction.BAS || direction == Direction.HAUT) {
                personne.setSurLeToit();
                ((Bandit)personne).utiliseAction();
                description = (direction == Direction.BAS) ? (personne + " est descendu.") : (personne + " est monté.");
            }
        }
        modele.notifyObservers();
        // si l'auteur du déplacement et/ou la direction n'ont pas permis l'exécution du déplacement, description sera égal à null à ce stade (il faudra donc vérifier à chaque fois que le déplacement d'un personnage n'est pas null)
        return description;
    }
}
