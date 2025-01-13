package Main;

import Vue.VueDiagrammeClasse;
import Controleur.ControleurDiagrammeClasse;
import Controleur.ControleurRelation;
import Controleur.ControleurGenerationCode;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        // Lance l'application JavaFX
        Application.launch(App.class, args);
    }

    // Classe JavaFX qui sert de point d'entrée
    public static class App extends Application {

        @Override
        public void start(Stage primaryStage) {
            // Création de la vue
            VueDiagrammeClasse vue = new VueDiagrammeClasse();

            // Création des contrôleurs
            ControleurRelation controleurRelation = new ControleurRelation(vue, null);
            ControleurDiagrammeClasse controleurDiagrammeClasse = new ControleurDiagrammeClasse(vue, controleurRelation);
            ControleurGenerationCode controleurGenerationCode = new ControleurGenerationCode(vue, controleurDiagrammeClasse, controleurRelation);

            // Connecter les contrôleurs à la vue
            vue.connecterControleurs(controleurDiagrammeClasse, controleurRelation, controleurGenerationCode);

            // Initialisation de la fenêtre principale
            primaryStage.setTitle("Générateur de Code JAVA");
            primaryStage.setScene(vue.createScene());
            primaryStage.show();
        }
    }
}
