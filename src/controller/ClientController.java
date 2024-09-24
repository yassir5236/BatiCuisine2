

package controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.Client;
import service.ClientService;

public class ClientController {
    private final ClientService clientService;
    private final Scanner scanner;

    public ClientController() {
        this.clientService = new ClientService();
        this.scanner = new Scanner(System.in);
    }

    private boolean isValidName(String nom) {
        return nom != null && !nom.trim().isEmpty();
    }

    private boolean isValidAddress(String adresse) {
        return adresse != null && !adresse.trim().isEmpty();
    }

    private boolean isValidPhoneNumber(String telephone) {
        return telephone != null && Pattern.matches("\\d{10}", telephone);
    }

    public int addClient() {
        String nom;
        do {
            System.out.println("Entrer le nom du client:");
            nom = scanner.nextLine();
        } while (!isValidName(nom));

        String adresse;
        do {
            System.out.println("Entrer l'adresse du client:");
            adresse = scanner.nextLine();
        } while (!isValidAddress(adresse));

        String telephone;
        do {
            System.out.println("Entrer le telephone du client:");
            telephone = scanner.nextLine();
        } while (!isValidPhoneNumber(telephone));

        System.out.println("Est ce que le client est Professionel  (true/false)?");
        boolean estProfessionnel = scanner.nextLine().equals("true");

        Client client = new Client(nom, adresse, telephone, estProfessionnel);
        int idNewClient = clientService.addClient(client);
        System.out.println("Client ajouter avec  success.");

        Client clientFound = clientService.rechercheClient(nom);

        if (estProfessionnel) {
            System.out.println("prendre en compte que ce client est professionnel vous voulez appliquer des remises ? (true/false)");
            boolean choix = scanner.nextLine().equals("true");
            if (choix) {
                double clientRemise;
                do{
                System.out.println("Entrer remise pour ce client en (%)");
                 clientRemise = scanner.nextDouble();

                }while(!scanner.hasNextDouble());
                clientService.AddRemise(clientRemise, clientFound.getId());
            }
        }

        return idNewClient;
    }

    public void updateClient() {
        System.out.println("Entrer le id du  client:");
        int id = scanner.nextInt();
        scanner.nextLine();

        String nom;
        do {
            System.out.println("Entrer le nouveau nom du  client:");
            nom = scanner.nextLine();
        } while (!isValidName(nom));

        String adresse;
        do {
            System.out.println("Entrer la nouvelle  address du client:");
            adresse = scanner.nextLine();
        } while (!isValidAddress(adresse));

        String telephone;
        do {
            System.out.println("Entrer le nouveau telephone du client:");
            telephone = scanner.nextLine();
        } while (!isValidPhoneNumber(telephone));

        System.out.println("Est ce que le client est Professionel (true/false)?");
        boolean estProfessionnel = scanner.nextLine().equals("true");

        Client client = new Client(id, nom, adresse, telephone, estProfessionnel);
        boolean updated = clientService.updateClient(client);

        if (updated) {
            System.out.println("Client mise a jour success.");
        } else {
            System.out.println("Client with ID " + id + " not found.");
        }
    }
    public void getClient() {
        System.out.println("Entrer le id du client :");
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
            System.out.println("Client with ID " + id + " pas trouve.");
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
            System.out.println("List du toutes les client :");
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
                System.out.println("prendre en compte que ce client est professionnel vous voulez appliquer des remises ? (true/false)");
                boolean choix = scanner.nextLine().equals("true");
                if (choix) {
                    double clientRemise = -1;
                    do{
                        System.out.println("Entrer remise pour ce client en (%)");
                        while (!scanner.hasNextDouble()){
                            System.out.println("Entrée invalide. Veuillez entrer un nombre valide pour la remise.");
                            scanner.next();
                        }

                        clientRemise = scanner.nextDouble();

                    }while(clientRemise<-1);

                    clientService.AddRemise(clientRemise, client.getId());
                }
            }
            return  client.getId();

        }
        return -1;
    }
}
