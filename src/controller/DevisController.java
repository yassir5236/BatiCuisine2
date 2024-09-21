package controller;

import model.Devis;
import model.Projet;
import service.DevisService;
import service.ProjetService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

//    public void EnregistrerDevis(double coutTotalFinal,int idProjet){
//        System.out.println("--- Enregistrement du Devis---");
//        System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
//        String date = sc.nextLine();
//        LocalDate devisDate = LocalDate.parse(date);
//
//        System.out.println("Entrez la date de validité du devis (format : yyyy/mm/jj) : ");
//        String dateValide = sc.nextLine();
//        LocalDate devisValid = LocalDate.parse(dateValide);
//
//        System.out.println("Souhaitez-vous enregistrer le devis ? (true/false)  : ");
//
//        boolean accepteEnregistrer =sc.nextBoolean();
//        if(accepteEnregistrer) {
//
//            Projet projet = projetService.selectProjetById(idProjet);
//            Devis devis = new Devis(coutTotalFinal, devisDate, devisValid, accepteEnregistrer, projet);
//            devisService.addDevis(devis);
//            System.out.println("--  Fin du projet --");
//
//        }
//
//
//
//
//    }








    public void EnregistrerDevis(double coutTotalFinal, int idProjet) {
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("--- Enregistrement du Devis ---");

        // Date d'émission du devis
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

        // Date de validité du devis
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

        // Enregistrer le devis
        System.out.println("Souhaitez-vous enregistrer le devis ? (true/false) : ");
        boolean accepteEnregistrer = sc.nextBoolean();

        if (accepteEnregistrer) {
            Projet projet = projetService.selectProjetById(idProjet);
            Devis devis = new Devis(coutTotalFinal, devisDate, devisValid, accepteEnregistrer, projet);
            devisService.addDevis(devis);
            System.out.println("-- Fin du projet --");
        }
    }
}
