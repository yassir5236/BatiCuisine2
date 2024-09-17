package service;

import dao.ClientDAO;
import model.Client;
import service.interfaces.IClientService;

import java.sql.SQLException;
import java.util.List;

public class ClientService implements IClientService {
    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    @Override
    public void addClient(Client client) {
        try {
            clientDAO.insertClient(client);
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
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
        try {
            return clientDAO.selectClient(id);
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
        try {
            return clientDAO.selectAllClients();
        } catch (SQLException e) {
            System.out.println("Error fetching all clients: " + e.getMessage());
            return null;
        }
    }
}
