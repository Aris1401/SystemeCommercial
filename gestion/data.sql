-- Insert test data into the Service table
INSERT INTO Service (nom) VALUES
    ('Ressources Humaines'),
    ('Comptable'),
    ('Sécurité'),
    ('Informatique');

-- Insert test data into the Article table for Ressources Humaines service
INSERT INTO Article (nom, descriptionArticle) VALUES
    ('Formation RH', 'Formation pour le personnel des Ressources Humaines'),
    ('Manuel RH', 'Manuel des politiques RH'),
    ('Recrutement Kit', 'Ensemble de documents pour le processus de recrutement RH');

-- Insert test data into the Article table for Comptable service
INSERT INTO Article (nom, descriptionArticle) VALUES
    ('Logiciel Comptable', 'Logiciel de comptabilité avancé'),
    ('Guide Fiscal', 'Guide des lois fiscales actuelles'),
    ('Facturation Kit', 'Ensemble de modèles pour la facturation');

-- Insert test data into the Article table for Sécurité service
INSERT INTO Article (nom, descriptionArticle) VALUES
    ('Caméras de sécurité', 'Caméras de surveillance pour les locaux'),
    ('Formation Sécurité', 'Formation sur les procédures de sécurité'),
    ('Équipement de protection', 'Équipement de protection individuelle (EPI)');

-- Insert test data into the Article table for Informatique service
INSERT INTO Article (nom, descriptionArticle) VALUES
    ('Ordinateur Portable', 'Ordinateur portable haute performance'),
    ('Logiciel Antivirus', 'Logiciel de protection contre les virus'),
    ('Formation Informatique', 'Formation sur les dernières technologies informatiques');

-- Insert test data into the Unite table
INSERT INTO Unite (nom) VALUES
    ('Unité'),
    ('Kilogramme'),
    ('Litre'),
    ('Pièce');

-- Insérer des données de test dans la table Profil avec les profils spécifiés : "Service Achat", "Direction Approvisionnement", "Ressources Humaines", "Finances", "Direction Générale", "Chef de Département".
INSERT INTO Profil (nom, idService) VALUES
    ('Service Achat', null),
    ('Direction Approvisionnement', null),
    ('Ressources Humaines', (SELECT idService FROM Service WHERE nom = 'Ressources Humaines')),
    ('Finances', (SELECT idService FROM Service WHERE nom = 'Comptable')),
    ('Direction Générale', null),
    ('Chef de Département', null);

-- Insérer les types de produits correspondant aux catégories spécifiées
INSERT INTO TypeProduit (nom)
VALUES
    ('Formations'),
    ('Manuels'),
    ('Kits de recrutement et de facturation'),
    ('Equipements de sécurité'),
    ('Logiciels et équipements informatiques');

-- Insérer des fournisseurs avec les types de produits correspondants
-- Insérer des fournisseurs malgaches avec les types de produits correspondants
INSERT INTO Fournisseur (
  nom,
  contact,
  nomResponsable,
  contactResponsable,
  email,
  addresse,
  localisation,
  descriptionFournisseur,
  idTypeProduit
)
VALUES
  ('Fournisseur Formations MG', '123456789', 'Jean Rajaonarison', '987654321', 'jean.rajaonarison@fournisseurformations.mg', '123 Avenue des Formateurs, Antananarivo', 'Antananarivo', 'Fournisseur de formations à Antananarivo, Madagascar', (SELECT idTypeProduit FROM TypeProduit WHERE nom = 'Formations')),
  ('Fournisseur Manuels MG', '987654321', 'Sylvie Andrianampoinimerina', '123456789', 'sylvie.andrianampoinimerina@fournisseurmanuels.mg', '456 Rue des Manuels, Toamasina', 'Toamasina', 'Fournisseur de manuels à Toamasina, Madagascar', (SELECT idTypeProduit FROM TypeProduit WHERE nom = 'Manuels')),
  ('Fournisseur Kits MG', '555555555', 'Rakoto Ramiaramanana', '444444444', 'rakoto.ramiaramanana@fournisseurkits.mg', '789 Boulevard des Kits, Antsirabe', 'Antsirabe', 'Fournisseur de kits à Antsirabe, Madagascar', (SELECT idTypeProduit FROM TypeProduit WHERE nom = 'Kits de recrutement et de facturation')),
  ('Fournisseur Sécurité MG', '111111111', 'Alice Randriamampianina', '999999999', 'alice.randriamampianina@fournisseursecurite.mg', '1010 Avenue de la Sécurité, Fianarantsoa', 'Fianarantsoa', 'Fournisseur équipements de sécurité à Fianarantsoa, Madagascar', (SELECT idTypeProduit FROM TypeProduit WHERE nom = 'Equipements de sécurité')),
  ('Fournisseur Informatique MG', '777777777', 'Michel Razafindrakoto', '666666666', 'michel.razafindrakoto@fournisseurinformatique.mg', '1212 Rue de Informatique, Mahajanga', 'Mahajanga', 'Fournisseur informatique à Mahajanga, Madagascar', (SELECT idTypeProduit FROM TypeProduit WHERE nom = 'Logiciels et équipements informatiques'));


