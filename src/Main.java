//
//
//import controller.*;
//import service.ProjetService;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//        ClientController clientController = new ClientController();
//        MateriauController materiauController = new MateriauController();
//        MainOuvreController mainOuvreController = new MainOuvreController();
//        CoutTotalController coutTotalController = new CoutTotalController();
//        ProjetService projetService = new ProjetService();
//        ProjetController projetController = new ProjetController();
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            afficherMenuPrincipal();
//            int choice = lireChoix(sc);
//            double TV;
//
//            switch (choice) {
//                case 1:
//                    gestionClient(sc);
//                    break;
//                case 2:
//                    System.out.println("Affichage des projets existants ");
//                    projetController.afficherTousLesProjets();
//                    break;
//                case 3:
//                    boolean idValide = false;
//
//                    while (!idValide) {
//                        try {
//                            System.out.print("Entrez l'ID du projet ou tapez 0 pour retourner au menu précédent : ");
//                            int idProjet = sc.nextInt();
//
//                                coutTotalController.coutTotal(idProjet);
//                                idValide=true;
//
//
//                            if (idProjet == 0) {
//                                System.out.println("Retour au menu précédent...");
//                                break;
//                            }
//
//
//
//                        } catch (InputMismatchException e) {
//                            System.out.println("Erreur : Veuillez entrer un ID de projet valide (nombre entier).");
//                            sc.next();
//                        }
//                    }
//                    break;
//
//                case 4:
//                    System.out.println("Quitter le programme...");
//                    return;
//                case 5:
//                    int idprojet2 = sc.nextInt();
//                    materiauController.addMateriau2(idprojet2);                    ;
//                default:
//                    System.out.println("Choix incorrect, veuillez réessayer.");
//                    break;
//            }
//        }
//    }
//
//    private static void afficherMenuPrincipal() {
//        System.out.println("===== Menu principal =====");
//        System.out.println("1 - Créer un nouveau projet");
//        System.out.println("2 - Afficher les projets existants");
//        System.out.println("3 - Calculer le coût d'un projet");
//        System.out.println("4 - Quitter");
//        System.out.println("Choisissez une option : ");
//    }
//
//    public static void gestionClient(Scanner sc) {
//        ClientController clientController = new ClientController();
//        ProjetController projetController = new ProjetController();
//        CoutTotalController coutTotalController = new CoutTotalController();
//
//
//        while (true) {
//            System.out.println("--- Gestion des clients ---");
//            System.out.println("1 - Chercher un client existant");
//            System.out.println("2 - Ajouter un nouveau client");
//            System.out.println("3 - retour au menu principal");
//
//            System.out.print("Choisissez une option : ");
//
//            int choice = lireChoix(sc);
//
//            switch (choice) {
//                case 1:
//                    int idClient = clientController.recherchClient();
//                    System.out.print("Souhaitez-vous continuer avec ce client ? (true/false) : ");
//                    if (sc.nextBoolean()) {
//                        int idProjet = projetController.addProjet(idClient);
//                        coutTotalController.coutTotal(idProjet);
//                    } else {
//                        System.out.println("Action annulée.");
//                    }
//                    return;
//                case 2:
//                    int idNewClient = clientController.addClient();
//                    System.out.print("Souhaitez-vous continuer avec ce client ? (true/false) : ");
//                    if (sc.nextBoolean()) {
//                        projetController.addProjet(idNewClient);
//                        coutTotalController.coutTotal(idNewClient);
//                    } else {
//                        System.out.println("Action annulée.");
//                    }
//                    return;
//                case 3:
//                    return;
//                default:
//                    System.out.println("Choix invalide. Veuillez réessayer.");
//                    break;
//            }
//        }
//    }
//
//    private static int lireChoix(Scanner sc) {
//        int choix = -1;
//        while (true) {
//            try {
//                choix = sc.nextInt();
//                return choix;
//            } catch (InputMismatchException e) {
//                System.out.println("Saisie invalide, veuillez entrer un nombre.");
//                sc.next();
//            }
//        }
//    }
//}





