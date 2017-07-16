CREATE DATABASE  IF NOT EXISTS `virtualbook` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `virtualbook`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: virtualbook
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_location`
--

DROP TABLE IF EXISTS `tbl_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `building_name` varchar(255) DEFAULT NULL,
  `location` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_location`
--

LOCK TABLES `tbl_location` WRITE;
/*!40000 ALTER TABLE `tbl_location` DISABLE KEYS */;
INSERT INTO `tbl_location` (`id`, `building_name`, `location`, `phone_number`, `state`) VALUES (1,'TAMPA CLUB','101 E KENNEDY, TAMPA, FL, 33607 SUITE 4200','(222) 222-2222','Deleted'),(2,'MARRIOT WATERSIDE','NONE','(222) 222-2222','Active'),(3,'TAMPA GARDEN CLUB','NANA','(222) 222-2222','Active'),(4,'OPAL SANDS','22222','(222) 222-2222','Active'),(5,'GRAND HYATT','NONO','(555) 555-5555','Active'),(6,'THE RIALSO','ASDSAD','(222) 222-2222','Active'),(7,'THE REGENT','BRANDON ','(222) 222-2222','Active'),(8,'MAGNOLIA BLD','LKWLNS','(889) 898-9898','Active'),(9,'DON CESAR','ST PETE','(999) 999-9999','Active'),(10,'JACKSON BISTRO & SUSHI','DISCO','(333) 333-3333','Active'),(11,'SARASOTA','SARASOTA','(333) 333-3333','Active'),(12,'HILTON CLEARWATER','HIL CLEAR','(222) 222-2222','Active'),(13,'INNISBROOK','INNIS','(222) 222-2222','Active');
/*!40000 ALTER TABLE `tbl_location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-16 17:49:11
