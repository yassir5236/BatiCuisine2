//package dao;
//
//import model.Materiau;
//import model.Enum.TypeComposant;
//import model.Projet;
//import repository.MateriauRepository;
//import utils.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MateriauDAO implements MateriauRepository {
//
//    private static final String INSERT_MATERIAU_SQL = "INSERT INTO materiau (nom, type_composant, taux_tva, projet_id, cout_unitaire, quantite, cout_transport, coefficient_quantite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//    private static final String SELECT_MATERIAU_BY_ID = "SELECT * FROM materiau WHERE id = ?";
//    private static final String SELECT_ALL_MATERIAUX = "SELECT * FROM materiau";
//    private static final String UPDATE_MATERIAU_SQL = "UPDATE materiau SET nom = ?, type_composant = ?, taux_tva = ?, projet_id = ?, cout_unitaire = ?, quantite = ?, cout_transport = ?, coefficient_quantite = ? WHERE id = ?";
//    private static final String DELETE_MATERIAU_SQL = "DELETE FROM materiau WHERE id = ?";
//
//    private Connection connection;
//
//    public MateriauDAO() {
//        this.connection = DatabaseConnection.getConnection();
//    }
//
//    @Override
//    public void insertMateriau(Materiau materiau) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATERIAU_SQL)) {
//            preparedStatement.setString(1, materiau.getNom());
//            preparedStatement.setString(2, materiau.getTypeComposant().name());
//            preparedStatement.setDouble(3, materiau.getTauxTVA());
//            preparedStatement.setInt(4, materiau.getProjet().getId());
//            preparedStatement.setDouble(5, materiau.getCoutUnitaire());
//            preparedStatement.setDouble(6, materiau.getQuantite());
//            preparedStatement.setDouble(7, materiau.getCoutTransport());
//            preparedStatement.setDouble(8, materiau.getCoefficientQuantite());
//            preparedStatement.executeUpdate();
//        }
//    }
//
//    @Override
//    public Materiau selectMateriau(int id) throws SQLException {
//        Materiau materiau = null;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATERIAU_BY_ID)) {
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                materiau = new Materiau(
//                        rs.getInt("id"),
//                        rs.getString("nom"),
//                        TypeComposant.valueOf(rs.getString("type_composant")),
//                        rs.getDouble("taux_tva"),
//                        new Projet(rs.getInt("projet_id")), // Assuming you have a Projet object
//                        rs.getDouble("cout_unitaire"),
//                        rs.getDouble("quantite"),
//                        rs.getDouble("cout_transport"),
//                        rs.getDouble("coefficient_quantite")
//                );
//            }
//        }
//        return materiau;
//    }
//
//    @Override
//    public List<Materiau> selectAllMateriaux() throws SQLException {
//        List<Materiau> materiaux = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MATERIAUX)) {
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                materiaux.add(new Materiau(
//                        rs.getInt("id"),
//                        rs.getString("nom"),
//                        TypeComposant.valueOf(rs.getString("type_composant")),
//                        rs.getDouble("taux_tva"),
//                        new Projet(rs.getInt("projet_id")), // Assuming you have a Projet object
//                        rs.getDouble("cout_unitaire"),
//                        rs.getDouble("quantite"),
//                        rs.getDouble("cout_transport"),
//                        rs.getDouble("coefficient_quantite")
//                ));
//            }
//        }
//        return materiaux;
//    }
//
//    @Override
//    public boolean updateMateriau(Materiau materiau) throws SQLException {
//        boolean rowUpdated;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MATERIAU_SQL)) {
//            preparedStatement.setString(1, materiau.getNom());
//            preparedStatement.setString(2, materiau.getTypeComposant().name());
//            preparedStatement.setDouble(3, materiau.getTauxTVA());
//            preparedStatement.setInt(4, materiau.getProjet().getId());
//            preparedStatement.setDouble(5, materiau.getCoutUnitaire());
//            preparedStatement.setDouble(6, materiau.getQuantite());
//            preparedStatement.setDouble(7, materiau.getCoutTransport());
//            preparedStatement.setDouble(8, materiau.getCoefficientQuantite());
//            preparedStatement.setInt(9, materiau.getId());
//            rowUpdated = preparedStatement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }
//
//    @Override
//    public boolean deleteMateriau(int id) throws SQLException {
//        boolean rowDeleted;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MATERIAU_SQL)) {
//            preparedStatement.setInt(1, id);
//            rowDeleted = preparedStatement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
//}
