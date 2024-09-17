package repository;

import model.Client;

import java.util.List;
import java.util.Optional;




import model.Client;
import java.sql.SQLException;
import java.util.List;

public interface ClientRepository {

    void insertClient(Client client) throws SQLException;

    Client selectClient(int id) throws SQLException;

    List<Client> selectAllClients() throws SQLException;

    boolean updateClient(Client client) throws SQLException;

    boolean deleteClient(int id) throws SQLException;


}