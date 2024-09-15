package model;
import model.EtatProjet;

public class Projet {
    private int id;
    private String nomProjet;
    private double margeBeneficiaire;
    private EtatProjet etatProjet;
    private double coutTotal;
    private Client client;

    public Projet(int id, String nomProjet, double margeBeneficiaire, EtatProjet etatProjet, double coutTotal, Client client) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.etatProjet = etatProjet;
        this.coutTotal = coutTotal;
        this.client = client;
    }

    public Projet(String nomProjet, double margeBeneficiaire, EtatProjet etatProjet, double coutTotal, Client client) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.etatProjet = etatProjet;
        this.coutTotal = coutTotal;
        this.client = client;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nomProjet='" + nomProjet + '\'' +
                ", margeBeneficiaire=" + margeBeneficiaire +
                ", etatProjet=" + etatProjet +
                ", coutTotal=" + coutTotal +
                ", client=" + client +
                '}';
    }
}