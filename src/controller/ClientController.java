package controller;

import java.util.List;
import java.util.Scanner;


import model.Client;
import service.ClientService;

public class ClientController {
    private final ClientService clientService;
    private final Scanner scanner;

    public ClientController() {
        this.clientService = new ClientService();
        this.scanner = new Scanner(System.in);
    }

    public int addClient() {
        System.out.println("Enter the name of the client:");
        String nom = scanner.nextLine();

        System.out.println("Enter the address of the client:");
        String adresse = scanner.nextLine();

        System.out.println("Enter the telephone number of the client:");
        String telephone = scanner.nextLine();

        System.out.println("Is the client a professional (true/false)?");
        boolean estProfessionnel = scanner.nextBoolean();

        Client client = new Client(nom, adresse, telephone, estProfessionnel);
        int idNewClient = clientService.addClient(client);
        System.out.println("Client added successfully.");


        Client clientFound = clientService.rechercheClient(nom);

        if (estProfessionnel) {

            System.out.println("prendre en compte que ce client est proffissionel vous voulez appliquer des remises ? (true/false)");
            boolean choix = scanner.nextBoolean();
            if (choix) {
                System.out.println("Entrer remise pour ce client en (%)");
                double clientRemise= scanner.nextDouble();

                clientService.AddRemise(clientRemise,clientFound.getId());
            }

        }

        return idNewClient;
    }

    public void updateClient() {
        System.out.println("Enter the ID of the client:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new name of the client:");
        String nom = scanner.nextLine();

        System.out.println("Enter the new address of the client:");
        String adresse = scanner.nextLine();

        System.out.println("Enter the new telephone number of the client:");
        String telephone = scanner.nextLine();

        System.out.println("Is the client a professional (true/false)?");
        boolean estProfessionnel = scanner.nextBoolean();

        Client client = new Client(id, nom, adresse, telephone, estProfessionnel);
        boolean updated = clientService.updateClient(client);

        if (updated) {
            System.out.println("Client updated successfully.");
        } else {
            System.out.println("Client with ID " + id + " not found.");
        }
    }

    public void getClient() {
        System.out.println("Enter the ID of the client:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.getClient(id);
        if (client != null) {
            System.out.println("Client details:");
            System.out.println("ID: " + client.getId());
            System.out.println("Name: " + client.getNom());
            System.out.println("Address: " + client.getAdresse());
            System.out.println("Phone: " + client.getTelephone());
            System.out.println("Professional: " + client.isEstProfessionnel());
        } else {
            System.out.println("Client with ID " + id + " not found.");
        }
    }

    public void deleteClient() {
        System.out.println("Enter the ID of the client:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = clientService.deleteClient(id);
        if (deleted) {
            System.out.println("Client with ID " + id + " successfully deleted.");
        } else {
            System.out.println("Client with ID " + id + " not found.");
        }
    }

    public void getAllClients() {
        List<Client> clients = clientService.getAllClients();
        if (clients != null && !clients.isEmpty()) {
            System.out.println("List of all clients:");
            for (Client client : clients) {
                System.out.println(client);
            }
        } else {
            System.out.println("No clients found.");
        }
    }


    public int recherchClient() {
        System.out.println("--- Recherche de client existant ---");
        System.out.println("Entrez le nom du client : ");
        String nom = scanner.nextLine();

        Client client = clientService.rechercheClient(nom);
        if (client != null) {
            System.out.println("Name: " + client.getNom());
            System.out.println("Address: " + client.getAdresse());
            System.out.println("Phone: " + client.getTelephone());
            System.out.println("Professional: " + client.isEstProfessionnel());

            if (client.isEstProfessionnel()) {

                System.out.println("prendre en compte que ce client est proffissionel vous voulez appliquer des remises ? (true/false)");
                boolean choix = scanner.nextBoolean();
                if (choix) {
                    System.out.println("Entrer remise pour ce client en (%)");
                    double clientRemise= scanner.nextDouble();
                     clientService.AddRemise(clientRemise,client.getId());
                }

            }
        }
        return client.getId();


    }
}
