//package controller;
//
//import model.Client;
//import model.Projet;
//import service.ClientService;
//import service.ProjetService;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class ProjetController {
//    private final ProjetService projetService;
//    private final Scanner scanner;
//
//    // Constructor to initialize services and scanner
//    public ProjetController() {
//        this.projetService = new ProjetService();
//        this.scanner = new Scanner(System.in);
//    }
//
//    public void addProjet() {
//        System.out.println("Entrez le nom du projet:");
//        String nom_projet = scanner.nextLine();
//
//        System.out.println("Entrez la surface de la cuisine (en m²):");
//        String surface = scanner.nextLine();
//
//        Projet projet = new Projet(nom_projet, surface);
//        projetService.addProjet();
//
//
//
//
//
//
//    }
////
////    public void updateClient() {
////        System.out.println("Enter the ID of the client:");
////        int id = scanner.nextInt();
////        scanner.nextLine();  // Consume newline
////
////        System.out.println("Enter the new name of the client:");
////        String nom = scanner.nextLine();
////
////        System.out.println("Enter the new address of the client:");
////        String adresse = scanner.nextLine();
////
////        System.out.println("Enter the new telephone number of the client:");
////        String telephone = scanner.nextLine();
////
////        System.out.println("Is the client a professional (true/false)?");
////        boolean estProfessionnel = scanner.nextBoolean();
////
////        Client client = new Client(id, nom, adresse, telephone, estProfessionnel);
////        boolean updated = clientService.updateClient(client);
////
////        if (updated) {
////            System.out.println("Client updated successfully.");
////        } else {
////            System.out.println("Client with ID " + id + " not found.");
////        }
////    }
////
////    public void getClient() {
////        System.out.println("Enter the ID of the client:");
////        int id = scanner.nextInt();
////        scanner.nextLine();  // Consume newline
////
////        Client client = clientService.getClient(id);
////        if (client != null) {
////            System.out.println("Client details:");
////            System.out.println("ID: " + client.getId());
////            System.out.println("Name: " + client.getNom());
////            System.out.println("Address: " + client.getAdresse());
////            System.out.println("Phone: " + client.getTelephone());
////            System.out.println("Professional: " + client.isEstProfessionnel());
////        } else {
////            System.out.println("Client with ID " + id + " not found.");
////        }
////    }
////
////    public void deleteClient() {
////        System.out.println("Enter the ID of the client:");
////        int id = scanner.nextInt();
////        scanner.nextLine();  // Consume newline
////
////        boolean deleted = clientService.deleteClient(id);
////        if (deleted) {
////            System.out.println("Client with ID " + id + " successfully deleted.");
////        } else {
////            System.out.println("Client with ID " + id + " not found.");
////        }
////    }
////
////    public void getAllClients() {
////        List<Client> clients = clientService.getAllClients();
////        if (clients != null && !clients.isEmpty()) {
////            System.out.println("List of all clients:");
////            for (Client client : clients) {
////                System.out.println(client);
////            }
////        } else {
////            System.out.println("No clients found.");
////        }
////    }
////
////
////    public void recherchClient() {
////        System.out.println("--- Recherche de client existant ---");
////        System.out.println("Entrez le nom du client : ");
////        String nom = scanner.nextLine();
////
////        Client client = clientService.rechercheClient(nom);
////        if (client != null) {
////            System.out.println("Name: " + client.getNom());
////            System.out.println("Address: " + client.getAdresse());
////            System.out.println("Phone: " + client.getTelephone());
////            System.out.println("Professional: " + client.isEstProfessionnel());
////        }
////
////
////    }
//}
