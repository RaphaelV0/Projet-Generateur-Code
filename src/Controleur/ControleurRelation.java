package Controleur;

import Modele.Relation;
import Modele.Classe;
import Vue.VueDiagrammeClasse;
import javafx.scene.input.MouseEvent;
import enume.TypeRelation;

import java.util.List;

public class ControleurRelation {

    private List<Relation> relations;
    private VueDiagrammeClasse vue;

    // Constructeur
    public ControleurRelation(List<Relation> relations, VueDiagrammeClasse vue) {
        this.relations = relations;
        this.vue = vue;
    }

    // Méthode pour ajouter une nouvelle relation
    public void ajouterRelation(Classe classeDepart, Classe classeArrivee, TypeRelation typeRelation) {
        // Création de la relation avec le constructeur ajusté
        Relation nouvelleRelation = new Relation(classeDepart, classeArrivee, typeRelation);
        relations.add(nouvelleRelation);
        vue.mettreAJourVue("Relation ajoutée entre " + classeDepart.getNom() + " et " + classeArrivee.getNom() +
                " de type " + typeRelation);
    }

    // Méthode pour modifier une relation existante
    public void modifierRelation(Relation relation, TypeRelation nouveauType) {
        relation.setTypeRelation(nouveauType);
        vue.mettreAJourVue("Relation modifiée : " + relation.getClasseDepart().getNom() +
                " -> " + relation.getClasseArrivee().getNom() + " (" + nouveauType + ")");
    }

    // Méthode pour supprimer une relation
    public void supprimerRelation(Relation relation) {
        relations.remove(relation);
        vue.mettreAJourVue("Relation supprimée entre " + relation.getClasseDepart().getNom() +
                " et " + relation.getClasseArrivee().getNom());
    }

    // Méthode pour gérer la sélection d'une relation
    public void selectionnerRelationPourModification(MouseEvent event) {
        Relation relationSelectionnee = getRelationDepuisVue();
        if (relationSelectionnee != null) {
            vue.mettreAJourVue("Relation sélectionnée pour modification : " +
                    relationSelectionnee.getClasseDepart().getNom() + " -> " +
                    relationSelectionnee.getClasseArrivee().getNom());
        } else {
            vue.mettreAJourVue("Aucune relation sélectionnée.");
        }
    }

    // Méthode pour récupérer la relation sélectionnée depuis la vue
    private Relation getRelationDepuisVue() {
        Relation relationSelectionnee = vue.getRelationSelectionnee();
        if (relationSelectionnee != null) {
            return relationSelectionnee;
        } else {
            vue.mettreAJourVue("Aucune relation sélectionnée.");
            return null;
        }
    }
}