import Controleur.ControleurDiagrammeClasse;
import Vue.VueDiagrammeClasse;

public class Main {
    public static void main(String[] args) {
        // Initialiser le modèle
        ModeleDiagrammeClasse modele = new ModeleDiagrammeClasse();

        // Initialiser la vue
        VueDiagrammeClasse vue = new VueDiagrammeClasse();

        // Initialiser le contrôleur
        ControleurDiagrammeClasse controleur = new ControleurDiagrammeClasse(vue, modele);

        // Lancer l'application
        VueDiagrammeClasse.launch(VueDiagrammeClasse.class, args);
    }
}
