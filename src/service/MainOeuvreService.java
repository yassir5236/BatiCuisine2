package service;

import dao.ProjetDAO;
import model.Materiau;
import model.Projet;
import model.dto.MainDoeuvreDto;
import model.dto.MateriauDto;
import service.interfaces.IMainOeuvreService;






public class MainOeuvreService implements IMainOeuvreService {
    private final ProjetDAO projetDAO ;
    private final MainOeuvreDAO mainOeuvreDAO ;
    private final ProjetService projetService;

    public MainOeuvreService() {
        this.projetDAO=new ProjetDAO();
        this.mainOeuvreDAO=new MainOeuvreDAO();
        this.projetService=new ProjetService();
    }

    @Override
    public void ajouterMainOeuvre(MainDoeuvreDto mainDoeuvreDto) {
        Projet projet = projetService.selectProjetById(mainDoeuvreDto.projetId());

        Materiau materiau = new Materiau(mainDoeuvreDto.nom() , mainDoeuvreDto.typeComposant() ,mainDoeuvreDto.tauxTVA() ,projet,mainDoeuvreDto.coutUnitaire(),mainDoeuvreDto.quantite(),mainDoeuvreDto.coutTransport() ,mainDoeuvreDto.coefficientQuantite());
        try{
            mainDoeuvreDto.insertMateriau(materiau);
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
