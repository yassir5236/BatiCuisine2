package service;

import dao.DevisDAO;
import model.Devis;
import service.interfaces.IDevisService;

public class DevisService implements IDevisService {
    private final DevisDAO devisDAO;


    public DevisService() {
        this.devisDAO = new DevisDAO();
    }

    public void addDevis(Devis devis) {
        try {
            devisDAO.insertDevis(devis);

        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
