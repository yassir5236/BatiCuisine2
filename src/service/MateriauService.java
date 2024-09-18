package service;

import dao.ProjetDAO;
import model.Materiau;
import service.interfaces.IMateriauService;

public class MateriauService implements IMateriauService {
    private final ProjetDAO projetDAO ;

    public MateriauService() {
        this.projetDAO=new ProjetDAO();
    }

    @Override
    public void ajouterMateriau(Materiau materiau) {
        projetDAO.insertProjet(materiau);
    }


}
