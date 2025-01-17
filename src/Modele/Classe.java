package Modele;

import enume.Visibilite;
import enume.Type; // Assure-toi d'importer le bon enum Type

import java.util.ArrayList;
import java.util.List;

public class Classe {
    private String nom;
    private Visibilite visibilite;
    private List<Relation> relationsEntrantes;
    private List<Relation> relationsSortantes;
    private List<Attribut> attributs;
    private List<Methode> methodes;
    private double X;
    private double Y;

    // Constructeur
    public Classe(String nom, double X, double Y) {
        this.nom = nom;
        this.X = X;
        this.Y = Y;
        this.attributs = new ArrayList<>(); // Initialiser la liste des attributs
        this.methodes = new ArrayList<>(); // Initialiser la liste des méthodes
    }

    // Getters et Setters
    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Attribut> getAttributs() {
        return attributs;
    }

    public void setAttributs(List<Attribut> attributs) {
        this.attributs = attributs;
    }

    public List<Methode> getMethodes() {
        return methodes;
    }

    public void setMethodes(List<Methode> methodes) {
        this.methodes = methodes;
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

    // Méthode pour ajouter un attribut
    public void ajouterAttribut(Attribut attribut) {
        this.attributs.add(attribut);
    }

    // Méthode pour ajouter une méthode
    public void ajouterMethode(Methode methode) {
        this.methodes.add(methode);
    }
}
