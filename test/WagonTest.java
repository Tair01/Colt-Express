import ens_projet.modele.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class WagonTest {
    private Bandit bandit1, bandit2, bandit3;
    private Marshall marshall;
    private Train train;
    private HashSet<Personne> personnes;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    private Butin butin1, butin2, butin3;
    private Modele modele;
    @Before
    public void setUp() {
        modele = new Modele();
        // Initialise le train avec la liste de personnes
        train = new Train(modele,4, personnes);

        // Crée les instances de ens_projet.modele.Bandit et ens_projet.modele.Marshall avec la liste de personnes
        bandit1 = new Bandit(modele,"Alex", train);
        bandit2 = new Bandit(modele,"Souleimane", train);
        bandit3 = new Bandit(modele,"Tair", train);
        marshall = new Marshall(modele,"Pierre", train);

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


        butin1 = new Bourse(modele,100, train.getWagon(0));
        butin2 = new Bijou(modele, train.getWagon(0));
        butin3 = new Magot(modele, train);
    }
    @Test
    public void getNumero() {
        assertEquals(0, wagon1.getNumero());
        assertEquals(1, wagon2.getNumero());
        assertEquals(2, wagon3.getNumero());
        assertEquals(3, wagon4.getNumero());
        assertNotEquals(wagon1.getNumero(), wagon2.getNumero());
        assertNotEquals(wagon2.getNumero(), wagon3.getNumero());
    }

    @Test
    public void isLocomotive() {
        assertFalse(wagon1.isLocomotive());
        assertTrue(wagon4.isLocomotive());
    }

    @Test
    public void banditsPresents() {
        assertEquals(3, wagon1.banditsPresents().size());
    }

    @Test
    public void ajouterButin() {
        wagon1.ajouterButin(butin2);
        wagon1.ajouterButin(butin1);
        wagon2.ajouterButin(butin2);
        wagon2.ajouterButin(butin1);
        wagon3.ajouterButin(butin2);
        wagon3.ajouterButin(butin1);
        wagon4.ajouterButin(butin3); // la position de butin3 est sur le wagon locomotive

        assertNotNull(wagon1.getButins());
        assertNotNull(wagon2.getButins());
        assertNotNull(wagon3.getButins());
        assertNotNull(wagon4.getButins());

        assertTrue(wagon1.getButins().contains(butin2) && wagon1.getButins().contains(butin1));
        assertTrue(wagon2.getButins().contains(butin2) && wagon2.getButins().contains(butin1));
        assertTrue(wagon3.getButins().contains(butin2) && wagon3.getButins().contains(butin1));
        assertTrue(wagon4.getButins().contains(butin3));
    }

    @Test
    public void retireButin() {
        wagon1.ajouterButin(butin2);
        wagon1.ajouterButin(butin1);
        wagon2.ajouterButin(butin2);
        wagon2.ajouterButin(butin1);
        wagon4.ajouterButin(butin3);

        assertNotNull(wagon1.getButins());
        assertNotNull(wagon2.getButins());
        assertNotNull(wagon4.getButins());

        assertTrue(wagon1.getButins().contains(butin2) && wagon1.getButins().contains(butin1));
        assertTrue(wagon2.getButins().contains(butin2) && wagon2.getButins().contains(butin1));
        assertTrue(wagon4.getButins().contains(butin3));

        wagon1.retireButin(butin2);
        wagon1.retireButin(butin1);
        wagon2.retireButin(butin2);
        wagon4.retireButin(butin3);

        assertTrue(wagon1.getButins().isEmpty());
        assertTrue(!wagon2.getButins().contains(butin2) && wagon2.getButins().contains(butin1));
        assertTrue(wagon4.getButins().isEmpty());
    }

    @Test
    public void butinsPresents(){
        wagon1.ajouterButin(butin1);
        wagon1.ajouterButin(butin2);
        assertEquals(2, wagon1.getButins().size());
    }

    @Test
    public void getButins(){
        wagon1.ajouterButin(butin1);
        wagon1.ajouterButin(butin2);
        wagon2.ajouterButin(butin2);
        wagon3.ajouterButin(butin3);
        wagon4.ajouterButin(butin3);

        ArrayList<Butin> butinsWagon1 = wagon1.getButins();
        ArrayList<Butin> butinsWagon2 = wagon2.getButins();
        ArrayList<Butin> butinsWagon3 = wagon3.getButins();
        ArrayList<Butin> butinsWagon4 = wagon4.getButins();

        assertEquals(2, butinsWagon1.size());
        assertEquals(1, butinsWagon2.size());
        assertEquals(1, butinsWagon3.size());
        assertEquals(1, butinsWagon4.size());

        assertTrue(butinsWagon1.contains(butin1));
        assertTrue(butinsWagon1.contains(butin2));
        assertTrue(butinsWagon2.contains(butin2));
        assertTrue(butinsWagon3.contains(butin3));
        assertTrue(butinsWagon4.contains(butin3));
    }
}