package dao;


import model.Enum.TypeComposant;
import model.MainOeuvre;
import model.Projet;
import repository.MainOeuvreRepository;
import service.ProjetService;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainOeuvreDAO implements MainOeuvreRepository {

    private static final String INSERT_MAIN_OEUVRE_SQL = "INSERT INTO main_oeuvre (nom,type_composant, taux_tva,projet_id, taux_horaire, heures_travail,productivite_ouvrier ) VALUES (?, ?::type_composant, ?, ?, ?, ?, ?)";
    private static final String SELECT_MAIN_OEUVRE_BY_ID = "SELECT * FROM main_oeuvre WHERE id = ?";
    private static final String SELECT_ALL_MAIN_OEUVRES = "SELECT * FROM main_oeuvre";
    private static final String UPDATE_MAIN_OEUVRE_SQL = "UPDATE main_oeuvre SET nom = ?, taux_horaire = ?, heures_travail = ?, projet_id = ? WHERE id = ?";
    private static final String DELETE_MAIN_OEUVRE_SQL = "DELETE FROM main_oeuvre WHERE id = ?";

    private Connection connection;

    public MainOeuvreDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void insertMainOeuvre(MainOeuvre mainOeuvre) throws SQLException {
//        if (MainOeuvre == null) {
//            throw new IllegalArgumentException("Le projet associé au matériau ne peut pas être null.");
//        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MAIN_OEUVRE_SQL)) {
            preparedStatement.setString(1, mainOeuvre.getNom());
            preparedStatement.setString(2, mainOeuvre.getTypeComposant().name());
            preparedStatement.setDouble(3, mainOeuvre.getTauxTVA());
            preparedStatement.setInt(4, mainOeuvre.getProjet().getId());

            preparedStatement.setDouble(5, mainOeuvre.getTauxHoraire());

            preparedStatement.setDouble(6, mainOeuvre.getHeuresTravail());
            preparedStatement.setDouble(7, mainOeuvre.getProductiviteOuvrier());

            preparedStatement.executeUpdate();
        }
    }



    public List<MainOeuvre> selectAllMainOeuvres() {
        List<MainOeuvre> mainOeuvres = new ArrayList<>();
        String SELECT_ALL_MAIN_OEUVRES_SQL = "SELECT * FROM main_oeuvre";

        ProjetService projetService = new ProjetService();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_ALL_MAIN_OEUVRES_SQL);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                TypeComposant typeComposant = TypeComposant.valueOf(rs.getString("type_composant").toUpperCase());
                double tauxTVA = rs.getDouble("taux_tva");
                int projetId = rs.getInt("projet_id");
                double tauxHoraire = rs.getDouble("taux_horaire");
                double heuresTravail = rs.getDouble("heures_travail");
                double productiviteOuvrier = rs.getDouble("productivite_ouvrier");

                // Assurez-vous que le projet associé à la main-d'œuvre est récupéré
                Projet projet = projetService.selectProjetById(projetId);

                MainOeuvre mainOeuvre = new MainOeuvre(id, nom, typeComposant, tauxTVA, projet, tauxHoraire, heuresTravail, productiviteOuvrier);
                mainOeuvres.add(mainOeuvre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mainOeuvres;
    }






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





}
