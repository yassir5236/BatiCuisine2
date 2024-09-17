import controller.ClientController;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ClientController obj = new ClientController();

        System.out.println("chose something:");
        System.out.println(" ");
        System.out.println("1 - add client ");
        System.out.println("2 - update client ");
        System.out.println("3 - get client ");
        System.out.println("4 - get client ");
        System.out.println("5 - get all client ");



        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();



        switch (choice) {
            case 1:
                obj.addClient();
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
}