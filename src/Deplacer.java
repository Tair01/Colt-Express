public class Deplacer extends Action{
    @Override
    void executer(Direction d) {
        Bandit bandit = (Bandit) getPersonne();
        Wagon wagonActuel = bandit.getPosition();
        if(d == Direction.HAUT && bandit.isSurLeToit()){
            throw new RuntimeException("Le bandit est déjà sur le toit.");
        }if(d == Direction.BAS && !bandit.isSurLeToit()){
            throw new RuntimeException("Le bandit est déjà à l'intérieur.");
        }if(d == Direction.AVANT && bandit.wagonActuel.isLocomotive()){
            throw new RuntimeException("Le bandit est sur le locomotive");
        }if(d == Direction.ARRIERE && bandit.getPosition().getNumero() == 0){
            throw new RuntimeException("Le bandit est sur le dernière wagon");
        }


    }
}
