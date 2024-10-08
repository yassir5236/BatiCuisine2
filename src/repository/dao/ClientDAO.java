package repository.dao;



import model.Client;
import repository.ClientRepository;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO implements ClientRepository {

    private static final String INSERT_CLIENT_SQL = "INSERT INTO client (nom, adresse, telephone, est_professionnel) VALUES (?, ?, ?, ?) RETURNING id ";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM client WHERE id = ?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM client";
    private static final String UPDATE_CLIENT_SQL = "UPDATE client SET nom = ?, adresse = ?, telephone = ?, est_professionnel = ? WHERE id = ?";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM client WHERE id = ?";
    private static final String UPDATE_REMISE = "UPDATE client SET remise = ? WHERE id = ?";

    private Connection connection;

    public ClientDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public int  insertClient(Client client) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {

            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getAdresse());
            preparedStatement.setString(3, client.getTelephone());
            preparedStatement.setBoolean(4, client.isEstProfessionnel());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1;
    }

    @Override
    public Optional<Client> selectClient(int id) throws SQLException {
        Client client = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("est_professionnel")
                );
                return Optional.of(client);

            }
        }
        return Optional.empty();
    }

    @Override
    public Optional <List<Client>> selectAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("est_professionnel")
                ));
            }
        }
        return clients.isEmpty() ? Optional.empty() : Optional.of(clients);
    }

    @Override
    public boolean updateClient(Client client) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getAdresse());
            preparedStatement.setString(3, client.getTelephone());
            preparedStatement.setBoolean(4, client.isEstProfessionnel());
            preparedStatement.setInt(5, client.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteClient(int id) throws SQLException {
        boolean rowDeleted;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


    @Override
    public Optional<Client> rechercheClient(String nom) throws SQLException {
        String query = "select * from client where nom = ?";
        Client client = null;
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,nom);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("est_professionnel")

                );
                return Optional.of(client);
            }else{
                System.out.println("Client n'existe pas !");
            }

        }catch(SQLException e){
            e.printStackTrace();

        }
        return Optional.empty();
    }


    public void AddRemise(double clientRemise, int idClient) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_REMISE)){
            ps.setDouble(1, clientRemise);
            ps.setInt(2,idClient);
            ps.executeUpdate();
            System.out.println("Remise added !");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
