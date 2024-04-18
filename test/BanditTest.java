import ens_projet.modele.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Classe de test pour la classe Bandit.
 */
public class BanditTest {
    private Bandit bandit1, bandit2, bandit3;
    private Butin butin1, butin2, butin3;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    private Train train;
    private HashSet<Personne> personnes;
    private Modele modele;
    private CaisseMunitions caisseMunitions1, caisseMunitions2;

    @Before
    public void setUp(){
        // Initialisation des objets pour les tests
        modele = new Modele();
        train = new Train(modele, 4, personnes); // Passer le modèle comme paramètre au train
        bandit1 = new Bandit(modele,"Alex", train); // Passer le modèle comme paramètre au bandit
        bandit2 = new Bandit(modele,"Souleimane",  train);
        bandit3 = new Bandit(modele,"Tair",train);

        personnes = new HashSet<>(Arrays.asList(bandit1, bandit2, bandit3));
        train.getPersonnes().addAll(personnes);

        butin1 = new Bourse(modele, 100, train.getWagon(2));
        butin2 = new Bijou(modele,train.getWagon(0));
        butin3 = new Magot(modele,train);

        wagon1 = train.getWagon(0);
        wagon2 = train.getWagon(1);
        wagon3 = train.getWagon(2);
        wagon4 = train.getWagon(3);

        caisseMunitions1 = new CaisseMunitions(modele, wagon1, 3);
        caisseMunitions2 = new CaisseMunitions(modele, wagon2, 5);
    }

    /**
     * Test de la méthode getButins().
     */
    @Test
    public void getButins() {
        // Ajout de butins au bandit1
        bandit1.ajouteButin(butin1);
        bandit1.ajouteButin(butin2);
        bandit1.ajouteButin(butin3);

        // Vérification que les butins sont présents dans la liste des butins du bandit1
        assertNotNull(bandit1.getButins());
        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertTrue(bandit1.getButins().contains(butin3));
    }

    /**
     * Test de la méthode ajouteButin().
     */
    @Test
    public void ajouteButin() {
        // Ajout de butins au bandit1
        bandit1.ajouteButin(butin1);
        bandit1.ajouteButin(butin2);
        bandit1.ajouteButin(butin3);

        // Vérification que les butins sont bien ajoutés au bandit1
        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertTrue(bandit1.getButins().contains(butin3));
    }

    /**
     * Test de la méthode lacheButin().
     */
    @Test
    public void lacheButin() {
        // Ajout de butins au bandit1
        bandit1.ajouteButin(butin1);
        bandit1.ajouteButin(butin2);
        bandit1.ajouteButin(butin3);

        // Vérification que les butins sont présents avant et après la libération de certains butins
        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertTrue(bandit1.getButins().contains(butin3));

        // Libération de butins un à un et vérification à chaque étape
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

    /**
     * Test de la méthode montantT().
     */
    @Test
    public void montantT() {
        // Création d'un bijou à ajouter au bandit1
        Bijou bijou1 = new Bijou(modele,train.getWagon(2));

        // Ajout de butins au bandit1
        bandit1.ajouteButin(butin1);
        bandit1.ajouteButin(butin2);
        bandit1.ajouteButin(butin3);

        // Vérification que les butins sont présents avant et après l'ajout d'un bijou
        assertTrue(bandit1.getButins().contains(butin1));
        assertTrue(bandit1.getButins().contains(butin2));
        assertTrue(bandit1.getButins().contains(butin3));

        // Vérification du montant total des butins
        assertEquals(1600, bandit1.montantT());

        // Ajout d'un bijou supplémentaire et vérification du nouveau montant total
        bandit1.ajouteButin(bijou1);
        assertEquals(2100, bandit1.montantT());

        // Libération de butins un à un et vérification du montant total à chaque étape
        bandit1.lacheButin();
        bandit1.lacheButin();
        assertEquals(600, bandit1.montantT());

        bandit1.lacheButin();
        bandit1.lacheButin();
        assertEquals(0, bandit1.montantT());
    }

    /**
     * Test de la méthode ajouteMunitions().
     */
    @Test
    public void ajouteMunitions(){
        // Vérification du nombre de balles initial du bandit1
        assertEquals(6, bandit1.getBalles());

        // Ajout de caisses de munitions et vérification du nouveau nombre de balles
        bandit1.ajouteMunitions(caisseMunitions1);
        assertEquals(9, bandit1.getBalles());
        bandit1.ajouteMunitions(caisseMunitions2);
        assertEquals(14, bandit1.getBalles());

        // Ajout de caisses de munitions au bandit2 et vérification du nombre de balles
        bandit2.ajouteMunitions(caisseMunitions1);
        bandit2.ajouteMunitions(caisseMunitions2);
        bandit2.ajouteMunitions(caisseMunitions1);
        assertEquals(17, bandit2.getBalles());
    }
}
