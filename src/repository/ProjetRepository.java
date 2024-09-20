package repository;

import model.Projet;
import java.sql.SQLException;
import java.util.List;

public interface ProjetRepository {

    int insertProjet(Projet projet) throws SQLException;

    Projet selectProjetById(int id) throws SQLException;

//    List<Projet> selectAllProjets() throws SQLException;
//
//    boolean updateProjet(Projet projet) throws SQLException;
//
//    boolean deleteProjet(int id) throws SQLException;
}
