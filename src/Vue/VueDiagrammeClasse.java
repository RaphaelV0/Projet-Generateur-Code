package Vue;

import Modele.Classe;
import Modele.Relation;
import Controleur.ControleurDiagrammeClasse;
import Controleur.ControleurRelation;
import Controleur.ControleurGenerationCode;
import enume.Visibilite;
import enume.Type;
import enume.CardinaliteEnum;
import enume.TypeRelation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VueDiagrammeClasse extends Application {

    private Pane workspacePane;  // Zone de travail graphique
    private MenuButton classDropdown;  // Liste déroulante pour les classes
    private MenuButton relationDropdown;  // Liste déroulante pour les relations
    private Button generateCodeButton;  // Bouton pour générer le code
    private Label notificationLabel;  // Label pour afficher les notifications

    private ControleurDiagrammeClasse controleurDiagrammeClasse;  // Contrôleur des classes
    private ControleurRelation controleurRelation;  // Contrôleur des relations
    private ControleurGenerationCode controleurGenerationCode;  // Contrôleur de génération de code

    private Classe classeSelectionnee;  // Classe actuellement sélectionnée

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Générateur de Code JAVA");

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
    public void connecterControleurs(ControleurDiagrammeClasse controleurDiagrammeClasse,
                                     ControleurRelation controleurRelation,
                                     ControleurGenerationCode controleurGenerationCode) {
        this.controleurDiagrammeClasse = controleurDiagrammeClasse;
        this.controleurRelation = controleurRelation;
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
        generateCodeButton.setOnAction(e -> controleurGenerationCode.genererCode());

        // Ajouter les éléments au panneau de contrôle
        controlPanel.getChildren().addAll(classDropdown, relationDropdown, generateCodeButton);

        // Zone de notification
        notificationLabel = new Label("Bienvenue dans le générateur UML !");
        notificationLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");

        bottomPanel.getChildren().addAll(controlPanel, notificationLabel);
        root.setBottom(bottomPanel);

        // Actions des menus
        addClassItem.setOnAction(e -> workspacePane.setOnMouseClicked(event -> controleurDiagrammeClasse.ajouterClasseSurClic(event)));
        modifyClassItem.setOnAction(e -> workspacePane.setOnMouseClicked(event -> controleurDiagrammeClasse.selectionnerClassePourModification(event)));
        deleteClassItem.setOnAction(e -> workspacePane.setOnMouseClicked(event -> controleurDiagrammeClasse.supprimerClasseSurClic(event)));

        // Actions des menus Relation
        addRelationItem.setOnAction(e -> {
            if (classeSelectionnee != null) {
                controleurRelation.selectionnerClassePourRelation(classeSelectionnee); // Action pour choisir la classe liée
            } else {
                mettreAJourVue("Veuillez sélectionner une classe avant d'ajouter une relation.");
            }
        });
        modifyRelationItem.setOnAction(e -> {
            Relation relationSelectionnee = controleurRelation.obtenirRelationSelectionnee();
            if (relationSelectionnee != null) {
                controleurRelation.modifierRelation(relationSelectionnee, TypeRelation.HERITAGE, CardinaliteEnum.UN);
            } else {
                mettreAJourVue("Aucune relation sélectionnée pour modification.");
            }
        });
        deleteRelationItem.setOnAction(e -> {
            Relation relationSelectionnee = controleurRelation.obtenirRelationSelectionnee();
            if (relationSelectionnee != null) {
                controleurRelation.supprimerRelation(relationSelectionnee);
            } else {
                mettreAJourVue("Aucune relation sélectionnée pour suppression.");
            }
        });
    }

    // Méthode pour ajouter une classe dans la vue
    public void ajouterClasseVue(Classe classe) {
        Text textClasse = new Text(classe.getX(), classe.getY(), classe.getNom());
        textClasse.setOnMouseClicked(event -> {
            classeSelectionnee = classe;
            textClasse.setStyle("-fx-font-weight: bold; -fx-fill: blue;");
            mettreAJourVue("Classe sélectionnée: " + classe.getNom());
        });
        workspacePane.getChildren().add(textClasse);
    }

    // Méthode pour ajouter une relation dans la vue
    public void ajouterRelationVue(Relation relation) {
        double x1 = relation.getClasse1().getX();
        double y1 = relation.getClasse1().getY();
        double x2 = relation.getClasse2().getX();
        double y2 = relation.getClasse2().getY();

        Line ligneRelation = new Line(x1, y1, x2, y2);
        ligneRelation.setStrokeWidth(2);

        // Notifier le contrôleur lorsqu'une relation est sélectionnée
        ligneRelation.setOnMouseClicked(event -> {
            controleurRelation.selectionnerRelation(relation); // Appel au contrôleur pour sélectionner la relation
            mettreAJourVue("Relation sélectionnée entre " + relation.getClasse1().getNom() + " et " + relation.getClasse2().getNom());
        });

        workspacePane.getChildren().addAll(ligneRelation, new Text((x1 + x2) / 2, (y1 + y2) / 2, relation.getTypeRelation().getLabel() + " (" + relation.getCardinaliteCible().getLabel() + ")"));
    }
    // Méthode pour afficher un dialogue pour choisir la relation
    public void afficherDialogueRelation(Classe classe1, Classe classe2) {
        // Créer un dialogue pour choisir le type de relation et la cardinalité
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Sélection de la relation");

        // Créer les boutons radio pour le type de relation
        ToggleGroup typeRelationGroup = new ToggleGroup();
        RadioButton inheritanceRadioButton = new RadioButton("Héritage");
        inheritanceRadioButton.setToggleGroup(typeRelationGroup);
        RadioButton associationRadioButton = new RadioButton("Association");
        associationRadioButton.setToggleGroup(typeRelationGroup);

        // Créer un combo box pour choisir la cardinalité
        ComboBox<CardinaliteEnum> cardinaliteComboBox = new ComboBox<>();
        cardinaliteComboBox.getItems().setAll(CardinaliteEnum.values());

        // Ajouter les éléments au dialogue
        VBox vbox = new VBox(10, inheritanceRadioButton, associationRadioButton, cardinaliteComboBox);
        vbox.setStyle("-fx-padding: 10;");
        dialog.getDialogPane().setContent(vbox);

        // Ajouter les boutons de confirmation
        ButtonType confirmButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        // Gérer la confirmation du choix
        dialog.setResultConverter(button -> {
            if (button == confirmButtonType) {
                TypeRelation selectedTypeRelation = inheritanceRadioButton.isSelected() ? TypeRelation.HERITAGE : TypeRelation.ASSOCIATION;
                CardinaliteEnum selectedCardinalite = cardinaliteComboBox.getValue();
                // Appeler le contrôleur pour créer la relation
                controleurRelation.creerRelation(selectedTypeRelation, selectedCardinalite);
            }
            return null;
        });

        dialog.showAndWait();
    }

    public Classe getClasseSelectionnee() {
        return classeSelectionnee;  // La variable classeSelectionnee est déjà définie dans ta classe
    }
    public void supprimerClasseVue(Classe classe) {
        // Trouver le Text représentant la classe et le retirer
        workspacePane.getChildren().removeIf(node -> node instanceof Text && ((Text) node).getText().equals(classe.getNom()));
        mettreAJourVue("Classe supprimée : " + classe.getNom());
    }
    // Méthode pour supprimer une relation dans la vue
    public void supprimerRelation(Relation relation) {
        double x1 = relation.getClasse1().getX();
        double y1 = relation.getClasse1().getY();
        double x2 = relation.getClasse2().getX();
        double y2 = relation.getClasse2().getY();

        // Trouver et supprimer la ligne représentant la relation
        workspacePane.getChildren().removeIf(node -> node instanceof Line &&
                ((Line) node).getStartX() == x1 &&
                ((Line) node).getStartY() == y1 &&
                ((Line) node).getEndX() == x2 &&
                ((Line) node).getEndY() == y2);

        // Vous pouvez aussi supprimer le texte de la relation si vous en affichez un
        workspacePane.getChildren().removeIf(node -> node instanceof Text &&
                ((Text) node).getText().contains(relation.getTypeRelation().getLabel()));

        mettreAJourVue("Relation supprimée entre " + relation.getClasse1().getNom() + " et " + relation.getClasse2().getNom());
    }

    // Méthode pour mettre à jour la vue avec un message
    public void mettreAJourVue(String message) {
        notificationLabel.setText(message);
    }

    // Placeholders pour les opérations de fichier
    private void ouvrirFichier() {
        mettreAJourVue("Fonction d'ouverture de fichier non implémentée.");
    }

    private void enregistrerSous() {
        mettreAJourVue("Fonction d'enregistrement non implémentée.");
    }

    private void sauvegarder() {
        mettreAJourVue("Fonction de sauvegarde non implémentée.");
    }
    public Scene createScene() {
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

        return new Scene(root, 800, 600); // Création de la scène avec la taille et le contenu de la fenêtre
    }
}
