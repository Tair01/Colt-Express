import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BanditTest {
    private Bandit bandit;
    private Butin butin1, butin2, butin3;
    @Before
    public void setUp(){
        bandit = new Bandit("Alex", true);
        butin1 = new Bourse(100);
        butin2 = new Bijou();
        butin3 = new Magot();
    }

    @Test
    public void getButins() {
        bandit.ajouteButin(butin1);
        bandit.ajouteButin(butin2);
        bandit.ajouteButin(butin3);

        assertNotNull(bandit.getButins());
        assertTrue(bandit.getButins().contains(butin1));
        assertTrue(bandit.getButins().contains(butin2));
        assertTrue(bandit.getButins().contains(butin3));
    }

    @Test
    public void ajouteButin() {
        bandit.ajouteButin(butin1);
        bandit.ajouteButin(butin2);
        bandit.ajouteButin(butin3);

        assertTrue(bandit.getButins().contains(butin1));
        assertTrue(bandit.getButins().contains(butin2));
        assertTrue(bandit.getButins().contains(butin3));
    }

    @Test
    public void lacheButin() {
        bandit.ajouteButin(butin1);
        bandit.ajouteButin(butin2);
        bandit.ajouteButin(butin3);

        assertTrue(bandit.getButins().contains(butin1));
        assertTrue(bandit.getButins().contains(butin2));
        assertTrue(bandit.getButins().contains(butin3));

        bandit.lacheButin();
        assertTrue(bandit.getButins().contains(butin1));
        assertTrue(bandit.getButins().contains(butin2));
        assertFalse(bandit.getButins().contains(butin3));

        bandit.lacheButin();
        assertTrue(bandit.getButins().contains(butin1));
        assertFalse(bandit.getButins().contains(butin2));
        assertFalse(bandit.getButins().contains(butin3));

        bandit.lacheButin();
        assertFalse(bandit.getButins().contains(butin1));
        assertFalse(bandit.getButins().contains(butin2));
        assertFalse(bandit.getButins().contains(butin3));

        assertTrue(bandit.getButins().isEmpty());
    }

    @Test
    public void montantT() {
        Bijou bijou1 = new Bijou();
        bandit.ajouteButin(butin1);
        bandit.ajouteButin(butin2);
        bandit.ajouteButin(butin3);

        assertTrue(bandit.getButins().contains(butin1));
        assertTrue(bandit.getButins().contains(butin2));
        assertTrue(bandit.getButins().contains(butin3));

        assertEquals(1600, bandit.montantT());
        bandit.ajouteButin(bijou1);
        assertEquals(2100, bandit.montantT());

        bandit.lacheButin();
        bandit.lacheButin();
        assertEquals(600, bandit.montantT());

        bandit.lacheButin();
        bandit.lacheButin();
        assertEquals(0, bandit.montantT());
    }

    @Test
    public void effectuerAction() {
        Train train = new Train(4);
        Wagon locomotive = train.getWagonInd(train.getNombreW());
        Bandit bandit2 = new Bandit("Pierre", true);

        bandit.setPosition(locomotive);
        Deplacer deplacement = new Deplacer(bandit,Direction.ARRIERE);

        bandit.effectuerAction(deplacement, deplacement.getDirection());
        assertEquals(train.getNombreW() - 1, bandit.getPosition().getNumero());

        bandit.effectuerAction(deplacement, deplacement.getDirection());
        assertEquals(train.getNombreW() - 2, bandit.getPosition().getNumero());

        bandit.effectuerAction(deplacement, deplacement.getDirection());
        assertEquals(train.getNombreW() - 3, bandit.getPosition().getNumero());

        Deplacer deplacement1 = new Deplacer(bandit, Direction.AVANT);

        bandit.effectuerAction(deplacement1,deplacement1.getDirection());
        assertEquals(train.getNombreW() - 2, bandit.getPosition().getNumero());

        bandit.effectuerAction(deplacement1,deplacement1.getDirection());
        assertEquals(train.getNombreW() - 1, bandit.getPosition().getNumero());

        Deplacer deplacement2 = new Deplacer(bandit, Direction.BAS);
        bandit.effectuerAction(deplacement2, deplacement2.getDirection());
        assertFalse(bandit.isSurLeToit());

        Deplacer deplacement3 = new Deplacer(bandit, Direction.HAUT);
        bandit.effectuerAction(deplacement3, deplacement3.getDirection());
        assertTrue(bandit.isSurLeToit());
    }
}