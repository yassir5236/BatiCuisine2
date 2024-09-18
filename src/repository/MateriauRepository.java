package repository;

import model.Materiau;
import java.sql.SQLException;
import java.util.List;

public interface MateriauRepository {

    void insertMateriau(Materiau materiau) throws SQLException;

//    Materiau selectMateriau(int id) throws SQLException;
//
//    List<Materiau> selectAllMateriaux() throws SQLException;
//
//    boolean updateMateriau(Materiau materiau) throws SQLException;
//
//    boolean deleteMateriau(int id) throws SQLException;
}
