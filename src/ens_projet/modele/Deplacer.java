package ens_projet.modele;
import java.util.Random;

public class Deplacer extends Action {
    public Deplacer(Personne p, Direction d) {
        super(p, d);
    }

    //TODO : optimiser variable nouvelIndice

    @Override
    public String executer() {
        Personne personne = getPersonne();
        Wagon wagonActuel = personne.getPosition();
        Train train = personne.getPosition().train;
        int nouvelIndice;

        String description = null;

        // Vérification des conditions de déplacement
        if ( (direction == Direction.HAUT && personne.isSurLeToit())
                || (direction == Direction.BAS && !personne.isSurLeToit())
                || (direction == Direction.AVANT && wagonActuel.isLocomotive())
                || (direction == Direction.ARRIERE && wagonActuel.equals(train.getWagon(0)))
        ) return description;

        if(personne instanceof Marshall) {
            Direction directionMarshall;
            if (Math.random() <= Marshall.NERVOSITE_MARSHALL) {
                Random r = new Random();
                directionMarshall = Direction.values()[r.nextInt(2)];
                nouvelIndice = (directionMarshall == Direction.ARRIERE) ? Math.max(0, wagonActuel.getNumero() - 1) : Math.min(wagonActuel.getNumero() + 1, train.getNombreW() - 1);
                wagonActuel = train.getWagon(nouvelIndice);
                personne.setPosition(wagonActuel);
                description = personne.getNom() + " se déplace du wagon " + wagonActuel.getNumero() + " vers le wagon " + wagonActuel;
            }
            return description;
        }

        if(personne instanceof Bandit) {
            if (direction == Direction.AVANT || direction == Direction.ARRIERE) {
                // le code de la variable nouvelIndice est redondant, mais ça reste plus simple que de créer une lambda-expression qui prend en paramètre une direction (il faudrait créer une interface)
                nouvelIndice = (direction == Direction.ARRIERE) ? Math.max(0, wagonActuel.getNumero() - 1) : Math.min(wagonActuel.getNumero() + 1, train.getNombreW() - 1);
                wagonActuel = train.getWagon(nouvelIndice);
                personne.setSurLeToit();
                personne.setPosition(wagonActuel);
                description = personne.getNom() + " se déplace du wagon " + wagonActuel.getNumero() + " vers le wagon " + wagonActuel;

            }
           else if (direction == Direction.BAS || direction == Direction.HAUT) {
                personne.setSurLeToit();
                description = (direction == Direction.BAS) ? (personne.getNom() + " est descendu.") : (personne.getNom() + " est monté.");
            }
            return description;
        }
        return description;
    }
}
