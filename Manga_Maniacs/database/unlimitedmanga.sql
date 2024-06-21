CREATE DATABASE  IF NOT EXISTS `unlimitedmanga` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `unlimitedmanga`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: unlimitedmanga
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appartiene`
--

DROP TABLE IF EXISTS `appartiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appartiene` (
  `id_genere` int NOT NULL,
  `id_manga` int NOT NULL,
  PRIMARY KEY (`id_genere`,`id_manga`),
  KEY `CE_id_manga_idx` (`id_manga`),
  CONSTRAINT `CE_id_genere` FOREIGN KEY (`id_genere`) REFERENCES `genere` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CE_id_manga_genere` FOREIGN KEY (`id_manga`) REFERENCES `manga` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appartiene`
--

LOCK TABLES `appartiene` WRITE;
/*!40000 ALTER TABLE `appartiene` DISABLE KEYS */;
INSERT INTO `appartiene` VALUES (5,1),(6,1),(7,1),(6,3),(8,3);
/*!40000 ALTER TABLE `appartiene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collocamento`
--

DROP TABLE IF EXISTS `collocamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collocamento` (
  `id_deposito` int NOT NULL,
  `id_manga` int NOT NULL,
  `disponibilita` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_deposito`,`id_manga`),
  KEY `CE_id_manga_idx` (`id_manga`),
  CONSTRAINT `CE_id_deposito` FOREIGN KEY (`id_deposito`) REFERENCES `deposito` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CE_id_manga` FOREIGN KEY (`id_manga`) REFERENCES `manga` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collocamento`
--

