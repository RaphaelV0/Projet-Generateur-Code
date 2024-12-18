package Modele;

import enume.CardinaliteEnum;

public class Cardinalite {
    private CardinaliteEnum cardinalite;

    public Cardinalite(CardinaliteEnum cardinalite) {
        this.cardinalite = cardinalite;
    }

    // Getter et Setter
    public CardinaliteEnum getCardinalite() {
        return cardinalite;
    }

    public void setCardinalite(CardinaliteEnum cardinalite) {
        this.cardinalite = cardinalite;
    }
}



