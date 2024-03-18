public class Bandit extends Personne {
    public Bandit(String n, boolean s) {
        super(n, s);
    }

    @Override
    void effectuerAction(Action a, Direction direction) {
        Personne personne = a.getPersonne();
        if (personne instanceof Bandit) {
            Bandit bandit = (Bandit) personne;
            if (a instanceof Deplacer) {
                Deplacer d = (Deplacer) a;
                if (bandit.getPosition() != null) {
                    d.executer();
                }
            } else if (a instanceof Tirer) {
                Tirer t = (Tirer) a;
                t.executer();
            } else if (a instanceof Braquer) {
                Braquer b = (Braquer) a;
                b.executer();
            } else {
                throw new IllegalArgumentException("Action non prise en charge pour le bandit.");
            }
        } else {
            throw new IllegalArgumentException("Action associée à une personne invalide.");
        }
    }
}
