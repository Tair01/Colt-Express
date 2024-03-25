import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class DeplacerTest {
    private Train train;
    private Bandit bandit1, bandit2, bandit3;
    private Marshall marshall;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    private HashSet<Personne> personnes;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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

        bandit1.position = wagon1;
        bandit2.position = wagon1;
        bandit3.position = wagon3;
        marshall.position = wagon4;
    }
    @Test
    public void executer() {
        assertEquals(0, bandit1.getPosition().getNumero());
        assertEquals(0, bandit2.getPosition().getNumero());
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
    public void testExceptions() {
        // Cas où le bandit est déjà sur le toit
        bandit1.setSurLeToit(true);
        Action action1 = new Deplacer(bandit1, Direction.HAUT);
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Le bandit est déjà sur le toit.");
        action1.executer();

        // Cas où le bandit est déjà à l'intérieur
        bandit1.setSurLeToit(false);
        Action action2 = new Deplacer(bandit1, Direction.BAS);
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Le bandit est déjà à l'intérieur.");
        action2.executer();

        // Cas où le bandit est sur la locomotive
        wagon1.isLocomotive();
        Action action3 = new Deplacer(bandit1, Direction.AVANT);
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Le bandit est sur la locomotive.");
        action3.executer();

        // Cas où le bandit est sur le dernier wagon
        Wagon wagonActuel = train.getWagon(train.getNombreW() - 1); // Le dernier wagon
        Action action4 = new Deplacer(bandit1, Direction.ARRIERE);
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Le bandit est sur le dernier wagon.");
        action4.executer();
    }
    @Test
    public void testMarshallMovesBeforeBanditActions() {
        // Initialisation du jeu avec un Marshall et des bandits
        Action action = new Deplacer(marshall, Direction.AVANT);
        // Vérifiez les positions initiales des bandits
        assertEquals(0, bandit1.getPosition().getNumero());
        assertEquals(0, bandit2.getPosition().getNumero());
        assertEquals(2, bandit3.getPosition().getNumero());

        // Effectuez plusieurs actions de bandit et vérifiez le déplacement du Marshall
        for (int i = 0; i < 5; i++) {
            // Déplacement aléatoire du Marshall avant chaque action de bandit
            action.executer();

            // Action de bandit
            Action action1 = new Deplacer(bandit1, Direction.AVANT);
            action1.executer();
            // Vérifiez la nouvelle position de bandit1
            assertEquals(wagon2, bandit1.getPosition());

            // Action de bandit
            Action action2 = new Deplacer(bandit3, Direction.ARRIERE);
            action2.executer();
            // Vérifiez la nouvelle position de bandit3
            assertEquals(wagon2, bandit3.getPosition());

            // Action de bandit
            Action action3 = new Deplacer(bandit2, Direction.BAS);
            action3.executer();
            // Vérifiez l'état de bandit2
            assertFalse(bandit2.isSurLeToit());

            // Action de bandit
            Action action4 = new Deplacer(bandit2, Direction.HAUT);
            action4.executer();
            // Vérifiez l'état de bandit2
            assertTrue(bandit2.isSurLeToit());
        }
    }
}