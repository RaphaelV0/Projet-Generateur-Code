package Modele;

import enume.TypeRelation;
import enume.CardinaliteEnum;
import Modele.Cardinalite;

public class Relation {
    private TypeRelation typeRelation;
    private Classe classeDepart;
    private Classe classeArrivee;
    private Cardinalite cardinalite;

    // Getters et Setters
    public TypeRelation getTypeRelation() {
        return typeRelation;
    }

    public void setTypeRelation(TypeRelation typeRelation) {
        this.typeRelation = typeRelation;
    }

    public Classe getClasseDepart() {
        return classeDepart;
    }

    public void setClasseDepart(Classe classeDepart) {
        this.classeDepart = classeDepart;
    }

    public Classe getClasseArrivee() {
        return classeArrivee;
    }

    public void setClasseArrivee(Classe classeArrivee) {
        this.classeArrivee = classeArrivee;
    }

    public Cardinalite getCardinalite() {
        return cardinalite;
    }

    public void setCardinalite(Cardinalite cardinalite) {
        this.cardinalite = cardinalite;
    }
}
