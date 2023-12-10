create database systemecommercial;

DROP TABLE IF EXISTS DemandeAjoutArticle CASCADE;
DROP TABLE IF EXISTS DemandeProforma CASCADE;
DROP TABLE IF EXISTS ProformaBesoinAchat CASCADE;
DROP TABLE IF EXISTS ValidationBonDeCommande CASCADE;
DROP TABLE IF EXISTS ArticleBonDeCommande CASCADE;
DROP TABLE IF EXISTS BonDeCommande CASCADE;
DROP TABLE IF EXISTS ModeDePaiement CASCADE;
DROP TABLE IF EXISTS Proforma CASCADE;
DROP TABLE IF EXISTS Fournisseur CASCADE;
DROP TABLE IF EXISTS TypeProduit CASCADE;
DROP TABLE IF EXISTS ValidationBesoinAchat CASCADE;
DROP TABLE IF EXISTS ArticleBesoinAchat CASCADE;
DROP TABLE IF EXISTS BesoinAchat CASCADE;
DROP TABLE IF EXISTS TypeUtilisateur CASCADE;
DROP TABLE IF EXISTS Utilisateur CASCADE;
DROP TABLE IF EXISTS ProfilUtilisateur CASCADE;
DROP TABLE IF EXISTS Profil CASCADE;
DROP TABLE IF EXISTS Service CASCADE;
DROP TABLE IF EXISTS Article CASCADE;
DROP TABLE IF EXISTS Unite CASCADE;

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
    description TEXT,
    PRIMARY KEY(idBesoinAchat),
    FOREIGN KEY(idService) REFERENCES Service (idService)
);

-- ALTER TABLE BesoinAchat ADD COLUMN description TEXT

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
    FOREIGN KEY(idUtilisateur) REFERENCES Utilisateur(idUtilisateur)
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
    idUnite int REFERENCES Unite(idUnite) DEFAULT 1,
    PRIMARY KEY(idProforma),
    FOREIGN KEY(idFournisseur) REFERENCES  Fournisseur(idFournisseur),
    FOREIGN KEY(idArticle) REFERENCES Article (idArticle)
);

-- ALTER TABLE Proforma ADD COLUMN idUnite int REFERENCES Unite(idUnite) DEFAULT 1;

create table ModeDePaiement(
    idModeDePaiement serial,
    nom VARCHAR(255),
    PRIMARY KEY(idModeDePaiement)
);

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
    statusBonDeCommande int,
    PRIMARY KEY(idBonDeCommande),
    FOREIGN KEY(idFournisseur) REFERENCES Fournisseur(idFournisseur),
    FOREIGN KEY(idBesoinAchat) REFERENCES BesoinAchat(idBesoinAchat),
    FOREIGN KEY(idModeDePaiement) REFERENCES ModeDePaiement(idModeDePaiement)
);

-- ALTER TABLE BonDeCommande ADD COLUMN statusBonDeCommande int;

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

create table ProformaArticleBesoinAchat(
    idProformaBesoinAchat serial,
    idProforma int,
    idArticleBesoinAchat int,
    dateLiaison TIMESTAMP,
    PRIMARY KEY(idProformaBesoinAchat),
    FOREIGN KEY(idArticleBesoinAchat) REFERENCES ArticleBesoinAchat(idArticleBesoinAchat)
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
    statusDemande int,
    PRIMARY KEY(idDemandeAjoutArticle)
);

-- ALTER TABLE DemandeAjoutArticle ADD COLUMN statusDemande int;

create table ConfigurationValues(
    designation varchar(255),
    valeur varchar(255)
);

-- Cote fournisseur
create table DemandeProformaCoteFournisseur(
    idDemandeProformaCoteFournisseur SERIAL PRIMARY KEY,
    dateDemande TIMESTAMP,
    idArticle INT,
    idUnite INT,
    quantite DECIMAL(18, 5),
    idFournisseur INT,
    etat INT,
    lienEmail TEXT
);

create table ProformaCoteFournisseur(
    idProformaCoteFournisseur SERIAL PRIMARY KEY,
    idDemandeProformaCoteFournisseur INT REFERENCES DemandeProformaCoteFournisseur(idDemandeProformaCoteFournisseur)
    quantite DECIMAL(18, 5),
    prixUnitaire DECIMAL(18, 5)
);

create table ValidationDemandeProformaCoteFournisseur(
    idValidationDemandeProformaCoteFournisseur SERIAL PRIMARY KEY,
    dateValidation TIMESTAMP,
    idDemandeProformaCoteFournisseur INT REFERENCES DemandeProformaCoteFournisseur(idDemandeProformaCoteFournisseur)
);

-- Cote antsika
create table DemandeRecuProforma(
    idDemandeRecuProforma SERIAL PRIMARY KEY,
    idFournisseurFrom INT,
    lienEmail TEXT,
    quantite DECIMAL(18, 5),
    idArticle INT REFERENCES Article(idArticle),
    idUnite INT REFERENCES Unite(idUnite),
    dateDemande TIMESTAMP,
    etat INT
);

create table ReponseDemandeRecuProforma (
    idReponseDemandeRecuProforma SERIAL PRIMARY KEY,
    dateReponse TIMESTAMP,
    lienEmail TEXT,
    quantite DECIMAL(18, 5),
    prixUnitaire DECIMAL(18, 5),
    idDemandeRecuProforma INT REFERENCES DemandeRecuProforma(idDemandeRecuProforma)
);

-- Stock
create table MouvementStock (
    idMouvementStock SERIAL PRIMARY KEY,
    entree DECIMAL DEFAULT 0,
    sortie DECIMAL DEFAULT 0,
    dateMouvement TIMESTAMP,
    idArticle INT REFERENCES Article(idArticle),
    idUnite INT REFERENCES Unite(idUnite),
    prixUnitaire DECIMAL DEFAULT 0
);

create table UniteEquivalence (
    idUniteEquivalence SERIAL PRIMARY KEY,
    idUnite INT REFERENCES Unite(idUnite),
    idArticle INT REFERENCES Article(idArticle),
    quantite DECIMAL DEFAULT 0
);

-- Accessibilties pages
create table profilNonAccessiblePage(
    idNonAccessiblePage serial PRIMARY KEY,
    idProfile int REFERENCES Profil(idProfil),
    nonAccessiblePage VARCHAR(255)
);