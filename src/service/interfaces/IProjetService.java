package service.interfaces;

import model.Projet;

public interface IProjetService {
    void addProjet(Projet projet);
    Projet selectProjetById(int id );
}
