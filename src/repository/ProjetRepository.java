package repository;

import model.Projet;
import model.Enum.EtatProjet;

import java.util.List;
import java.util.Optional;

public interface ProjetRepository {
    void addProjet(Projet projet) throws Exception;
    Optional<Projet> getProjetById(int id) throws Exception;
    List<Projet> getAllProjets() throws Exception;
    void updateProjet(Projet projet) throws Exception;
    void deleteProjet(int id) throws Exception;
}
