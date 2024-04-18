import ens_projet.modele.Modele;
import ens_projet.modele.Personne;
import ens_projet.modele.Train;
import ens_projet.modele.Wagon;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Classe de test pour la classe Train.
 */
public class TrainTest {
    private Train train;
    private Modele modele;

    @Before
    public void setUp(){
        // Initialisation des objets pour les tests
        modele = new Modele();
        train = new Train(modele, 4, new HashSet<Personne>());
        for (int i = 0; i < 4; i++) {
            train.getWagons().add(new Wagon(modele, i, train));
        }
    }

    @Test
    public void getWagons() {
        // Test de la méthode getWagons pour vérifier si elle renvoie une liste non nulle de wagons
        assertNotNull(train.getWagons());
    }

    @Test
    public void getWagonInd() {
        // Test de la méthode getWagon pour vérifier si elle renvoie le wagon correct en fonction de l'indice
        assertNotNull(train.getWagon(0));
        assertEquals(0, train.getWagon(0).getNumero());
        assertEquals(1, train.getWagon(1).getNumero());
        assertEquals(2, train.getWagon(2).getNumero());
        assertEquals(3, train.getWagon(3).getNumero());
    }

    @Test
    public void getNombreW() {
        // Test de la méthode getNombreW pour vérifier si elle renvoie le nombre total de wagons dans le train
        assertEquals(4, train.getNombreW());
    }

    @Test
    public void getPersonnes() {
        // Test de la méthode getPersonnes pour vérifier si elle renvoie une liste non nulle de personnes dans le train
        HashSet<Personne> personnes = train.getPersonnes();
        assertNotNull(personnes);
        assertTrue(personnes.isEmpty()); // Vérifie que la liste est initialement vide
    }
}
