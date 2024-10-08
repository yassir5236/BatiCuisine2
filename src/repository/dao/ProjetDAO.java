package repository.dao;


import model.Projet;
import model.Client;
import repository.ProjetRepository;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDAO implements ProjetRepository {

    private static final String INSERT_PROJET_SQL = "INSERT INTO projet (nom_projet, marge_beneficiaire, etat_projet, cout_total, client_id, surface) VALUES (?, ?, ?::etat_projet, ?, ?, ?) RETURNING id";

    private static final String SELECT_PROJET_DETAILS_BY_ID = "";

    private static final String SELECT_ALL_PROJETS = "SELECT p.id, p.nom_projet, p.surface, p.marge_beneficiaire, p.etat_projet, p.cout_total, "
            + "c.id AS client_id, c.nom AS client_nom, c.adresse AS client_adresse, "
            + "c.telephone AS client_telephone, c.est_professionnel AS client_est_professionnel "
            + "FROM projet p "
            + "JOIN client c ON p.client_id = c.id";
    private static final String UPDATE_PROJET_SQL = "UPDATE projet SET nom_projet = ?, marge_beneficiaire = ?, etat_projet = ?, cout_total = ?, client_id = ? WHERE id = ?";
    private static final String DELETE_PROJET_SQL = "DELETE FROM projet WHERE id = ?";

    private Connection connection;

    public ProjetDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public int insertProjet(Projet projet) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJET_SQL)) {
            preparedStatement.setString(1, projet.getNomProjet());
            preparedStatement.setDouble(2, projet.getMargeBeneficiaire());
            preparedStatement.setString(3, projet.getEtatProjet().name());
            preparedStatement.setDouble(4, projet.getCoutTotal());
            preparedStatement.setInt(5, projet.getClient().getId());
//            preparedStatement.setDouble(6, projet.getCoutTotal());
            preparedStatement.setDouble(6, projet.getSurface());

            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public void updateCoutTotal(double coutTotal, int id) throws SQLException {

        String sql = "UPDATE projet SET cout_total = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDouble(1, coutTotal);
            ps.setInt(2,id);
            ps.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }













    @Override
    public List<Projet> selectAllProjets() throws SQLException {
        List<Projet> projets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJETS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                projets.add(new Projet(

                        rs.getInt("id"),
                        rs.getString("nom_projet"),
                        rs.getDouble("surface")   ,
                        rs.getDouble("marge_beneficiaire"),
                        rs.getDouble("cout_total"),


                        new Client(
                                rs.getInt("client_id"),
                                rs.getString("client_nom"),
                                rs.getString("client_adresse"),
                                rs.getString("client_telephone"),
                                rs.getBoolean("client_est_professionnel")
                        )
                ));
            }
        }
        return projets;
    }






    public Projet  selectProjetById(int id) {
        Projet  projet = null;
        String query = "SELECT client.remise ,projet.nom_projet,projet.surface, projet.marge_beneficiaire,client.nom,client.adresse  from projet\n" +
                "inner join client on client.id=projet.client_id\n" +
                "WHERE projet.id = ? ;";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nom_projet = rs.getString("nom_projet");
                double surface = rs.getDouble("surface");
                double margeBeneficiaire = rs.getDouble("marge_beneficiaire");
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                double remise = rs.getDouble("remise");

                Client client = new Client();
                client.setNom(nom);
                client.setAdresse(adresse);
                client.setRemise(remise);


                projet = new Projet(
                        id,
                        nom_projet,
                        surface,
                        margeBeneficiaire,
                        client

                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projet;
    }


}
