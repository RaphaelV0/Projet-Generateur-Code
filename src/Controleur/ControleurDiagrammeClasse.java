package Controleur;

import Vue.VueDiagrammeClasse;
import javafx.scene.control.MenuItem;

public class ControleurDiagrammeClasse {

    private VueDiagrammeClasse vue;
    private ModeleDiagrammeClasse modele;

    // Constructeur du contrôleur
    public ControleurDiagrammeClasse(VueDiagrammeClasse vue, ModeleDiagrammeClasse modele) {
        this.vue = vue;
        this.modele = modele;

        // Associer le contrôleur à la vue
        this.vue.setControleur(this);
    }

    // Méthode pour initialiser les gestionnaires d'événements
    public void initEventHandlers() {
        // Gestion des événements pour les classes
        MenuItem addClassItem = vue.getClassDropdown().getItems().get(0); // Ajouter Classe
        MenuItem modifyClassItem = vue.getClassDropdown().getItems().get(1); // Modifier Classe
        MenuItem deleteClassItem = vue.getClassDropdown().getItems().get(2); // Supprimer Classe

        addClassItem.setOnAction(e -> handleAddClass());
        modifyClassItem.setOnAction(e -> handleModifyClass());
        deleteClassItem.setOnAction(e -> handleDeleteClass());

        // Gestion des événements pour les relations
        MenuItem addRelationItem = vue.getRelationDropdown().getItems().get(0); // Ajouter Relation
        MenuItem modifyRelationItem = vue.getRelationDropdown().getItems().get(1); // Modifier Relation
        MenuItem deleteRelationItem = vue.getRelationDropdown().getItems().get(2); // Supprimer Relation

        addRelationItem.setOnAction(e -> handleAddRelation());
        modifyRelationItem.setOnAction(e -> handleModifyRelation());
        deleteRelationItem.setOnAction(e -> handleDeleteRelation());

        // Gestion de l'événement pour générer le code
        vue.getGenerateCodeButton().setOnAction(e -> generateCode());
    }

    // Exemple de gestion des événements
    private void handleAddClass() {
        System.out.println("Ajout d'une classe.");
    }

    private void handleModifyClass() {
        System.out.println("Modification d'une classe.");
    }

    private void handleDeleteClass() {
        System.out.println("Suppression d'une classe.");
    }

    private void handleAddRelation() {
        System.out.println("Ajout d'une relation.");
    }

    private void handleModifyRelation() {
        System.out.println("Modification d'une relation.");
    }

    private void handleDeleteRelation() {
        System.out.println("Suppression d'une relation.");
    }

    private void generateCode() {
        System.out.println("Génération du code.");
    }
}
