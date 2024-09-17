package service.interfaces;

import model.Client;

import java.util.List;

public interface IClientService {
    void addClient(Client client);

    boolean updateClient(Client client);

    Client getClient(int id);

    boolean deleteClient(int id);

    List<Client> getAllClients();
}
