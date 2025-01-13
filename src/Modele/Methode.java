package Modele;

import enume.Type;
import enume.Visibilite;
import java.util.List;

public class Methode {
    private String nom;
    private String retour;
    private List<Attribut> parametres;
    private Visibilite visibilite;
    private Type type;

    public Methode(String nom, String retour, List<Attribut> parametres, Visibilite visibilite) {
        this.nom = nom;
        this.retour = retour;
        this.parametres = parametres;
        this.visibilite = visibilite;
        this.type = Type.VOID;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getRetour() { return retour; }
    public void setRetour(String retour) { this.retour = retour; }
    public List<Attribut> getParametres() { return parametres; }
    public void setParametres(List<Attribut> parametres) { this.parametres = parametres; }
    public Visibilite getVisibilite() { return visibilite; }
    public void setVisibilite(Visibilite visibilite) { this.visibilite = visibilite; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
}
