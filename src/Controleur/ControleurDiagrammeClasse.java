package Controleur;

import Modele.Classe;
import Modele.Relation;
import Modele.Attribut;
import Modele.Methode;
import Vue.VueDiagrammeClasse;
import javafx.scene.input.MouseEvent;
import enume.TypeRelation;
import java.util.List;

public class ControleurDiagrammeClasse {

    private VueDiagrammeClasse vue;
    private List<Classe> classes;    // Liste des classes du modèle
    private List<Relation> relations; // Liste des relations du modèle

    public ControleurDiagrammeClasse(VueDiagrammeClasse vue) {
        this.vue = vue;
    }

    // Ajouter une classe
    public void ajouterClasseSurClic(MouseEvent event) {
        // Exemple de création d'une nouvelle classe
        Classe nouvelleClasse = new Classe();
        nouvelleClasse.setNom("NouvelleClasse");
        classes.add(nouvelleClasse);
        vue.ajouterClasseVue(nouvelleClasse); // Mise à jour de la vue
        vue.mettreAJourVue("Classe ajoutée : " + nouvelleClasse.getNom());
    }

    // Modifier une classe
    public void selectionnerClassePourModification(MouseEvent event) {
        Classe classeSelectionnee = vue.getClasseSelectionnee();
        if (classeSelectionnee != null) {
            // Exemple de modification : changer le nom de la classe
            classeSelectionnee.setNom("ClasseModifiee");
            vue.mettreAJourClasseVue(classeSelectionnee); // Mise à jour de la vue
            vue.mettreAJourVue("Classe modifiée : " + classeSelectionnee.getNom());
        } else {
            vue.mettreAJourVue("Aucune classe sélectionnée pour modification.");
        }
    }

    // Supprimer une classe
    public void supprimerClasseSurClic(MouseEvent event) {
        Classe classeSelectionnee = vue.getClasseSelectionnee();
        if (classeSelectionnee != null) {
            classes.remove(classeSelectionnee);
            vue.supprimerClasseVue(classeSelectionnee); // Mise à jour de la vue
            vue.mettreAJourVue("Classe supprimée : " + classeSelectionnee.getNom());
        } else {
            vue.mettreAJourVue("Aucune classe sélectionnée pour suppression.");
        }
    }

    // Créer une relation
    public void creerRelation(MouseEvent event) {
        Relation nouvelleRelation = new Relation();
        // Exemple de configuration de la relation
        nouvelleRelation.setTypeRelation(TypeRelation.ASSOCIATION_FORTE);
        nouvelleRelation.setClasseDepart(vue.getClasseDepartSelectionnee());
        nouvelleRelation.setClasseArrivee(vue.getClasseArriveeSelectionnee());

        if (nouvelleRelation.getClasseDepart() != null && nouvelleRelation.getClasseArrivee() != null) {
            relations.add(nouvelleRelation);
            vue.ajouterRelationVue(nouvelleRelation); // Mise à jour de la vue
            vue.mettreAJourVue("Relation ajoutée entre " +
                    nouvelleRelation.getClasseDepart().getNom() + " et " +
                    nouvelleRelation.getClasseArrivee().getNom());
        } else {
            vue.mettreAJourVue("Veuillez sélectionner les classes pour la relation.");
        }
    }

    // Modifier une relation
    public void selectionnerRelationPourModification(MouseEvent event) {
        Relation relationSelectionnee = vue.getRelationSelectionnee();
        if (relationSelectionnee != null) {
            // Exemple de modification : changer le type de la relation
            relationSelectionnee.setTypeRelation(TypeRelation.COMPOSITION);
            vue.mettreAJourRelationVue(relationSelectionnee); // Mise à jour de la vue
            vue.mettreAJourVue("Relation modifiée.");
        } else {
            vue.mettreAJourVue("Aucune relation sélectionnée pour modification.");
        }
    }

    // Supprimer une relation
    public void supprimerRelationSurClic(MouseEvent event) {
        Relation relationSelectionnee = vue.getRelationSelectionnee();
        if (relationSelectionnee != null) {
            relations.remove(relationSelectionnee);
            vue.supprimerRelationVue(relationSelectionnee); // Mise à jour de la vue
            vue.mettreAJourVue("Relation supprimée.");
        } else {
            vue.mettreAJourVue("Aucune relation sélectionnée pour suppression.");
        }
    }

    // Méthode pour récupérer les classes (optionnelle si nécessaire pour le modèle)
    public List<Classe> getClasses() {
        return classes;
    }

    // Méthode pour récupérer les relations (optionnelle si nécessaire pour le modèle)
    public List<Relation> getRelations() {
        return relations;
    }
}
