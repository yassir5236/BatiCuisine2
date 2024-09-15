package repository;

import model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    void addClient(Client client) throws Exception;
    Optional<Client> getClientById(int id) throws Exception;
    List<Client> getAllClients() throws Exception;
    void updateClient(Client client) throws Exception;
    void deleteClient(int id) throws Exception;
}
