package service;

import dao.ProjetDAO;
import model.Projet;
import service.interfaces.IProjetService;

public class ProjetService implements IProjetService {
private final ProjetDAO projetDAO ;

    public ProjetService(){
        this.projetDAO = new ProjetDAO();
    }




    @Override
    public void addProjet(Projet projet) {
//        projetDAO.insertProjet(projet);
        System.out.println("adding project");
    }


    @Override
    public Projet selectProjetById(int id) {
        Projet projet = null;
        try {
            projet = projetDAO.selectProjetById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projet;
    }
}
