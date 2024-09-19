package service;

import dao.MainOeuvreDAO;
import dao.ProjetDAO;
import model.MainOeuvre;
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

        MainOeuvre mainOeuvre = new MainOeuvre(mainDoeuvreDto.nom() , mainDoeuvreDto.typeComposant() ,mainDoeuvreDto.tauxTVA() ,projet ,mainDoeuvreDto.tauxHoraire() ,mainDoeuvreDto.heuresTravaile() , mainDoeuvreDto.productiviteOuvrier());
        try{
            mainOeuvreDAO.insertMainOeuvre(mainOeuvre);
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
