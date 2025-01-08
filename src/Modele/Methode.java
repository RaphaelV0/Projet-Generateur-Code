package Modele;

import enume.Visibilite;

import java.util.List;

public class Methode {
    private String nom;
    private String type;
    private List<String> parametres;
    private Visibilite visibilite;

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getParametres() {
        return parametres;
    }

    public void setParametres(List<String> parametres) {
        this.parametres = parametres;
    }

    public Visibilite getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(Visibilite visibilite) {
        this.visibilite = visibilite;
    }
}
