package repository;

import model.Devis;
import java.sql.SQLException;
import java.util.List;

public interface DevisRepository {

    void insertDevis(Devis devis) throws SQLException;

//    Devis selectDevis(int id) throws SQLException;
//
//    List<Devis> selectAllDevis() throws SQLException;
//
//    boolean updateDevis(Devis devis) throws SQLException;
//
//    boolean deleteDevis(int id) throws SQLException;
}
