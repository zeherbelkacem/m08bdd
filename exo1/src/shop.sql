DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;

CREATE TABLE T_Articles (
    IdArticle int(4) PRIMARY KEY AUTO_INCREMENT,
    Description varchar(30) NOT NULL,
    Brand varchar(30) NOT NULL,
    UnitaryPrice float(8) NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Souris', 'Logitoch', 65); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clavier', 'Microhard', 49.5); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Système d''exploitation', 'Fenetres Vistouille', 100); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Tapis souris', 'Chapeau bleu', 5); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clé USB 8 To', 'Syno', 65); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Laptop', 'PH', 1199); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('CD x 500', 'CETME', 250); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD-R x 100', 'CETME', 99); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD+R x 100', 'Logitech', 105); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Batterie Laptop', 'PH', 80); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Casque Audio', 'Syno', 105); 
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Webcam', 'Logitoch', 755); 
