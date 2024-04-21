import ens_projet.modele.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Classe de test pour la classe Tirer.
 */
public class TirerTest {
    private Train train;
    private Bandit bandit1, bandit2, bandit3;
    private Marshall marshall;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    private HashSet<Personne> personnes;
    private Butin butin1, butin2, butin3;
    private Modele modele;
    private CaisseMunitions caisseMunitions1, caisseMunitions2;

    @Before
    public void setUp() {
        // Initialisation des objets pour les tests
        modele = new Modele(true);
        train = new Train(modele, 4, personnes);
        wagon1 = train.getWagon(0);
        wagon2 = train.getWagon(1);
        wagon3 = train.getWagon(2);
        wagon4 = train.getWagon(3);

        // Création des instances de Bandit et Marshall avec la liste de personnes
        bandit1 = new Bandit(modele, "Alex", train);
        bandit2 = new Bandit(modele, "Souleimane", train);
        bandit3 = new Bandit(modele, "Tair", train);
        marshall = new Marshall(modele, "Pierre", train);

        personnes = new HashSet<>(Arrays.asList(bandit1, bandit2, bandit3, marshall));
        train.getPersonnes().addAll(personnes);

        // Création de caisses de munitions dans les wagons
        caisseMunitions1 = new CaisseMunitions(modele, wagon1, 3);
        caisseMunitions2 = new CaisseMunitions(modele, wagon2, 5);
        wagon1.getCaissesMunitions().add(caisseMunitions1);
        wagon2.getCaissesMunitions().add(caisseMunitions2);

        // Positionnement initial des bandits dans le wagon1
        bandit1.setPosition(wagon1);
        bandit2.setPosition(wagon1);
        bandit3.setPosition(wagon1);

        // Création des butins
        butin1 = new Bourse(modele, 100, train.getWagon(0));
        butin2 = new Bijou(modele, train.getWagon(0));
        butin3 = new Magot(modele, train);
    }

    @Test
    public void executer() {
        // Test de la méthode executer de la classe Tirer

        // Vérifier que chaque bandit a 6 balles au départ
        assertEquals(6, bandit1.getBalles());
        assertEquals(6, bandit2.getBalles());
        assertEquals(6, bandit3.getBalles());

        // Ajouter 3 butins dans le wagon #1
        wagon1.ajouterButin(butin1);
        wagon1.ajouterButin(butin2);
        wagon1.ajouterButin(butin3);

        assertEquals(3, wagon1.getButins().size());

        // Effectuer un braquage par le bandit 2 et le bandit 3
        Braquer braquer1 = new Braquer(modele, bandit1);
        Braquer braquer2 = new Braquer(modele, bandit2);
        Braquer braquer3 = new Braquer(modele, bandit3);
        braquer1.executer();
        braquer2.executer();
        braquer3.executer();

        // Vérifier que le butin a été retiré du wagon
        assertEquals(0, wagon1.getButins().size());
        assertTrue(wagon1.getButins().isEmpty());

        // Vérifier que chaque bandit a récupéré un butin du wagon
        ArrayList<Butin> butinsBandit1 = bandit1.getButins();
        ArrayList<Butin> butinsBandit2 = bandit2.getButins();
        ArrayList<Butin> butinsBandit3 = bandit3.getButins();
        assertEquals(1, butinsBandit2.size());
        assertEquals(1, butinsBandit3.size());
        assertTrue(butinsBandit2.contains(butin1) || butinsBandit2.contains(butin2) || butinsBandit2.contains(butin3));
        assertTrue(butinsBandit3.contains(butin1) || butinsBandit3.contains(butin2) || butinsBandit3.contains(butin3));

        // Vérifier que chaque bandit a un montant total supérieur à zéro après le braquage
        assertTrue(bandit2.montantT() > 0);
        assertTrue(bandit3.montantT() > 0);

        // Effectuer une action de tir par le bandit 1
        Action action = new Tirer(modele, bandit1);
        action.executer();

        // Vérifier que le nombre de balles du bandit 1 est réduit de 1 après le tir
        assertEquals(8, bandit1.getBalles()); // 8 car bandit1 braque le wagon1 et dans le wagon1 y a une caisse de munitions avec 3 balles

        // Vérifier que le butin récupéré par le bandit 2 ou le bandit 3 est désormais vide
        assertTrue(butinsBandit2.isEmpty() || butinsBandit3.isEmpty());
        assertTrue(bandit2.montantT() == 0 || bandit3.montantT() == 0);

        // Effectuer une action de tir par le bandit 2
        Action action2 = new Tirer(modele, bandit2);
        action2.executer();

        // Vérifier que le nombre de balles du bandit 2 est réduit de 1 après le tir
        assertEquals(8, bandit2.getBalles());
    }
}
