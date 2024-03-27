import ens_projet.modele.Personne;
import ens_projet.modele.Train;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class TrainTest {
    private Train train;

    @Before
    public void setUp(){
        train = new Train(4, new HashSet<Personne>());
    }
    @Test
    public void getWagons() {
        assertNotNull(train.getWagons());
    }
    @Test
    public void getWagonInd() {
        assertNotNull(train.getWagon(0));
        assertEquals(0, train.getWagon(0).getNumero());
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

}