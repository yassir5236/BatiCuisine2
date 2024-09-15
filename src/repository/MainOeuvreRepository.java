package repository;

import model.MainOeuvre;

import java.util.List;
import java.util.Optional;

public interface MainOeuvreRepository {
    void addMainOeuvre(MainOeuvre mainOeuvre) throws Exception;
    Optional<MainOeuvre> getMainOeuvreById(int id) throws Exception;
    List<MainOeuvre> getAllMainOeuvres() throws Exception;
    void updateMainOeuvre(MainOeuvre mainOeuvre) throws Exception;
    void deleteMainOeuvre(int id) throws Exception;
}
