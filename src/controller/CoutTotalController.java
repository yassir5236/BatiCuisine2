package controller;

import model.Projet;
import service.ProjetService;

import java.util.Scanner;

public class CoutTotalController {
private final ProjetService projetService;
private final MateriauController materiauController ;
private final  MainOuvreController  mainOuvreController ;
private final   ProjetController    projetController;





   public CoutTotalController(){
        this.projetService= new ProjetService();
        this.materiauController = new MateriauController();
       this.mainOuvreController = new MainOuvreController();
       this.projetController  = new ProjetController();
   }

public void coutTotal(int idProjet ,double TV) {
        System.out.println(" Calcul du coût en cours... \n ");
        System.out.println("--- Résultat du Calcul---");

        Projet projet =projetService.selectProjetById(idProjet);
        if(projet==null){
            System.out.println("Projet n'existe pas !");
        }else{
            System.out.println("Nom du projet  :" + projet.getNomProjet());
            System.out.println("client         :" + projet.getClient().getNom());
            System.out.println("Surface        :" + projet.getSurface() + "m²");
            System.out.println("Adresse        :" + projet.getClient().getAdresse());
        }

        System.out.println("\n");
//        System.out.println("-- Détail des Coûts--");
        materiauController.afficherDetailDesCoutsMateriau(idProjet);
        mainOuvreController.afficherDetailDesCoutsMainOeuvre(idProjet);
        projetController.calculerCoutTotalDuProjet(idProjet,TV);





}








}
