package enume;

public enum TypeRelation {
    ASSOCIATION("Association"),
    HERITAGE("Héritage");

    private final String label;

    TypeRelation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
