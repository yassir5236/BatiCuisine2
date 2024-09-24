create TYPE etat_projet as enum
 ('En_cours', 'Termine', 'Annule')

create type type_composant as ENUM 
('Materiel','Main_doeuvre')




CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255),
    telephone VARCHAR(20),
    est_professionnel BOOLEAN NOT NULL,
    remise DOUBLE PRECISION 
);

create table projet (
id serial primary key,
nom_projet varchar(255) not null,
marge_beneficiaire double precision not null,
etat_projet etat_projet not null,
cout_total double precision ,
surface double precision,
client_id int references client(id) on delete cascade  
	
)

create table composant (
id serial primary key ,
nom varchar(255),
type_composant type_composant not null,
taux_TVA double precision ,
projet_id int references projet(id) on delete cascade 
);

create table materiau (
cout_unitaire double precision not null,
quantite double precision not null,
cout_transport double precision not null,
coefficient_quantite double precision not null
	
)inherits(composant);


create table main_oeuvre (
taux_horaire double precision not null,
heures_travail double precision not null,
productivite_ouvrier double precision not null

)inherits(composant);


create table devis(
id serial primary key ,
montant_estimate double precision not null,
date_emission date not null ,
date_validate date not null  ,
accepte boolean default false ,
projet_id int references projet (id) on delete cascade 

)






















