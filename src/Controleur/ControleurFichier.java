package Controleur;

import Vue.VueDiagrammeClasse;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ControleurFichier {

    private VueDiagrammeClasse vue;

    public ControleurFichier(VueDiagrammeClasse vue) {
        this.vue = vue;
    }

    // Ouvrir un fichier UML
    public void ouvrirFichier(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers UML", "*.uml"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                String contenu = new String(Files.readAllBytes(Paths.get(file.getPath())));
                System.out.println("Fichier ouvert :\n" + contenu);
                // TODO : Charger les classes et relations depuis le fichier
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Sauvegarder un fichier UML
    public void sauvegarder(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers UML", "*.uml"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                String contenu = "Exemple de contenu UML"; // Remplacer par les données réelles
                Files.write(Paths.get(file.getPath()), contenu.getBytes());
                System.out.println("Fichier sauvegardé : " + file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
