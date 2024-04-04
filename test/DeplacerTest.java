import ens_projet.modele.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class DeplacerTest {
    private Train train;
    private Bandit bandit1, bandit2, bandit3;
    private Marshall marshall;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    private HashSet<Personne> personnes;
    @Before
    public void setUp(){
        train = new Train(4, personnes);
        bandit1 = new Bandit("Alex", train);
        bandit2 = new Bandit("Souleimane", train);
        bandit3 = new Bandit("Tair", train);
        marshall = new Marshall("Pierre", train);

        personnes = new HashSet<>(Arrays.asList(bandit1, bandit2, bandit3, marshall));
        train.getPersonnes().addAll(personnes);

        wagon1 = train.getWagon(0);
        wagon2 = train.getWagon(1);
        wagon3 = train.getWagon(2);
        wagon4 = train.getWagon(3);

        bandit1.setPosition(wagon1);
        bandit2.setPosition(wagon2);
        bandit3.setPosition(wagon3);
        marshall.setPosition(wagon4);
    }
    @Test
    public void executer() {
        assertEquals(0, bandit1.getPosition().getNumero());
        assertEquals(1, bandit2.getPosition().getNumero());
        assertEquals(2, bandit3.getPosition().getNumero());

        // Deplacement ver l'Avant
        Action action = new Deplacer(bandit1, Direction.AVANT);
        action.executer();
        assertEquals(wagon2, bandit1.getPosition());

        //Deplacement vers l'Arriere
        Action action1 = new Deplacer(bandit3, Direction.ARRIERE);
        action1.executer();
        assertEquals(wagon2, bandit3.getPosition());

        //Deplacement vers le Bas
        Action action2 = new Deplacer(bandit2, Direction.BAS);
        action2.executer();
        assertFalse(bandit2.isSurLeToit());

        //Deplacement vers le Haut
        Action action3 = new Deplacer(bandit2, Direction.HAUT);
        action3.executer();
        assertTrue(bandit2.isSurLeToit());
    }
    @Test
    public void testMarshallMovesBeforeBanditActions() {
        // Initialisation du jeu avec un ens_projet.modele.Marshall et des bandits
        Action action = new Deplacer(marshall, Direction.AVANT);
        // Vérifiez les positions initiales des bandits
        assertEquals(0, bandit1.getPosition().getNumero());
        assertEquals(1, bandit2.getPosition().getNumero());
        assertEquals(2, bandit3.getPosition().getNumero());

        // Effectuez plusieurs actions de bandit et vérifiez le déplacement du ens_projet.modele.Marshall
        for (int i = 0; i < 5; i++) {
            // Déplacement aléatoire du ens_projet.modele.Marshall avant chaque action de bandit
            action.executer();

            // ens_projet.modele.Action de bandit
            Deplacer deplacer1 = new Deplacer(bandit1, Direction.AVANT);
            deplacer1.executer();

            // Vérifiez la nouvelle position de bandit1
            assertEquals(wagon2, bandit1.getPosition());

            // ens_projet.modele.Action de bandit
            Deplacer deplacer2 = new Deplacer(bandit3, Direction.ARRIERE);
            deplacer2.executer();

            // Vérifiez la nouvelle position de bandit3
            assertEquals(wagon2, bandit3.getPosition());

            // ens_projet.modele.Action de bandit
            Deplacer deplcaer3 = new Deplacer(bandit2, Direction.BAS);
            deplcaer3.executer();
            // Vérifiez l'état de bandit2
            assertFalse(bandit2.isSurLeToit());

            // ens_projet.modele.Action de bandit
            Deplacer deplacer4 = new Deplacer(bandit2, Direction.HAUT);
            deplacer4.executer();
            // Vérifiez l'état de bandit2
            assertTrue(bandit2.isSurLeToit());
        }
    }
}