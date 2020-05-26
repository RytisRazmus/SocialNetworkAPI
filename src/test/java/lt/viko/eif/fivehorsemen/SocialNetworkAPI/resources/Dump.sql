CREATE DATABASE  IF NOT EXISTS `cityTransport` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `cityTransport`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 78.61.168.194    Database: cityTransport
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.38-MariaDB-0+deb9u1

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
-- Table structure for table `Chat`
--

DROP TABLE IF EXISTS `Chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chat`
--

LOCK TABLES `Chat` WRITE;
/*!40000 ALTER TABLE `Chat` DISABLE KEYS */;
INSERT INTO `Chat` VALUES (1,'+++'),(2,'Alfa Patinai'),(3,'Beta Patinai');
/*!40000 ALTER TABLE `Chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChatParticipant`
--

DROP TABLE IF EXISTS `ChatParticipant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChatParticipant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `chatId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `chatId` (`chatId`),
  CONSTRAINT `ChatParticipant_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `ChatParticipant_ibfk_2` FOREIGN KEY (`chatId`) REFERENCES `Chat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChatParticipant`
--

LOCK TABLES `ChatParticipant` WRITE;
/*!40000 ALTER TABLE `ChatParticipant` DISABLE KEYS */;
INSERT INTO `ChatParticipant` VALUES (1,1,1),(2,2,1),(3,3,1),(4,3,2),(5,2,2),(6,1,2),(7,10,2);
/*!40000 ALTER TABLE `ChatParticipant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postID` int(11) DEFAULT NULL,
  `userImageId` int(11) DEFAULT NULL,
  `fromUser` int(11) NOT NULL,
  `text` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `postID` (`postID`),
  KEY `userImageId` (`userImageId`),
  KEY `fromUser` (`fromUser`),
  CONSTRAINT `Comment_ibfk_1` FOREIGN KEY (`postID`) REFERENCES `Post` (`id`),
  CONSTRAINT `Comment_ibfk_2` FOREIGN KEY (`userImageId`) REFERENCES `UserImage` (`id`),
  CONSTRAINT `Comment_ibfk_3` FOREIGN KEY (`fromUser`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
INSERT INTO `Comment` VALUES (1,2,NULL,2,'Ir visai neblogas!','2020-05-19 09:29:35'),(2,NULL,3,2,'Labai panasus','2020-05-19 09:32:45');
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Content`
--

DROP TABLE IF EXISTS `Content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Content`
--

LOCK TABLES `Content` WRITE;
/*!40000 ALTER TABLE `Content` DISABLE KEYS */;
INSERT INTO `Content` VALUES (1,'Oh my, this is a first post!'),(2,'Rytis daro posta'),(3,'Maciau gera pana.'),(4,'Maciau gera pana.'),(5,'Maciau gera pana.'),(6,'atsibodo.'),(7,'atsibodo.'),(8,'atsibodo.'),(9,'Sorry uz daug vienodu postu, man lagina.'),(11,'Prieš dešimt metų su merginomis pirmą kartą pabandžiau būti ne nuoga. Nuo tada daug supratau apie drabužius ir dabar dažnai apsirengiu.'),(12,'I feel like I\'m on fiiiiire.'),(13,'Sveiki draugai!'),(14,'Sveikas!'),(15,'Ieškau palstikos chirurgo'),(16,'Aš mechanikas, bet galiu paoperuoti.');
/*!40000 ALTER TABLE `Content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmojiReaction`
--

DROP TABLE IF EXISTS `EmojiReaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EmojiReaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unicode` varchar(10) NOT NULL,
  `positivityScale` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmojiReaction`
--

LOCK TABLES `EmojiReaction` WRITE;
/*!40000 ALTER TABLE `EmojiReaction` DISABLE KEYS */;
INSERT INTO `EmojiReaction` VALUES (1,'U+1F600',8),(2,'U+1F602',10),(3,'U+1F642',7);
/*!40000 ALTER TABLE `EmojiReaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Friend`
--

DROP TABLE IF EXISTS `Friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `friendId` int(11) NOT NULL,
  `dateAdded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`,`friendId`),
  UNIQUE KEY `friendId` (`friendId`,`userId`),
  CONSTRAINT `Friend_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `Friend_ibfk_2` FOREIGN KEY (`friendId`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=367 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Friend`
--

LOCK TABLES `Friend` WRITE;
/*!40000 ALTER TABLE `Friend` DISABLE KEYS */;
INSERT INTO `Friend` VALUES (1,1,2,'2020-05-14 15:47:27'),(2,2,1,'2020-05-14 15:47:27'),(6,1,3,'2020-05-14 16:18:39'),(7,3,1,'2020-05-14 16:18:39'),(8,5,3,'2020-05-14 16:59:45'),(9,3,5,'2020-05-14 16:59:46'),(10,2,3,'2020-05-14 17:01:48'),(11,3,2,'2020-05-14 17:01:48'),(16,2,10,'2020-05-16 18:39:51'),(17,10,2,'2020-05-16 18:39:51'),(18,10,3,'2020-05-19 08:59:57'),(19,3,10,'2020-05-19 08:59:57'),(20,10,1,'2020-05-19 09:06:53'),(21,1,10,'2020-05-19 09:06:53'),(22,11,1,'2020-05-19 09:06:55'),(23,1,11,'2020-05-19 09:06:55'),(24,12,3,'2020-05-19 09:18:28'),(25,3,12,'2020-05-19 09:18:28'),(26,3,11,'2020-05-19 09:20:11'),(27,11,3,'2020-05-19 09:20:11'),(28,4,5,'2020-05-22 10:16:41'),(29,5,4,'2020-05-22 10:16:42'),(362,8,9,'2020-05-22 10:20:26'),(363,9,8,'2020-05-22 10:20:26');
/*!40000 ALTER TABLE `Friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FriendInvite`
--

DROP TABLE IF EXISTS `FriendInvite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FriendInvite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `toUser` int(11) NOT NULL,
  `fromUser` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `toUser` (`toUser`,`fromUser`),
  UNIQUE KEY `fromUser` (`fromUser`,`toUser`),
  CONSTRAINT `FriendInvite_ibfk_1` FOREIGN KEY (`toUser`) REFERENCES `User` (`id`),
  CONSTRAINT `FriendInvite_ibfk_2` FOREIGN KEY (`fromUser`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FriendInvite`
--

LOCK TABLES `FriendInvite` WRITE;
/*!40000 ALTER TABLE `FriendInvite` DISABLE KEYS */;
INSERT INTO `FriendInvite` VALUES (1,1,3),(17,1,10),(28,1,11),(4,2,3),(18,2,10),(6,3,2),(36,3,9),(15,3,10),(30,3,12),(16,4,2),(3,5,3),(13,10,2);
/*!40000 ALTER TABLE `FriendInvite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GroupInvites`
--

DROP TABLE IF EXISTS `GroupInvites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GroupInvites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`),
  KEY `groupId_2` (`groupId`),
  CONSTRAINT `GroupInvites_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `GroupInvites_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `GroupP` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GroupInvites`
--

LOCK TABLES `GroupInvites` WRITE;
/*!40000 ALTER TABLE `GroupInvites` DISABLE KEYS */;
INSERT INTO `GroupInvites` VALUES (1,1,5),(3,10,3),(5,8,4),(9,4,2),(11,3,1);
/*!40000 ALTER TABLE `GroupInvites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GroupModerator`
--

DROP TABLE IF EXISTS `GroupModerator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GroupModerator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moderatorId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `moderatorId` (`moderatorId`),
  KEY `groupId` (`groupId`),
  CONSTRAINT `GroupModerator_ibfk_1` FOREIGN KEY (`moderatorId`) REFERENCES `User` (`id`),
  CONSTRAINT `GroupModerator_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `GroupP` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GroupModerator`
--

LOCK TABLES `GroupModerator` WRITE;
/*!40000 ALTER TABLE `GroupModerator` DISABLE KEYS */;
INSERT INTO `GroupModerator` VALUES (1,3,5),(2,2,4),(3,1,2),(4,4,3),(5,5,1);
/*!40000 ALTER TABLE `GroupModerator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GroupP`
--

DROP TABLE IF EXISTS `GroupP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GroupP` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(60) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GroupP`
--

LOCK TABLES `GroupP` WRITE;
/*!40000 ALTER TABLE `GroupP` DISABLE KEYS */;
INSERT INTO `GroupP` VALUES (1,'Apleistų pastatų tyrinėtojai','Bebaimiai griuvančio sovietinio palikimo atradėjai'),(2,'Pievų bridėjai','Aukštų prievų tyrinėtojai, erkių surinkėjai'),(3,'Gintaro rinkėjai','Megėjam rakum po smėly ropinėti'),(4,'Dornų lenktyninkai','Patys kiečiausi savo šalies sportininkai'),(5,'Alfa patinai','Patarimai kaip nebuti beta ir susirasti daug mergikių');
/*!40000 ALTER TABLE `GroupP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Image`
--

DROP TABLE IF EXISTS `Image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contentId` int(11) DEFAULT NULL,
  `imageURL` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `contentId` (`contentId`),
  CONSTRAINT `Image_ibfk_1` FOREIGN KEY (`contentId`) REFERENCES `Content` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Image`
--

LOCK TABLES `Image` WRITE;
/*!40000 ALTER TABLE `Image` DISABLE KEYS */;
INSERT INTO `Image` VALUES (1,3,'https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow_female_black_white.jpg'),(2,4,'https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow_female_black_white.jpg'),(3,5,'https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow_female_black_white.jpg'),(4,6,'https://cdn.pixabay.com/photo/2020/04/09/06/29/sad-man-5019913_960_720.jpg'),(5,7,'https://cdn.pixabay.com/photo/2020/04/09/06/29/sad-man-5019913_960_720.jpg'),(6,8,'https://cdn.pixabay.com/photo/2020/04/09/06/29/sad-man-5019913_960_720.jpg'),(7,9,'https://tedideas.files.wordpress.com/2019/03/featured_art_sorry_jenice_kim.jpg'),(9,NULL,'https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce0cc5ebeb.jpg'),(10,NULL,'https://i.pinimg.com/originals/cb/b6/1d/cbb61dc9f560a4e96c2c64f41a90ce3f.jpg'),(11,NULL,'https://i.pinimg.com/originals/cb/b6/1d/cbb61dc9f560a4e96c2c64f41a90ce3f.jpg'),(12,NULL,'https://pbs.twimg.com/profile_images/420022709989277698/yg8zKkNK_400x400.png'),(13,NULL,'https://i.pinimg.com/236x/ba/06/5e/ba065e39ff4c952bfbcfe4b1f99f4260.jpg'),(14,NULL,'https://i.pinimg.com/474x/fa/ba/54/faba5498b3167071dc93e22f3ce1e22a.jpg'),(15,11,'https://i.ytimg.com/vi/BuNluYTUkrA/maxresdefault.jpg'),(16,NULL,'https://jp.lt/wp-content/uploads/2019/02/Oksana_Pikul.jpg'),(17,12,'https://static.eurovision.tv/hb-cgi/images/8fd8837b-fb88-461b-8ac7-412b3612146e/hero.jpeg');
/*!40000 ALTER TABLE `Image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Interests`
--

DROP TABLE IF EXISTS `Interests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Interests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `tag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `tag` (`tag`),
  CONSTRAINT `Interests_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `Interests_ibfk_2` FOREIGN KEY (`tag`) REFERENCES `Tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Interests`
--

LOCK TABLES `Interests` WRITE;
/*!40000 ALTER TABLE `Interests` DISABLE KEYS */;
INSERT INTO `Interests` VALUES (1,1,16),(2,2,16),(3,3,16),(4,2,4),(5,2,17),(6,3,20),(7,1,17);
/*!40000 ALTER TABLE `Interests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chatId` int(11) NOT NULL,
  `fromUser` int(11) NOT NULL,
  `contentId` int(11) NOT NULL,
  `reactionId` int(11) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `chatId` (`chatId`),
  KEY `fromUser` (`fromUser`),
  KEY `contentId` (`contentId`),
  KEY `reactionId` (`reactionId`),
  CONSTRAINT `Message_ibfk_1` FOREIGN KEY (`chatId`) REFERENCES `Chat` (`id`),
  CONSTRAINT `Message_ibfk_2` FOREIGN KEY (`fromUser`) REFERENCES `User` (`id`),
  CONSTRAINT `Message_ibfk_3` FOREIGN KEY (`contentId`) REFERENCES `Content` (`id`),
  CONSTRAINT `Message_ibfk_4` FOREIGN KEY (`reactionId`) REFERENCES `Reaction` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Message`
--

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;
INSERT INTO `Message` VALUES (1,1,2,13,3,'2020-05-19 09:11:03'),(2,1,1,14,NULL,'2020-05-19 08:49:53'),(4,2,10,15,NULL,'2020-05-19 13:33:49'),(5,2,5,16,NULL,'2020-05-19 13:36:10');
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Post`
--

DROP TABLE IF EXISTS `Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `contentId` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `groupId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contentId` (`contentId`),
  KEY `userId` (`userId`),
  KEY `groupId` (`groupId`),
  CONSTRAINT `Post_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `Post_ibfk_2` FOREIGN KEY (`contentId`) REFERENCES `Content` (`id`),
  CONSTRAINT `Post_ibfk_3` FOREIGN KEY (`groupId`) REFERENCES `GroupP` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Post`
--

LOCK TABLES `Post` WRITE;
/*!40000 ALTER TABLE `Post` DISABLE KEYS */;
INSERT INTO `Post` VALUES (2,1,1,'2020-05-14 16:29:59',NULL),(3,3,2,'2020-05-14 17:01:08',NULL),(6,1,3,'2020-05-14 19:37:34',NULL),(7,1,4,'2020-05-14 19:43:25',NULL),(8,1,5,'2020-05-14 19:43:34',NULL),(9,3,6,'2020-05-14 19:53:19',NULL),(10,3,7,'2020-05-14 19:55:39',NULL),(11,3,8,'2020-05-14 19:56:54',NULL),(12,3,9,'2020-05-14 20:00:13',NULL),(14,10,11,'2020-05-16 18:36:52',NULL),(15,2,12,'2020-05-17 12:32:48',NULL);
/*!40000 ALTER TABLE `Post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reaction`
--

DROP TABLE IF EXISTS `Reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Reaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reactorId` int(11) NOT NULL,
  `emojiId` int(11) NOT NULL,
  `postId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reactorId` (`reactorId`),
  KEY `emojiId` (`emojiId`),
  KEY `postId` (`postId`),
  CONSTRAINT `Reaction_ibfk_1` FOREIGN KEY (`reactorId`) REFERENCES `User` (`id`),
  CONSTRAINT `Reaction_ibfk_2` FOREIGN KEY (`emojiId`) REFERENCES `EmojiReaction` (`id`),
  CONSTRAINT `Reaction_ibfk_3` FOREIGN KEY (`postId`) REFERENCES `Post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reaction`
--

LOCK TABLES `Reaction` WRITE;
/*!40000 ALTER TABLE `Reaction` DISABLE KEYS */;
INSERT INTO `Reaction` VALUES (1,1,1,2),(2,2,3,3),(3,1,2,NULL);
/*!40000 ALTER TABLE `Reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tag`
--

DROP TABLE IF EXISTS `Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `contentId` int(11) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `contentId` (`contentId`),
  KEY `groupId` (`groupId`),
  CONSTRAINT `Tag_ibfk_1` FOREIGN KEY (`contentId`) REFERENCES `Content` (`id`),
  CONSTRAINT `Tag_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `GroupP` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tag`
--

LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
INSERT INTO `Tag` VALUES (1,'Pastatas',NULL,1),(2,'Apleistas',NULL,1),(3,'Baisu',NULL,1),(4,'Pieva',NULL,1),(5,'Dalgis',NULL,2),(6,'Žolė',NULL,2),(7,'Žalia',NULL,2),(8,'Erkės',NULL,3),(9,'Gintaras',NULL,3),(10,'Jūra',NULL,3),(11,'Smėlis',NULL,3),(12,'Pinigai',NULL,3),(13,'Erkės',NULL,2),(14,'Dronas',NULL,4),(15,'Lenktynės',NULL,4),(16,'Alfa',NULL,5),(17,'Mergikės',NULL,5),(18,'Daug mergikių',NULL,5),(19,'BMW',NULL,5),(20,'Berniukai',NULL,NULL);
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(36) NOT NULL,
  `password` varchar(60) NOT NULL,
  `phoneNumber` varchar(12) DEFAULT NULL,
  `lastSeen` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dateOfBirth` date NOT NULL,
  `fullName` text,
  `country` varchar(24) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'laurynas.zlatkus@gmail.com','Laurynas','Zlatkus','123456','911','2020-05-22 17:21:13','2020-05-15','laurynas zlatkus','Lithuania','Panevezys'),(2,'jonas.zemaitis@gmail.com','Jonas','Žemaitis','654321','911','2020-05-22 17:21:04','2020-05-15','jonas žemaitis','Lithuania','Vilnius'),(3,'rytis.razmus@gmail.com','Rytis','Razmus','123456','112','2020-05-22 17:21:08','2020-05-15','rytis razmus','Lithuania','Vilnius'),(4,'gogelis.mogelis@yahoo.com','Googelis','Moogelis','11223369','69694200','2020-05-16 09:54:09','2020-05-15','googelis moogelis',NULL,NULL),(5,'petras.petraitis@gmail.com','petras','Petraits','123456','112','2020-05-16 09:54:34','2020-05-15','petras petraitis',NULL,NULL),(6,'boris.mend@yahoo.com','Borisas','Mendelejavas','2313639','420420420','2020-05-16 09:55:01','2020-05-15','borisas mendelejavas',NULL,NULL),(8,'grazuoliukas@one.lt','Grazvydas','Bagotyrius','123456','2345','2020-05-16 09:55:36','2020-05-19','grazvydas bagotyrius',NULL,NULL),(9,'batman@gotham.com','Not Bruce','Not Wayne','123456','55508','2020-05-16 09:56:04','1961-05-15','not bruce not wayne',NULL,NULL),(10,'oksana.pikul@olialia.lt','Oksana','Pikul','papai','867845312','2020-05-16 09:56:33','1980-05-15','oksana pikul',NULL,NULL),(11,'peter.parker@gmail.com','Peter','Parker','123456','86458539','2020-05-16 09:56:50','1964-01-05','peter parker',NULL,NULL),(12,'dauztas@one.lt','As is savivaldybes','ieskau narkotiku','123456','112','2020-05-16 09:57:12','1974-05-15','as is savivaldybes ieskau narkotiku',NULL,NULL),(31,'ciupakabra@gmail.com','Legendary','Beast','123456','112','2020-05-23 11:52:37','2012-05-22',NULL,NULL,NULL),(32,'yomamma@gmail.com','Fat','Mamma','123456','112','2020-05-23 11:54:50','2000-01-28',NULL,NULL,NULL);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserGroup`
--

DROP TABLE IF EXISTS `UserGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserGroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `groupId` (`groupId`),
  CONSTRAINT `UserGroup_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `UserGroup_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `GroupP` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserGroup`
--

LOCK TABLES `UserGroup` WRITE;
/*!40000 ALTER TABLE `UserGroup` DISABLE KEYS */;
INSERT INTO `UserGroup` VALUES (1,1,1),(2,2,1),(3,5,1),(4,8,2),(5,9,2),(6,6,3),(7,3,3),(8,10,3),(9,11,4),(12,11,4),(13,5,5),(14,4,5),(15,8,5),(17,9,5);
/*!40000 ALTER TABLE `UserGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserImage`
--

DROP TABLE IF EXISTS `UserImage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserImage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `image` int(11) NOT NULL,
  `isPrimaryImage` bit(1) NOT NULL DEFAULT b'0',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `image` (`image`),
  CONSTRAINT `UserImage_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `UserImage_ibfk_2` FOREIGN KEY (`image`) REFERENCES `Image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserImage`
--

LOCK TABLES `UserImage` WRITE;
/*!40000 ALTER TABLE `UserImage` DISABLE KEYS */;
INSERT INTO `UserImage` VALUES (1,1,9,_binary '','2020-05-16 17:26:12'),(2,2,10,_binary '','2020-05-16 17:26:34'),(3,3,11,_binary '','2020-05-16 17:26:48'),(4,4,12,_binary '','2020-05-16 17:27:02'),(5,5,13,_binary '','2020-05-16 17:27:13'),(6,8,14,_binary '','2020-05-16 17:27:49'),(7,10,16,_binary '','2020-05-16 18:38:58');
/*!40000 ALTER TABLE `UserImage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-26 15:50:30
