package Controleur;

import Modele.Classe;
import Modele.Attribut;
import Modele.Methode;
import Modele.Relation;
import Vue.VueDiagrammeClasse;
import enume.CardinaliteEnum;
import enume.TypeRelation;
import enume.Visibilite;
import enume.Type;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ChoiceDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControleurDiagrammeClasse {

    private final VueDiagrammeClasse vue;
    private final ControleurRelation controleurRelation;
    private final List<Classe> classes;

    public ControleurDiagrammeClasse(VueDiagrammeClasse vue, ControleurRelation controleurRelation) {
        this.vue = vue;
        this.controleurRelation = controleurRelation;
        this.classes = new ArrayList<>();
    }

    // Ajouter une classe sur clic
    public void ajouterClasseSurClic(MouseEvent event) {
        Classe nouvelleClasse = new Classe("NouvelleClasse", event.getX(), event.getY());
        classes.add(nouvelleClasse);
        vue.ajouterClasseVue(nouvelleClasse);
        vue.mettreAJourVue("Classe ajoutée à la position (" + event.getX() + ", " + event.getY() + ")");
    }

    // Sélectionner une classe pour modification
    public void selectionnerClassePourModification(MouseEvent event) {
        Classe classeSelectionnee = vue.getClasseSelectionnee();
        if (classeSelectionnee != null) {
            // Demander le nouveau nom de la classe
            TextInputDialog dialog = new TextInputDialog(classeSelectionnee.getNom());
            dialog.setTitle("Modifier Classe");
            dialog.setHeaderText("Modifier la classe : " + classeSelectionnee.getNom());
            dialog.setContentText("Nom de la classe :");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(nouveauNom -> classeSelectionnee.setNom(nouveauNom));

            // Ajouter ou modifier les attributs
            ajouterOuModifierAttributs(classeSelectionnee);

            // Ajouter ou modifier les méthodes
            ajouterOuModifierMethodes(classeSelectionnee);

            vue.mettreAJourVue("Classe modifiée : " + classeSelectionnee.getNom());
        } else {
            vue.mettreAJourVue("Aucune classe sélectionnée.");
        }
    }



    // Méthode pour ajouter ou modifier des attributs
    private void ajouterOuModifierAttributs(Classe classe) {
        boolean ajouterAttributs = true;

        while (ajouterAttributs) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Ajouter/Modifier Attribut");
            dialog.setHeaderText("Ajouter ou modifier un attribut pour la classe : " + classe.getNom());
            dialog.setContentText("Nom de l'attribut :");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && !result.get().trim().isEmpty()) {
                String nomAttribut = result.get().trim();

                // Choisir le type de l'attribut via un ChoiceDialog pour l'énuméré Type
                ChoiceDialog<Type> typeDialog = new ChoiceDialog<>(Type.STRING, Type.values()); // Utilisation de Type au lieu de TypeEnum
                typeDialog.setTitle("Type de l'Attribut");
                typeDialog.setHeaderText("Type pour l'attribut : " + nomAttribut);
                typeDialog.setContentText("Choisissez le type de l'attribut :");

                Optional<Type> typeResult = typeDialog.showAndWait();
                Type typeAttribut = typeResult.orElse(Type.STRING); // Valeur par défaut si l'utilisateur annule

                // Choisir la visibilité de l'attribut
                ChoiceDialog<Visibilite> visibilityDialog = new ChoiceDialog<>(Visibilite.PRIVATE, Visibilite.values());
                visibilityDialog.setTitle("Visibilité de l'Attribut");
                visibilityDialog.setHeaderText("Visibilité pour l'attribut : " + nomAttribut);
                visibilityDialog.setContentText("Choisissez la visibilité :");

                Optional<Visibilite> visibilityResult = visibilityDialog.showAndWait();
                Visibilite visibiliteAttribut = visibilityResult.orElse(Visibilite.PRIVATE);

                Attribut nouvelAttribut = new Attribut(nomAttribut, typeAttribut, visibiliteAttribut);
                classe.ajouterAttribut(nouvelAttribut);
                vue.mettreAJourVue("Attribut ajouté : " + nomAttribut + " (" + typeAttribut + ", " + visibiliteAttribut + ")");
            } else {
                ajouterAttributs = false; // Arrêter l'ajout si aucun nom n'est saisi
            }
        }
    }

    // Méthode pour ajouter ou modifier des méthodes
    private void ajouterOuModifierMethodes(Classe classe) {
        boolean ajouterMethodes = true;

        while (ajouterMethodes) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Ajouter/Modifier Méthode");
            dialog.setHeaderText("Ajouter ou modifier une méthode pour la classe : " + classe.getNom());
            dialog.setContentText("Nom de la méthode :");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && !result.get().trim().isEmpty()) {
                String nomMethode = result.get().trim();

                // Choisir le type de retour de la méthode via un ChoiceDialog pour l'énuméré Type
                ChoiceDialog<Type> retourDialog = new ChoiceDialog<>(Type.VOID, Type.values()); // Utilisation de Type pour le type de retour
                retourDialog.setTitle("Type de Retour");
                retourDialog.setHeaderText("Type de retour pour la méthode : " + nomMethode);
                retourDialog.setContentText("Choisissez le type de retour :");

                Optional<Type> retourResult = retourDialog.showAndWait();
                Type typeRetour = retourResult.orElse(Type.VOID); // Valeur par défaut si l'utilisateur annule

                // Choisir la visibilité de la méthode
                ChoiceDialog<Visibilite> visibilityDialog = new ChoiceDialog<>(Visibilite.PUBLIC, Visibilite.values());
                visibilityDialog.setTitle("Visibilité de la Méthode");
                visibilityDialog.setHeaderText("Visibilité pour la méthode : " + nomMethode);
                visibilityDialog.setContentText("Choisissez la visibilité :");

                Optional<Visibilite> visibilityResult = visibilityDialog.showAndWait();
                Visibilite visibiliteMethode = visibilityResult.orElse(Visibilite.PUBLIC);
                // Création de la liste des paramètres pour la méthode (vide par défaut ici)
                List<Attribut> params = new ArrayList<>();

                Methode nouvelleMethode = new Methode(nomMethode, typeRetour.name(), params , visibiliteMethode);
                classe.ajouterMethode(nouvelleMethode);
                vue.mettreAJourVue("Méthode ajoutée : " + nomMethode + " (" + typeRetour + ", " + visibiliteMethode + ")");
            } else {
                ajouterMethodes = false; // Arrêter l'ajout si aucun nom n'est saisi
            }
        }
    }
    public List<Classe> getClasses() {
        return classes;
    }

    // Méthode pour ajouter une relation entre deux classes
    public void ajouterRelationEntreClasses(Classe classe1, Classe classe2, TypeRelation typeRelation, CardinaliteEnum cardinaliteSource, CardinaliteEnum cardinaliteCible) {
        if (classe1 != null && classe2 != null && typeRelation != null && cardinaliteSource != null && cardinaliteCible != null) {
            // Créer une nouvelle relation avec les deux classes, le type de relation et les cardinalités
            Relation nouvelleRelation = new Relation(classe1, classe2, typeRelation, cardinaliteSource, cardinaliteCible);

            // Ajouter la relation à la vue
            vue.ajouterRelationVue(nouvelleRelation);

            // Mettre à jour la vue avec le message de succès
            vue.mettreAJourVue("Relation ajoutée entre " + classe1.getNom() + " et " + classe2.getNom() + " avec le type de relation : " + typeRelation
                    + " et cardinalités : " + cardinaliteSource + " - " + cardinaliteCible);
        } else {
            vue.mettreAJourVue("Les deux classes, le type de relation et les cardinalités doivent être sélectionnés pour créer une relation.");
        }
    }
    // Supprimer une classe sur clic
    public void supprimerClasseSurClic(MouseEvent event) {
        Classe classeSelectionnee = vue.getClasseSelectionnee();
        if (classeSelectionnee != null) {
            classes.remove(classeSelectionnee);
            vue.supprimerClasseVue(classeSelectionnee);
            vue.mettreAJourVue("Classe supprimée : " + classeSelectionnee.getNom());
        } else {
            vue.mettreAJourVue("Aucune classe sélectionnée pour suppression.");
        }
    }

}
