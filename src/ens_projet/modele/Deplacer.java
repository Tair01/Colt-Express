package ens_projet.modele;

public class Deplacer extends Action {
    public Deplacer(Personne p, Direction d) {
        super(p, d);
    }

    @Override
    public String executer() {
        Personne personne = getPersonne();
        Wagon wagonActuel = personne.getPosition();
        Train train = personne.getPosition().train;
        Direction d = getDirection();
        int nouveauIndice;

        StringBuilder description = new StringBuilder();
        description.append("ens_projet.modele.Direction: ").append(d).append("\n");
        description.append("Position actuelle du bandit: ").append(wagonActuel.getNumero()).append("\n\n");


        // Vérification des conditions de déplacement
        if (d == Direction.HAUT && personne.isSurLeToit()) {
            throw new RuntimeException("Le bandit est déjà sur le toit.");
        }if (d == Direction.BAS && !personne.isSurLeToit()) {
            throw new RuntimeException("Le bandit est déjà à l'intérieur.");
        }if (d == Direction.AVANT && wagonActuel.isLocomotive()) {
            throw new RuntimeException("Le bandit est sur la locomotive.");
        }if (d == Direction.ARRIERE && wagonActuel.equals(train.getWagon(0))) {
            throw new RuntimeException("Le bandit est sur le dernier wagon.");
        }

        System.out.println(personne.getNom() + " se déplace " + d);

        if(personne instanceof Marshall){
            System.out.println("C'est bien un ens_projet.modele.Marshall");
            if(Math.random() <= Marshall.NERVOSITE_MARSHALL){
                System.out.println("La position du ens_projet.modele.Marshall est:" + personne.getPosition());
                Direction[] directionsPossibleM = {Direction.AVANT, Direction.ARRIERE};
                Direction directionMarshall = directionsPossibleM[(int) (Math.random() * directionsPossibleM.length)];
                Wagon wagonMarshall = train.getWagon(train.getNombreW() - 1);
                int nouvIndiceMarshall = (directionMarshall == Direction.ARRIERE) ? wagonMarshall.getNumero() - 1 : wagonMarshall.getNumero()  + 1;
                if(nouvIndiceMarshall >= 0 && nouvIndiceMarshall < train.getNombreW()) {
                    System.out.println("Deplacement Possible");
                    wagonMarshall = train.getWagon(nouvIndiceMarshall);
                    personne.position = wagonMarshall;
                    System.out.println("Sa nouvelle position est:" + personne.getPosition());
                    System.out.println();
                }
            }
        }
        if(personne instanceof Bandit) {
            if (d == Direction.AVANT || d == Direction.ARRIERE) {
                description.append("Un déplacement\n");
                nouveauIndice = (d == Direction.ARRIERE) ? wagonActuel.getNumero() - 1 : wagonActuel.getNumero() + 1;
                if (nouveauIndice >= 0 && nouveauIndice < train.getNombreW()) {
                    description.append("Déplacement possible\n");
                    wagonActuel = train.getWagon(nouveauIndice);
                    personne.setSurLeToit(personne.isSurLeToit());
                    personne.position = wagonActuel;
                }
            }
            if (d == Direction.BAS || d == Direction.HAUT) {
                description.append("Déplacement vers le Haut ou vers le Bas\n");
                personne.setSurLeToit(d == Direction.HAUT);
            }
        }
        description.append("Nouvelle position du bandit: ").append(personne.getPosition().getNumero()).append("\n");
        return description.toString();
    }
}
