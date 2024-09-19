package model.dto;

import model.Enum.TypeComposant;

public record MainDoeuvreDto(

        String nom,
        TypeComposant typeComposant,
        double tauxTVA,
        int projetId ,
        double tauxHoraire,
        double heuresTravaile,
        double productiviteOuvrier
) {


}
