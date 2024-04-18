package ens_projet.modele;
import java.util.Random;

public class Deplacer extends Action {
    public Deplacer(Modele m,Personne p, Direction d) {
        super(m,p, d);
    }

    @Override
    public String executer() {
        Personne personne = getPersonne();
        Wagon wagonActuel = personne.getPosition();
        Train train = personne.getPosition().train;
        Wagon wagonActuelM = train.getMarshall().getPosition();
        Marshall marshall = train.getMarshall();
        int nouvelIndice, nouvelIndiceM;
        String description = null;

        // CAS GÉNÉRAL : vérification des conditions triviales de déplacement
        if ( (direction == Direction.HAUT && personne.isSurLeToit())
                || (direction == Direction.BAS && !personne.isSurLeToit())
                || (direction == Direction.AVANT && wagonActuel.isLocomotive())
                || (direction == Direction.ARRIERE && wagonActuel.equals(train.getWagon(0)))
        ) return description;

        // CAS MARSHALL
        if (personne instanceof Bandit && Math.random() <= Marshall.NERVOSITE_MARSHALL) {
            System.out.println("Marshall se déplace avant l'action du Bandit");
            Random r = new Random();
            Direction directionMarshall = Direction.values()[r.nextInt(2)];
            nouvelIndiceM = (directionMarshall == Direction.ARRIERE) ? Math.max(0, wagonActuelM.getNumero() - 1) : Math.min(wagonActuelM.getNumero() + 1, train.getNombreW() - 1);
            wagonActuelM = train.getWagon(nouvelIndiceM);
            marshall.setPosition(wagonActuelM);
            description = marshall.toString() + " se déplace du wagon " + marshall.getPosition().getNumero() + " vers le wagon " + wagonActuelM.getNumero();
        }

        // CAS BANDIT
        if (personne.getNbActions() > 0){
            if (direction == Direction.AVANT || direction == Direction.ARRIERE) {
                nouvelIndice = (direction == Direction.ARRIERE) ? Math.max(0, wagonActuel.getNumero() - 1) : Math.min(wagonActuel.getNumero() + 1, train.getNombreW() - 1);
                wagonActuel = train.getWagon(nouvelIndice);
                personne.setSurLeToit();
                personne.setPosition(wagonActuel);
                personne.utiliseAction();
                description = personne.toString() + " se déplace du wagon " + wagonActuel.getNumero() + " vers le wagon " + wagonActuel;
            } else if (direction == Direction.BAS || direction == Direction.HAUT) {
                personne.setSurLeToit();
                personne.utiliseAction();
                description = (direction == Direction.BAS) ? (personne.toString() + " est descendu.") : (personne.toString() + " est monté.");
            }
        }
        // si l'auteur du déplacement et/ou la direction n'ont pas permis l'exécution du déplacement, description sera égal à null à ce stade (il faudra donc vérifier à chaque fois que le déplacement d'un personnage n'est pas null)
        return description;
    }
}
