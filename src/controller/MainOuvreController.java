package controller;

import model.Enum.TypeComposant;
import model.MainOeuvre;
import model.Projet;
import model.dto.MainDoeuvreDto;
import model.dto.MateriauDto;
import service.MainOeuvreService;
import service.MateriauService;
import service.ProjetService;

import java.util.Scanner;

public class MainOuvreController {
    private final MainOeuvreService mainOeuvreService;
    private final Scanner scanner;
    private final ProjetService projetService;
    private final MainOeuvreDAO mainOeuvreDAO;



    public MainOuvreController() {
        this.mainOeuvreService = new MainOeuvreService();
        this.scanner = new Scanner(System.in);
        this.projetService= new ProjetService();
        this.mainOeuvreDAO= new MainOeuvreDAO();

    }


    public void addMainOuvre() {
        System.out.println("Entrez le nom du MainOuvre");
        String nom = scanner.nextLine();

        System.out.println("Entrez le type de composant (MATERIAU, MAIN_DOEUVRE):");
        String typeComposantStr = scanner.nextLine().toUpperCase();

        TypeComposant typeComposant;
        try {
            typeComposant = TypeComposant.valueOf(typeComposantStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Type de composant invalide.");
            return;
        }

        System.out.println("Entrez tauxTVA:");
        double tauxTVA = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Entrez l'ID du projet:");
        int projetId = scanner.nextInt();

        Projet projet = projetService.selectProjetById(projetId);
        if (projet == null) {
            System.out.println("Le projet avec l'ID spécifié n'existe pas.");
            return;
        }



        System.out.println("Entrez le tauxHoraire (€/m²):");
        double tauxHoraire = scanner.nextDouble();

        System.out.println("Entrez heuresTravaile:");
        double heuresTravaile = scanner.nextDouble();

        System.out.println("Entrez productiviteOuvrier de ce MainOuvre (€):");
        double productiviteOuvrier = scanner.nextDouble();






        final MainDoeuvreDto mainDoeuvreDto = new MainDoeuvreDto(
                 nom,
                 typeComposant,
                 tauxTVA,
                 projetId ,
                 tauxHoraire,
                 heuresTravaile,
                 productiviteOuvrier

        );

        mainOeuvreService.ajouterMainOeuvre(mainDoeuvreDto);
        System.out.println("MainOeuvre ajouté avec succès.");
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
