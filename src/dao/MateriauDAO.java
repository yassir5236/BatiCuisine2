    package dao;

    import model.Materiau;
    import model.Enum.TypeComposant;
    import model.Projet;
    import repository.MateriauRepository;
    import service.ProjetService;
    import utils.DatabaseConnection;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class MateriauDAO implements MateriauRepository {

        private static final String INSERT_MATERIAU_SQL = "INSERT INTO materiau (nom, type_composant, taux_tva, projet_id, cout_unitaire, quantite, cout_transport, coefficient_quantite) VALUES (?, ?::type_composant, ?, ?, ?, ?, ?, ?)";
        private static final String SELECT_MATERIAU_BY_ID = "SELECT * FROM materiau WHERE id = ?";
        private static final String SELECT_ALL_MATERIAUX = "SELECT * FROM materiau";
        private static final String UPDATE_MATERIAU_SQL = "UPDATE materiau SET nom = ?, type_composant = ?, taux_tva = ?, projet_id = ?, cout_unitaire = ?, quantite = ?, cout_transport = ?, coefficient_quantite = ? WHERE id = ?";
        private static final String DELETE_MATERIAU_SQL = "DELETE FROM materiau WHERE id = ?";

        private Connection connection;

        public MateriauDAO() {
            this.connection = DatabaseConnection.getConnection();
        }

        public void insertMateriau(Materiau materiau) throws SQLException {
            if (materiau.getProjet() == null) {
                throw new IllegalArgumentException("Le projet associé au matériau ne peut pas être null.");
            }

            String typeComposantString = (materiau.getTypeComposant() != null) ? materiau.getTypeComposant().name() : null;

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATERIAU_SQL)) {
                preparedStatement.setString(1, materiau.getNom()); // Assurez-vous que getNom() renvoie une chaîne
    //            preparedStatement.setString(2, materiau.getTypeComposant().name().toLowerCase()); // Convertir l'énum en chaîne et gérer les valeurs null
                preparedStatement.setString(2, materiau.getTypeComposant().name());

                preparedStatement.setDouble(3, materiau.getTauxTVA()); // Assurez-vous que getTauxTVA() renvoie un double
                preparedStatement.setInt(4, materiau.getProjet().getId()); // Assurez-vous que getProjet() renvoie un projet avec un ID valide
                preparedStatement.setDouble(5, materiau.getCoutUnitaire()); // Assurez-vous que getCoutUnitaire() renvoie un double
                preparedStatement.setDouble(6, materiau.getQuantite()); // Assurez-vous que getQuantite() renvoie un double
                preparedStatement.setDouble(7, materiau.getCoutTransport()); // Assurez-vous que getCoutTransport() renvoie un double
                preparedStatement.setDouble(8, materiau.getCoefficientQuantite()); // Assurez-vous que getCoefficientQuantite() renvoie un double
                preparedStatement.executeUpdate();
            }
        }

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

        public List<Materiau> selectAllMateriaux() {
            List<Materiau> materiaux = new ArrayList<>();
            ProjetService projetService = new ProjetService();

            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(SELECT_ALL_MATERIAUX);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nom");
                    TypeComposant typeComposant = TypeComposant.valueOf(rs.getString("type_composant").toUpperCase());
                    double tauxTVA = rs.getDouble("taux_tva");
                    int projetId = rs.getInt("projet_id");
                    double coutUnitaire = rs.getDouble("cout_unitaire");
                    double quantite = rs.getDouble("quantite");
                    double coutTransport = rs.getDouble("cout_transport");
                    double coefficientQuantite = rs.getDouble("coefficient_quantite");


                    // Assurez-vous que le projet associé au matériau est récupéré
                    Projet projet = projetService.selectProjetById(projetId);
                    if (projet == null) {
                        System.out.println("not found");
                    }

                    Materiau materiau = new Materiau(id, nom, typeComposant, tauxTVA, projet, coutUnitaire, quantite, coutTransport, coefficientQuantite);
                    materiaux.add(materiau);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return materiaux;
        }

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

//    @Override
//    public boolean deleteMateriau(int id) throws SQLException {
//        boolean rowDeleted;
//        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MATERIAU_SQL)) {
//            preparedStatement.setInt(1, id);
//            rowDeleted = preparedStatement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
}
