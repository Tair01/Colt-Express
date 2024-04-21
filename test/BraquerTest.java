import ens_projet.modele.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Classe de test pour la classe Braquer.
 */
public class BraquerTest {
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
        modele = new Modele();
        train = new Train(modele, 4, personnes);
        wagon1 = train.getWagon(0);
        wagon2 = train.getWagon(1);
        wagon3 = train.getWagon(2);
        wagon4 = train.getWagon(3);

        // Crée les instances de Bandit et Marshall avec la liste de personnes
        bandit1 = new Bandit(modele, "Alex", train);
        bandit2 = new Bandit(modele, "Souleimane", train);
        bandit3 = new Bandit(modele, "Tair", train);
        marshall = new Marshall(modele, "Pierre", train);

        personnes = new HashSet<>(Arrays.asList(bandit1, bandit2, bandit3, marshall));
        train.getPersonnes().addAll(personnes);

        caisseMunitions1 = new CaisseMunitions(modele, wagon1, 3);
        caisseMunitions2 = new CaisseMunitions(modele, wagon2, 5);
        wagon1.getCaissesMunitions().add(caisseMunitions1);
        wagon2.getCaissesMunitions().add(caisseMunitions2);

        // Positionnement des personnages dans les wagons
        bandit1.setPosition(wagon1);
        bandit2.setPosition(wagon2);
        bandit3.setPosition(wagon3);
        marshall.setPosition(wagon4);

        // Création de quelques butins
        butin1 = new Bourse(modele, 100, train.getWagon(0));
        butin2 = new Bijou(modele, train.getWagon(0));
        butin3 = new Magot(modele, train);
    }

    @Test
    public void executer() {
        // Test de l'exécution de l'action Braquer

        // Vérification que chaque bandit a 6 balles au départ
        assertEquals(6, bandit1.getBalles());

        // Ajout de 3 butins dans le wagon1
        wagon1.ajouterButin(butin1);
        wagon1.ajouterButin(butin2);
        wagon1.ajouterButin(butin3);

        // Création d'une action de Braquage pour bandit1
        Braquer braquer = new Braquer(modele, bandit1);
        braquer.executer();

        // Vérification du résultat du braquage pour bandit1
        ArrayList<Butin> butinsWagon1 = wagon1.getButins();
        ArrayList<Butin> butinsBandit1 = bandit1.getButins();

        assertEquals(2, butinsWagon1.size()); // Le butin a été retiré du wagon
        assertEquals(1, butinsBandit1.size()); // Le bandit a récupéré un butin
        assertTrue(butinsBandit1.contains(butin1) || butinsBandit1.contains(butin2) || butinsBandit1.contains(butin3)); // Le butin récupéré est bien celui du wagon1
        assertTrue(bandit1.montantT() > 0);

        assertEquals(1, wagon1.getCaissesMunitions().size()); // Une balle a été retirée du wagon
        assertEquals(9, bandit1.getBalles()); // Le bandit a récupéré 3 balles, car dans le wagon1 il y a une caisse de munitions avec 3 balles

        // Test de cas où le wagon ne contient pas de butins
        Braquer braquer1 = new Braquer(modele, bandit2);
        braquer1.executer();

        // Vérification du résultat du braquage pour bandit2 dans un wagon sans butins
        ArrayList<Butin> butinsWagon2 = wagon2.getButins();
        ArrayList<Butin> butinsBandit2 = bandit2.getButins();

        assertEquals(0, butinsWagon2.size());
        assertEquals(0, butinsBandit2.size());

        assertEquals(1, wagon2.getCaissesMunitions().size());
        assertEquals(11, bandit2.getBalles()); // Le bandit2 a déjà 11 balles

        // Test de cas où le wagon contient une caisse de munitions
        Braquer braquer2 = new Braquer(modele, bandit2);
        braquer2.executer();
        assertEquals(16, bandit2.getBalles()); // Le bandit a 16 balles car il se situe dans le wagon2 et le wagon2 a une caisse de munitions avec 5 balles
    }
}
