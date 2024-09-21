package dao;

import model.Devis;
import model.Projet;
import repository.DevisRepository;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevisDAO implements DevisRepository {

    private static final String INSERT_DEVIS_SQL = "INSERT INTO devis ( montant_estimate,date_emission, date_validate,accepte, projet_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_DEVIS_BY_ID = "SELECT * FROM devis WHERE id = ?";
    private static final String SELECT_ALL_DEVIS = "SELECT * FROM devis";
    private static final String UPDATE_DEVIS_SQL = "UPDATE devis SET nom = ?, montant = ?, date_creation = ?, projet_id = ? WHERE id = ?";
    private static final String DELETE_DEVIS_SQL = "DELETE FROM devis WHERE id = ?";

    private Connection connection;

    public DevisDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void insertDevis(Devis devis) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEVIS_SQL)) {
            preparedStatement.setDouble(1, devis.getMontantEstimate());
            preparedStatement.setDate(2, java.sql.Date.valueOf(devis.getDateEmission()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(devis.getDateValide()));
            preparedStatement.setBoolean(4, devis.isAccepte());
            preparedStatement.setInt(5, devis.getProjet().getId());
            preparedStatement.executeUpdate();
            System.out.println(" Devis enregistré avec succès ");
        }
    }
//
//    @Override
//    public Devis selectDevis(int id) throws SQLException {
//        Devis devis = null;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVIS_BY_ID)) {
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                devis = new Devis(
//                        rs.getInt("id"),
//                        rs.getString("nom"),
//                        rs.getDouble("montant"),
//                        rs.getDate("date_creation").toLocalDate(), // Assuming LocalDate for date
//                        new Projet(rs.getInt("projet_id")) // Assuming a simple constructor for Projet
//                );
//            }
//        }
//        return devis;
//    }
//
//    @Override
//    public List<Devis> selectAllDevis() throws SQLException {
//        List<Devis> devisList = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEVIS)) {
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                devisList.add(new Devis(
//                        rs.getInt("id"),
//                        rs.getString("nom"),
//                        rs.getDouble("montant"),
//                        rs.getDate("date_creation").toLocalDate(), // Assuming LocalDate for date
//                        new Projet(rs.getInt("projet_id")) // Assuming a simple constructor for Projet
//                ));
//            }
//        }
//        return devisList;
//    }
//
//    @Override
//    public boolean updateDevis(Devis devis) throws SQLException {
//        boolean rowUpdated;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVIS_SQL)) {
//            preparedStatement.setString(1, devis.getNom());
//            preparedStatement.setDouble(2, devis.getMontant());
//            preparedStatement.setDate(3, java.sql.Date.valueOf(devis.getDateCreation())); // Assuming LocalDate for date
//            preparedStatement.setInt(4, devis.getProjet().getId());
//            preparedStatement.setInt(5, devis.getId());
//            rowUpdated = preparedStatement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }
//
//    @Override
//    public boolean deleteDevis(int id) throws SQLException {
//        boolean rowDeleted;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEVIS_SQL)) {
//            preparedStatement.setInt(1, id);
//            rowDeleted = preparedStatement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
}
