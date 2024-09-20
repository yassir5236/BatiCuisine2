package controller;

import dao.MainOeuvreDAO;
import model.Client;
import model.Enum.TypeComposant;
import model.MainOeuvre;
import model.Projet;
import model.dto.MainDoeuvreDto;
import model.dto.MateriauDto;
import service.ClientService;
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


    public void addMainOuvre(int idProjet) {
        System.out.println("--- Ajout de la main-d'œuvre-- \n");
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





        System.out.println("Entrez le tauxHoraire (€/m²):");
        double tauxHoraire = scanner.nextDouble();

        System.out.println("Entrez heuresTravaile:");
        double heuresTravaile = scanner.nextDouble();

        System.out.println("Entrez productiviteOuvrier de ce MainOuvre (€):");
        double productiviteOuvrier = scanner.nextDouble();





        int projetId = idProjet;

        Projet projet = projetService.selectProjetById(projetId);
        if (projet == null) {
            System.out.println("Le projet avec l'ID spécifié n'existe pas.");
            return;
        }

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
        System.out.println("voulez vous inserer un autre MainDoeuvre (true/false) ?");
        boolean choix = scanner.nextBoolean();
        scanner.nextLine();
        if (choix) {
            addMainOuvre(idProjet);
        }
    }


}
