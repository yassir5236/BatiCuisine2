package service.interfaces;

import model.Projet;

public interface IProjetService {
    int addProjet(Projet projet);
    Projet selectProjetById(int id );
    void updateCoutTotal(double coutTotal,int idProjet);
}
