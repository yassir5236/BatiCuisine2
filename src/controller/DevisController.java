package controller;

import model.Devis;
import model.Projet;
import service.DevisService;
import service.ProjetService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class DevisController {

private final Scanner sc;
private final DevisService devisService;
private final ProjetService projetService;


    public DevisController(){
      this.sc = new Scanner(System.in);
      this.devisService = new DevisService();
      this.projetService = new ProjetService();
    }





    public void EnregistrerDevis(double coutTotalFinal, int idProjet) {
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("--- Enregistrement du Devis ---");

        LocalDate devisDate = null;
        while (devisDate == null) {
            try {
                System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
                String date = sc.nextLine();
                devisDate = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide. Veuillez entrer une date au format jj/mm/aaaa.");
            }
        }

        LocalDate devisValid = null;
        while (devisValid == null) {
            try {
                System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
                String dateValide = sc.nextLine();
                devisValid = LocalDate.parse(dateValide, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide. Veuillez entrer une date au format jj/mm/aaaa.");
            }
        }

        System.out.println("Souhaitez-vous enregistrer le devis ? (true/false) : ");
        boolean accepteEnregistrer = sc.nextLine().equalsIgnoreCase("true");

        if (accepteEnregistrer) {
            Projet projet = projetService.selectProjetById(idProjet);
            Devis devis = new Devis(coutTotalFinal, devisDate, devisValid, accepteEnregistrer, projet);
            devisService.addDevis(devis);
            System.out.println("-- Fin du projet --");
        }
    }



    public void DisplayAllDevis() {
        DevisService devisService = new DevisService();
        List<Devis> devisList = devisService.DisplayAllDevis();

        if (devisList.isEmpty()) {
            System.out.println("Aucun devis trouvé.");
            return;
        }

        devisList.forEach(devis -> {
            System.out.printf("Id devis : %d, Montant estimé : %.2f €, Date d'émission : %s, Date de validation : %s, Accepté : %s%n",
                    devis.getId(),
                    devis.getMontantEstimate(),
                    devis.getDateEmission(),
                    devis.getDateValide(),
                    devis.isAccepte() ? "Oui" : "Non");
        });


        boolean accepteDevis = false ;
        boolean responseValide = false;

        do {
            System.out.println("vous voulez refuser un devis ? (true/false) : ");
            String acceptedDevis = sc.nextLine();
            if (acceptedDevis.equals("true")) {
                accepteDevis = true;
                responseValide = true;
            } else if (acceptedDevis.equals("false")) {
                accepteDevis = false;
                responseValide = true;


            }
            }

            while (!responseValide) ;

            if (accepteDevis) {
                System.out.println("Entrer  devis id du projet  : ");
                int idDevis = sc.nextInt();
                devisService.UpdateAccepteDevis(idDevis);
                System.out.println("-- Devis refused --");

            }




    }

}
