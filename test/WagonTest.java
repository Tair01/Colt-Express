import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WagonTest {
    private Train train;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    @Before
    public void setUp(){
        train = new Train(4);
        wagon1 = new Wagon(1, train);
        wagon2 = new Wagon(2, train);
        wagon3 = new Wagon(3, train);
        wagon4 = new Wagon(4,train); // Le wagon locomotive
    }
    @Test
    public void getNumero() {
        assertEquals(1, wagon1.getNumero());
        assertEquals(2, wagon2.getNumero());
        assertEquals(3, wagon3.getNumero());
        assertEquals(4, wagon4.getNumero());
        assertNotEquals(wagon1.getNumero(), wagon2.getNumero());
        assertNotEquals(wagon2.getNumero(), wagon3.getNumero());
    }

    @Test
    public void isLocomotive() {
        assertFalse(wagon1.isLocomotive());
        assertTrue(wagon4.isLocomotive());
    }

    @Test
    public void nbBanditsPresents() {
        Bandit bandit1 = new Bandit("Alex", true);
        Bandit bandit2 = new Bandit("Pierre", false);
        Bandit bandit3 = new Bandit("Olivier", true);
        Wagon wagon = train.getWagonInd(4);
        bandit1.setPosition(wagon);
        bandit2.setPosition(wagon);
        bandit3.setPosition(wagon);
        train.ajoutePersonne(bandit1);
        train.ajoutePersonne(bandit2);
        train.ajoutePersonne(bandit3);
        assertEquals(3, wagon.nbBanditsPresents());
    }

    @Test
    public void ajouterButin() {
        Bourse bourse = new Bourse(100);
        Bijou bijou = new Bijou();
        Magot magot = new Magot();

        wagon1.ajouterButin(bijou);
        wagon1.ajouterButin(bourse);
        wagon2.ajouterButin(bijou);
        wagon2.ajouterButin(bourse);
        wagon3.ajouterButin(bijou);
        wagon3.ajouterButin(bourse);
        wagon4.ajouterButin(magot); // la position de magot est sur le wagon locomotive

        assertNotNull(wagon1.getButins());
        assertNotNull(wagon2.getButins());
        assertNotNull(wagon3.getButins());
        assertNotNull(wagon4.getButins());

        assertTrue(wagon1.getButins().contains(bijou) && wagon1.getButins().contains(bourse));
        assertTrue(wagon2.getButins().contains(bijou) && wagon2.getButins().contains(bourse));
        assertTrue(wagon3.getButins().contains(bijou) && wagon3.getButins().contains(bourse));
        assertTrue(wagon4.getButins().contains(magot));
    }

    @Test
    public void retireButin() {
        Bourse bourse = new Bourse(100);
        Bijou bijou = new Bijou();
        Magot magot = new Magot();

        wagon1.ajouterButin(bijou);
        wagon1.ajouterButin(bourse);
        wagon2.ajouterButin(bijou);
        wagon2.ajouterButin(bourse);
        wagon4.ajouterButin(magot);

        assertNotNull(wagon1.getButins());
        assertNotNull(wagon2.getButins());
        assertNotNull(wagon4.getButins());

        assertTrue(wagon1.getButins().contains(bijou) && wagon1.getButins().contains(bourse));
        assertTrue(wagon2.getButins().contains(bijou) && wagon2.getButins().contains(bourse));
        assertTrue(wagon4.getButins().contains(magot));

        wagon1.retireButin(bijou);
        wagon1.retireButin(bourse);
        wagon2.retireButin(bijou);
        wagon4.retireButin(magot);

        assertTrue(wagon1.getButins().isEmpty());
        assertTrue(!wagon2.getButins().contains(bijou) && wagon2.getButins().contains(bourse));
        assertTrue(wagon4.getButins().isEmpty());
    }
}