package Controleur;

import Modele.Classe;
import Modele.Relation;
import Vue.VueDiagrammeClasse;
import enume.TypeRelation;
import enume.CardinaliteEnum;

import java.util.ArrayList;
import java.util.List;

public class ControleurRelation {

    private final VueDiagrammeClasse vue;
    private final ControleurDiagrammeClasse controleurDiagrammeClasse;
    private List<Relation> relations;
    private Classe classeSelectionnee1;
    private Classe classeSelectionnee2;
    private Relation relationSelectionnee;

    public ControleurRelation(VueDiagrammeClasse vue, ControleurDiagrammeClasse controleurDiagrammeClasse) {
        this.vue = vue;
        this.controleurDiagrammeClasse = controleurDiagrammeClasse;
        this.relations = new ArrayList<>();
        this.relationSelectionnee = null;
    }

    // Méthode pour obtenir la relation sélectionnée
    public Relation obtenirRelationSelectionnee() {
        return relationSelectionnee;
    }

    // Méthode pour sélectionner une relation
    public void selectionnerRelation(Relation relation) {
        this.relationSelectionnee = relation;
        vue.mettreAJourVue("Relation sélectionnée entre " + relation.getClasse1().getNom() + " et " + relation.getClasse2().getNom());
    }

    // Méthode pour ajouter une relation entre deux classes
    public void ajouterRelation(Classe classe1, Classe classe2, TypeRelation typeRelation, CardinaliteEnum cardinaliteEnum) {
        if (classe1 == null || classe2 == null) {
            vue.mettreAJourVue("Erreur : Les deux classes doivent être sélectionnées pour ajouter une relation.");
            return;
        }

        Relation relation = new Relation(classe1, classe2, typeRelation, cardinaliteEnum, cardinaliteEnum);
        relations.add(relation);
        vue.ajouterRelationVue(relation);
        vue.mettreAJourVue("Relation ajoutée entre " + classe1.getNom() + " et " + classe2.getNom() + " (" + typeRelation.getLabel() + ", " + cardinaliteEnum.getLabel() + ")");
    }

    // Méthode pour modifier une relation
    public void modifierRelation(Relation relation, TypeRelation nouveauTypeRelation, CardinaliteEnum nouvelleCardinalite) {
        // Modifier les détails de la relation
        relation.setTypeRelation(nouveauTypeRelation);
        relation.setCardinalite(nouvelleCardinalite);

        // Redessiner la relation modifiée
        vue.mettreAJourVue("Relation modifiée entre " + relation.getClasse1().getNom() + " et " + relation.getClasse2().getNom());
    }

    // Méthode pour supprimer une relation
    public void supprimerRelation(Relation relation) {
        // Supprimer la relation de la liste
        relations.remove(relation);

        // Supprimer la relation de la vue
        vue.supprimerRelation(relation);

        // Mettre à jour la vue avec un message de confirmation
        vue.mettreAJourVue("Relation supprimée entre " + relation.getClasse1().getNom() + " et " + relation.getClasse2().getNom());
    }

    // Méthode pour sélectionner une classe à relier
    public void selectionnerClassePourRelation(Classe classe) {
        // Si c'est la première classe sélectionnée
        if (classeSelectionnee1 == null) {
            classeSelectionnee1 = classe;
            vue.mettreAJourVue("Première classe sélectionnée : " + classe.getNom());
        } else {
            classeSelectionnee2 = classe;
            vue.mettreAJourVue("Deuxième classe sélectionnée : " + classe.getNom());

            // Si les deux classes sont sélectionnées, demander à l'utilisateur de choisir le type de relation
            if (classeSelectionnee1 != null && classeSelectionnee2 != null) {
                // Demander à la vue d'afficher un dialogue pour choisir le type de relation et la cardinalité
                vue.afficherDialogueRelation(classeSelectionnee1, classeSelectionnee2);
            }
        }
    }

    // Méthode pour réinitialiser la sélection des classes
    public void reinitialiserSelection() {
        classeSelectionnee1 = null;
        classeSelectionnee2 = null;
        vue.mettreAJourVue("Sélection réinitialisée.");
    }
    public List<Relation> getRelations() {
        return relations;
    }
    // Méthode pour créer une relation depuis la vue après la sélection des classes et du type de relation
    public void creerRelation(TypeRelation typeRelation, CardinaliteEnum cardinaliteEnum) {
        if (classeSelectionnee1 != null && classeSelectionnee2 != null) {
            ajouterRelation(classeSelectionnee1, classeSelectionnee2, typeRelation, cardinaliteEnum);
            reinitialiserSelection(); // Réinitialiser la sélection des classes après création de la relation
        } else {
            vue.mettreAJourVue("Erreur : Les deux classes doivent être sélectionnées avant de créer une relation.");
        }
    }
}
