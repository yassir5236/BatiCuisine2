package service;

import repository.dao.ClientDAO;
import model.Client;
import service.interfaces.IClientService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import  java.util.Optional;


public class ClientService implements IClientService {
    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    @Override
    public int addClient(Client client) {
        try {
            return clientDAO.insertClient(client);
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public boolean updateClient(Client client) {
        try {
            return clientDAO.updateClient(client);
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Client getClient(int id) {
        try {Optional<Client> client = clientDAO.selectClient(id);
            return client.orElse(new Client());
        } catch (SQLException e) {
            System.out.println("Error fetching client with ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteClient(int id) {
        try {
            return clientDAO.deleteClient(id);
        } catch (SQLException e) {
            System.out.println("Error deleting client with ID " + id + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Client> getAllClients() {
        try { Optional <List<Client>> client = clientDAO.selectAllClients();
            return client.orElse(new ArrayList<>());

        } catch (SQLException e) {
            System.out.println("Error fetching all clients: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Client rechercheClient(String nom){
        try {
            Optional<Client> client =  clientDAO.rechercheClient(nom);
            return client.orElse(null);

        } catch (SQLException e) {
            System.out.println("Error fetching recherche client: " + e.getMessage());
            return null;
        }
    }


    public void AddRemise(double clientRemise , int idClient){
        try{
            clientDAO.AddRemise(clientRemise, idClient);
        }catch (Exception e){
            System.out.println("Error adding remise: " + e.getMessage());
        }
    }

}
