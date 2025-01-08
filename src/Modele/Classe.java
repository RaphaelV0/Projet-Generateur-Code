package Modele;

import enume.Visibilite;
import java.util.List;

public class Classe {
    private String nom;
    private Visibilite visibilite;
    private List<Relation> relationsEntrantes;
    private List<Relation> relationsSortantes;
    private List<Attribut> attributs;
    private List<Methode> methodes;

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Attribut> getAttributs() {
        return attributs;
    }
    public List<Methode> getMethodes() {
        return methodes;
    }
    public Visibilite getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(Visibilite visibilite) {
        this.visibilite = visibilite;
    }

    public List<Relation> getRelationsEntrantes() {
        return relationsEntrantes;
    }

    public void setRelationsEntrantes(List<Relation> relationsEntrantes) {
        this.relationsEntrantes = relationsEntrantes;
    }

    public List<Relation> getRelationsSortantes() {
        return relationsSortantes;
    }

    public void setRelationsSortantes(List<Relation> relationsSortantes) {
        this.relationsSortantes = relationsSortantes;
    }
}
