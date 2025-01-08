package Controleur;

import Modele.Classe;
import Modele.Attribut;
import Modele.Methode;
import Modele.Relation;
import Modele.Cardinalite;
import enume.Visibilite;
import enume.TypeRelation;
import enume.CardinaliteEnum;

import java.util.List;

public class ControleurGenerationCode {

    private List<Classe> classes;

    public ControleurGenerationCode(List<Classe> classes) {
        this.classes = classes;
    }

    public String genererCode() {
        StringBuilder code = new StringBuilder();

        for (Classe classe : classes) {
            code.append("public class ").append(classe.getNom()).append(" {\n\n");

            // Générer les attributs
            for (Attribut attribut : classe.getAttributs()) {
                code.append("\t")
                        .append(visibiliteToString(attribut.getVisibilite()))
                        .append(" ")
                        .append(attribut.getType())
                        .append(" ")
                        .append(attribut.getNom())
                        .append(";\n");
            }
            code.append("\n");

            // Générer les relations
            for (Relation relation : classe.getRelationsSortantes()) {
                Classe classeArrivee = relation.getClasseArrivee();
                String typeRelation = relationTypeToString(relation.getTypeRelation());
                String multiplicite = cardinaliteToString(relation.getCardinalite());

                code.append("\t")
                        .append("private ")
                        .append(multiplicite)
                        .append("<")
                        .append(classeArrivee.getNom())
                        .append("> ")
                        .append(classeArrivee.getNom().toLowerCase())
                        .append("Relation;\n");
            }
            code.append("\n");

            // Générer les méthodes
            for (Methode methode : classe.getMethodes()) {
                code.append("\t")
                        .append(visibiliteToString(methode.getVisibilite()))
                        .append(" ")
                        .append(methode.getType())
                        .append(" ")
                        .append(methode.getNom())
                        .append("(");

                // Ajouter les paramètres
                List<String> parametres = methode.getParametres();
                for (int i = 0; i < parametres.size(); i++) {
                    code.append(parametres.get(i)).append(" param").append(i);
                    if (i < parametres.size() - 1) {
                        code.append(", ");
                    }
                }
                code.append(") {\n");
                code.append("\t\t// TODO: Implement method\n");
                code.append("\t}\n");
            }

            code.append("}\n\n");
        }

        return code.toString();
    }

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

    private String relationTypeToString(TypeRelation typeRelation) {
        switch (typeRelation) {
            case COMPOSITION:
                return "composition";
            case AGGREGATION:
                return "aggregation";
            case ASSOCIATION_FORTE:
                return "association forte";
            case ASSOCIATION_FAIBLE:
                return "association faible";
            default:
                return "";
        }
    }

    private String cardinaliteToString(Cardinalite cardinalite) {
        CardinaliteEnum min = cardinalite.getMin();
        CardinaliteEnum max = cardinalite.getMax();
        if (max == CardinaliteEnum.UN) {
            return ""; // Simple référence unique
        } else {
            return "List"; // Liste pour les collections (multiplicité)
        }
    }
}
