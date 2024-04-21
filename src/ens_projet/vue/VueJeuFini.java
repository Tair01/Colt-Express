package ens_projet.vue;

import javax.swing.*;
import java.awt.*;

public class VueJeuFini {
    private final JFrame frame;

    public VueJeuFini(Vue v, String nomGagnant) {
        frame = v.frame;
        frame.getContentPane().removeAll();
        JLabel label = new JLabel("Partie finie ! Gagnant : " + nomGagnant);
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

}
