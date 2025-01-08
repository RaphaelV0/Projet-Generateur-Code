package Vue;

import Controleur.ControleurDiagrammeClasse;
import Controleur.ControleurGenerationCode;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Modele.Classe;
import Modele.Relation;
import enume.Visibilite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.scene.input.MouseEvent;

public class VueDiagrammeClasse extends Application {

    private Pane workspacePane; // Zone de travail graphique
    private MenuButton classDropdown; // Liste déroulante pour les classes
    private MenuButton relationDropdown; // Liste déroulante pour les relations
    private Button generateCodeButton; // Bouton pour générer le code
    private Label notificationLabel; // Label pour afficher les notifications

    private ControleurDiagrammeClasse controleur; // Référence au contrôleur de diagramme de classe
    private ControleurGenerationCode controleurGenerationCode; // Référence au contrôleur de génération de code

    // Fichier de sauvegarde par défaut (emplacement temporaire pour la sauvegarde rapide)
    private File defaultSaveFile = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Générateur de Code UML");

        // Initialisation de l'interface
        BorderPane root = new BorderPane();

        // Initialiser les menus
        initialiserMenus(root);

        // Initialiser l'espace de travail
        workspacePane = new Pane();
        workspacePane.setStyle("-fx-background-color: white; -fx-border-color: black;");
        workspacePane.setPrefSize(600, 400);
        root.setCenter(workspacePane);

        // Initialiser le panneau inférieur
        initialiserPanneauInferieur(root);

        // Créer la scène principale
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode pour connecter les contrôleurs
    public void connecterControleur(ControleurDiagrammeClasse controleurDiagrammeClasse, ControleurGenerationCode controleurGenerationCode) {
        this.controleur = controleurDiagrammeClasse;
        this.controleurGenerationCode = controleurGenerationCode;
    }

    // Méthode pour initialiser les menus
    private void initialiserMenus(BorderPane root) {
        MenuBar menuBar = new MenuBar();

        // Menu Fichier
        Menu fichierMenu = new Menu("Fichier");
        MenuItem ouvrirItem = new MenuItem("Ouvrir...");
        MenuItem enregistrerItem = new MenuItem("Enregistrer sous...");
        MenuItem sauvegarderItem = new MenuItem("Sauvegarder");
        MenuItem quitterItem = new MenuItem("Quitter");
        fichierMenu.getItems().addAll(ouvrirItem, enregistrerItem, sauvegarderItem, new SeparatorMenuItem(), quitterItem);

        // Ajouter les menus à la barre de menu
        menuBar.getMenus().add(fichierMenu);
        root.setTop(menuBar);

        // Gestion des actions du menu Fichier
        ouvrirItem.setOnAction(e -> ouvrirFichier());
        enregistrerItem.setOnAction(e -> enregistrerSous());
        sauvegarderItem.setOnAction(e -> sauvegarder());
        quitterItem.setOnAction(e -> System.exit(0));
    }