LOCK TABLES `collocamento` WRITE;
/*!40000 ALTER TABLE `collocamento` DISABLE KEYS */;
INSERT INTO `collocamento` VALUES (2,1,8),(2,3,5),(3,3,10);
/*!40000 ALTER TABLE `collocamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contiene`
--

DROP TABLE IF EXISTS `contiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contiene` (
  `id_manga` int NOT NULL,
  `id_ordine` int NOT NULL,
  `iva_acquisto` double DEFAULT NULL,
  `prezzo_acquisto` double DEFAULT NULL,
  `quantita` int DEFAULT NULL,
  PRIMARY KEY (`id_manga`,`id_ordine`),
  KEY `CE_id_ordine_idx` (`id_ordine`),
  CONSTRAINT `CE_id_manga_cart` FOREIGN KEY (`id_manga`) REFERENCES `manga` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `CE_id_ordine` FOREIGN KEY (`id_ordine`) REFERENCES `ordine` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contiene`
--

LOCK TABLES `contiene` WRITE;
/*!40000 ALTER TABLE `contiene` DISABLE KEYS */;
/*!40000 ALTER TABLE `contiene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposito`
--

DROP TABLE IF EXISTS `deposito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deposito` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposito`
--

LOCK TABLES `deposito` WRITE;
/*!40000 ALTER TABLE `deposito` DISABLE KEYS */;
INSERT INTO `deposito` VALUES (3,'deposito£'),(1,'deposito1'),(2,'deposito2');
/*!40000 ALTER TABLE `deposito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genere`
--

DROP TABLE IF EXISTS `genere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genere` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `descrizione` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genere`
--

LOCK TABLES `genere` WRITE;
/*!40000 ALTER TABLE `genere` DISABLE KEYS */;
INSERT INTO `genere` VALUES (5,'ECCHI','Fra la vasta produzione dei fumetti giapponesi è in costante crescita e diffusione quella relativa ai manga ecchi, una parola composta dell\'alfabeto del Paese del Sol Levante che, sostanzialmente, individua tutte quelle opere in cui sono rappresentati dei contenuti erotici o spinti ma che mai arrivano a toccare soglie di nudità o volgarità troppo spinte'),(6,'HORROR','Il genere horror giapponese è molto famoso nella letteratura classica e nei film, dove ci ha regalato perle importanti per gli appassionati come la saga di The Ring, Uzumaki o Jijatsu Sakuru. A rendere particolarmente confacente la cultura giapponese con questo genere è sicuramente la presenza, nella mitologia e nella cultura nipponica, di figure e di entità che si prestano bene ad essere interpretate come orrorifiche e spaventose, grazie ai racconti e alle leggende che aleggiano intorno alle stesse.'),(7,'ISEKAI','Sono ormai diffuse in tutto l\'Occidente le light novel, ovvero dei romanzi a cui vengono in seguito aggiunte delle illustrazioni per renderli dei veri e propri fumetti. Non tutti sanno, però, che esiste un sottogenere di questa categoria che dà vita ai cosiddetti manga isekai. Dalla traduzione letterale dell\'ideogramma giapponese - ovvero mondo differente - il genere isekai prevede che il protagonista dell\'opera si risvegli di punto in bianco, spesso dimenticandosi del suo passato o ricordandolo interamente o in parte, in un mondo nuovo, fantasy, distopico o reale, a seguito di un\'evocazione da parte di forze sovrannaturali o a seguito di una reincarnazione.'),(8,'SHOJO','Se stai pensando a romantiche e commoventi scene sotto una pioggia di petali di ciliegio, con molta probabilità potrai trovarle in un manga shojo. Il termine \"shojo\" significa \"ragazza\" e più che indicare un genere specifico si riferisce al destinatario più comune a cui sono indirizzati i contenuti. In questa tipologia di manga troviamo generalmente tematiche sentimentali, con protagonisti idealizzati e avvolti in un\'aria quasi magica. I personaggi principali sono perlopiù ragazze o ragazzi dalla personalità e dai tratti idealizzati, quasi surreali.'),(9,'MECHA','Se siete fan di tutto ciò che si trova al confine tra tecnologico e fantascientifico i manga mecha sono perfetti per voi. Amanti di universi tecnologicamente strabilianti unitevi! Tutto quello che state cercando è proprio tra le pagine di questi manga.'),(10,'Josei ','hojo ma più “adulto”'),(11,'Romantic Comedy ','in parole povere le commedie romantiche si basano sull’elemento romantico e condiscono il tutto con triangoli amorosi, equivoci e molte gag comiche che hanno fatto la storia. Insomma, niente di particolarmente innovativo, se non il fatto che siano dannatamente coinvolgenti!');
/*!40000 ALTER TABLE `genere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manga`
--

DROP TABLE IF EXISTS `manga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manga` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Titolo` varchar(45) DEFAULT NULL,
  `prezzo` double DEFAULT NULL,
  `editore` varchar(45) DEFAULT NULL,
  `autore` varchar(45) DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `descrizione` varchar(500) DEFAULT NULL,
  `img` varchar(45) DEFAULT NULL,
  `inv` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manga`
--

LOCK TABLES `manga` WRITE;
/*!40000 ALTER TABLE `manga` DISABLE KEYS */;
INSERT INTO `manga` VALUES (1,'Shingeki no Kyojin',4.23,'Panini Comics','Hajime Isayama',22,'Hundreds of years ago, horrifying creatures which resembled humans appeared. These mindless, towering giants, called \"Titans,\" proved to be an existential threat, as they preyed on whatever','./img/Shingeki no Kyojin.jpg',0),(3,'One Piece',4.23,'Panini Comics','Eiichirō Oda',22,'Gol D. Roger, a man referred to as the \"Pirate King,\" is set to be executed by the World Government. But just before his demise, he confirms the existence of a great treasure, One Piece, located somewhere within the vast ocean known as the Grand Line. Announcing that One Piece can be claimed by anyone worthy enough to reach it, the Pirate King is executed and the Great Age of Pirates begins.','./img/One Piece.jpg',0),(4,'nonso',9,'ertyu','ertyu',10,'sefoija',NULL,0),(6,'serf',1.6,'ertyu','ertyu',10,'efewef',NULL,0),(7,'serf',1.6,'ertyu','ertyu',10,'efewef',NULL,0),(8,'serf',1.6,'ertyu','ertyu',10,'efewef',NULL,0),(9,'serf',1.6,'ertyu','ertyu',10,'efewef',NULL,0),(10,'nonso',9,'ertyu','ertyu',10,'jnouihn',NULL,0);
/*!40000 ALTER TABLE `manga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `id` int NOT NULL,
  `Id_utente` int DEFAULT NULL,
  `iva_tot` double DEFAULT NULL,
  `somma_tot` double DEFAULT NULL,
  `data_ordine` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `CE_id_utente` FOREIGN KEY (`id`) REFERENCES `utente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `psw` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `data_nascita` varchar(45) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `ruolo` varchar(45) DEFAULT 'utente',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'giuseppe','0000','hdgejdidj','30-10-2001','giuseppe','Sgambato','admin'),(2,'tizio','1111','dafsfsaf',NULL,'tizio','bo','utente'),(3,'pene','0000',NULL,NULL,NULL,NULL,'utente'),(16,'giuse','0000',NULL,NULL,NULL,NULL,'utente');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-11 14:20:16
