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
