package service.interfaces;
import model.Materiau;

import model.Client;
import model.dto.MateriauDto;

import java.util.List;

public interface IMateriauService {
    void ajouterMateriau(MateriauDto materiauDto);
    List<Materiau> getMateriauxByProjet(int projetId);
//    boolean updateClient(Client client);
//
//    Client getClient(int id);
//
//    boolean deleteClient(int id);
//
//    List<Client> getAllClients();
//
//    Client rechercheClient(String nom);
}