INSERT INTO ConfigurationValues (designation, valeur) VALUES ('TVA', '20');
INSERT INTO ConfigurationValues (designation, valeur) VALUES ('N_BonDeCommande_Format', '000000');

INSERT INTO ModeDePaiement(nom) VALUES ('Carte Bancaire'), ('Cheque'), ('Espece');

    -- Insérer deux types d'utilisateurs
    INSERT INTO TypeUtilisateur (nom)
    VALUES
      ('Lambda'),
      ('Personnel');

    -- Insérer des profils avec idService à NULL
    INSERT INTO Profil (nom, idService)
    VALUES
      ('Service Achat', NULL),
      ('Direction Approvisionnement', NULL),
      ('Ressources Humaines', NULL),
      ('Finances', NULL),
      ('Direction Générale', NULL),
      ('Chef de Département', NULL);

    -- Insérer des utilisateurs associés à chaque profil
    INSERT INTO Utilisateur (nom, prenom, email, motDePasse, idTypeUtilisateur)
    VALUES
      ('AcheteurA', 'Acheteur', 'acheteura@example.com', 'motDePasseHashé', 1),
      ('ApprovisionneurA', 'Approvisionneur', 'approvisionneura@example.com', 'motDePasseHashé', 1),
      ('RHA', 'RH', 'rha@example.com', 'motDePasseHashé', 1),
      ('FinanceurA', 'Financeur', 'financeura@example.com', 'motDePasseHashé', 1),
      ('DirecteurA', 'Directeur', 'directeura@example.com', 'motDePasseHashé', 1),
      ('ChefDepartementA', 'Chef', 'chefa@example.com', 'motDePasseHashé', 1);


    INSERT INTO ProfilUtilisateur (idUtilisateur, idProfil, dateAjout) VALUES (1, 1, CURRENT_TIMESTAMP);
    INSERT INTO ProfilUtilisateur (idUtilisateur, idProfil, dateAjout) VALUES (2, 2, CURRENT_TIMESTAMP);
    INSERT INTO ProfilUtilisateur (idUtilisateur, idProfil, dateAjout) VALUES (3, 3, CURRENT_TIMESTAMP);
    INSERT INTO ProfilUtilisateur (idUtilisateur, idProfil, dateAjout) VALUES (4, 4, CURRENT_TIMESTAMP);
    INSERT INTO ProfilUtilisateur (idUtilisateur, idProfil, dateAjout) VALUES (5, 5, CURRENT_TIMESTAMP);
    INSERT INTO ProfilUtilisateur (idUtilisateur, idProfil, dateAjout) VALUES (6, 6, CURRENT_TIMESTAMP);

--- Stock
insert into unite(nom) values ('Paquet');
insert into uniteequivalence(idArticle, idUnite, quantite) values (13, 1, 1), (13, 5, 10);

insert into mouvementstock(entree, datemouvement, idArticle, idUnite, prixunitaire) values (500, CURRENT_TIMESTAMP, 13, 1, 300);

INSERT INTO ConfigurationValues (designation, valeur) VALUES ('company-id', '0');
INSERT INTO ConfigurationValues (designation, valeur) VALUES ('company-email', 'cest.arisaina@gmail.com');
INSERT INTO ConfigurationValues (designation, valeur) VALUES ('company-name', 'Commercant');

--String idFournisseur = ConfigurationValues.getConfigurationValue("company-id");
--        String email = ConfigurationValues.getConfigurationValue("company-email");
--        String name = ConfigurationValues.getConfigurationValue("company-name");