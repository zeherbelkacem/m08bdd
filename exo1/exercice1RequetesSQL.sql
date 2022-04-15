------------------
-- ex1: -
------------------
--1.1/ 
	SOURCE src/shop.sql;
--1.2/ 
	SHOW tables;
--1.3/
	DESCRIBE t_articles;
--1.4/
	INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('dock', 'hp', 62.5);
--1.5/
	UPDATE t_articles SET unitaryprice= 15 WHERE idarticle =1;
	SELECT unitaryprice FROM t_articles WHERE idarticle =1;
--1.6/ 
	DELETE FROM t_articles WHERE idarticle=9;
    SELECT *FROM t_articles;
--1.7/ 
	SELECT *FROM t_articles WHERE unitaryprice>100;
--1.8/ 
	SELECT *FROM t_articles WHERE unitaryPrice >50 AND unitaryprice<100;
--1.9/ 
	SELECT *FROM t_articles ORDER BY unitaryPrice ASC;
--1.10/ 
	SELECT description FROM t_articles;
--1.11/ 
	SELECT *FROM t_articles WHERE brand LIKE 'apple';
    SELECT *FROM t_articles WHERE brand LIKE 'a%';
--1.12/ 
	CREATE TABLE category(idCategory int(4) PRIMARY KEY AUTO_INCREMENT, CatName varchar(30) NOT NULL) ENGINE = InnoDB;
    UPDATE t_articles SET catname = 'Materiel info' WHERE idarticle =1;
--1.13/ 
	SELECT idarticle, description, brand, unitaryprice, catname FROM t_articles INNER JOIN category 
	 WHERE t_articles.idcategory = category.idcategory AND idarticle BETWEEN 10 AND 17 order by unitaryprice asc;

	---Exo 6 & 7 ---
		CREATE TABLE T_Users (IdUser int(4) PRIMARY KEY AUTO_INCREMENT, Login varchar(20) NOT NULL UNIQUE, Password varchar(20) NOT NULL) ENGINE = InnoDB;
		INSERT INTO T_users (login, password) VALUES ('belkacem@localhost', 'belkacem');
		INSERT INTO T_users (login, password) VALUES ('hugo@localhost', 'hugo');
	
--Exercice 11 : Vous étiez root jusqu’ici donc administrateur système aussi faites en sorte 
--de permettre l’accès à un utilisateur uniquement sur la base de données Shop.--

--add users--
	CREATE USER allPrevi@loacalhost IDENTIFIED BY 'allPriv';
	CREATE USER readOnly@loacalhost IDENTIFIED BY 'readOnly';
	CREATE USER writeOnly@loacalhost IDENTIFIED BY 'writeOnly';
	
	-- Privilege---
	GRANT ALL PRIVILEGES ON shop.* TO allPrevi@loacalhost;
	GRANT SELECT ON shop.* TO readOnly@localhost IDENTIFIED BY 'readOnly'
	GRANT INSERT, DELETE, UPDATE ON shop.* TO writeOnly@localhost IDENTIFIED BY 'writeOnly';