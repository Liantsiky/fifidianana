create base fifidianana;
ALTER TABLE centre ALTER designation  TYPE varchar(100);
--candidat
create table candidat (
    id INT primary key,
    nomPrenom  VARCHAR(30),
    antoko VARCHAR(100)
);
--region
create table region(
    id serial primary key,
    designation VARCHAR(30)
);
--district
create table district (
    id serial primary key,
    designation varchar(30),
    idRegion int,
    foreign key(idRegion) references region(id)
);
--commune
create table commune (
    id serial primary key,
    designation varchar(30),
    iddistrict int,
    foreign key(iddistrict) references district(id)
);
--fokontany
create table fokontany (
    id serial primary key,
    designation varchar(30),
    idcommune int,
    foreign key(idcommune) references commune(id)
);
--centre de vote
create table centre (
    id serial primary key,
    designation varchar(30),
    idfokontany int,
    foreign key(idfokontany) references fokontany(id)
);
--bureau de vote
create table bureau (
    id varchar(20) primary key,
    designation varchar(30),
    idcentre int,
    foreign key(idcentre) references centre(id)
);
--resultatbureaudevote
create table resultatBureau (
    id serial primary key,
    idBureau varchar(20),
    inscrits int,
    votants int,
    blancs int,
    nuls int,
    exprimes int,
    foreign key (idBureau) references bureau(id)
);
--resultat candidat par bureau de vote
    create table resultatCandidat(
        id serial primary key,
        idResultatBureau int,
        idCandidat int,
        voix int,
        pourcentage double precision,
        foreign key (idResultatBureau) references resultatBureau(id),
        foreign key (idCandidat) references candidat(id)
    );
