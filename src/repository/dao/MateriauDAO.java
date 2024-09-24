    package repository.dao;

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

}
