package ens_projet.vue;

import ens_projet.controleur.Bouton;
import ens_projet.modele.Bandit;
import ens_projet.modele.Modele;

import javax.swing.*;
import java.awt.*;


// VueCommande est une vue indépendante qui initie des processus
// grâce à des entrées utilisateur (texte, souris). Par conséquent, elle n'a pas besoin
// d'implémenter l'interface Observer

public class VueCommandes extends JPanel {
    private final Modele m;
    private Bouton[] banditDeplacer;
    private Bouton banditBraquer, banditTirer;
    private JTextField textField;
    private final JLabel choisirBandit;
    private final JLabel nbActionsBandit;
    private final JLabel montantButin;

    public VueCommandes(Modele m) {
        this.m = m;
        setPreferredSize(new Dimension(1000, 250));
        setLayout(null);
        initTextField();
        choisirBandit = new JLabel("Bandit choisi :");
        choisirBandit.setBounds(10, 40, 150, 30);
        nbActionsBandit = new JLabel("Nombre d'actions :");
        nbActionsBandit.setBounds(10, 60, 150, 30);
        montantButin = new JLabel("Montant total :");
        montantButin.setBounds(10, 80, 150, 30);
        add(choisirBandit);
        add(nbActionsBandit);
        add(montantButin);
        initBoutons();
    }

    public void initTextField() {
        textField = new JTextField();
        textField.setBounds(10, 10, 150, 30);
        textField.setPreferredSize(new Dimension(150, 30));
        textField.addActionListener(e -> {
            String nomBandit = textField.getText();
            Bandit bandit = m.getTrain().choisirBandit(nomBandit);
            if (bandit != null) {
                choisirBandit.setText("Bandit choisi : " + nomBandit);
                nbActionsBandit.setText("Nombre d'actions : " + bandit.getNbActions());
                montantButin.setText("Montant total : "
                        + bandit.montantT() + " $");
                if (bandit.getNbActions() == 0) {
                    JOptionPane.showMessageDialog(this, "Veuillez choisir un autre bandit !", "Information", JOptionPane.INFORMATION_MESSAGE);
                    choisirBandit.setText("Entrez le nom d'un autre bandit : ");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aucun bandit trouvé avec ce nom", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });
        add(textField);
        //return bandit;
    }

    public void initBoutons() {
        //setLayout(null);
        int size = 65;


        // ce carré central permet de positionner automatiquement les six boutons
        // en effet, la forme groupée de des boutons de déplacement forme un carré au milieu, donc pour les positionner, nous pouvons l'exploiter
        // et nous pouvons nous servir de cela pour positionner également les boutons d'action
        // size, X, Y
        int[] centralSquare = {75, 275, 87};

        // Positions et dimensions des boutons de déplacement

        banditDeplacer = new Bouton[4];
        banditDeplacer[0] = new Bouton("→", centralSquare[1] + centralSquare[0], centralSquare[2], size, size);
        banditDeplacer[1] = new Bouton("←", centralSquare[1] - centralSquare[0], centralSquare[2], size, size);
        banditDeplacer[2] = new Bouton("↓", centralSquare[1], centralSquare[2] + centralSquare[0], size, size);
        banditDeplacer[3] = new Bouton("↑", centralSquare[1], centralSquare[2] - centralSquare[0], size, size);

        for (Bouton b : banditDeplacer) {
            this.add(b);
        }

        // Position et dimension des boutons "Braquer" et "Tirer"
        banditBraquer = new Bouton("Braquer", centralSquare[1] + centralSquare[0] + 2 * size + 30, centralSquare[2] - size/2, size * 3, size * 2);
        banditTirer = new Bouton("Tirer", centralSquare[1] + centralSquare[0] + 5 * size + 60, centralSquare[2] - size/2, size * 3, size * 2);

        this.add(banditBraquer);
        this.add(banditTirer);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Bouton[] getBanditDeplacer() {
        return banditDeplacer;
    }

    public Bouton getBanditBraquer() {
        return banditBraquer;
    }

    public Bouton getBanditTirer() {
        return banditTirer;
    }

    public JTextField getTextField() {
        return textField;
    }

}
