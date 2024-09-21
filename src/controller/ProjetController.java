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
import model.MainOeuvre;
import model.Materiau;
import model.Projet;
import service.ClientService;
import service.MainOeuvreService;
import service.MateriauService;
import service.ProjetService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProjetController {
    private final ProjetService projetService;
    private final Scanner scanner;
    private final MateriauController materiauController;
    private final MainOuvreController mainOuvreController;
    private final MateriauService materiauService;
    private final MainOeuvreService mainOeuvreService;
    private final DevisController devisController;

    public ProjetController() {
        this.projetService = new ProjetService();
        this.scanner = new Scanner(System.in);
        this.materiauController = new MateriauController();
        this.mainOuvreController = new MainOuvreController();
        this.materiauService = new MateriauService();
        this.mainOeuvreService=new MainOeuvreService();
        this.devisController=new DevisController();
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






    public void calculerCoutTotalDuProjet(int idProjet ,double  TV) {
        Projet projet = projetService.selectProjetById(idProjet);
        if (projet == null) {
            System.out.println("Le projet avec l'ID spécifié n'existe pas.");
            return;
        }

        List<Materiau> materiaux = materiauService.getMateriauxByProjet(idProjet);
        List<MainOeuvre> mainOeuvres = mainOeuvreService.getMainOeuvresByProjet(idProjet);

        double coutTotalMateriaux = materiaux.stream()
                .mapToDouble(materiau -> {
                    double coutMateriau = (materiau.getQuantite() * materiau.getCoutUnitaire()) + materiau.getCoutTransport();
                    return coutMateriau * (1 + materiau.getTauxTVA() / 100);
                })
                .sum();

        double coutTotalMainOeuvre = mainOeuvres.stream()
                .mapToDouble(mainOeuvre -> {
                    double coutMainOeuvre = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail();
                    return coutMainOeuvre * (1 + mainOeuvre.getTauxTVA() / 100);
                })
                .sum();

        double coutTotalAvantMarge = coutTotalMateriaux + coutTotalMainOeuvre;

        double margeBeneficiaire = projet.getMargeBeneficiaire();
        double montantMarge = coutTotalAvantMarge * (margeBeneficiaire / 100);

        double coutTotalFinal = coutTotalAvantMarge + montantMarge;

        System.out.printf("Coût total avant marge : %.2f €\n", coutTotalAvantMarge);
        System.out.printf("Marge bénéficiaire (%.2f%%) : %.2f €\n", margeBeneficiaire, montantMarge);
        System.out.printf("**Coût total final du projet : %.2f €**\n", coutTotalFinal+(TV*coutTotalFinal/100));

        projetService.updateCoutTotal(coutTotalFinal,idProjet);

        devisController.EnregistrerDevis(coutTotalFinal,idProjet);
    }








    public void afficherTousLesProjets() {
        List<Projet> projets = projetService.selectAllProjet();
        for (Projet projet : projets) {
            System.out.println("ID du projet : " + projet.getId());
            System.out.println("Nom du projet : " + projet.getNomProjet());
            System.out.println("Surface : " + projet.getSurface() + " m²");
            System.out.println("Marge bénéficiaire : " + projet.getMargeBeneficiaire() + "%");
            System.out.println("Coût total : " + projet.getCoutTotal() + " €");
//            System.out.println("Client : " + projet.getClient().getNom());
            System.out.println("Adresse du client : " + projet.getClient().getAdresse());
            System.out.println("Téléphone du client : " + projet.getClient().getTelephone());
            System.out.println("Le client est un professionnel ? " + (projet.getClient().isEstProfessionnel() ? "Oui" : "Non"));
            System.out.println("------------------------------------------------------------");
        }
    }


}

