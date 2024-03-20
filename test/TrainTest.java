import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class TrainTest {
    private Train train;
    @Before
    public void setUp(){
        train = new Train(4);
    }
    @Test
    public void getWagons() {
        assertNotNull(train.getWagons());
    }
    @Test
    public void getWagonInd() {
        assertNotNull(train.getWagonInd(0));
        assertEquals(0, train.getWagonInd(0).getNumero());
    }

    @Test
    public void getNombreW() {
        assertEquals(4, train.getNombreW());
    }

    @Test
    public void getPersonnes() {
        HashSet<Personne> personnes = train.getPersonnes();
        assertNotNull(personnes);
        assertTrue(personnes.isEmpty());
    }

    @Test
    public void ajoutePersonne() {
        Bandit bandit = new Bandit("Alex", true);
        train.ajoutePersonne(bandit);
        assertTrue(train.getPersonnes().contains(bandit));
    }

    @Test
    public void retirerPersonne() {
        Bandit bandit = new Bandit("Alex", true);
        train.ajoutePersonne(bandit);
        assertTrue(train.getPersonnes().contains(bandit));
        train.retirerPersonne(bandit);
        assertFalse(train.getPersonnes().contains(bandit));
    }

    @Test
    public void nouveauPosition() {
        Bandit bandit = new Bandit("Alex",true);
        Wagon wagon = train.getWagonInd(4);
        train.ajoutePersonne(bandit);
        bandit.setPosition(wagon);
        assertEquals(wagon, bandit.getPosition());
        Wagon nouveauWagon = train.getWagonInd(3);
        train.nouveauPosition(bandit, nouveauWagon);
        assertEquals(nouveauWagon, bandit.getPosition());
        assertTrue(train.getPersonnes().contains(bandit) && bandit.getPosition().getNumero() == nouveauWagon.getNumero());
    }
}