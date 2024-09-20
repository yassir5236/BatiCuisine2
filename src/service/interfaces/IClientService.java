package service.interfaces;

import model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    int  addClient(Client client);

    boolean updateClient(Client client);

    Client getClient(int id);

    boolean deleteClient(int id);

    List<Client> getAllClients();

    Client rechercheClient(String nom);
}
