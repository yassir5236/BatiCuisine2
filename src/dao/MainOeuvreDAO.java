//package dao;
//
//
//import model.MainOeuvre;
//import model.Projet;
//import repository.MainOeuvreRepository;
//import utils.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainOeuvreDAO implements MainOeuvreRepository {
//
//    private static final String INSERT_MAIN_OEUVRE_SQL = "INSERT INTO main_oeuvre (nom, taux_horaire, heures_travail, projet_id) VALUES (?, ?, ?, ?)";
//    private static final String SELECT_MAIN_OEUVRE_BY_ID = "SELECT * FROM main_oeuvre WHERE id = ?";
//    private static final String SELECT_ALL_MAIN_OEUVRES = "SELECT * FROM main_oeuvre";
//    private static final String UPDATE_MAIN_OEUVRE_SQL = "UPDATE main_oeuvre SET nom = ?, taux_horaire = ?, heures_travail = ?, projet_id = ? WHERE id = ?";
//    private static final String DELETE_MAIN_OEUVRE_SQL = "DELETE FROM main_oeuvre WHERE id = ?";
//
//    private Connection connection;
//
//    public MainOeuvreDAO() {
//        this.connection = DatabaseConnection.getConnection();
//    }
//
//    @Override
//    public void insertMainOeuvre(MainOeuvre mainOeuvre) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MAIN_OEUVRE_SQL)) {
//            preparedStatement.setString(1, mainOeuvre.getNom());
//            preparedStatement.setDouble(2, mainOeuvre.getTauxHoraire());
//            preparedStatement.setDouble(3, mainOeuvre.getHeuresTravail());
//            preparedStatement.setInt(4, mainOeuvre.getProjet().getId());
//            preparedStatement.executeUpdate();
//        }
//    }
//
//    @Override
//    public MainOeuvre selectMainOeuvre(int id) throws SQLException {
//        MainOeuvre mainOeuvre = null;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAIN_OEUVRE_BY_ID)) {
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                mainOeuvre = new MainOeuvre(
//                        rs.getInt("id"),
//                        rs.getString("nom"),
//                        rs.getDouble("taux_horaire"),
//                        rs.getDouble("heures_travail"),
//                        new Projet(rs.getInt("projet_id")) // Assuming a simple constructor for Projet
//                );
//            }
//        }
//        return mainOeuvre;
//    }
//
//    @Override
//    public List<MainOeuvre> selectAllMainOeuvres() throws SQLException {
//        List<MainOeuvre> mainOeuvres = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MAIN_OEUVRES)) {
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                mainOeuvres.add(new MainOeuvre(
//                        rs.getInt("id"),
//                        rs.getString("nom"),
//                        rs.getDouble("taux_horaire"),
//                        rs.getDouble("heures_travail"),
//                        new Projet(rs.getInt("projet_id")) // Assuming a simple constructor for Projet
//                ));
//            }
//        }
//        return mainOeuvres;
//    }
//
//    @Override
//    public boolean updateMainOeuvre(MainOeuvre mainOeuvre) throws SQLException {
//        boolean rowUpdated;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MAIN_OEUVRE_SQL)) {
//            preparedStatement.setString(1, mainOeuvre.getNom());
//            preparedStatement.setDouble(2, mainOeuvre.getTauxHoraire());
//            preparedStatement.setDouble(3, mainOeuvre.getHeuresTravail());
//            preparedStatement.setInt(4, mainOeuvre.getProjet().getId());
//            preparedStatement.setInt(5, mainOeuvre.getId());
//            rowUpdated = preparedStatement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }
//
//    @Override
//    public boolean deleteMainOeuvre(int id) throws SQLException {
//        boolean rowDeleted;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MAIN_OEUVRE_SQL)) {
//            preparedStatement.setInt(1, id);
//            rowDeleted = preparedStatement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
//}
