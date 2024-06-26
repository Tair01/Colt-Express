package ens_projet.vue;

import ens_projet.modele.Butin;
import ens_projet.modele.Modele;
import ens_projet.modele.Personne;
import ens_projet.modele.Train;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Predicate;

public class VueTrain extends JPanel implements Observer {

    public final int WIDTH = 800;
    public final int HEIGHT = 545;
    private final Modele model;

    public VueTrain(Modele model) {
        this.model = model;
        model.addObserver(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
    }

    static <T> void printItemsInContainers(Graphics g, Iterable<T> objectCollection, int stringYRoof, int stringYInterior, int XContainer, int widthContainer, FontMetrics fm, Predicate<T> isOnRoof, int offset, int margin) {
        int stringX = XContainer + margin;
        for (T item : objectCollection) {
            String string = item.toString();
            int stringWidth = fm.stringWidth(string);

            if (stringX + offset + stringWidth > XContainer + widthContainer) {  // Débordement, besoin de sauter une ligne
                if (isOnRoof.test(item)) stringYRoof += fm.getHeight();  // Saut à la ligne suivante
                else stringYInterior += fm.getHeight();
                stringX = XContainer + margin;  // Réinitialisation à la position de départ pour les noms
            }

            g.drawString(string, stringX, isOnRoof.test(item) ? stringYRoof : stringYInterior);
            stringX += stringWidth + offset;  // Espacement entre les noms
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font fPersonnesButins = new Font("Verdana", Font.PLAIN, 10);
        Font fTitres = new Font("Tahoma", Font.BOLD, 12);
        FontMetrics fm = g.getFontMetrics();
        Train train = model.getTrain();

        g.setColor(Color.BLACK);

        int padding = 30;
        int largeurWagon = ((WIDTH - (2 * padding)) - (10 * (train.getNombreW() - 1))) / train.getNombreW();
        int hauteurWagon = HEIGHT - padding;

        for (int i = 0; i < Modele.NB_WAGONS; i++) {

            int X = padding + i * (largeurWagon + 10);
            g.drawRect(X, padding, largeurWagon, hauteurWagon);
            g.drawLine(X, (hauteurWagon + padding) / 2, X + largeurWagon, (hauteurWagon + padding) / 2);
            g.setFont(fTitres);
            g.drawString("Intérieur", X + 5, (hauteurWagon + padding) / 2 + 15);
            g.drawString("Toit", X + 5, padding + 15);

            String texteWagon;
            if (i == train.getNombreW() - 1) {
                texteWagon = "Locomotive";
            } else {
                texteWagon = "Wagon " + (i + 1); // Ajoutez 1 car les indices commencent à 0
            }
            g.setFont(fTitres.deriveFont(Font.BOLD));
            g.drawString(texteWagon, X + largeurWagon / 2 - g.getFontMetrics().stringWidth(texteWagon) / 2, padding - 5);

            g.setFont(fPersonnesButins);

            int offset = 3;
            int margin = 5;

            HashSet<Personne> personnes = new HashSet<>(train.getWagon(i).getPersonnesW());
            int nomYRoofInit = padding + 35;  // Position initiale pour les noms sur le toit
            int nomYInteriorInit = (hauteurWagon + padding) / 2 + 35;

            Predicate<Personne> isSurLeToit = Personne::isSurLeToit;
            printItemsInContainers(g, personnes, nomYRoofInit, nomYInteriorInit, X, largeurWagon, fm, isSurLeToit, offset, margin);

            ArrayList<Butin> butinsW = train.getWagon(i).getButins();
            int butinYRoofInit = padding + 3 * hauteurWagon / 8 - 10;
            int butinYInteriorInit = padding + 7 * hauteurWagon / 8 - 5;

            Predicate<Butin> isSurLeToitButin = Butin::isSurLeToit;
            printItemsInContainers(g, butinsW, butinYRoofInit, butinYInteriorInit, X, largeurWagon, fm, isSurLeToitButin, offset, margin);
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}
