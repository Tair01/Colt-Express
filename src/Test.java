public class Test {
    public static void main(String[] args){
        Train train = new Train(4);
        Bandit bandit = new Bandit("Alex", true);
        Wagon locomotive = train.getWagonInd(train.getNombreW());
        bandit.setPosition(locomotive);
        train.ajoutePersonne(bandit);

        System.out.println("Le nombre de wagons est : " + train.getNombreW());
        System.out.println("Le numéro de position de bandit dans le train est : " + bandit.getPosition().getNumero() + " eme wagon");
        System.out.println();

        System.out.println("Premier déplacement vers l'arrière :");
        Deplacer deplacement1 = new Deplacer(bandit, Direction.ARRIERE);
        bandit.effectuerAction(deplacement1, deplacement1.getDirection());
        System.out.println("Nouvelle position après le premier déplacement : " + bandit.getPosition().getNumero() + " eme wagon");
        System.out.println("Est-ce que le bandit est sur le toit ? " + bandit.isSurLeToit());
        System.out.println();

        System.out.println("Deuxième déplacement vers l'arrière :");
        Deplacer deplacement2 = new Deplacer(bandit, Direction.ARRIERE);
        bandit.effectuerAction(deplacement2, deplacement2.getDirection());
        System.out.println("Nouvelle position après le deuxième déplacement : " + bandit.getPosition().getNumero() + " eme wagon");
        System.out.println("Est-ce que le bandit est sur le toit ? " + bandit.isSurLeToit());
        System.out.println();

        System.out.println("Troisième déplacement vers l'avant :");
        Deplacer deplacement3 = new Deplacer(bandit, Direction.AVANT);
        bandit.effectuerAction(deplacement3, deplacement3.getDirection());
        System.out.println("Nouvelle position après le troisième déplacement : " + bandit.getPosition().getNumero() + " eme wagon");
        System.out.println("Est-ce que le bandit est sur le toit ? " + bandit.isSurLeToit());
        System.out.println();

        System.out.println("Quatrième déplacement vers l'avant :");
        Deplacer deplacement4 = new Deplacer(bandit, Direction.AVANT);
        bandit.effectuerAction(deplacement4, deplacement4.getDirection());
        System.out.println("Nouvelle position après le quatrième déplacement : " + bandit.getPosition().getNumero() + " eme wagon");
        System.out.println("Est-ce que le bandit est sur le toit ? " + bandit.isSurLeToit());
        System.out.println();

        System.out.println("Cinquième déplacement vers l'avant :");
        Deplacer deplacement5 = new Deplacer(bandit, Direction.BAS);
        bandit.effectuerAction(deplacement5, deplacement5.getDirection());
        System.out.println("Est-ce que le bandit est sur le toit ? " + bandit.isSurLeToit());
        System.out.println();

        System.out.println("Sixième déplacement vers l'avant :");
        Deplacer deplacement6 = new Deplacer(bandit, Direction.HAUT);
        bandit.effectuerAction(deplacement6, deplacement6.getDirection());
        System.out.println("Est-ce que le bandit est sur le toit ? " + bandit.isSurLeToit());
        System.out.println();

    }
}
