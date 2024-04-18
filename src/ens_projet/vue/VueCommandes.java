package ens_projet.vue;
import ens_projet.controleur.Bouton;
import ens_projet.modele.Bandit;
import ens_projet.modele.Modele;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VueCommandes extends JPanel implements Observer {
    private Modele m;
    private Bouton[] banditDeplacer;
    private Bouton banditBraquer, banditTirer;
    private JTextField textField;
    private JLabel banditChoisi, nbActionDeBanditCh, montantTotalDeButins;
    public VueCommandes(Modele m){
        this.m = m;
        m.addObserver(this);
        setPreferredSize(new Dimension(1000, 200));
        setLayout(null);
        initBoutons();
        initTextField();
        initLabel();

        Bandit banditChoisi = m.getTrain().getBanditChoisi();
        if (banditChoisi != null) {
            banditChoisi.addObserver(() -> {
                changementInfo(banditChoisi.getNbActions(), banditChoisi.montantT());
            });
        }
    }
    public void initTextField() {
        textField = new JTextField();
        textField.setBounds(10, 10, 150, 30);
        textField.setPreferredSize(new Dimension(150,30));
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBandit = textField.getText();
                Bandit bandit = m.getTrain().choisirPersonne(nomBandit);
                if(bandit != null){
                    banditChoisi.setText("Bandit choisi: " + nomBandit);
                    nbActionDeBanditCh.setText("Nombre d'actions : " + bandit.getNbActions());
                    montantTotalDeButins.setText("Montant total : " + bandit.montantT() + "$");
                    if(bandit.getNbActions() == 0){
                        JOptionPane.showMessageDialog(VueCommandes.this, "Veuillez choisir un autre Bandit!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        banditChoisi.setText("Entrez le nom d'un nouveau Bandit");
                    }
                }else{
                    JOptionPane.showMessageDialog(VueCommandes.this, "Aucun bandit trouvé avec ce nom", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        add(textField);
    }

    public void initLabel() {
        banditChoisi = new JLabel("Bandit choisi: ");
        banditChoisi.setBounds(10, 40, 150, 30);
        nbActionDeBanditCh = new JLabel("Nombre d'actions : ");
        nbActionDeBanditCh.setBounds(10, 60,150,30);
        montantTotalDeButins = new JLabel("Montant total: ");
        montantTotalDeButins.setBounds(10,80,150,30);
        add(banditChoisi);
        add(nbActionDeBanditCh);
        add(montantTotalDeButins);
    }
    public void changementInfo(int nbActions, int total) {
        nbActionDeBanditCh.setText("Nombre d'actions : " + nbActions);
        montantTotalDeButins.setText("Montant total: " + total );
    }
    public void initBoutons(){
        //setLayout(null);
        int largeurBouton = 50;
        int hauteurBouton = 50;

        // Positions et dimensions des boutons de déplacement
        banditDeplacer = new Bouton[4];
        banditDeplacer[0] = new Bouton("→", 580, 80, largeurBouton, hauteurBouton);
        banditDeplacer[1] = new Bouton("←", 420, 80, largeurBouton, hauteurBouton);
        banditDeplacer[2] = new Bouton("↓", 500, 150, largeurBouton, hauteurBouton);
        banditDeplacer[3] = new Bouton("↑", 500, 10, largeurBouton, hauteurBouton);

        // Haut: 500, 10
        // Bas: 500, 150
        // Gauche: 420, 80
        // Droite: 570, 80
        for(Bouton b: banditDeplacer){
            this.add(b);
        }

        // Position et dimension des boutons "Braquer" et "Tirer"
        banditBraquer = new Bouton("Braquer", 700, 10, largeurBouton * 2, hauteurBouton);
        banditTirer = new Bouton("Tirer", 700, 70, largeurBouton * 2, hauteurBouton);

        this.add(banditBraquer);
        this.add(banditTirer);
    }
    @Override
    public void paintComponent(Graphics g){super.paintComponent(g);}
    @Override
    public void update(){this.repaint();}
    public Bouton[] getBanditDeplacer() {
        return banditDeplacer;
    }
    public Bouton getBanditBraquer() {
        return banditBraquer;
    }
    public Bouton getBanditTirer() {
        return banditTirer;
    }
    public JTextField getTextField(){return textField;}
}
