package dao;


import model.Projet;
import model.Client;
import model.Enum.EtatProjet;
import repository.ProjetRepository;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDAO implements ProjetRepository {

    private static final String INSERT_PROJET_SQL = "INSERT INTO projet (nom_projet, marge_beneficiaire, etat_projet, cout_total, client_id, surface) VALUES (?, ?, ?::etat_projet, ?, ?, ?) RETURNING id";

    private static final String SELECT_PROJET_DETAILS_BY_ID = "";

    private static final String SELECT_ALL_PROJETS = "SELECT * FROM projet";
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
            preparedStatement.setNull(4, java.sql.Types.DOUBLE);
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





//    public Projet selectProjetById(int id) {
//        Projet projet = null;
//        String query = "SELECT * FROM projet WHERE id = ?";
//
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                String etatProjetStr = rs.getString("etat_projet");
//
//                // Conversion de la chaîne en enum
//                EtatProjet etatProjet = EtatProjet.valueOf(etatProjetStr.toUpperCase());
//
//                projet = new Projet(
//                        rs.getInt("id"),
//                        rs.getString("nom_projet"),
//                        etatProjet
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return projet;
//    }


















//
//    @Override
//    public List<Projet> selectAllProjets() throws SQLException {
//        List<Projet> projets = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJETS)) {
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                projets.add(new Projet(
//                        rs.getInt("id"),
//                        rs.getString("nom_projet"),
//                        rs.getDouble("marge_beneficiaire"),
//                        EtatProjet.valueOf(rs.getString("etat_projet")),
//                        rs.getDouble("cout_total"),
//                        new Client(
//                                rs.getInt("client_id"),
//                                rs.getString("client_nom"),      // Assuming you select this from the DB
//                                rs.getString("client_adresse"),  // Assuming you select this from the DB
//                                rs.getString("client_telephone"), // Assuming you select this from the DB
//                                rs.getBoolean("client_est_professionnel") // Assuming you select this from the DB
//                        )
//                ));
//            }
//        }
//        return projets;
//    }
//
//    @Override
//    public boolean updateProjet(Projet projet) throws SQLException {
//        boolean rowUpdated;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROJET_SQL)) {
//            preparedStatement.setString(1, projet.getNomProjet());
//            preparedStatement.setDouble(2, projet.getMargeBeneficiaire());
//            preparedStatement.setString(3, projet.getEtatProjet().name());
//            preparedStatement.setDouble(4, projet.getCoutTotal());
//            preparedStatement.setInt(5, projet.getClient().getId());
//            preparedStatement.setInt(6, projet.getId());
//            rowUpdated = preparedStatement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }
//
//    @Override
//    public boolean deleteProjet(int id) throws SQLException {
//        boolean rowDeleted;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJET_SQL)) {
//            preparedStatement.setInt(1, id);
//            rowDeleted = preparedStatement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }






    public Projet  selectProjetById(int id) {
        Projet  projet = null;
        String query = "SELECT projet.nom_projet,projet.surface,client.nom,client.adresse  from projet\n" +
                "inner join client on client.id=projet.client_id\n" +
                "WHERE projet.id = ? ;";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nom_projet = rs.getString("nom_projet");
                double surface = rs.getDouble("surface");
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");

                Client client = new Client();
                client.setNom(nom);
                client.setAdresse(adresse);


                projet = new Projet(
                        nom_projet,
                        surface,
                        client

                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projet;
    }


}
