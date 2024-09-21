//import controller.ClientController;
//import controller.MainOuvreController;
//import controller.MateriauController;
//import controller.ProjetController;
//import model.Projet;
//
//import java.util.Scanner;
//
//
//public class Main {
//    public static void main(String[] args) {
//        ClientController obj = new ClientController();
//        MateriauController obj2 = new MateriauController();
//        MainOuvreController obj3 = new MainOuvreController();
//
//
//
//        System.out.println("===== Menu principale ======");
//        System.out.println(" 1 - Créer un nouveau projet");
//        System.out.println(" 2 - Afficher les projets existants ");
//        System.out.println(" 3 - Calculer le coût d'un projet ");
//        System.out.println(" 4 - Quitter \n");
//        System.out.println(" Choisissez une option : ");
//
//
//
//
//
//
//
//        Scanner sc = new Scanner(System.in);
//        int choice = sc.nextInt();
//
//
//
//
//        switch (choice) {
//            case 1:
//                GestionClient();
//                break;
//                case 7:
//
//                break;
//            default:
//                System.out.println("your chose is wronggg");
//                break;
//        }
//
//
//    }
//
//    public static void GestionClient() {
//        ClientController client = new ClientController();
//        ProjetController projet = new ProjetController();
//        MateriauController obj2 = new MateriauController();
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("--- Recherche de client ---");
//        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?\n");
//        System.out.println("1 - Chercher un client existant ");
//        System.out.println("2 - Ajouter un nouveau client ");
//        System.out.println("\n");
//        System.out.println(" Choisissez une option :");
//
//        int choice = sc.nextInt();
//        switch (choice) {
//            case 1:
//                int idClient = client.recherchClient();
//                System.out.println(idClient);
//                System.out.println("Souhaitez-vous continuer avec ce client ? (true/false) : ");
//                boolean response = sc.nextBoolean();
//
//                if (response) {
//                     projet.addProjet(idClient);
//
//                } else {
//                    System.out.println("Action annulée.");
//                }
//                break;
//            case 2:
//                int idNewClient = client.addClient();
//                System.out.println(idNewClient);
//                System.out.println("Souhaitez-vous continuer avec ce client ? (true/false) : ");
//
//                boolean response2 = sc.nextBoolean();
//
//                if (response2) {
//                    projet.addProjet(idNewClient);
//                } else {
//                    System.out.println("Action annulée.");
//                }
//
//            case 3:
//
//
//        }
//    }
//}


import controller.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClientController clientController = new ClientController();
        MateriauController materiauController = new MateriauController();
        MainOuvreController mainOuvreController = new MainOuvreController();
        CoutTotalController coutTotalController = new CoutTotalController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            afficherMenuPrincipal();
            int choice = lireChoix(sc);

            switch (choice) {
                case 1:
                    gestionClient(sc);
                    break;
                case 2:
                    System.out.println("Affichage des projets existants ()");
                    break;
                case 3:
                    boolean idValide = false;

                    while (!idValide) {
                        try {
                            System.out.print("Entrez l'ID du projet ou tapez 0 pour retourner au menu précédent : ");
                            int idProjet = sc.nextInt();

                            if (idProjet == 0) {
                                System.out.println("Retour au menu précédent...");
                                break;
                            }

                            coutTotalController.coutTotal(idProjet);
                            idValide=true;

                        } catch (InputMismatchException e) {
                            System.out.println("Erreur : Veuillez entrer un ID de projet valide (nombre entier).");
                            sc.next();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Quitter le programme...");
                    return;
                case 5:
                    int idprojet2 = sc.nextInt();
                    materiauController.addMateriau2(idprojet2);                    ;
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
        System.out.println("4 - Quitter");
        System.out.println("Choisissez une option : ");
    }

    public static void gestionClient(Scanner sc) {
        ClientController clientController = new ClientController();
        ProjetController projetController = new ProjetController();
        CoutTotalController coutTotalController = new CoutTotalController();


        while (true) {
            System.out.println("--- Gestion des clients ---");
            System.out.println("1 - Chercher un client existant");
            System.out.println("2 - Ajouter un nouveau client");
            System.out.println("3 - retour au menu principal");

            System.out.print("Choisissez une option : ");

            int choice = lireChoix(sc);
            switch (choice) {
                case 1:
                    int idClient = clientController.recherchClient();
//                    System.out.println("ID du client : " + idClient);
                    System.out.print("Souhaitez-vous continuer avec ce client ? (true/false) : ");
                    if (sc.nextBoolean()) {
                        int idProjet = projetController.addProjet(idClient);
                        coutTotalController.coutTotal(idProjet);
                    } else {
                        System.out.println("Action annulée.");
                    }
                    return;
                case 2:
                    int idNewClient = clientController.addClient();
//                    System.out.println("ID du nouveau client : " + idNewClient);
                    System.out.print("Souhaitez-vous continuer avec ce client ? (true/false) : ");
                    if (sc.nextBoolean()) {
                        projetController.addProjet(idNewClient);
                    } else {
                        System.out.println("Action annulée.");
                    }
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    private static int lireChoix(Scanner sc) {
        int choix = -1;
        while (true) {
            try {
                choix = sc.nextInt();
                return choix; // Retourne le choix si la saisie est correcte
            } catch (InputMismatchException e) {
                System.out.println("Saisie invalide, veuillez entrer un nombre.");
                sc.next();
            }
        }
    }
}
