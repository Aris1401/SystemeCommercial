create database systemecommercial;

create table TypeUtilisateur(
    idTypeUtilisateur serial,
    nom VARCHAR(255),
    PRIMARY KEY(idTypeUtilisateur)
);

create table Utilisateur(
    idUtilisateur serial,
    nom  VARCHAR(255),
    prenom  VARCHAR(255),
    email VARCHAR(255),
    motDePasse VARCHAR(255),
    idTypeUtilisateur int,
    PRIMARY KEY(idUtilisateur),
    FOREIGN KEY(idTypeUtilisateur) REFERENCES TypeUtilisateur(idTypeUtilisateur)
);

create table Service(
    idService serial,
    nom VARCHAR(255),
    PRIMARY KEY(idService)
);

create table Profil(
    idProfil serial,
    nom VARCHAR(255),
    idService int,
    PRIMARY KEY(idProfil),
    FOREIGN KEY(idService) REFERENCES Service(idService)
); 

create table ProfilUtilisateur(
    idProfilUtilisateur serial,
    idUtilisateur int,
    idProfil int,
    dateAjout TIMESTAMP,
    PRIMARY KEY(idProfilUtilisateur),
    FOREIGN KEY (idProfil) REFERENCES Profil(idProfil),
    FOREIGN KEY(idUtilisateur) REFERENCES Utilisateur(idUtilisateur)
);

create table BesoinAchat(
    idBesoinAchat serial,
    idService int,
    dateBesoin TIMESTAMP,
    dateCloture TIMESTAMP,
    statusBesoin int,
    PRIMARY KEY(idBesoinAchat),
    FOREIGN KEY(idService) REFERENCES Service (idService)
);

create table Article(
    idArticle serial,
    nom VARCHAR(255),
    descriptionArticle VARCHAR(255),
    PRIMARY KEY(idArticle)
);

create table Unite(
    idUnite serial,
    nom VARCHAR(255),
    PRIMARY KEY(idUnite)
);

create table ArticleBesoinAchat(
   idArticleBesoinAchat serial,
   idArticle int,
   idBesoinAchat int,
   quantite DECIMAL,
   idUnite int,
   descriptionArticleBesoin VARCHAR(255),
   PRIMARY KEY(idArticleBesoinAchat),
   FOREIGN KEY(idArticle) REFERENCES Article (idArticle),
   FOREIGN KEY(idBesoinAchat) REFERENCES BesoinAchat (idBesoinAchat)
);

create table ValidationBesoinAchat(
    idValidationBesoinAchat serial,
    idBesoinAchat int, 
    idUtilisateur int,
    dateValidation TIMESTAMP,
    commentaire VARCHAR(255),
    statusValidation int,
    PRIMARY KEY(idValidationBesoinAchat),
    FOREIGN KEY(idBesoinAchat) REFERENCES BesoinAchat (idBesoinAchat),
    FOREIGN KEY(Utilisateur) REFERENCES Utilisateur(Utilisateur)
);

create table TypeProduit(
    idTypeProduit serial,
    nom VARCHAR(255),
    PRIMARY KEY(idTypeProduit)
);

create table Fournisseur(
  idFournisseur serial,
  nom VARCHAR(255),
  contact VARCHAR(10),
  nomResponsable VARCHAR(255),
  contactResponsable VARCHAR(255),
  email VARCHAR(255),
  addresse VARCHAR(255),
  localisation VARCHAR(255),
  descriptionFournisseur VARCHAR(255),
  idTypeProduit int,
  PRIMARY KEY(idFournisseur),
  FOREIGN KEY(idTypeProduit) REFERENCES TypeProduit(idTypeProduit)
);

create table Proforma(
    idProforma serial,
    idFournisseur int,
    dateObtention TIMESTAMP,
    prixUnitaire DECIMAL,
    quantite DECIMAL,
    idArticle int,
    PRIMARY KEY(idProforma),
    FOREIGN KEY(idFournisseur) REFERENCES  Fournisseur(idTypeProduit),
    FOREIGN KEY(idArticle) REFERENCES Article (idArticle)
);

create table ModeDePaiement(
    idModeDePaiement serial,
    nom VARCHAR(255),
    PRIMARYKEY(idModeDePaiement)
)

create table BonDeCommande(
    idBonDeCommande serial,
    titre VARCHAR(255),
    dateCreation TimeStamp,
    idFournisseur int,
    idBesoinAchat int,
    dateLivraison Timestamp,
    idModeDePaiement int,
    conditionDePaiement VARCHAR(255),
    montantTotal DECIMAL,
    PRIMARY KEY(idBonDeCommande),
    FOREIGN KEY(idFournisseur) REFERENCES Fournisseur(idFournisseur),
    FOREIGN KEY(idBesoinAchat) REFERENCES BesoinAchat(idBesoinAchat),
    FOREIGN KEY(idModeDePaiement) REFERENCES ModeDePaiement(idModeDePaiement)
);

create table ArticleBonDeCommande(
    idArticleBonDeCommande serial,
    idArticle int,
    quantite DECIMAL,
    prixUnitaireHT DECIMAL,
    TVA DECIMAL,
    idBonDeCommande int,
    PRIMARY KEY(idArticleBonDeCommande),
    FOREIGN KEY(idArticle) REFERENCES Article(idArticle),
    FOREIGN KEY(idBonDeCommande) REFERENCES BonDeCommande(idBonDeCommande)

);

create table ValidationBonDeCommande(
   idValidationBonDeCommande serial,
   dateValidation TIMESTAMP,
   commentaire VARCHAR(255),
   idUtilisateur int,
   statusValidation int,
   PRIMARY KEY(idValidationBonDeCommande),
   FOREIGN KEY(idUtilisateur) REFERENCES Utilisateur(idUtilisateur)
);

create table ProformaBesoinAchat(
    idProformaBesoinAchat serial,
    idProforma int,
    idBesoinAchat int,
    dateLivraison TIMESTAMP,
    PRIMARY KEY(idProformaBesoinAchat),
    FOREIGN KEY(idBesoinAchat) REFERENCES BesoinAchat(idBesoinAchat)
);

create table DemandeProforma(
     idDemandeProforma serial,
     idArticleBesoinAchat INT,
     dateDemande TIMESTAMP,
     idFournisseur int,
     statusDemande int,
     PRIMARY KEY(idDemandeProforma),
     FOREIGN KEY(idArticleBesoinAchat) REFERENCES ArticleBesoinAchat(idArticleBesoinAchat),
     FOREIGN KEY(idFournisseur) REFERENCES Fournisseur(idFournisseur)
);

create table DemandeAjoutArticle(
    idDemandeAjoutArticle serial,
    nomArticle VARCHAR(255),
    dateLivraison TIMESTAMP,
    PRIMARY KEY(idDemandeAjoutArticle)
);

