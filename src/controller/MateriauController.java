package controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Projet;



import model.Client;
import model.Enum.TypeComposant;
import model.Materiau;
import model.dto.MateriauDto;
import service.ClientService;
import service.MateriauService;
import service.ProjetService;

public class MateriauController {
    private final MateriauService materiauService;
    private final Scanner scanner;
    private final ProjetService projetService;


    public MateriauController() {
        this.materiauService = new MateriauService();
        this.scanner = new Scanner(System.in);
        this.projetService= new ProjetService();
    }


    public void addMateriau(int idProjet) {

        System.out.println("Entrez le nom du matériau");
        String nom = scanner.nextLine();

        System.out.println("Entrez le coût unitaire de ce matériau (€/m²):");
        double coutUnitaire = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Entrez la quantité de ce matériau:");
        double quantite = scanner.nextDouble();

        System.out.println("Entrez le coût de transport de ce matériau (€):");
        double coutTransport = scanner.nextDouble();

        System.out.println("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité):");
        double coefficientQuantite = scanner.nextDouble();

        System.out.println("Entrez tauxTVA:");
        double tauxTVA = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Entrez le type de composant (MATERIAU, MAIN_DOEUVRE):");
        String typeComposantStr = scanner.nextLine().toUpperCase();

        TypeComposant typeComposant;
        try {
            typeComposant = TypeComposant.valueOf(typeComposantStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Type de composant invalide.");
            return;
        }

//        System.out.println("Entrez l'ID du projet:");
//        int projetId = scanner.nextInt();
//        scanner.nextLine();

        int projetId = idProjet;

        Projet projet = projetService.selectProjetById(projetId);
        if (projet == null) {
            System.out.println("Le projet avec l'ID spécifié n'existe pas.");
            return;
        }

        final MateriauDto materiauDto = new MateriauDto(
                nom,
                typeComposant,
                tauxTVA,
                projetId,
                coutUnitaire,
                quantite,
                coutTransport,
                coefficientQuantite
        );

        materiauService.ajouterMateriau(materiauDto);
        System.out.println("Materiau ajouté avec succès.\n");
        System.out.println("voulez vous inserer un autre materiau (true/false) ?");
        boolean choix = scanner.nextBoolean();
        scanner.nextLine();
        if (choix) {
            addMateriau(idProjet);
        }


    }







    public void addMateriau2(int idProjet) {

        System.out.println("Entrez le nom du matériau");
        String nom = scanner.nextLine();

        System.out.println("Entrez le coût unitaire de ce matériau (€/m²):");
        double coutUnitaire = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Entrez la quantité de ce matériau:");
        double quantite = scanner.nextDouble();

        System.out.println("Entrez le coût de transport de ce matériau (€):");
        double coutTransport = scanner.nextDouble();

        System.out.println("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité):");
        double coefficientQuantite = scanner.nextDouble();

        System.out.println("Entrez tauxTVA:");
        double tauxTVA = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Entrez le type de composant (MATERIAU, MAIN_DOEUVRE):");
        String typeComposantStr = scanner.nextLine().toUpperCase();

        TypeComposant typeComposant;
        try {
            typeComposant = TypeComposant.valueOf(typeComposantStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Type de composant invalide.");
            return;
        }

//        System.out.println("Entrez l'ID du projet:");
//        int projetId = scanner.nextInt();
//        scanner.nextLine();

        int projetId = idProjet;



        Projet projet = projetService.selectProjetById(projetId);
        if (projet == null) {
            System.out.println("Le projet avec l'ID spécifié n'existe pas.");
            return;
        }

        final MateriauDto materiauDto = new MateriauDto(
                nom,
                typeComposant,
                tauxTVA,
                projetId,
                coutUnitaire,
                quantite,
                coutTransport,
                coefficientQuantite
        );


        materiauService.ajouterMateriau(materiauDto);
        System.out.println("Materiau ajouté avec succès.\n");
        System.out.println("voulez vous inserer un autre materiau (true/false) ?");
        boolean choix = scanner.nextBoolean();
        scanner.nextLine();
        if (choix) {
            addMateriau(idProjet);
        }


    }
















    public void afficherDetailDesCoutsMateriau(int  idProjet) {

        List<Materiau> materiaux = materiauService.getMateriauxByProjet(idProjet);

        if (materiaux.isEmpty()) {
            System.out.println("Aucun matériau trouvé pour ce projet.");
            return;
        }

        // Détail des coûts
        System.out.println("--- Détail des Coûts ---");
        double coutTotalMateriaux = materiaux.stream()
                .peek(materiau -> {
                    double coutMateriau = materiau.getQuantite() * materiau.getCoutUnitaire();
                    System.out.printf("%s : %.2f € (quantité : %.2f %s, coût unitaire : %.2f €/unité, transport : %.2f €)\n",
                            materiau.getNom(),
                            coutMateriau + materiau.getCoutTransport(),
                            materiau.getQuantite(),
                            "m²",
                            materiau.getCoutUnitaire(),
                            materiau.getCoutTransport());
                })
                .mapToDouble(materiau -> (materiau.getQuantite() * materiau.getCoutUnitaire()) + materiau.getCoutTransport())
                .sum();


        System.out.printf("\n**Coût total des matériaux avant TVA : %.2f €**\n", coutTotalMateriaux);


        double coutTotalAvecTVA = materiaux.stream()
                .mapToDouble(materiau -> {
                    double coutMateriau = (materiau.getQuantite() * materiau.getCoutUnitaire()) + materiau.getCoutTransport();
                    return coutMateriau * (1 + materiau.getTauxTVA() / 100);
                })
                .sum();

        System.out.printf("**Coût total des matériaux avec TVA (20%%) : %.2f €**\n", coutTotalAvecTVA);
    }


























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
