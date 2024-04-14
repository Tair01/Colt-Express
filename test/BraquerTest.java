import ens_projet.modele.*;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class BraquerTest {
    private Train train;
    private Bandit bandit1, bandit2, bandit3;
    private Marshall marshall;
    private Wagon wagon1, wagon2, wagon3, wagon4;
    private HashSet<Personne> personnes;
    private Butin butin1, butin2, butin3;
    private Modele modele;
    @Before
    public void setUp(){
        modele = new Modele();
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
        butin2 = new Bijou(modele,train.getWagon(0));
        butin3 = new Magot(modele,train);
    }
    @Test
    public void executer() {

        // Dans chaque Wagon il y a déjà 2 balles
        assertEquals(2, wagon1.getCaissesMunitions().size());
        assertEquals(2, wagon2.getCaissesMunitions().size());
        assertEquals(2, wagon3.getCaissesMunitions().size());
        assertEquals(2, wagon4.getCaissesMunitions().size());

        // Chaque bandit a 6 balles au départ
        assertEquals(6, bandit1.getBalles());

        // Le wagon #1 a 3 butins
        wagon1.ajouterButin(butin1);
        wagon1.ajouterButin(butin2);
        wagon1.ajouterButin(butin3);

        Braquer braquer = new Braquer(modele,bandit1, Direction.random()); // Quand la ens_projet.modele.Direction.random(), alors la dir est pas importante
        braquer.executer();

        ArrayList<Butin> butinsWagon1 = wagon1.getButins();
        ArrayList<Butin> butinsBandit1 = bandit1.getButins();



        assertEquals(2, butinsWagon1.size()); // Le butin a été retiré du wagon
        assertEquals(1, butinsBandit1.size()); // Le bandit a récupéré un butin
        assertTrue(butinsBandit1.contains(butin1) || butinsBandit1.contains(butin2) || butinsBandit1.contains(butin3)); // Le butin récupéré est bien celui du wagon1
        assertTrue(bandit1.montantT() > 0);

        assertEquals(1, wagon1.getCaissesMunitions().size()); // Une balle a été retirée du wagon
        assertEquals(7,bandit1.getBalles()); // Le bandit a récupéré une balle

        // Le cas quand le wagon ne contient pas des butins
        Braquer braquer1 = new Braquer(modele,bandit2, Direction.random());
        braquer1.executer();

        ArrayList<Butin> butinsWagon2 = wagon2.getButins();
        ArrayList<Butin> butinsBandit2 = bandit2.getButins();

        assertEquals(0,butinsBandit2.size());


        assertEquals(1, wagon2.getCaissesMunitions().size());
        assertEquals(7,bandit2.getBalles());

        Braquer braquer2 = new Braquer(modele,bandit2, Direction.random());
        braquer2.executer();

        assertEquals(0, wagon2.getCaissesMunitions().size());
        assertEquals(8,  bandit2.getBalles());
    }
}