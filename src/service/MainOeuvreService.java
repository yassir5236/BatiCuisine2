package service;

import repository.dao.MainOeuvreDAO;
import repository.dao.ProjetDAO;
import model.MainOeuvre;
import model.Projet;
import model.dto.MainDoeuvreDto;
import service.interfaces.IMainOeuvreService;

import java.util.List;
import java.util.stream.Collectors;


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

    public List<MainOeuvre> getMainOeuvresByProjet(int projetId) {
        return mainOeuvreDAO.selectAllMainOeuvres().stream()
                .filter(mainOeuvre -> mainOeuvre.getProjet().getId() == projetId)
                .collect(Collectors.toList());
    }




}
