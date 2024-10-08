package repository;

import model.Client;

import java.util.List;
import java.util.Optional;




import model.Client;
import java.sql.SQLException;
import java.util.List;

public interface ClientRepository {

    int insertClient(Client client) throws SQLException;

    Optional<Client> selectClient(int id) throws SQLException;

    Optional <List<Client>> selectAllClients() throws SQLException;

    boolean updateClient(Client client) throws SQLException;

    boolean deleteClient(int id) throws SQLException;

    Optional<Client> rechercheClient(String nom) throws SQLException;

}