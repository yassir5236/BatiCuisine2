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
    public int  addProjet(Projet projet) {
        int id ;
        try {
             id= projetDAO.insertProjet(projet);
             return id;

        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
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

    public void updateCoutTotal(double coutTotal,int idProjet) {
        try{
            projetDAO.updateCoutTotal(coutTotal,idProjet);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
