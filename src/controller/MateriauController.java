package controller;

import java.util.List;
import java.util.Scanner;


import model.Client;
import model.Enum.TypeComposant;
import model.Materiau;
import service.ClientService;
import service.MateriauService;

public class MateriauController {
    private final MateriauService materiauService;
    private final Scanner scanner;

    // Constructor to initialize services and scanner
    public MateriauController() {
        this.materiauService = new MateriauService();
        this.scanner = new Scanner(System.in);
    }

    public void addMateriau() {
        System.out.println("Entrez le nom du matériau");
        String nom = scanner.nextLine();


        System.out.println("Entrez le coût unitaire de ce matériau (€/m²:");
        double coutUnitaire = scanner.nextDouble();


        System.out.println("Entrez la quantité de ce matériau:");
        double quantite = scanner.nextDouble();


        System.out.println("Entrez le coût de transport de ce matériau (€)");
        double coutTransport = scanner.nextDouble();

        System.out.println("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité):");
        double coefficientQuantite = scanner.nextDouble();

        TypeComposant typeComposant=null;

        System.out.println("Entrez tauxTVA:");
        double tauxTVA = scanner.nextDouble();

        Materiau materiau = new Materiau(nom, typeComposant,  tauxTVA,  projet,
         coutUnitaire,  quantite,  coutTransport,  coefficientQuantite);
        materiauService.ajouterMateriau(materiau);
        System.out.println("Client added successfully.");
    }

//    public void updateClient() {
//        System.out.println("Enter the ID of the materiau:");
//        int id = scanner.nextInt();
//        scanner.nextLine();  // Consume newline
//
//        System.out.println("Enter the new name of the materiau:");
//        String nom = scanner.nextLine();
//
//        System.out.println("Enter the new address of the materiau:");
//        String adresse = scanner.nextLine();
//
//        System.out.println("Enter the new telephone number of the materiau:");
//        String telephone = scanner.nextLine();
//
//        System.out.println("Is the materiau a professional (true/false)?");
//        boolean estProfessionnel = scanner.nextBoolean();
//
//        Client materiau = new Client(id, nom, adresse, telephone, estProfessionnel);
//        boolean updated = materiauService.updateClient(materiau);
//
//        if (updated) {
//            System.out.println("Client updated successfully.");
//        } else {
//            System.out.println("Client with ID " + id + " not found.");
//        }
//    }

//    public void getClient() {
//        System.out.println("Enter the ID of the materiau:");
//        int id = scanner.nextInt();
//        scanner.nextLine();  // Consume newline
//
//        Client materiau = materiauService.getClient(id);
//        if (materiau != null) {
//            System.out.println("Client details:");
//            System.out.println("ID: " + materiau.getId());
//            System.out.println("Name: " + materiau.getNom());
//            System.out.println("Address: " + materiau.getAdresse());
//            System.out.println("Phone: " + materiau.getTelephone());
//            System.out.println("Professional: " + materiau.isEstProfessionnel());
//        } else {
//            System.out.println("Client with ID " + id + " not found.");
//        }
//    }

//    public void deleteClient() {
//        System.out.println("Enter the ID of the materiau:");
//        int id = scanner.nextInt();
//        scanner.nextLine();  // Consume newline
//
//        boolean deleted = materiauService.deleteClient(id);
//        if (deleted) {
//            System.out.println("Client with ID " + id + " successfully deleted.");
//        } else {
//            System.out.println("Client with ID " + id + " not found.");
//        }
//    }

//    public void getAllClients() {
//        List<Client> materiaus = materiauService.getAllClients();
//        if (materiaus != null && !materiaus.isEmpty()) {
//            System.out.println("List of all materiaus:");
//            for (Client materiau : materiaus) {
//                System.out.println(materiau);
//            }
//        } else {
//            System.out.println("No materiaus found.");
//        }
//    }


//    public void recherchClient() {
//        System.out.println("--- Recherche de materiau existant ---");
//        System.out.println("Entrez le nom du materiau : ");
//        String nom = scanner.nextLine();
//
//        Client materiau = materiauService.rechercheClient(nom);
//        if (materiau != null) {
//            System.out.println("Name: " + materiau.getNom());
//            System.out.println("Address: " + materiau.getAdresse());
//            System.out.println("Phone: " + materiau.getTelephone());
//            System.out.println("Professional: " + materiau.isEstProfessionnel());
//        }
//
//
//    }
}
