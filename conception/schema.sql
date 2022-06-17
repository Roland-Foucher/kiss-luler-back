-- MariaDB dump 10.19  Distrib 10.6.4-MariaDB, for osx10.16 (x86_64)
--
-- Host: 127.0.0.1    Database: kiss-luler-back
-- ------------------------------------------------------
-- Server version	10.6.4-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `consideration`
--

DROP TABLE IF EXISTS `consideration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consideration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consid_amount` int(10) unsigned NOT NULL,
  `description` tinytext DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKimeom8mttfiynorty0y8xt5lx` (`project_id`),
  CONSTRAINT `FKimeom8mttfiynorty0y8xt5lx` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` enum('TOURDATE','EP','CD','EVENT','INSTRUMENT','COMMUNICATION') NOT NULL,
  `date_end` date NOT NULL,
  `date_init` date NOT NULL,
  `description` tinytext DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `status` enum('INPROGRESS','CONCEPTION','BLACKLISTED','PAUSED') NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo06v2e9kuapcugnyhttqa1vpt` (`user_id`),
  CONSTRAINT `FKo06v2e9kuapcugnyhttqa1vpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER','BLACKLISTED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double unsigned NOT NULL,
  `date` date NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1wqvsjpqw0y60tqxfkwva4p32` (`project_id`),
  KEY `FKj86u1x7csa8yd68ql2y1ibrou` (`user_id`),
  CONSTRAINT `FK1wqvsjpqw0y60tqxfkwva4p32` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKj86u1x7csa8yd68ql2y1ibrou` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-16 16:28:46
