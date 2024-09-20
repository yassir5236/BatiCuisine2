import controller.ClientController;
import controller.MainOuvreController;
import controller.MateriauController;
import controller.ProjetController;
import model.Projet;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ClientController obj = new ClientController();
        MateriauController obj2 = new MateriauController();
        MainOuvreController obj3 = new MainOuvreController();



        System.out.println("===== Menu principale ======");
        System.out.println(" 1 - Créer un nouveau projet");
        System.out.println(" 2 - Afficher les projets existants ");
        System.out.println(" 3 - Calculer le coût d'un projet ");
        System.out.println(" 4 - Quitter \n");
        System.out.println(" Choisissez une option : ");






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
            case 6:

//                obj2.addMateriau();
//                boolean choix= false ;
//               do {
////                   obj2.addMateriau();
//                   System.out.println(" Voulez-vous ajouter un autre matériau ? (true/false) : ");
//                   choix = sc.nextBoolean();
//
//               }while(choix);
                break;
            case 7:
//                obj3.addMainOuvre();
                break;
            default:
                System.out.println("your chose is wronggg");
                break;
        }


    }



    public static void GestionClient() {
        ClientController client = new ClientController();
        ProjetController projet = new ProjetController();
        MateriauController obj2 = new MateriauController();
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
                int idClient = client.recherchClient();
                System.out.println(idClient);
                System.out.println("Souhaitez-vous continuer avec ce client ? (true/false) : ");
                boolean response = sc.nextBoolean();

                if (response) {
                     projet.addProjet(idClient);

                } else {
                    System.out.println("Action annulée.");
                }
                break;
            case 2:
                int idNewClient = client.addClient();
                System.out.println(idNewClient);
                System.out.println("Souhaitez-vous continuer avec ce client ? (true/false) : ");

                boolean response2 = sc.nextBoolean();

                if (response2) {
                    projet.addProjet(idNewClient);
                } else {
                    System.out.println("Action annulée.");
                }

            case 3:
//                obj2.addMateriau();


        }
    }
}