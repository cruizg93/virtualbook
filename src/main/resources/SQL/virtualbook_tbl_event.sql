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
-- Table structure for table `tbl_event`
--

DROP TABLE IF EXISTS `tbl_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `forward_payment` double NOT NULL,
  `client_same_as_client` int(1) NOT NULL DEFAULT '1',
  `contact_person_email` varchar(255) DEFAULT NULL,
  `contact_person_name` varchar(255) DEFAULT NULL,
  `contact_person_number` varchar(255) DEFAULT NULL,
  `contact_date` date DEFAULT NULL,
  `event_date` datetime NOT NULL,
  `delivery` double NOT NULL,
  `drop_off_time` datetime NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `pick_up_time` datetime NOT NULL,
  `state` varchar(255) NOT NULL,
  `taxPercentage` double NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk13wlumwm6cs6eeodkcd1prlc` (`client_id`),
  KEY `FKfaoimse18nu9cjefomvsnstbr` (`location_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event`
--

LOCK TABLES `tbl_event` WRITE;
/*!40000 ALTER TABLE `tbl_event` DISABLE KEYS */;
INSERT INTO `tbl_event` (`id`, `comments`, `forward_payment`, `client_same_as_client`, `contact_person_email`, `contact_person_name`, `contact_person_number`, `contact_date`, `event_date`, `delivery`, `drop_off_time`, `event_name`, `pick_up_time`, `state`, `taxPercentage`, `client_id`, `location_id`) VALUES (8,'promo price',414.625,1,NULL,NULL,NULL,'2017-07-13','2017-07-10 09:07:15',50,'2017-07-13 09:07:15','PROMO PRICE','2017-07-13 09:07:15','Active',7,5,1),(9,'',695.5,1,'','','','2017-07-13','2017-07-17 09:07:11',50,'2017-07-13 09:27:09','NO GLOR','2017-07-13 09:27:09','Active',7,5,1),(18,'3',3,1,NULL,NULL,NULL,'2017-07-16','2016-12-01 02:12:43',3,'2017-07-16 17:46:42','2016 TEST','2017-07-16 17:46:42','DELETED',3,13,5),(3,'paid in full via paypal #2334234',1284,1,'','','','2017-07-13','2017-07-17 12:07:30',50,'2017-07-13 06:19:10','MEGAN HUNTEY','2017-07-13 06:19:10','Active',7,3,3),(4,'',1524.75,1,'','','','2017-07-13','2017-07-25 13:07:00',50,'2017-07-13 06:20:42','RACHEL','2017-07-13 06:20:42','Active',7,4,5),(10,'',920.2,1,'','','','2017-07-13','2017-07-24 09:07:11',50,'2017-07-13 09:28:11','CAMPAIGN','2017-07-13 09:28:11','Active',7,5,1),(11,'',1016.5,1,'','','','2017-07-13','2017-07-31 09:07:07',50,'2017-07-13 09:29:07','KAITLYN','2017-07-13 09:29:07','Active',7,6,7),(12,'',0,1,'','','','2017-07-13','2017-07-16 09:07:50',50,'2017-07-13 09:31:50','SUE','2017-07-13 09:31:50','DELETED',7,2,2),(13,'',0,1,NULL,NULL,NULL,'2017-07-13','2017-07-16 10:07:14',50,'2017-07-13 09:37:08','CASCADE TEST','2017-07-13 09:37:08','Active',7,7,7),(14,'',0,1,NULL,NULL,NULL,'2017-07-13','2017-07-07 06:07:46',50,'2017-07-13 09:47:03','TA','2017-07-13 09:47:03','Active',7,5,1),(15,'',0,1,NULL,NULL,NULL,'2017-07-13','2017-07-03 09:07:09',50,'2017-07-13 09:49:09','INAME','2017-07-13 09:49:09','Active',7,9,13),(16,'7',0,1,'','','','2017-07-13','2017-07-17 09:07:45',50,'2017-07-13 09:49:45','CENCELADO','2017-07-13 09:49:45','Active',7,5,1),(17,'',0,1,'','','','2017-07-13','2017-07-24 09:07:20',50,'2017-07-13 09:50:19','BODA','2017-07-13 09:50:19','Active',7,10,8);
/*!40000 ALTER TABLE `tbl_event` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-16 17:49:10
