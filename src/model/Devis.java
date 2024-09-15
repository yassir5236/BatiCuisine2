package model;

import java.time.LocalDate;


public class Devis {
    private int id;
    private double montantEstimate;
    private LocalDate dateEmission;
    private LocalDate dateValide;
    private boolean accepte;
    private Projet projet;

    public Devis(int id, double montantEstimate, LocalDate dateEmission, LocalDate dateValide, boolean accepte, Projet projet) {
        this.id = id;
        this.montantEstimate = montantEstimate;
        this.dateEmission = dateEmission;
        this.dateValide = dateValide;
        this.accepte = accepte;
        this.projet = projet;
    }

    public Devis(double montantEstimate, LocalDate dateEmission, LocalDate dateValide, boolean accepte, Projet projet) {
        this.montantEstimate = montantEstimate;
        this.dateEmission = dateEmission;
        this.dateValide = dateValide;
        this.accepte = accepte;
        this.projet = projet;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontantEstimate() {
        return montantEstimate;
    }

    public void setMontantEstimate(double montantEstimate) {
        this.montantEstimate = montantEstimate;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateValide() {
        return dateValide;
    }

    public void setDateValide(LocalDate dateValide) {
        this.dateValide = dateValide;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", montantEstimate=" + montantEstimate +
                ", dateEmission=" + dateEmission +
                ", dateValide=" + dateValide +
                ", accepte=" + accepte +
                ", projet=" + projet +
                '}';
    }
}
