package Modele;

import enume.CardinaliteEnum;
import enume.TypeRelation;

public class Relation {
    private Classe source;
    private Classe cible;
    private TypeRelation type;
    private CardinaliteEnum cardinaliteSource;
    private CardinaliteEnum cardinaliteCible;

    public Relation(Classe source, Classe cible, TypeRelation type, CardinaliteEnum cardinaliteSource, CardinaliteEnum cardinaliteCible) {
        this.source = source;
        this.cible = cible;
        this.type = type;
        this.cardinaliteSource = cardinaliteSource;
        this.cardinaliteCible = cardinaliteCible;
    }

    // Getters et setters
    public Classe getSource() { return source; }
    public void setSource(Classe source) { this.source = source; }
    public Classe getCible() { return cible; }
    public void setCible(Classe cible) { this.cible = cible; }
    public TypeRelation getTypeRelation() { return type; }
    public void setType(TypeRelation type) { this.type = type; }
    public CardinaliteEnum getCardinaliteSource() { return cardinaliteSource; }
    public void setCardinaliteSource(CardinaliteEnum cardinaliteSource) { this.cardinaliteSource = cardinaliteSource; }
    public CardinaliteEnum getCardinaliteCible() { return cardinaliteCible; }
    public void setCardinaliteCible(CardinaliteEnum cardinaliteCible) { this.cardinaliteCible = cardinaliteCible; }

    // Méthodes supplémentaires
    public Classe getClasse1() {
        return source;
    }

    public Classe getClasse2() {
        return cible;
    }

    public void setTypeRelation(TypeRelation typeRelation) {
        this.type = typeRelation;
    }

    public void setCardinalite(CardinaliteEnum cardinalite) {
        // Met à jour les deux côtés de la relation
        if (this.source == null) {
            this.cardinaliteCible = cardinalite;
        } else {
            this.cardinaliteSource = cardinalite;
        }
    }

    public String getLabel() {
        return type.getLabel() + " (" + cardinaliteSource.getLabel() + " - " + cardinaliteCible.getLabel() + ")";
    }

    // Si tu veux ajouter une méthode pour récupérer la visibilité ou d'autres informations, voici un exemple:
    public String getVisibilite() {
        // Retourner la visibilité basée sur la relation ou une méthode sur le type de relation
        return "public"; // Exemple, à modifier selon ton besoin
    }
}
