package Modele;

import enume.CardinaliteEnum;

public class Cardinalite {
    private CardinaliteEnum min;
    private CardinaliteEnum max;

    // Getters et Setters
    public CardinaliteEnum getMin() {
        return min;
    }

    public void setMin(CardinaliteEnum min) {
        this.min = min;
    }

    public CardinaliteEnum getMax() {
        return max;
    }

    public void setMax(CardinaliteEnum max) {
        this.max = max;
    }
}
