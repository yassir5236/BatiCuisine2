package controller;

import model.Projet;
import service.ProjetService;

import java.util.Scanner;

public class CoutTotalController {
private final ProjetService projetService;



   public CoutTotalController(){
        this.projetService= new ProjetService();
   }

public void coutTotal(int idProjet ) {
        System.out.println(" Calcul du coût en cours... \n ");
        System.out.println("--- Résultat du Calcul---");

        Projet projet =projetService.selectProjetById(idProjet);
        if(projet==null){
            System.out.println("Projet n'existe pas !");
        }else{
            System.out.println("Nom du projet  :" + projet.getNomProjet());
            System.out.println("client         :" + projet.getClient().getNom());
            System.out.println("Surface        :" + projet.getSurface());
            System.out.println("Adresse        :" + projet.getClient().getAdresse());
        }




    }








}
