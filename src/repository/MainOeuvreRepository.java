package repository;

import model.MainOeuvre;
import java.sql.SQLException;
import java.util.List;

public interface MainOeuvreRepository {

    void insertMainOeuvre(MainOeuvre mainOeuvre) throws SQLException;

    MainOeuvre selectMainOeuvre(int id) throws SQLException;

    List<MainOeuvre> selectAllMainOeuvres() throws SQLException;

    boolean updateMainOeuvre(MainOeuvre mainOeuvre) throws SQLException;

    boolean deleteMainOeuvre(int id) throws SQLException;
}
