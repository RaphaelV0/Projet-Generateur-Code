package Controleur;

import Modele.Classe;
import Modele.Attribut;
import Modele.Methode;
import Modele.Relation;
import enume.Visibilite;
import Vue.VueDiagrammeClasse;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControleurClasse {

    private VueDiagrammeClasse vue;
    private List<Classe> classes; // Liste des classes dans le modèle

    public ControleurClasse(VueDiagrammeClasse vue) {
        this.vue = vue;
        this.classes = new ArrayList<>();
    }

    // Ajouter une classe
    public void ajouterClasse() {
        TextInputDialog nomDialog = new TextInputDialog();
        nomDialog.setTitle("Ajouter une classe");
        nomDialog.setHeaderText("Nouvelle classe");
        nomDialog.setContentText("Nom de la classe :");

        Optional<String> nomResult = nomDialog.showAndWait();
        if (nomResult.isEmpty() || nomResult.get().trim().isEmpty()) {
            afficherErreur("Le nom de la classe ne peut pas être vide.");
            return;
        }

        ChoiceDialog<Visibilite> visibiliteDialog = new ChoiceDialog<>(Visibilite.PUBLIC, Visibilite.values());
        visibiliteDialog.setTitle("Visibilité de la classe");
        visibiliteDialog.setHeaderText("Choisissez la visibilité de la classe");
        visibiliteDialog.setContentText("Visibilité :");

        Optional<Visibilite> visibiliteResult = visibiliteDialog.showAndWait();
        if (visibiliteResult.isEmpty()) {
            afficherErreur("Vous devez choisir une visibilité pour la classe.");
            return;
        }

        Classe nouvelleClasse = new Classe(nomResult.get(), visibiliteResult.get());
        classes.add(nouvelleClasse); // Ajoute la classe au modèle
        vue.mettreAJourVue("Classe ajoutée : " + nouvelleClasse.getNom());
    }

    // Modifier une classe
    public void modifierClasse() {
        if (classes.isEmpty()) {
            afficherErreur("Aucune classe disponible à modifier.");
            return;
        }

        ChoiceDialog<Classe> choixClasseDialog = new ChoiceDialog<>(classes.get(0), classes);
        choixClasseDialog.setTitle("Modifier une classe");
        choixClasseDialog.setHeaderText("Choisissez une classe à modifier");
        choixClasseDialog.setContentText("Classe :");

        Optional<Classe> classeResult = choixClasseDialog.showAndWait();
        if (classeResult.isEmpty()) {
            return;
        }

        Classe classe = classeResult.get();

        TextInputDialog modifierNomDialog = new TextInputDialog(classe.getNom());
        modifierNomDialog.setTitle("Modifier une classe");
        modifierNomDialog.setHeaderText("Modifier le nom de la classe");
        modifierNomDialog.setContentText("Nom :");

        Optional<String> nomResult = modifierNomDialog.showAndWait();
        nomResult.ifPresent(classe::setNom);

        ChoiceDialog<Visibilite> modifierVisibiliteDialog = new ChoiceDialog<>(classe.getVisibilite(), Visibilite.values());
        modifierVisibiliteDialog.setTitle("Modifier la visibilité");
        modifierVisibiliteDialog.setHeaderText("Choisissez une nouvelle visibilité");
        modifierVisibiliteDialog.setContentText("Visibilité :");

        Optional<Visibilite> visibiliteResult = modifierVisibiliteDialog.showAndWait();
        visibiliteResult.ifPresent(classe::setVisibilite);

        vue.mettreAJourVue("Classe modifiée : " + classe.getNom());
    }

    // Supprimer une classe
    public void supprimerClasse() {
        if (classes.isEmpty()) {
            afficherErreur("Aucune classe disponible à supprimer.");
            return;
        }

        ChoiceDialog<Classe> choixClasseDialog = new ChoiceDialog<>(classes.get(0), classes);
        choixClasseDialog.setTitle("Supprimer une classe");
        choixClasseDialog.setHeaderText("Choisissez une classe à supprimer");
        choixClasseDialog.setContentText("Classe :");

        Optional<Classe> classeResult = choixClasseDialog.showAndWait();
        classeResult.ifPresent(classe -> {
            classes.remove(classe);
            vue.mettreAJourVue("Classe supprimée : " + classe.getNom());
        });
    }

    // Ajouter un attribut à une classe
    public void ajouterAttribut(Classe classe) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter un attribut");
        dialog.setHeaderText("Nouvel attribut");
        dialog.setContentText("Nom de l'attribut :");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nomAttribut -> {
            if (!nomAttribut.trim().isEmpty()) {
                // TODO : Ajouter le type de l'attribut
                Attribut nouvelAttribut = new Attribut(nomAttribut, null);
                classe.addAttribut(nouvelAttribut);
                vue.mettreAJourVue("Attribut ajouté à " + classe.getNom() + " : " + nomAttribut);
            } else {
                afficherErreur("Le nom de l'attribut ne peut pas être vide.");
            }
        });
    }

    // Afficher une erreur
    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
