package model;


public class Client {
    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean est_Professionnel;

    public Client(int id, String nom, String adresse, String telephone, boolean estProfessionnel) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.est_Professionnel = estProfessionnel;
    }

    public Client( String nom, String adresse, String telephone, boolean estProfessionnel) {

        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.est_Professionnel = estProfessionnel;
    }

    public Client() {

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEstProfessionnel() {
        return est_Professionnel;
    }

    public void setEstProfessionnel(boolean est_Professionnel) {
        this.est_Professionnel = est_Professionnel;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", est_Professionnel=" + est_Professionnel +
                '}';
    }
}
