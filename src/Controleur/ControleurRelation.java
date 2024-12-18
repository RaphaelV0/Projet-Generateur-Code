package Controleur;

import Modele.Classe;
import Modele.Relation;
import enume.Visibilite;

public class ControleurRelation {

    private Vue.VueDiagrammeClasse vue;

    // Constructeur
    public ControleurRelation(Vue.VueDiagrammeClasse vue) {
        this.vue = vue;
    }

    // Méthode pour ajouter une relation entre deux classes
    public void ajouterRelation(Classe classeSource, Classe classeCible) {
        // Logique pour créer et ajouter la relation
        Relation relation = new Relation(classeSource, classeCible);
        classeSource.ajouterRelationSortante(relation);  // Ajoute la relation sortante à la classe source
        classeCible.ajouterRelationEntrante(relation);   // Ajoute la relation entrante à la classe cible

        // Optionnel : vous pouvez mettre à jour la vue, si nécessaire
        // Par exemple, ajouter un visuel de la relation sur le diagramme
        System.out.println("Relation ajoutée entre " + classeSource.getNom() + " et " + classeCible.getNom());
    }

    // Méthode pour modifier une relation (exemple de méthode, à adapter selon vos besoins)
    public void modifierRelation(Relation relation) {
        // Logique de modification de la relation
        System.out.println("Modification de la relation.");
    }

    // Méthode pour supprimer une relation
    public void supprimerRelation(Relation relation) {
        // Logique pour supprimer la relation
        Classe classeSource = relation.getClasseSource();
        Classe classeCible = relation.getClasseCible();

        classeSource.getRelationsSortantes().remove(relation);
        classeCible.getRelationsEntrantes().remove(relation);

        // Optionnel : Mise à jour de la vue pour supprimer la représentation graphique
        System.out.println("Relation supprimée entre " + classeSource.getNom() + " et " + classeCible.getNom());
    }
}
