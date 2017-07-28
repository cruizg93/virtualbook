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
-- Dumping data for table `tbl_event`
--

LOCK TABLES `tbl_event` WRITE;
/*!40000 ALTER TABLE `tbl_event` DISABLE KEYS */;
INSERT INTO `tbl_event` (`id`, `comments`, `forward_payment`, `client_same_as_client`, `contact_person_email`, `contact_person_name`, `contact_person_number`, `contact_date`, `event_date`, `drop_off_time`, `pick_up_time`, `delivery`, `event_name`, `state`, `taxPercentage`, `client_id`, `location_id`) VALUES (44,'',0,0,'','','','2017-07-27','2017-07-27 22:34:00',NULL,NULL,50,'TEST NULL','ACTIVE',7,10,NULL),(45,'',0,0,'','','','2017-07-27',NULL,NULL,NULL,0,'TEST NULL 2','ACTIVE',0,15,NULL),(46,'',0,1,'','','','2017-07-27',NULL,NULL,NULL,0,'TEST NULL3','ACTIVE',0,7,NULL),(47,'',0,0,'','','','2017-07-27',NULL,NULL,NULL,0,'TEST NULL 4','ACTIVE',0,9,NULL),(48,'',0,1,'','','','2017-07-27',NULL,NULL,NULL,0,'TEST 5','ACTIVE',0,5,NULL);
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

-- Dump completed on 2017-07-27 23:58:50
