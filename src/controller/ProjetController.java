package controller;

import model.Client;
import model.Enum.EtatProjet;
import model.MainOeuvre;
import model.Projet;
import service.ClientService;
import service.ProjetService;

import java.util.List;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class ProjetController {
    private final ProjetService projetService;
    private final Scanner scanner;
    private final MateriauController materiauController;
    private double coutTotal;
    private final MainOuvreController mainOuvreController;

    public ProjetController() {
        this.projetService = new ProjetService();
        this.scanner = new Scanner(System.in);
        this.materiauController = new MateriauController();
        this.mainOuvreController= new MainOuvreController();
    }

    public void addProjet(int idClient) {


        System.out.println("--- Création d'un Nouveau Projet-- \n");
        System.out.println("Entrez le nom du projet:");
        String nomProjet = scanner.nextLine();

        System.out.println("Entrez la marge bénéficiaire pour ce  projet:");
        double margeBeneficiaire = scanner.nextDouble();

        scanner.nextLine();
        System.out.println("Entrez l'Etat de ce projet: (EN_COURS, TERMINE , ANNULE)");
        String etatProjetStr = scanner.nextLine();
        EtatProjet etatProjet =EtatProjet.valueOf(etatProjetStr.toUpperCase());

        System.out.println("Entrez la surface de la cuisine (en m²):");
        double surface = scanner.nextDouble();

        ClientService clientService = new ClientService();
        Client client = clientService.getClient(idClient);
        if (client == null) {
            System.out.println("Erreur: Client non trouvé !");

        }


        Projet projet = new Projet( nomProjet, margeBeneficiaire,  etatProjet,  coutTotal,  client , surface);

        int idProjet = projetService.addProjet(projet);



        System.out.println("--- Ajout des matériaux---\n");

         materiauController.addMateriau(idProjet);
        mainOuvreController.addMainOuvre(idProjet);








    }

}
