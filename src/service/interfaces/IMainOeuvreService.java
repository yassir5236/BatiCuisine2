package service.interfaces;

import model.MainOeuvre;
import model.dto.MainDoeuvreDto;
import model.dto.MateriauDto;

import java.util.List;

public interface IMainOeuvreService {
    void ajouterMainOeuvre(MainDoeuvreDto mainDoeuvreDto);
    List<MainOeuvre> getMainOeuvresByProjet(int projetId);
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
