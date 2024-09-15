package repository;

import model.Materiau;

import java.util.List;
import java.util.Optional;

public interface MateriauRepository {
    void addMateriau(Materiau materiau) throws Exception;
    Optional<Materiau> getMateriauById(int id) throws Exception;
    List<Materiau> getAllMateriaux() throws Exception;
    void updateMateriau(Materiau materiau) throws Exception;
    void deleteMateriau(int id) throws Exception;
}
