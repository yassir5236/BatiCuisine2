
package model;


import model.Enum.TypeComposant;

public class Composant {
    private int id;
    private String nom;
    private TypeComposant typeComposant;
    private double tauxTVA;
    private Projet projet;

    public Composant(int id, String nom, TypeComposant typeComposant, double tauxTVA, Projet projet) {
        this.id = id;
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
        this.projet = projet;
    }

    public Composant(String nom, TypeComposant typeComposant, double tauxTVA, Projet projet) {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
        this.projet = projet;
    }



    public Composant(String nom,  double tauxTVA, Projet projet) {
        this.nom = nom;
        this.tauxTVA = tauxTVA;
        this.projet = projet;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public String toString() {
        return "Composant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", typeComposant=" + typeComposant +
                ", tauxTVA=" + tauxTVA +
                ", projet=" + projet +
                '}';
    }
}
