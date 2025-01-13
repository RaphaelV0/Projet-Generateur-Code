package enume;

public enum Visibilite {
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    PACKAGE_PRIVATE("");

    private final String representation;

    Visibilite(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
