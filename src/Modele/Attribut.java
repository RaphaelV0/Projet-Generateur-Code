package Modele;

import enume.Visibilite;
import enume.Type;

public class Attribut {
    private String nom;           // Nom de l'attribut
    private Type type;            // Type de l'attribut (enum Type)
    private Visibilite visibilite; // Visibilité de l'attribut (enum Visibilite)

    // Constructeur
    public Attribut(String nom, Type type, Visibilite visibilite) {
        this.nom = nom;
        this.type = type;
        this.visibilite = visibilite;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Visibilite getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(Visibilite visibilite) {
        this.visibilite = visibilite;
    }
}
