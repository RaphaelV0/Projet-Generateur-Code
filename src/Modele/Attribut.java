package Modele;

import enume.Visibilite;
import enume.Type;
public class Attribut {
    private String nom;
    private Type type;
    private Visibilite visibilite;

    public Attribut(String nom, Type type, Visibilite visibilite) {
        this.nom = nom;
        this.type = type;
        this.visibilite = visibilite;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public Visibilite getvisibilite() { return visibilite; }
    public void setvisibilite(Visibilite visibilite) { this.visibilite = visibilite; }
}
