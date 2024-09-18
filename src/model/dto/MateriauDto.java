package model.dto;

import model.Enum.TypeComposant;

public record MateriauDto(

        String nom,
        TypeComposant typeComposant,
        double tauxTVA,
        int projetId ,
        double coutUnitaire,
        double quantite,
        double coutTransport,
        double coefficientQuantite
) {


}
