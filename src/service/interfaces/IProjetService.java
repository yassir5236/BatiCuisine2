package service.interfaces;

import model.Projet;

import java.util.List;

public interface IProjetService {
    int addProjet(Projet projet);
    Projet selectProjetById(int id );
    void updateCoutTotal(double coutTotal,int idProjet);
    List<Projet> selectAllProjet();
}
