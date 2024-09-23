package model;
import model.Enum.EtatProjet;

import java.util.List;

public class Projet {
    private int id;
    private String nomProjet;
    private double margeBeneficiaire;
    private EtatProjet etatProjet;
    private double coutTotal;
    private double surface;
    private Client client;
    private List<Composant> composants;



    public Projet(int id, String nomProjet, double margeBeneficiaire, EtatProjet etatProjet, double coutTotal, Client client, double surface) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.etatProjet = etatProjet;
        this.coutTotal = coutTotal;
        this.client = client;
        this.surface = surface;

    }






    public Projet(String nomProjet, double margeBeneficiaire, EtatProjet etatProjet, double coutTotal, Client client , double surface) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.etatProjet = etatProjet;
        this.coutTotal = coutTotal;
        this.client = client;
        this.surface = surface;

    }


    public Projet(int id, String nom, EtatProjet etatProjet) {
        this.id = id;
        this.nomProjet = nom;
        this.etatProjet = etatProjet;
    }

    public Projet( String nom,double surface ,Client client) {
        this.nomProjet = nom;
        this.surface = surface;
        this.client=client;
    }


    public Projet(int id ,  String nom,double surface , double margeBeneficiaire,Client client) {
        this.id= id;
        this.nomProjet = nom;
        this.surface = surface;
        this.margeBeneficiaire= margeBeneficiaire;
        this.client=client;
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

    public double getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }
    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nomProjet='" + nomProjet + '\'' +
                ", margeBeneficiaire=" + margeBeneficiaire +
                ", etatProjet=" + etatProjet +
                ", coutTotal=" + coutTotal +
                ", surface=" + surface +

                ", client=" + client +
                '}';
    }
}