    // Méthode pour initialiser le panneau inférieur
    private void initialiserPanneauInferieur(BorderPane root) {
        VBox bottomPanel = new VBox(10);
        bottomPanel.setPadding(new Insets(10));
        bottomPanel.setAlignment(Pos.CENTER_LEFT);

        HBox controlPanel = new HBox(20);
        controlPanel.setAlignment(Pos.CENTER_LEFT);

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
        generateCodeButton.setOnAction(e -> genererCode());

        // Ajouter les éléments au panneau de contrôle
        controlPanel.getChildren().addAll(classDropdown, relationDropdown, generateCodeButton);

        // Zone de notification
        notificationLabel = new Label("Bienvenue dans le générateur UML !");
        notificationLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");

        bottomPanel.getChildren().addAll(controlPanel, notificationLabel);
        root.setBottom(bottomPanel);

        // Actions des menus
        addClassItem.setOnAction(e -> controleur.ajouterClasseSurClic(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null)));
        modifyClassItem.setOnAction(e -> controleur.selectionnerClassePourModification(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null)));
        deleteClassItem.setOnAction(e -> controleur.supprimerClasseSurClic(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null)));

        addRelationItem.setOnAction(e -> controleur.creerRelation(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null)));
        modifyRelationItem.setOnAction(e -> controleur.selectionnerRelationPourModification(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null)));
        deleteRelationItem.setOnAction(e -> controleur.supprimerRelationSurClic(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null)));
    }

    // Méthode pour ouvrir un fichier
    private void ouvrirFichier() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir un fichier UML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers UML", "*.uml"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                String contenu = new String(Files.readAllBytes(Paths.get(file.getPath())));
                mettreAJourVue("Fichier ouvert : " + file.getName());
                // TODO: Charger les données dans le workspace
            } catch (IOException ex) {
                mettreAJourVue("Erreur lors de l'ouverture du fichier.");
                ex.printStackTrace();
            }
        }
    }

    // Méthode pour enregistrer sous un nouveau fichier
    private void enregistrerSous() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer sous");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers UML", "*.uml"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            defaultSaveFile = file;
            sauvegarder();
        }
    }

    // Méthode pour sauvegarder dans le fichier par défaut
    private void sauvegarder() {
        if (defaultSaveFile != null) {
            try {
                String contenu = "Exemple de contenu UML";
                Files.write(Paths.get(defaultSaveFile.getPath()), contenu.getBytes());
                mettreAJourVue("Fichier sauvegardé : " + defaultSaveFile.getName());
            } catch (IOException ex) {
                mettreAJourVue("Erreur lors de la sauvegarde.");
                ex.printStackTrace();
            }
        } else {
            mettreAJourVue("Aucun fichier par défaut. Utilisez 'Enregistrer sous...' pour spécifier un fichier.");
        }
    }

    // Méthode pour générer le code
    private void genererCode() {
        if (controleurGenerationCode != null) {
            String code = controleurGenerationCode.genererCode();
            afficherCode(code);
        } else {
            mettreAJourVue("Erreur : Contrôleur de génération de code non connecté.");
        }
    }

    // Méthode pour afficher le code généré
    public void afficherCode(String code) {
        TextArea codeArea = new TextArea(code);
        codeArea.setEditable(false);
        codeArea.setWrapText(true);
        codeArea.setPrefHeight(300);
        workspacePane.getChildren().clear();
        workspacePane.getChildren().add(codeArea);
    }

    // Méthode pour ajouter une classe dans la vue
    public void ajouterClasseVue(Classe classe) {
        Label classeLabel = new Label(classe.getNom());
        classeLabel.setStyle("-fx-border-color: black; -fx-padding: 5;");
        classeLabel.setLayoutX(50); // Position par défaut (modifiable)
        classeLabel.setLayoutY(50); // Position par défaut (modifiable)
        workspacePane.getChildren().add(classeLabel);
    }

    // Méthode pour mettre à jour une classe dans la vue
    public void mettreAJourClasseVue(Classe classe) {
        // Mise à jour visuelle de la classe (à implémenter selon les besoins)
        mettreAJourVue("Classe mise à jour : " + classe.getNom());
    }

    // Méthode pour supprimer une classe de la vue
    public void supprimerClasseVue(Classe classe) {
        workspacePane.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().equals(classe.getNom()));
    }

    // Méthode pour ajouter une relation dans la vue
    public void ajouterRelationVue(Relation relation) {
        Line ligneRelation = new Line(100, 100, 200, 200); // Position de départ/arrivée fictive
        workspacePane.getChildren().add(ligneRelation);
        mettreAJourVue("Relation ajoutée.");
    }

    // Méthode pour mettre à jour une relation dans la vue
    public void mettreAJourRelationVue(Relation relation) {
        // Mise à jour visuelle de la relation (à implémenter selon les besoins)
        mettreAJourVue("Relation mise à jour.");
    }

    // Méthode pour supprimer une relation de la vue
    public void supprimerRelationVue(Relation relation) {
        workspacePane.getChildren().removeIf(node -> node instanceof Line); // Suppression simplifiée
    }

    // Méthode pour mettre à jour la vue avec un message
    public void mettreAJourVue(String message) {
        notificationLabel.setText(message);
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

    // Méthodes pour obtenir les classes et relations sélectionnées (fictives pour l'exemple)
    public Classe getClasseSelectionnee() {
        // Logique pour obtenir la classe sélectionnée dans l'interface graphique
        return null; // Remplacer par la classe réelle sélectionnée
    }

    public Relation getRelationSelectionnee() {
        // Logique pour obtenir la relation sélectionnée dans l'interface graphique
        return null; // Remplacer par la relation réelle sélectionnée
    }

    public Classe getClasseDepartSelectionnee() {
        // Logique pour obtenir la classe de départ pour une relation
        return null; // Remplacer par la classe réelle sélectionnée
    }

    public Classe getClasseArriveeSelectionnee() {
        // Logique pour obtenir la classe d'arrivée pour une relation
        return null; // Remplacer par la classe réelle sélectionnée
    }
}
