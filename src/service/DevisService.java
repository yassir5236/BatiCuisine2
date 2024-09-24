package service;

import dao.DevisDAO;
import model.Devis;
import service.interfaces.IDevisService;

import java.util.ArrayList;
import java.util.List;

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

    public List<Devis> DisplayAllDevis(){
        List <Devis> devisList = new ArrayList<>();
        try{
            DevisDAO devisDAO = new DevisDAO();
           devisList= devisDAO.DisplayAllDevis();
        }catch (Exception e){
            e.printStackTrace();
        }

        return devisList;
    }


    public void UpdateAccepteDevis(int idDevis){
        try {
            DevisDAO devisDAO = new DevisDAO();
            devisDAO.UpdateAccepteDevis(idDevis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
