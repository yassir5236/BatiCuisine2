package repository;

import model.Devis;

import java.util.List;
import java.util.Optional;

public interface DevisRepository {
    void addDevis(Devis devis) throws Exception;
    Optional<Devis> getDevisById(int id) throws Exception;
    List<Devis> getAllDevis() throws Exception;
    void updateDevis(Devis devis) throws Exception;
    void deleteDevis(int id) throws Exception;
}
