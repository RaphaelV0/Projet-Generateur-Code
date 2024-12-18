package Modele;

import java.util.ArrayList;
import java.util.List;
import enume.Visibilite;

public class Classe {
    private String nom;  // Nom de la classe
    private Visibilite visibilite;  // Visibilité de la classe
    private List<Relation> relationsEntrantes;  // Relations où cette classe est la classe arrivée
    private List<Relation> relationsSortantes;  // Relations où cette classe est la classe départ
    private List<Attribut> attributs;  // Liste des attributs de la classe
    private List<Methode> methodes;  // Liste des méthodes de la classe

    // Constructeur
    public Classe(String nom, Visibilite visibilite) {
        this.nom = nom;
        this.visibilite = visibilite;
        this.relationsEntrantes = new ArrayList<>();
        this.relationsSortantes = new ArrayList<>();
        this.attributs = new ArrayList<>();
        this.methodes = new ArrayList<>();
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public void addRelationEntrante(Relation relation) {
        this.relationsEntrantes.add(relation);
    }

    public List<Relation> getRelationsSortantes() {
        return relationsSortantes;
    }

    public void addRelationSortante(Relation relation) {
        this.relationsSortantes.add(relation);
    }

    public List<Attribut> getAttributs() {
        return attributs;
    }

    public void addAttribut(Attribut attribut) {
        this.attributs.add(attribut);
    }

    public List<Methode> getMethodes() {
        return methodes;
    }

    public void addMethode(Methode methode) {
        this.methodes.add(methode);
    }
}
