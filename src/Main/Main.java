package Main;

import Vue.VueDiagrammeClasse;
import Controleur.ControleurClasse;
import Controleur.ControleurFichier;
import Controleur.ControleurRelation;
import Controleur.ControleurGenerationCode;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        // Lancer l'application JavaFX
        Application.launch(VueDiagrammeClasse.class, args);
    }
}
