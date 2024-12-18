package Modele;
import enume.TypeRelation;

public class Relation {
    private Classe classeDepart;  // Classe qui initie la relation
    private Classe classeArrivee;  // Classe qui reçoit la relation
    private TypeRelation typeRelation;  // Type de la relation
    private Cardinalite cardinaliteGauche;  // Cardinalité pour la classe départ
    private Cardinalite cardinaliteDroite;  // Cardinalité pour la classe arrivée

    // Constructeur
    public Relation(Classe classeDepart, Classe classeArrivee, TypeRelation typeRelation,
                    Cardinalite cardinaliteGauche, Cardinalite cardinaliteDroite) {
        this.classeDepart = classeDepart;
        this.classeArrivee = classeArrivee;
        this.typeRelation = typeRelation;
        this.cardinaliteGauche = cardinaliteGauche;
        this.cardinaliteDroite = cardinaliteDroite;
    }

    // Getters et Setters
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

    public TypeRelation getTypeRelation() {
        return typeRelation;
    }

    public void setTypeRelation(TypeRelation typeRelation) {
        this.typeRelation = typeRelation;
    }

    public Cardinalite getCardinaliteGauche() {
        return cardinaliteGauche;
    }

    public void setCardinaliteGauche(Cardinalite cardinaliteGauche) {
        this.cardinaliteGauche = cardinaliteGauche;
    }

    public Cardinalite getCardinaliteDroite() {
        return cardinaliteDroite;
    }

    public void setCardinaliteDroite(Cardinalite cardinaliteDroite) {
        this.cardinaliteDroite = cardinaliteDroite;
    }
}
