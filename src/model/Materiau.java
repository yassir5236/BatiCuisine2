package model;
import model.Enum.TypeComposant;


public class Materiau extends Composant {
    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQuantite;

    public Materiau(int id, String nom, TypeComposant typeComposant, double tauxTVA, Projet projet,
                    double coutUnitaire, double quantite, double coutTransport, double coefficientQuantite) {
        super(id, nom, typeComposant, tauxTVA, projet);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQuantite = coefficientQuantite;
    }

    public Materiau(String nom, TypeComposant typeComposant, double tauxTVA, Projet projet,
                    double coutUnitaire, double quantite, double coutTransport, double coefficientQuantite) {
        super(nom, typeComposant, tauxTVA, projet);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQuantite = coefficientQuantite;
    }






    // Getters and Setters
    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQuantite() {
        return coefficientQuantite;
    }

    public void setCoefficientQuantite(double coefficientQuantite) {
        this.coefficientQuantite = coefficientQuantite;
    }

    @Override
    public String toString() {
        return "Materiau{" +
                "coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQuantite=" + coefficientQuantite +
                "} " + super.toString();
    }
}
