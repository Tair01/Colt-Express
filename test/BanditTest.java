import ens_projet.modele.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class BanditTest {
    private Bandit bandit1, bandit2, bandit3;
    private Butin butin1, butin2, butin3;
    private Train train;
    private HashSet<Personne> personnes;
    @Before
    public void setUp(){
        train = new Train(4, personnes);
        bandit1 = new Bandit("Alex", train);
        bandit2 = new Bandit("Souleimane", train);
        bandit3 = new Bandit("Tair", train);

        personnes = new HashSet<>(Arrays.asList(bandit1, bandit2, bandit3));
        train.getPersonnes().addAll(personnes);

        butin1 = new Bourse(100, train.getWagon(2));
        butin2 = new Bijou(train.getWagon(0));
        butin3 = new Magot(train);
    }

    @Test
    public void getButins() {
        bandit1.ajouteButin(butin1);
        bandit1.ajouteButin(butin2);
        bandit1.ajouteButin(butin3);

        assertNotNull(bandit1.getButins());
        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertTrue(bandit1.getButins().contains(butin3));
    }

    @Test
    public void ajouteButin() {
        bandit1.ajouteButin(butin1);
        bandit1.ajouteButin(butin2);
        bandit1.ajouteButin(butin3);

        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertTrue(bandit1.getButins().contains(butin3));
    }

    @Test
    public void lacheButin() {
        bandit1.ajouteButin(butin1);
        bandit1.ajouteButin(butin2);
        bandit1.ajouteButin(butin3);

        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertTrue(bandit1.getButins().contains(butin3));

        bandit1.lacheButin();
        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertFalse(bandit1.getButins().contains(butin3));

        bandit1.lacheButin();
        assertTrue(bandit1.getButins().contains(butin1));
        assertFalse(bandit1.getButins().contains(butin2));
        assertFalse(bandit1.getButins().contains(butin3));

        bandit1.lacheButin();
        assertFalse(bandit1.getButins().contains(butin1));
        assertFalse(bandit1.getButins().contains(butin2));
        assertFalse(bandit1.getButins().contains(butin3));

        assertTrue(bandit1.getButins().isEmpty());
    }

    @Test
    public void montantT() {
        Bijou bijou1 = new Bijou(train.getWagon(2));
        bandit1.ajouteButin(butin1);
        bandit1.ajouteButin(butin2);
        bandit1.ajouteButin(butin3);

        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertTrue(bandit1.getButins().contains(butin3));

        assertEquals(1600, bandit1.montantT());
        bandit1.ajouteButin(bijou1);
        assertEquals(2100, bandit1.montantT());

        bandit1.lacheButin();
        bandit1.lacheButin();
        assertEquals(600, bandit1.montantT());

        bandit1.lacheButin();
        bandit1.lacheButin();
        assertEquals(0, bandit1.montantT());
    }
}