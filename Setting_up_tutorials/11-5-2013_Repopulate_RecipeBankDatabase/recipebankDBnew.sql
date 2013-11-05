CREATE DATABASE  IF NOT EXISTS `recipebank` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `recipebank`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: recipebank
-- ------------------------------------------------------
-- Server version	5.6.13

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `AccountId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `NickName` varchar(45) NOT NULL,
  `GmailAddress` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Gender` int(11) NOT NULL DEFAULT '0' COMMENT 'Default value is 0(Zero) means male;\nWhen value is 1(One) means female;',
  `CustomizedFace` longblob,
  `AccountState` int(11) DEFAULT '0' COMMENT '0 means this account is active.\n1 means this account is deleted.',
  PRIMARY KEY (`AccountId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='User Information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Dongchao','Feng','Steven','sorafeng@gmail.com','123',0,NULL,0),(2,'admin','admin','admin','admin@gmail.com','123',0,NULL,0),(3,'Huijun','Sun','Amy','janiswukong@gmail.com','123',1,NULL,0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `CategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryTitle` varchar(45) NOT NULL,
  PRIMARY KEY (`CategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Entree'),(2,'Side Dish'),(3,'Meat'),(4,'Veggie'),(5,'Noodles'),(6,'Fried'),(7,'Dressing'),(8,'Sauce'),(9,'Pasta'),(10,'Salad'),(11,'Asian'),(12,'Low Calorie');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `CommentId` int(11) NOT NULL AUTO_INCREMENT,
  `DetailInfo` varchar(1000) NOT NULL,
  `RecipeId` int(11) NOT NULL,
  `AccountId` int(11) NOT NULL,
  `CommentTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CommentId`),
  KEY `fk_Comment_RecipeId_idx` (`RecipeId`),
  KEY `fk_Comment_AccountId_idx` (`AccountId`),
  CONSTRAINT `fk_comment_AccountId` FOREIGN KEY (`AccountId`) REFERENCES `account` (`AccountId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_RecipeId` FOREIGN KEY (`RecipeId`) REFERENCES `recipe` (`RecipeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Seem nice! I will try the recipe',1,1,'2013-01-01 00:00:01'),(2,'Accept!',2,1,'2013-01-01 00:00:06'),(3,'Accept!',3,3,'2013-01-01 00:00:13'),(4,'Ok',1,1,'2013-10-31 21:04:51');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite` (
  `FavouriteId` int(11) NOT NULL AUTO_INCREMENT,
  `AccountId` int(11) NOT NULL,
  `RecipeId` int(11) NOT NULL,
  PRIMARY KEY (`FavouriteId`),
  KEY `fk_favourite_AccountId_idx` (`AccountId`),
  KEY `fk_favourite_RecipeId_idx` (`RecipeId`),
  CONSTRAINT `fk_favourite_AccountId` FOREIGN KEY (`AccountId`) REFERENCES `account` (`AccountId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_favourite_RecipeId` FOREIGN KEY (`RecipeId`) REFERENCES `recipe` (`RecipeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
INSERT INTO `favourite` VALUES (1,1,2),(2,2,3),(3,1,1),(4,1,5);
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followlist`
--

DROP TABLE IF EXISTS `followlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followlist` (
  `FollowId` int(11) NOT NULL AUTO_INCREMENT,
  `AccountId` int(11) NOT NULL,
  `FriendId` int(11) DEFAULT NULL,
  `FollowState` int(11) NOT NULL DEFAULT '0' COMMENT '0 means active\n1 means follow canceled',
  PRIMARY KEY (`FollowId`),
  KEY `AccountId_idx` (`AccountId`),
  CONSTRAINT `fk_share_accountId` FOREIGN KEY (`AccountId`) REFERENCES `account` (`AccountId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followlist`
--

LOCK TABLES `followlist` WRITE;
/*!40000 ALTER TABLE `followlist` DISABLE KEYS */;
INSERT INTO `followlist` VALUES (1,1,2,0),(2,1,3,0),(3,2,1,0),(4,3,1,0),(5,3,2,0);
/*!40000 ALTER TABLE `followlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient` (
  `IngredientId` int(11) NOT NULL AUTO_INCREMENT,
  `IngredientName` varchar(45) NOT NULL,
  PRIMARY KEY (`IngredientId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'green sweet pepper'),(2,'onion'),(3,'serrano pepper'),(4,'vegetable oil'),(5,'6-inch white corn tortillas'),(6,'Nonstick cooking spary'),(7,'shredded Monterey Jack cheese (2 ounces)'),(8,'tomato, halved crosswise'),(9,'snipped fresh cilantro'),(10,'butter, softened'),(11,'packed brown sugar'),(12,'granulated sugar'),(13,'baking powder'),(14,'baking soda'),(15,'eggs'),(16,'vanilla'),(17,'all-purpose flour'),(18,'rolled oats');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe` (
  `RecipeId` int(11) NOT NULL AUTO_INCREMENT,
  `RecipeTitle` varchar(45) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `Rate` int(11) DEFAULT '0',
  `RecipeState` int(11) DEFAULT '0' COMMENT '0 means active\n1 means was deleted',
  `Photo` longblob,
  PRIMARY KEY (`RecipeId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Chicken Salad','Fresh salad with grilled chicken',4,1,NULL),(2,'Macaroni & Cheese','Macaroni nooldes covered in cheddar cheese',2,1,NULL),(3,'Uncle Sams Ribs','Uncle Sams bbq smoked ribs covered in special bbq sauce',5,1,NULL),(4,'Apple Vinegrette','Salad Dressing',3,1,NULL),(5,'Pad Thai','Chicken, Shrimp or Beef Pad Thai',2,1,NULL),(6,'Lemon Butter Asparagus','Pan fried asparagus in butter with lemon and pepper',4,1,NULL),(7,'Meatballs','Simple meatball recipe',1,1,NULL),(8,'Aunt Marys Fish & Chips','Aunt Marys famous beer batter fish and sea salt fries',5,1,NULL),(9,'Scalloped Potatoes','Scalloped Potatoes',3,1,NULL),(10,'Garlic and Herb LoL','Leg of lamb roasted with organic herbs and garlic',3,1,NULL),(11,'fajita-style quesadillas','Hot and savory',4,0,NULL),(12,'oatmeal cookies','The recipes for oatmeal cookies.',3,0,NULL);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipecategory`
--

DROP TABLE IF EXISTS `recipecategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipecategory` (
  `RecipeCategoryId` int(11) NOT NULL AUTO_INCREMENT,
  `RecipeId` int(11) NOT NULL,
  `CategoryId` int(11) NOT NULL,
  PRIMARY KEY (`RecipeCategoryId`),
  KEY `fk_recipecategory_RecipeId_idx` (`RecipeId`),
  KEY `fk_recipecategory_CategoryId_idx` (`CategoryId`),
  CONSTRAINT `fk_recipecategory_CategoryId` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_recipecategory_RecipeId` FOREIGN KEY (`RecipeId`) REFERENCES `recipe` (`RecipeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipecategory`
--

LOCK TABLES `recipecategory` WRITE;
/*!40000 ALTER TABLE `recipecategory` DISABLE KEYS */;
INSERT INTO `recipecategory` VALUES (0,1,10),(2,2,5),(3,1,3),(4,3,3),(5,4,10),(6,5,3),(7,6,3),(8,7,3),(9,8,3),(10,8,6),(11,9,4),(12,10,3),(13,10,11),(14,11,12),(15,12,12);
/*!40000 ALTER TABLE `recipecategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipeingredientlist`
--

DROP TABLE IF EXISTS `recipeingredientlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipeingredientlist` (
  `RecipeIngredientListId` int(11) NOT NULL AUTO_INCREMENT,
  `RecipeId` int(11) NOT NULL,
  `IngredientId` int(11) NOT NULL,
  `ingredientMeasure` varchar(45) DEFAULT NULL,
  `IngredientQuanlity` decimal(5,2) DEFAULT '0.00',
  PRIMARY KEY (`RecipeIngredientListId`),
  KEY `fk_RI_RecipeId_idx` (`RecipeId`),
  KEY `fk_RI_IngredientId_idx` (`IngredientId`),
  CONSTRAINT `fk_ri_IngredientId` FOREIGN KEY (`IngredientId`) REFERENCES `ingredient` (`IngredientId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ri_RecipeId` FOREIGN KEY (`RecipeId`) REFERENCES `recipe` (`RecipeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipeingredientlist`
--

LOCK TABLES `recipeingredientlist` WRITE;
/*!40000 ALTER TABLE `recipeingredientlist` DISABLE KEYS */;
INSERT INTO `recipeingredientlist` VALUES (1,11,1,NULL,0.50),(2,11,2,NULL,0.50),(3,11,3,NULL,0.50),(4,11,4,'teaspoon',2.00),(5,11,5,NULL,4.00),(6,11,6,NULL,1.00),(7,11,7,'cup',0.50),(8,11,8,'thin slices',2.00),(9,11,9,'teaspoon',1.00),(10,12,10,'cup',0.75),(11,12,11,'cup',1.00),(12,12,12,'cup',0.50),(13,12,13,'teaspoon',1.00),(14,12,14,'teaspoon',0.25),(15,12,15,NULL,2.00),(16,12,16,'teaspoon',1.00),(17,12,17,'cup',1.75),(18,12,18,'cup',2.00);
/*!40000 ALTER TABLE `recipeingredientlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipestep`
--

DROP TABLE IF EXISTS `recipestep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipestep` (
  `RecipeStepId` int(11) NOT NULL AUTO_INCREMENT,
  `StepDesc` varchar(1000) NOT NULL,
  `StepPhoto` longblob,
  `RecipeId` int(11) NOT NULL,
  `stepTime` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`RecipeStepId`),
  KEY `fk_rs_RecipeId_idx` (`RecipeId`),
  CONSTRAINT `fk_rs_RecipeId` FOREIGN KEY (`RecipeId`) REFERENCES `recipe` (`RecipeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipestep`
--

LOCK TABLES `recipestep` WRITE;
/*!40000 ALTER TABLE `recipestep` DISABLE KEYS */;
INSERT INTO `recipestep` VALUES (1,'In a large skillet cook sweet pepper, onion, and serrano pepper in hot oil over medium-high heat for 3 to 5 minutes or until vegetables are just tender. Remove from heat.',NULL,11,5.00),(2,'Lightly coat one side of each tortilla with cooking spray. On the uncoated side of two of the tortillas, divide half of the cheese. Top with onion mixture, tomato slices, the 1 tablespoon cilantro, and the remaining cheese. Top with remaining tortillas, coated sides up.',NULL,11,5.00),(3,'Heat a very large skillet or griddle over medium heat. Cook quesadillas for 4 to 5 minutes per side or until cheese melts and tortillas are lightly browned. Cut each quesadilla into 4 wedges. Serve warm and, if desired, with sour cream, additional cilantro and lime wedges.',NULL,11,5.00),(4,'Preheat oven to 375 degrees F. In a large mixing bowl, beat butter with an electric mixer on medium to high speed for 30 seconds. Add brown sugar, granulated sugar, baking powder, baking soda, and, if desired, cinnamon and cloves. Beat until combined, scraping side of bowl occasionally. Beat in eggs and vanilla until combined. Beat in as much of the flour as you can with the mixer. Using a wooden spoon, stir in any remaining flour. Stir in rolled oats.',NULL,12,6.00),(5,'Drop dough by rounded teaspoons 2 inches apart onto ungreased cookie sheets. Bake for 8 to 10 minutes or until edges are golden. Let stand on cookie sheets for 1 minute. Transfer to wire racks and let cool. Makes about 48 cookies.',NULL,12,6.00);
/*!40000 ALTER TABLE `recipestep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sharerecipe`
--

DROP TABLE IF EXISTS `sharerecipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sharerecipe` (
  `ShareRecipeId` int(11) NOT NULL AUTO_INCREMENT,
  `AccountId` int(11) NOT NULL,
  `RecipeId` int(11) NOT NULL,
  `ShareTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ShareRecipeId`),
  KEY `fk_sharerecipe_AccountId_idx` (`AccountId`),
  CONSTRAINT `fk_sharerecipe_AccountId` FOREIGN KEY (`AccountId`) REFERENCES `account` (`AccountId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sharerecipe`
--

LOCK TABLES `sharerecipe` WRITE;
/*!40000 ALTER TABLE `sharerecipe` DISABLE KEYS */;
INSERT INTO `sharerecipe` VALUES (1,1,11,'2013-10-31 21:03:53'),(2,2,3,'2013-10-31 21:05:04');
/*!40000 ALTER TABLE `sharerecipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingingredients`
--

DROP TABLE IF EXISTS `shoppingingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingingredients` (
  `ShoppingIngredientsId` int(11) NOT NULL AUTO_INCREMENT,
  `IngredientId` int(11) NOT NULL,
  `IngredientMeasure` varchar(100) DEFAULT NULL,
  `IngredientQuantity` decimal(5,2) DEFAULT NULL,
  `RecipeId` int(11) NOT NULL,
  `ShoppingIngredientState` int(11) NOT NULL DEFAULT '0' COMMENT '0 means need to buy\n1 means have bought',
  `ShoppingListId` int(11) NOT NULL,
  PRIMARY KEY (`ShoppingIngredientsId`),
  KEY `fk_SI_IngredientId_idx` (`IngredientId`),
  KEY `fk_SI_RecipeId_idx` (`RecipeId`),
  KEY `fk_SI_ShoppingListId_idx` (`ShoppingListId`),
  CONSTRAINT `fk_SI_IngredientId` FOREIGN KEY (`IngredientId`) REFERENCES `ingredient` (`IngredientId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_SI_RecipeId` FOREIGN KEY (`RecipeId`) REFERENCES `recipe` (`RecipeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_SI_ShoppingListId` FOREIGN KEY (`ShoppingListId`) REFERENCES `shoppinglist` (`shoppingListId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingingredients`
--

LOCK TABLES `shoppingingredients` WRITE;
/*!40000 ALTER TABLE `shoppingingredients` DISABLE KEYS */;
INSERT INTO `shoppingingredients` VALUES (1,1,'bag',2.00,11,1,1);
/*!40000 ALTER TABLE `shoppingingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppinglist`
--

DROP TABLE IF EXISTS `shoppinglist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppinglist` (
  `ShoppingListId` int(11) NOT NULL AUTO_INCREMENT,
  `ShoppingDate` date DEFAULT NULL,
  PRIMARY KEY (`ShoppingListId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppinglist`
--

LOCK TABLES `shoppinglist` WRITE;
/*!40000 ALTER TABLE `shoppinglist` DISABLE KEYS */;
INSERT INTO `shoppinglist` VALUES (1,'2013-01-01');
/*!40000 ALTER TABLE `shoppinglist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'recipebank'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-05 14:13:48
