public class Deplacer extends Action {
    public Deplacer(Personne p, Direction d) {
        super(p, d);
    }

    @Override
    String executer() {
        Bandit bandit = (Bandit) getPersonne();
        Wagon wagonActuel = bandit.getPosition();
        Train train = bandit.getPosition().train;
        Direction d = getDirection();

        // 4 conditions sur le deplacement
        if (d == Direction.HAUT && bandit.isSurLeToit()) {
            throw new RuntimeException("Le bandit est déjà sur le toit.");
        }
        if (d == Direction.BAS && !bandit.isSurLeToit()) {
            throw new RuntimeException("Le bandit est déjà à l'intérieur.");
        }
        if (d == Direction.AVANT && wagonActuel.isLocomotive()) {
            throw new RuntimeException("Le bandit est sur la locomotive.");
        }
        if (d == Direction.ARRIERE && wagonActuel == train.getWagon(0)) {
            throw new RuntimeException("Le bandit est sur le dernier wagon.");
        }
        if (bandit.isSurLeToit()) {
            if (d == Direction.ARRIERE) {
                //System.out.println("Direction ARRIERE");
                int nouveauIndice = wagonActuel.getNumero() - 1;
                if (nouveauIndice >= 0 && nouveauIndice < train.getNombreW()) {
                    //System.out.println("Déplacement ARRIERE possible");
                    Wagon nouveauWagon = train.getWagon(nouveauIndice);
                    bandit.setSurLeToit(true);
                }
            }
            if (d == Direction.AVANT) {
                //System.out.println("Direction AVANT");
                int nouveauIndice = wagonActuel.getNumero() + 1;
                if (nouveauIndice >= 0 && nouveauIndice <= train.getNombreW()) {
                    //System.out.println("Déplacement AVANT possible");
                    Wagon nouveauWagon = train.getWagon(nouveauIndice);
                    bandit.setSurLeToit(true);
                } else {
                    throw new RuntimeException("Déplacement impossible dans cette direction.Errer d'indice!");
                }
            }
            if (d == Direction.BAS) {
                //System.out.println("Direction BAS");
                bandit.setSurLeToit(false);
            }
        } else {
            if (d == Direction.ARRIERE) {
                //System.out.println("Direction ARRIERE");
                int nouveauIndice = wagonActuel.getNumero() - 1;
                if (nouveauIndice >= 0 && nouveauIndice < train.getNombreW()) {
                    //System.out.println("Déplacement ARRIERE possible");
                    Wagon nouveauWagon = train.getWagon(nouveauIndice);
                    bandit.setSurLeToit(false);
                }
            }
            if (d == Direction.AVANT) {
                //System.out.println("Direction AVANT");
                int nouveauIndice = wagonActuel.getNumero() + 1;
                if (nouveauIndice >= 0 && nouveauIndice < train.getNombreW()) {
                    //System.out.println("Déplacement AVANT possible");
                    Wagon nouveauWagon = train.getWagon(nouveauIndice);
                    bandit.setSurLeToit(false);
                } else {
                    throw new RuntimeException("Déplacement impossible dans cette direction.Errer d'indice!");
                }
            }
            if (d == Direction.HAUT) {
                //System.out.println("Direction HAUT");
                bandit.setSurLeToit(true);
            }
        } return null;
    }
}