import controller.*;
import service.ProjetService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final ClientController clientController = new ClientController();
    private static final MateriauController materiauController = new MateriauController();
    private static final MainOuvreController mainOuvreController = new MainOuvreController();
    private static final CoutTotalController coutTotalController = new CoutTotalController();
    private static final ProjetService projetService = new ProjetService();
    private static final ProjetController projetController = new ProjetController();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            afficherMenuPrincipal();
            int choice = lireChoix(sc);

            switch (choice) {
                case 1:
                    gestionClient(sc);
                    break;
                case 2:
                    afficherProjetsExistants();
                    break;
                case 3:
                    calculerCoutProjet(sc);
                    break;
                case 4:
                    gestionDesDevis(sc);
                    break;
                case 5:
                    quitterProgramme();
                    break;
                default:
                    System.out.println("Choix incorrect, veuillez réessayer.");
                    break;
            }
        }
    }

    private static void afficherMenuPrincipal() {
        System.out.println("===== Menu principal =====");
        System.out.println("1 - Créer un nouveau projet");
        System.out.println("2 - Afficher les projets existants");
        System.out.println("3 - Calculer le coût d'un projet");
        System.out.println("4 - Gestion des devis ");
        System.out.println("5 - Quitter");
        System.out.print("Choisissez une option : ");
    }


    private static void gestionClient(Scanner sc) {
        while (true) {
            System.out.println("--- Gestion des clients ---");
            System.out.println("1 - Chercher un client existant");
            System.out.println("2 - Ajouter un nouveau client");
            System.out.println("3 - Retour au menu principal");

            System.out.print("Choisissez une option : ");
            int choice = lireChoix(sc);

            switch (choice) {
                case 1:
                    chercherClient(sc);
                    break;
                case 2:
                    ajouterNouveauClient(sc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    private static void chercherClient(Scanner sc) {
        int idClient = clientController.recherchClient();
        System.out.print("Souhaitez-vous continuer avec ce client ? (true/false) : ");
        if (sc.nextBoolean()) {
            int idProjet = projetController.addProjet(idClient);
            coutTotalController.coutTotal(idProjet);
        } else {
            System.out.println("Action annulée.");
        }
    }

    private static void ajouterNouveauClient(Scanner sc) {
        int idNewClient = clientController.addClient();
        System.out.print("Souhaitez-vous continuer avec ce client ? (true/false) : ");
        if (sc.nextBoolean()) {
            int idProjet = projetController.addProjet(idNewClient);
            coutTotalController.coutTotal(idProjet);
        } else {
            System.out.println("Action annulée.");
        }
    }

    private static void afficherProjetsExistants() {
        System.out.println("Affichage des projets existants...");
        projetController.afficherTousLesProjets();
    }

    private static void calculerCoutProjet(Scanner sc) {
        boolean idValide = false;

        while (!idValide) {
            try {
                System.out.print("Entrez l'ID du projet ou tapez 0 pour retourner au menu précédent : ");
                int idProjet = sc.nextInt();

                if (idProjet == 0) {
                    System.out.println("Retour au menu précédent...");
                    return;
                }

                coutTotalController.coutTotal(idProjet);
                idValide = true;

            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un ID de projet valide (nombre entier).");
                sc.next();
            }
        }
    }

    private static void ajouterMateriauAuProjet(Scanner sc) {
        try {
            System.out.print("Entrez l'ID du projet pour ajouter un matériau : ");
            int idProjet = sc.nextInt();
            materiauController.addMateriau2(idProjet);
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un ID de projet valide.");
            sc.next(); // Pour consommer l'entrée invalide
        }
    }

    private static void quitterProgramme() {
        System.out.println("Quitter le programme...");
    }

    private static int lireChoix(Scanner sc) {
        int choix = -1;
        while (true) {
            try {
                choix = sc.nextInt();
                return choix;
            } catch (InputMismatchException e) {
                System.out.println("Saisie invalide, veuillez entrer un nombre.");
                sc.next();
            }
        }
    }



    private static void gestionDesDevis(Scanner sc){
        System.out.println("1- Afficher toutes les devis ");
        System.out.println("2- Retur au menu principal ");
        int choix = sc.nextInt();

        switch (choix) {
            case 1:
                DevisController devisController = new DevisController();
                devisController.DisplayAllDevis();
                break;
            case 2:
                return;
        }





    }
}
