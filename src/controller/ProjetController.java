//package controller;
//
//import model.Client;
//import model.Enum.EtatProjet;
//import model.MainOeuvre;
//import model.Projet;
//import service.ClientService;
//import service.ProjetService;
//
//import java.util.List;
//import java.util.Scanner;
//
//import static java.lang.String.valueOf;
//
//public class ProjetController {
//    private final ProjetService projetService;
//    private final Scanner scanner;
//    private final MateriauController materiauController;
//    private double coutTotal;
//    private final MainOuvreController mainOuvreController;
//
//    public ProjetController() {
//        this.projetService = new ProjetService();
//        this.scanner = new Scanner(System.in);
//        this.materiauController = new MateriauController();
//        this.mainOuvreController= new MainOuvreController();
//    }
//
//    public void addProjet(int idClient) {
//
//
//        System.out.println("--- Création d'un Nouveau Projet-- \n");
//        System.out.println("Entrez le nom du projet:");
//        String nomProjet = scanner.nextLine();
//
//        System.out.println("Entrez la marge bénéficiaire pour ce  projet:");
//        double margeBeneficiaire = scanner.nextDouble();
//
//        scanner.nextLine();
//        System.out.println("Entrez l'Etat de ce projet: (EN_COURS, TERMINE , ANNULE)");
//        String etatProjetStr = scanner.nextLine();
//        EtatProjet etatProjet =EtatProjet.valueOf(etatProjetStr.toUpperCase());
//
//        System.out.println("Entrez la surface de la cuisine (en m²):");
//        double surface = scanner.nextDouble();
//
//        ClientService clientService = new ClientService();
//        Client client = clientService.getClient(idClient);
//        if (client == null) {
//            System.out.println("Erreur: Client non trouvé !");
//
//        }
//
//
//        Projet projet = new Projet( nomProjet, margeBeneficiaire,  etatProjet,  coutTotal,  client , surface);
//
//        int idProjet = projetService.addProjet(projet);
//
//
//
//        System.out.println("--- Ajout des matériaux---\n");
//
//         materiauController.addMateriau(idProjet);
//        mainOuvreController.addMainOuvre(idProjet);
//
//
//
//
//
//
//
//
//    }
//
//}






package controller;

import model.Client;
import model.Enum.EtatProjet;
import model.Projet;
import service.ClientService;
import service.ProjetService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProjetController {
    private final ProjetService projetService;
    private final Scanner scanner;
    private final MateriauController materiauController;
    private final MainOuvreController mainOuvreController;

    public ProjetController() {
        this.projetService = new ProjetService();
        this.scanner = new Scanner(System.in);
        this.materiauController = new MateriauController();
        this.mainOuvreController = new MainOuvreController();
    }

    public int  addProjet(int idClient) {
        System.out.println("--- Création d'un Nouveau Projet ---");

        System.out.print("Nom du projet: ");
        String nomProjet = scanner.nextLine();

        double margeBeneficiaire = getValidatedDouble("Marge bénéficiaire (doit être positive): ", 0, Double.MAX_VALUE);
        EtatProjet etatProjet = getValidatedEtatProjet();
        double surface = getValidatedDouble("Surface de la cuisine (en m², doit être positive): ", 0, Double.MAX_VALUE);

        Client client = new ClientService().getClient(idClient);
        if (client == null) {
            System.out.println("Erreur: Client non trouvé !");
            return -1 ;
        }

        Projet projet = new Projet(nomProjet, margeBeneficiaire, etatProjet, 0, client, surface);
        int idProjet = projetService.addProjet(projet);

        System.out.println("--- Ajout des matériaux et main-d'œuvre ---");
        materiauController.addMateriau(idProjet);
        mainOuvreController.addMainOuvre(idProjet);
        return idProjet;

    }

    private double getValidatedDouble(String prompt, double min, double max) {
        double value = -1;
        while (value < min || value > max) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value < min || value > max) {
                    System.out.println("Erreur: Valeur non valide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur: Veuillez entrer un nombre valide.");
            }
        }
        return value;
    }

    private EtatProjet getValidatedEtatProjet() {
        EtatProjet etatProjet = null;
        while (etatProjet == null) {
            try {
                System.out.print("État du projet (EN_COURS, TERMINE, ANNULE): ");
                etatProjet = EtatProjet.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: État non valide.");
            }
        }
        return etatProjet;
    }
}

