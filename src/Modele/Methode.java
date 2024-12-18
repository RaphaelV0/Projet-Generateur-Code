package Modele;

import enume.Type;
import enume.Visibilite;

import java.util.ArrayList;
import java.util.List;

public class Methode {
    private String nom;                        // Nom de la méthode
    private Type typeRetour;                   // Type de retour de la méthode
    private Visibilite visibilite;             // Visibilité de la méthode
    private List<Parametre> parametres;        // Liste des paramètres de la méthode

    // Classe interne pour les paramètres
    public static class Parametre {
        private String nom;       // Nom du paramètre
        private Type type;        // Type du paramètre

        public Parametre(String nom, Type type) {
            this.nom = nom;
            this.type = type;
        }

        public String getNom() {
            return nom;
        }

        public Type getType() {
            return type;
        }
    }

    // Constructeur
    public Methode(String nom, Type typeRetour, Visibilite visibilite) {
        this.nom = nom;
        this.typeRetour = typeRetour;
        this.visibilite = visibilite;
        this.parametres = new ArrayList<>();
    }

    // Méthodes pour ajouter des paramètres
    public void ajouterParametre(String nom, Type type) {
        this.parametres.add(new Parametre(nom, type));
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Type getTypeRetour() {
        return typeRetour;
    }

    public void setTypeRetour(Type typeRetour) {
        this.typeRetour = typeRetour;
    }

    public Visibilite getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(Visibilite visibilite) {
        this.visibilite = visibilite;
    }

    public List<Parametre> getParametres() {
        return parametres;
    }
}
