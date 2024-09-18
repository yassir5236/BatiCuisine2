import controller.ClientController;
import model.Projet;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ClientController obj = new ClientController();


        System.out.println("===== Menu principale ======");
        System.out.println(" 1 - Créer un nouveau projet");
        System.out.println(" 2 - Afficher les projets existants ");
        System.out.println(" 3 - Calculer le coût d'un projet ");
        System.out.println(" 4 - Quitter ");




//        System.out.println("1 - add client ");
//        System.out.println("2 - update client ");
//        System.out.println("3 - get client ");
//        System.out.println("4 - get client ");
//        System.out.println("5 - get all client ");



        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();



        switch (choice) {
            case 1:
                GestionClient();
                break;
            case 2:
                obj.updateClient();
                break;
            case 3:
                obj.getClient();
                break;
            case 4:
                obj.deleteClient();
                break;
            case 5:
                obj.getAllClients();
                break;
            default:
                System.out.println("your chose is wronggg");
                break;
        }


    }



    public static void GestionClient() {
        ClientController client = new ClientController();
//        ProjetController projet = new ProjetController();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?\n");
        System.out.println("1 - Chercher un client existant ");
        System.out.println("2 - Ajouter un nouveau client ");
        System.out.println("\n");
        System.out.println(" Choisissez une option :");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                client.recherchClient();
                System.out.println("Souhaitez-vous continuer avec ce client ? (y/n) : ");
                boolean response = sc.nextBoolean();
                if (response) {
                    projet.addProjet();
                }
                break;
            case 2:
                client.addClient();


        }
    }
}