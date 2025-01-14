package Controleur;

import Modele.Classe;
import Modele.Methode;
import Modele.Attribut;
import Modele.Relation;
import Vue.VueDiagrammeClasse;
import enume.Visibilite;
import enume.TypeRelation;
import Controleur.ControleurDiagrammeClasse;

import java.util.List;

public class ControleurGenerationCode {

    private VueDiagrammeClasse vue;
    private ControleurDiagrammeClasse controleurDiagrammeClasse;
    private ControleurRelation controleurRelation;

    public ControleurGenerationCode(VueDiagrammeClasse vue, ControleurDiagrammeClasse controleurDiagrammeClasse, ControleurRelation controleurRelation) {
        this.vue = vue;
        this.controleurDiagrammeClasse = controleurDiagrammeClasse;
        this.controleurRelation = controleurRelation;
    }

    // Méthode principale pour générer le code
    public void genererCode() {
        StringBuilder codeJava = new StringBuilder();

        // Récupérer toutes les classes depuis le contrôleur de diagramme de classe
        List<Classe> classes = controleurDiagrammeClasse.getClasses();

        // Générer le code pour chaque classe
        for (Classe classe : classes) {
            codeJava.append(genererCodePourClasse(classe));
        }

        // Appeler la méthode de la vue pour afficher le code généré
        vue.afficherCodeGenere(codeJava.toString());

        // Afficher également le code dans la console
        System.out.println(codeJava.toString());
    }

    // Générer le code pour une classe donnée
    private String genererCodePourClasse(Classe classe) {
        StringBuilder code = new StringBuilder();

        // Début de la classe
        code.append("public class ").append(classe.getNom()).append(" {\n");

        // Ajouter les attributs
        for (Attribut attribut : classe.getAttributs()) {
            code.append("\t").append(visibiliteToString(attribut.getvisibilite()))
                    .append(" ").append(attribut.getType())
                    .append(" ").append(attribut.getNom())
                    .append(";\n");
        }

        code.append("\n");

        // Ajouter les méthodes
        for (Methode methode : classe.getMethodes()) {
            code.append("\t").append(visibiliteToString(methode.getVisibilite()))
                    .append(" ").append(methode.getRetour())
                    .append(" ").append(methode.getNom())
                    .append("(").append(argumentsToString(methode)).append(") {\n")
                    .append("\t\t// TODO: Implementer cette methode\n")
                    .append("\t}\n\n");
        }

        // Ajouter les relations sous forme de commentaires (optionnel)
        List<Relation> relations = controleurRelation.getRelations();
        for (Relation relation : relations) {
            if (relation.getClasse1().equals(classe) || relation.getClasse2().equals(classe)) {
                code.append("\t// Relation: ").append(relation.getTypeRelation().getLabel())
                        .append(" avec ").append(
                                relation.getClasse1().equals(classe) ? relation.getClasse2().getNom() : relation.getClasse1().getNom()
                        ).append("\n");
            }
        }

        // Fin de la classe
        code.append("}\n\n");
        return code.toString();
    }

    // Convertir la visibilité en chaîne de caractères Java
    private String visibiliteToString(Visibilite visibilite) {
        switch (visibilite) {
            case PUBLIC:
                return "public";
            case PRIVATE:
                return "private";
            case PROTECTED:
                return "protected";
            default:
                return "";
        }
    }

    // Convertir les arguments de méthode en chaîne de caractères
    private String argumentsToString(Methode methode) {
        StringBuilder arguments = new StringBuilder();
        List<Attribut> params = methode.getParametres();
        for (int i = 0; i < params.size(); i++) {
            Attribut param = params.get(i);
            arguments.append(param.getType()).append(" ").append(param.getNom());
            if (i < params.size() - 1) {
                arguments.append(", ");
            }
        }
        return arguments.toString();
    }
}
