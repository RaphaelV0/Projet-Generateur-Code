package enume;

public enum CardinaliteEnum {
    ZERO_UN("0..1"),
    ZERO_N("0..n"),
    UN("1"),
    UN_N("1..n"),
    N("n");

    private final String representation;

    CardinaliteEnum(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    // Méthode getLabel() ajoutée pour obtenir un label plus descriptif
    public String getLabel() {
        switch (this) {
            case ZERO_UN:
                return "Zéro ou un";
            case ZERO_N:
                return "Zéro ou plusieurs";
            case UN:
                return "Un";
            case UN_N:
                return "Un ou plusieurs";
            case N:
                return "Plusieurs";
            default:
                return "Non défini";
        }
    }
}
