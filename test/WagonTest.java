import ens_projet.modele.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Classe de test pour la classe Wagon.
 */
public class WagonTest {
    private Bandit bandit1, bandit2, bandit3;
    private Marshall marshall;
    private Train train;
    private HashSet<Personne> personnes;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    private Butin butin1, butin2, butin3;
    private CaisseMunitions caisse1, caisse2;
    private Modele modele;

    @Before
    public void setUp() {
        // Initialisation des objets pour les tests
        modele = new Modele();
        // Initialise le train avec la liste de personnes
        train = new Train(modele, 4, personnes);

        // Crée les instances de Bandit et Marshall avec la liste de personnes
        bandit1 = new Bandit(modele, "Alex", train);
        bandit2 = new Bandit(modele, "Souleimane", train);
        bandit3 = new Bandit(modele, "Tair", train);
        marshall = new Marshall(modele, "Pierre", train);

        personnes = new HashSet<>(Arrays.asList(bandit1, bandit2, bandit3, marshall));
        train.getPersonnes().addAll(personnes);

        wagon1 = train.getWagon(0);
        wagon2 = train.getWagon(1);
        wagon3 = train.getWagon(2);
        wagon4 = train.getWagon(3);

        // Définir la position des personnages
        bandit1.setPosition(wagon1);
        bandit2.setPosition(wagon1);
        bandit3.setPosition(wagon1);
        marshall.setPosition(wagon4);

        // Créer des objets Butin
        butin1 = new Bourse(modele, 100, train.getWagon(0));
        butin2 = new Bijou(modele, train.getWagon(0));
        butin3 = new Magot(modele, train);

        // Créer des objets CaisseMunitions
        caisse1 = new CaisseMunitions(modele, wagon1, 3);
        caisse2 = new CaisseMunitions(modele, wagon2, 5);
        wagon1.getCaissesMunitions().add(caisse1);
        wagon2.getCaissesMunitions().add(caisse2);
        wagon3.getCaissesMunitions().add(caisse1);
        wagon3.getCaissesMunitions().add(caisse2);
    }

    @Test
    public void getNumero() {
        // Vérifie si les numéros de wagons sont corrects
        assertEquals(0, wagon1.getNumero());
        assertEquals(1, wagon2.getNumero());
        assertEquals(2, wagon3.getNumero());
        assertEquals(3, wagon4.getNumero());
        assertNotEquals(wagon1.getNumero(), wagon2.getNumero());
        assertNotEquals(wagon2.getNumero(), wagon3.getNumero());
    }

    @Test
    public void isLocomotive() {
        // Vérifie si un wagon est une locomotive ou non
        assertFalse(wagon1.isLocomotive());
        assertTrue(wagon4.isLocomotive());
    }

    @Test
    public void banditsPresents() {
        // Vérifie le nombre de bandits présents dans un wagon donné
        assertEquals(3, wagon1.banditsPresents().size());
    }

    @Test
    public void ajouterButin() {
        // Vérifie si les butins sont correctement ajoutés aux wagons
        wagon1.ajouterButin(butin2);
        wagon1.ajouterButin(butin1);
        wagon2.ajouterButin(butin2);
        wagon2.ajouterButin(butin1);
        wagon3.ajouterButin(butin2);
        wagon3.ajouterButin(butin1);
        wagon4.ajouterButin(butin3);

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
        // Vérifie si les butins sont correctement retirés des wagons
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
    public void butinsPresents() {
        // Vérifie le nombre de butins présents dans un wagon
        wagon1.ajouterButin(butin1);
        wagon1.ajouterButin(butin2);
        assertEquals(2, wagon1.getButins().size());
    }

    @Test
    public void getButins() {
        // Vérifie si les butins sont correctement récupérés des wagons
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

    @Test
    public void getCaissesMunitions() {
        // Vérifie si les caisses de munitions sont correctement récupérées des wagons
        ArrayList<CaisseMunitions> caissesWagon1 = wagon1.getCaissesMunitions();
        ArrayList<CaisseMunitions> caissesWagon2 = wagon2.getCaissesMunitions();
        ArrayList<CaisseMunitions> caissesWagon3 = wagon3.getCaissesMunitions();
        ArrayList<CaisseMunitions> caissesWagon4 = wagon4.getCaissesMunitions();

        assertEquals(1, caissesWagon1.size());
        assertEquals(1, caissesWagon2.size());
        assertTrue(caissesWagon1.contains(caisse1));
        assertTrue(caissesWagon2.contains(caisse2));

        assertEquals(2, caissesWagon3.size());
        assertTrue(caissesWagon3.contains(caisse1));
        assertTrue(caissesWagon3.contains(caisse2));

        assertEquals(0, caissesWagon4.size());
        assertTrue(caissesWagon4.isEmpty());
    }
}
