package service;

import dao.MateriauDAO;
import dao.ProjetDAO;
import dao.MateriauDAO;
import model.Materiau;
import model.Projet;
import model.dto.MateriauDto;
import service.interfaces.IMateriauService;

import java.util.List;
import java.util.stream.Collectors;

public class MateriauService implements IMateriauService {
    private final ProjetDAO projetDAO ;
    private final MateriauDAO materiauDAO ;
    private final ProjetService projetService;

    public MateriauService() {
        this.projetDAO=new ProjetDAO();
        this.materiauDAO=new MateriauDAO();
        this.projetService=new ProjetService();
    }

    @Override
    public void ajouterMateriau(MateriauDto materiauDto) {
        Projet projet = projetService.selectProjetById(materiauDto.projetId());


        Materiau materiau = new Materiau(materiauDto.nom() , materiauDto.typeComposant() ,materiauDto.tauxTVA() ,projet,materiauDto.coutUnitaire(),materiauDto.quantite(),materiauDto.coutTransport() ,materiauDto.coefficientQuantite());
        try{
            materiauDAO.insertMateriau(materiau);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Materiau> getMateriauxByProjet(int projetId) {
        return materiauDAO.selectAllMateriaux().stream()
                .filter(materiau -> materiau.getProjet().getId() == projetId)
                .collect(Collectors.toList());
    }






}
