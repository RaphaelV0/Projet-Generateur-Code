package Vue;

import Controleur.ControleurDiagrammeClasse;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VueDiagrammeClasse extends Application {

    private Pane workspacePane; // Zone de travail graphique
    private MenuButton classDropdown; // Liste déroulante pour les classes
    private MenuButton relationDropdown; // Liste déroulante pour les relations
    private Button generateCodeButton; // Bouton pour générer le code

    private ControleurDiagrammeClasse controleur; // Référence au contrôleur

    // Fichier de sauvegarde par défaut (emplacement temporaire pour la sauvegarde rapide)
    private File defaultSaveFile = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Générateur de Code");

        // Initialisation de l'interface
        BorderPane root = new BorderPane();

        // Barre de menu
        MenuBar menuBar = new MenuBar();

        // Menu Fichier
        Menu fichierMenu = new Menu("Fichier");
        MenuItem ouvrirItem = new MenuItem("Ouvrir...");
        MenuItem enregistrerItem = new MenuItem("Enregistrer sous...");
        MenuItem sauvegarderItem = new MenuItem("Sauvegarder");
        MenuItem quitterItem = new MenuItem("Quitter");
        fichierMenu.getItems().addAll(ouvrirItem, enregistrerItem, sauvegarderItem, new SeparatorMenuItem(), quitterItem);

        // Menu Édition
        Menu editionMenu = new Menu("Édition");
        MenuItem annulerItem = new MenuItem("Annuler");
        MenuItem copierItem = new MenuItem("Copier");
        MenuItem collerItem = new MenuItem("Coller");
        editionMenu.getItems().addAll(annulerItem, copierItem, collerItem);

        // Menu Aide
        Menu aideMenu = new Menu("Aide");
        MenuItem aProposItem = new MenuItem("À propos");
        MenuItem aideItem = new MenuItem("Aide");
        aideMenu.getItems().addAll(aideItem, aProposItem);

        // Ajouter les menus à la barre de menu
        menuBar.getMenus().addAll(fichierMenu, editionMenu, aideMenu);
        root.setTop(menuBar);

        // Espace de travail
        workspacePane = new Pane();
        workspacePane.setStyle("-fx-background-color: white; -fx-border-color: black;");
        workspacePane.setPrefSize(600, 400);
        root.setCenter(workspacePane);

        // Panneau inférieur
        HBox bottomPanel = new HBox(20);
        bottomPanel.setPadding(new Insets(10));
        bottomPanel.setAlignment(Pos.CENTER_LEFT);

        // Liste déroulante pour les classes
        classDropdown = new MenuButton("Classe");
        MenuItem addClassItem = new MenuItem("Ajouter Classe");
        MenuItem modifyClassItem = new MenuItem("Modifier Classe");
        MenuItem deleteClassItem = new MenuItem("Supprimer Classe");
        classDropdown.getItems().addAll(addClassItem, modifyClassItem, deleteClassItem);

        // Liste déroulante pour les relations
        relationDropdown = new MenuButton("Relation");
        MenuItem addRelationItem = new MenuItem("Ajouter Relation");
        MenuItem modifyRelationItem = new MenuItem("Modifier Relation");
        MenuItem deleteRelationItem = new MenuItem("Supprimer Relation");
        relationDropdown.getItems().addAll(addRelationItem, modifyRelationItem, deleteRelationItem);

        // Bouton "Générer le Code"
        generateCodeButton = new Button("Générer le Code");

        // Ajouter les éléments au panneau inférieur
        bottomPanel.getChildren().addAll(classDropdown, relationDropdown, generateCodeButton);
        root.setBottom(bottomPanel);

        // Créer la scène principale
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Gestion des actions du menu Fichier
        ouvrirItem.setOnAction(e -> ouvrirFichier(primaryStage));
        enregistrerItem.setOnAction(e -> enregistrerSous(primaryStage));
        sauvegarderItem.setOnAction(e -> sauvegarder());
        quitterItem.setOnAction(e -> quitter(primaryStage));

        // Gestion des actions du menu Édition (exemple)
        annulerItem.setOnAction(e -> System.out.println("Action annulée."));
        copierItem.setOnAction(e -> System.out.println("Contenu copié."));
        collerItem.setOnAction(e -> System.out.println("Contenu collé."));

        // Gestion des actions du menu Aide
        aProposItem.setOnAction(e -> afficherAPropos());
        aideItem.setOnAction(e -> afficherAide());
    }

    // Méthode pour connecter le contrôleur
    public void setControleur(ControleurDiagrammeClasse controleur) {
        this.controleur = controleur;
    }

    // Méthodes associées aux options du menu Fichier
    private void ouvrirFichier(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir un fichier UML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers UML", "*.uml"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                // Lire le contenu du fichier
                String contenu = new String(Files.readAllBytes(Paths.get(file.getPath())));
                System.out.println("Fichier ouvert :\n" + contenu);

                // TODO : Notifier le contrôleur pour charger les données
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void enregistrerSous(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer sous");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers UML", "*.uml"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                // Exemple de contenu à sauvegarder (remplacer par l'export réel)
                String contenu = "Exemple de contenu UML";

                Files.write(Paths.get(file.getPath()), contenu.getBytes());
                defaultSaveFile = file; // Mettre à jour le fichier de sauvegarde par défaut
                System.out.println("Fichier enregistré : " + file.getPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void sauvegarder() {
        if (defaultSaveFile != null) {
            try {
                // Exemple de contenu à sauvegarder (remplacer par l'export réel)
                String contenu = "Exemple de contenu UML";

                Files.write(Paths.get(defaultSaveFile.getPath()), contenu.getBytes());
                System.out.println("Fichier sauvegardé : " + defaultSaveFile.getPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Aucun fichier de sauvegarde par défaut. Utilisez 'Enregistrer sous...'");
        }
    }

    private void quitter(Stage stage) {
        stage.close();
        System.out.println("Application quittée.");
    }

    // Méthodes pour afficher les dialogues Aide et À propos
    private void afficherAide() {
        System.out.println("Afficher l'aide - À compléter.");
    }

    private void afficherAPropos() {
        System.out.println("À propos : Générateur UML v1.0. Par [Votre Nom].");
    }

    // Getters pour les éléments de la vue
    public Pane getWorkspacePane() {
        return workspacePane;
    }

    public MenuButton getClassDropdown() {
        return classDropdown;
    }

    public MenuButton getRelationDropdown() {
        return relationDropdown;
    }

    public Button getGenerateCodeButton() {
        return generateCodeButton;
    }
}
