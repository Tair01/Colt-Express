import ens_projet.modele.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Classe de test pour la classe Deplacer.
 */
public class DeplacerTest {
    private Train train;
    private Bandit bandit1, bandit2, bandit3;
    private Marshall marshall;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    private HashSet<Personne> personnes;
    private Modele modele;

    @Before
    public void setUp() {
        // Initialisation des objets pour les tests
        modele = new Modele();
        train = new Train(modele, 4, personnes);

        wagon1 = train.getWagon(0);
        wagon2 = train.getWagon(1);
        wagon3 = train.getWagon(2);
        wagon4 = train.getWagon(3);

        // Création des personnages
        bandit1 = new Bandit(modele, "Alex", train);
        bandit2 = new Bandit(modele, "Souleimane", train);
        bandit3 = new Bandit(modele, "Tair", train);
        marshall = new Marshall(modele, "Pierre", train);

        // Ajout des personnages à la liste des personnes dans le train
        personnes = new HashSet<>(Arrays.asList(bandit1, bandit2, bandit3, marshall));
        train.getPersonnes().addAll(personnes);

        // Positionnement initial des bandits dans les wagons
        bandit1.setPosition(wagon1);
        bandit2.setPosition(wagon2);
        bandit3.setPosition(wagon3);
        //marshall.setPosition(wagon4);
    }

    @Test
    public void executer() {
        // Test de déplacement des personnages

        // Vérification des positions initiales des bandits
        assertEquals(0, bandit1.getPosition().getNumero());
        assertEquals(1, bandit2.getPosition().getNumero());
        assertEquals(2, bandit3.getPosition().getNumero());

        // Déplacement vers l'Avant
        Action action = new Deplacer(modele, bandit1, Direction.AVANT);
        action.executer();
        assertEquals(wagon2, bandit1.getPosition());

        // Déplacement vers l'Arrière
        Action action1 = new Deplacer(modele, bandit3, Direction.ARRIERE);
        action1.executer();
        assertEquals(wagon2, bandit3.getPosition());

        // Déplacement vers le Bas
        Action action2 = new Deplacer(modele, bandit2, Direction.BAS);
        action2.executer();
        assertFalse(bandit2.isSurLeToit());

        // Déplacement vers le Haut
        Action action3 = new Deplacer(modele, bandit2, Direction.HAUT);
        action3.executer();
        assertTrue(bandit2.isSurLeToit());
    }

    @Test
    public void testMarshallMovesBeforeBanditActions() {
        // Test pour vérifier que le Marshall se déplace avant les actions des bandits

        // Vérification des positions initiales des bandits et du Marshall
        assertEquals(0, bandit1.getPosition().getNumero());
        assertEquals(1, bandit2.getPosition().getNumero());
        assertEquals(2, bandit3.getPosition().getNumero());
        assertEquals(3, marshall.getPosition().getNumero());

        // Stockage de la position initiale du Marshall
        int positionInitialeMarshall = marshall.getPosition().getNumero();

        // Action de bandit1
        Deplacer deplacer1 = new Deplacer(modele, bandit1, Direction.AVANT);
        deplacer1.executer();
        assertEquals(wagon2.getNumero(), bandit1.getPosition().getNumero());

        // Vérification de la nouvelle position du Marshall après le déplacement de bandit1
        assertNotEquals(positionInitialeMarshall, marshall.getPosition().getNumero());

        // Stockage de la nouvelle position du Marshall
        int nouvellePositionMarshall = marshall.getPosition().getNumero();

        // Action de bandit3
        Deplacer deplacer2 = new Deplacer(modele, bandit3, Direction.ARRIERE);
        deplacer2.executer();
        assertEquals(wagon2, bandit3.getPosition());

        // Vérification de la nouvelle position du Marshall après le déplacement de bandit3
        assertNotEquals(nouvellePositionMarshall, marshall.getPosition().getNumero());

        // Action de bandit2
        Deplacer deplacer3 = new Deplacer(modele, bandit2, Direction.BAS);
        deplacer3.executer();
        assertFalse(bandit2.isSurLeToit());

        // Action de bandit2
        Deplacer deplacer4 = new Deplacer(modele, bandit2, Direction.HAUT);
        deplacer4.executer();
        assertTrue(bandit2.isSurLeToit());
    }
}
