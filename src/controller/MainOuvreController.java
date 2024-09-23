//package controller;
//
//import dao.MainOeuvreDAO;
//import model.Client;
//import model.Enum.TypeComposant;
//import model.MainOeuvre;
//import model.Projet;
//import model.dto.MainDoeuvreDto;
//import model.dto.MateriauDto;
//import service.ClientService;
//import service.MainOeuvreService;
//import service.MateriauService;
//import service.ProjetService;
//
//import java.util.Scanner;
//
//public class MainOuvreController {
//    private final MainOeuvreService mainOeuvreService;
//    private final Scanner scanner;
//    private final ProjetService projetService;
//    private final MainOeuvreDAO mainOeuvreDAO;
//
//
//
//    public MainOuvreController() {
//        this.mainOeuvreService = new MainOeuvreService();
//        this.scanner = new Scanner(System.in);
//        this.projetService= new ProjetService();
//        this.mainOeuvreDAO= new MainOeuvreDAO();
//
//    }
//
//
//    public void addMainOuvre(int idProjet) {
//        System.out.println("--- Ajout de la main-d'œuvre-- \n");
//        System.out.println("Entrez le nom du MainOuvre");
//        String nom = scanner.nextLine();
//
//        System.out.println("Entrez le type de composant (MATERIAU, MAIN_DOEUVRE):");
//        String typeComposantStr = scanner.nextLine().toUpperCase();
//
//        TypeComposant typeComposant;
//        try {
//            typeComposant = TypeComposant.valueOf(typeComposantStr);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Type de composant invalide.");
//            return;
//        }
//
//        System.out.println("Entrez tauxTVA:");
//        double tauxTVA = scanner.nextDouble();
//
//        scanner.nextLine();
//
//
//
//
//
//        System.out.println("Entrez le tauxHoraire (€/m²):");
//        double tauxHoraire = scanner.nextDouble();
//
//        System.out.println("Entrez heuresTravaile:");
//        double heuresTravaile = scanner.nextDouble();
//
//        System.out.println("Entrez productiviteOuvrier de ce MainOuvre (€):");
//        double productiviteOuvrier = scanner.nextDouble();
//
//
//
//
//
//        int projetId = idProjet;
//
//        Projet projet = projetService.selectProjetById(projetId);
//        if (projet == null) {
//            System.out.println("Le projet avec l'ID spécifié n'existe pas.");
//            return;
//        }
//
//        final MainDoeuvreDto mainDoeuvreDto = new MainDoeuvreDto(
//                 nom,
//                 typeComposant,
//                 tauxTVA,
//                 projetId ,
//                 tauxHoraire,
//                 heuresTravaile,
//                 productiviteOuvrier
//
//        );
//
//        mainOeuvreService.ajouterMainOeuvre(mainDoeuvreDto);
//        System.out.println("MainOeuvre ajouté avec succès.");
//        System.out.println("voulez vous inserer un autre MainDoeuvre (true/false) ?");
//        boolean choix = scanner.nextBoolean();
//        scanner.nextLine();
//        if (choix) {
//            addMainOuvre(idProjet);
//        }
//    }
//
//
//}


package controller;

import model.Enum.TypeComposant;
import model.MainOeuvre;
import model.Projet;
import model.dto.MainDoeuvreDto;
import service.MainOeuvreService;
import service.ProjetService;

import java.util.List;
import java.util.Scanner;

public class MainOuvreController {
    private final MainOeuvreService mainOeuvreService;
    private final Scanner scanner;
    private final ProjetService projetService;

    public MainOuvreController() {
        this.mainOeuvreService = new MainOeuvreService();
        this.scanner = new Scanner(System.in);
        this.projetService = new ProjetService();
    }

    public void addMainOuvre(int idProjet) {
        System.out.println("--- Ajout de la main-d'œuvre ---");

        System.out.print("Nom de la main-d'œuvre: ");
        String nom = scanner.nextLine();

        System.out.print("Type de composant (MATERIAU, MAIN_DOEUVRE): ");
        TypeComposant typeComposant = TypeComposant.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Taux TVA: ");
        double tauxTVA = scanner.nextDouble();

        System.out.print("Taux horaire (€/m²): ");
        double tauxHoraire = scanner.nextDouble();

        System.out.print("Heures travaillées: ");
        double heuresTravaile = scanner.nextDouble();

        System.out.print("Productivité de l'ouvrier (€): ");
        double productiviteOuvrier = scanner.nextDouble();
        scanner.nextLine();

        Projet projet = projetService.selectProjetById(idProjet);
        if (projet == null) {
            System.out.println("Erreur: Projet non trouvé !");
            return;
        }

        MainDoeuvreDto mainDoeuvreDto = new MainDoeuvreDto(
                nom, typeComposant, tauxTVA, idProjet, tauxHoraire, heuresTravaile, productiviteOuvrier
        );

        mainOeuvreService.ajouterMainOeuvre(mainDoeuvreDto);
        System.out.println("Main d'œuvre ajoutée avec succès.");

        System.out.print("Ajouter une autre main-d'œuvre (true/false) ? ");
        if (scanner.nextBoolean()) {
            scanner.nextLine();
            addMainOuvre(idProjet);
        }
    }



    public void afficherDetailDesCoutsMainOeuvre(int idProjet) {
        List<MainOeuvre> mainOeuvres = mainOeuvreService.getMainOeuvresByProjet(idProjet);

        if (mainOeuvres.isEmpty()) {
            System.out.println("Aucune main-d'œuvre trouvée pour ce projet.");
            return;
        }


        System.out.println("--- Détail des Coûts de Main-d'œuvre ---");
        double coutTotalMainOeuvre = mainOeuvres.stream()
                .peek(mainOeuvre -> {
                    double coutMainOeuvre = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail();
                    System.out.printf("%s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.2f h, productivité : %.2f)\n",
                            mainOeuvre.getNom(),
                            coutMainOeuvre,
                            mainOeuvre.getTauxHoraire(),
                            mainOeuvre.getHeuresTravail(),
                            mainOeuvre.getProductiviteOuvrier());
                })
                .mapToDouble(mainOeuvre -> mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail())
                .sum();


        System.out.printf("\n**Coût total de la main-d'œuvre avant TVA  : %.2f €**\n", coutTotalMainOeuvre);

        double tauxTVA = mainOeuvres.stream()
                .mapToDouble(MainOeuvre::getTauxTVA)
                .findFirst()
                .orElse(0);


        double coutTotalAvecTVA = coutTotalMainOeuvre * (1 + tauxTVA / 100);

        System.out.printf("**Coût total de la main-d'œuvre avec TVA (%.2f%%) : %.2f €**\n", tauxTVA, coutTotalAvecTVA);
    }











}
