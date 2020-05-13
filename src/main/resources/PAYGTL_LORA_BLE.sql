/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.5.62 : Database - paygtl_lora_ble
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`paygtl_lora_ble` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `paygtl_lora_ble`;

/*Table structure for table `alertsettings` */

DROP TABLE IF EXISTS `alertsettings`;

CREATE TABLE `alertsettings` (
  `AlertID` int(11) NOT NULL AUTO_INCREMENT,
  `NoAMRInterval` bigint(255) NOT NULL,
  `LowBatteryVoltage` decimal(10,2) NOT NULL,
  `TimeOut` int(11) NOT NULL,
  `PerUnitValue` float NOT NULL,
  `ReconnectionCharges` int(11) DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`AlertID`),
  KEY `AlertID` (`AlertID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 MAX_ROWS=1;

/*Data for the table `alertsettings` */

insert  into `alertsettings`(`AlertID`,`NoAMRInterval`,`LowBatteryVoltage`,`TimeOut`,`PerUnitValue`,`ReconnectionCharges`,`RegisteredDate`,`ModifiedDate`) values 
(1,1442,3.35,330,2.4,50,'2020-04-07 02:20:35','2020-04-07 02:30:33');

/*Table structure for table `balancelog` */

DROP TABLE IF EXISTS `balancelog`;

CREATE TABLE `balancelog` (
  `ReadingID` bigint(255) NOT NULL AUTO_INCREMENT,
  `MeterID` varchar(70) NOT NULL,
  `Reading` decimal(10,2) NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `CommunityID` int(11) DEFAULT NULL,
  `BlockID` int(11) DEFAULT NULL,
  `CustomerID` bigint(20) DEFAULT NULL,
  `BatteryVoltage` decimal(10,2) NOT NULL,
  `TariffAmount` decimal(18,2) NOT NULL,
  `EmergencyCredit` varchar(30) NOT NULL,
  `MeterType` int(255) NOT NULL,
  `SolonideStatus` tinyint(2) DEFAULT NULL,
  `CreditStatus` tinyint(2) DEFAULT NULL,
  `TamperDetect` tinyint(2) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `Vacation` tinyint(4) DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `Minutes` int(11) DEFAULT NULL,
  `IoTTimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;

/*Data for the table `balancelog` */

insert  into `balancelog`(`ReadingID`,`MeterID`,`Reading`,`Balance`,`CommunityID`,`BlockID`,`CustomerID`,`BatteryVoltage`,`TariffAmount`,`EmergencyCredit`,`MeterType`,`SolonideStatus`,`CreditStatus`,`TamperDetect`,`LowBattery`,`Vacation`,`MeterSerialNumber`,`CRNNumber`,`Minutes`,`IoTTimeStamp`,`LogDate`) values 
(1,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,0,0,'1234','HAN0001',0,'2020-04-10 20:50:11','2020-05-13 19:36:06'),
(2,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,0,0,'1234','HAN0001',0,'2020-04-13 13:50:11','2020-05-13 19:36:06'),
(3,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,0,0,'1234','HAN0001',0,'2020-04-13 13:52:11','2020-05-13 19:36:06'),
(4,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,0,0,'1234','HAN0001',0,'2020-04-13 14:00:11','2020-05-13 19:36:06'),
(5,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,0,0,'1234','HAN0001',0,'2020-04-14 20:50:56','2020-05-13 19:36:06'),
(6,'70b3d5f83000157a',800.00,170.00,2,3,2,2.17,50.00,'0.0',3,1,0,0,0,0,'3456','HAN0002',0,'2020-04-15 15:20:35','2020-05-13 19:36:06'),
(7,'70b3d5f83000157a',816.00,20.00,2,3,2,2.18,50.00,'0.0',3,1,0,0,0,0,'3456','HAN0002',0,'2020-04-15 15:24:21','2020-05-13 19:36:06'),
(8,'70b3d5f83000157a',831.00,220.00,2,3,2,2.18,50.00,'200.0',3,0,0,0,0,0,'3456','HAN0002',0,'2020-04-15 15:50:27','2020-05-13 19:36:06'),
(9,'70b3d5f83000157a',831.00,220.00,2,3,2,2.18,50.00,'200.0',3,0,0,0,0,0,'3456','HAN0002',0,'2020-04-15 16:32:15','2020-05-13 19:36:06'),
(10,'70b3d5f83000157a',831.00,220.00,2,3,2,2.17,50.00,'200.0',3,1,0,0,0,0,'3456','HAN0002',0,'2020-04-15 17:51:46','2020-05-13 19:36:06'),
(11,'70b3d5f83000157a',0.00,220.00,2,3,2,2.18,50.00,'200.0',3,1,0,0,0,0,'3456','HAN0002',0,'2020-04-15 17:57:10','2020-05-13 19:36:06'),
(12,'70b3d5f83000157a',831.00,220.00,2,3,2,2.17,50.00,'200.0',3,1,0,0,0,0,'3456','HAN0002',0,'2020-04-15 19:02:57','2020-05-13 19:36:06'),
(13,'70b3d5f83000157a',834.00,70.00,2,3,2,2.17,50.00,'200.0',3,3,0,0,0,0,'3456','HAN0002',0,'2020-04-15 19:26:52','2020-05-13 19:36:06'),
(14,'70b3d5f83000157a',834.00,70.00,2,3,2,2.17,50.00,'200.0',3,0,0,0,0,0,'3456','HAN0002',0,'2020-04-15 19:31:08','2020-05-13 19:36:06'),
(15,'70b3d5f83000157a',834.00,70.00,2,3,2,2.18,50.00,'200.0',3,0,0,0,0,0,'3456','HAN0002',0,'2020-04-15 19:39:50','2020-05-13 19:36:06'),
(16,'70b3d5f83000157a',852.00,70.00,2,3,2,2.18,50.00,'200.0',3,1,0,0,0,0,'3456','HAN0002',0,'2020-04-15 20:38:52','2020-05-13 19:36:06'),
(17,'70b3d5f83000157a',164.00,900.00,2,3,2,2.18,1000.00,'0.0',3,1,0,0,0,0,'3456','HAN0002',0,'2020-04-16 10:36:55','2020-05-13 19:36:06'),
(18,'70b3d5f830000a68',4.00,900.00,1,1,6,2.18,1000.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-16 15:44:10','2020-05-13 19:36:06'),
(19,'70b3d5f830000a68',4.00,1250.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-16 16:37:37','2020-05-13 19:36:06'),
(20,'70b3d5f830000a68',4.00,1250.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-16 16:42:38','2020-05-13 19:36:06'),
(21,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,0,0,'1234','HAN0001',0,'2020-04-17 02:51:29','2020-05-13 19:36:06'),
(22,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,0,0,'1234','HAN0001',0,'2020-04-18 20:51:49','2020-05-13 19:36:06'),
(23,'70b3d5f830004f56',0.00,30.00,1,1,1,3.59,50.00,'20.0',3,0,0,0,0,0,'1234','HAN0001',0,'2020-04-20 13:34:40','2020-05-13 19:36:06'),
(24,'70b3d5f830000a68',90.00,1250.00,1,1,6,2.25,50.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-19 21:50:56','2020-05-13 19:36:06'),
(25,'70b3d5f830000a68',93.00,1250.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-04-19 23:39:15','2020-05-13 19:36:06'),
(26,'70b3d5f830000a68',93.00,1250.00,1,1,6,2.21,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-04-19 23:40:35','2020-05-13 19:36:06'),
(27,'70b3d5f830000a68',93.00,1400.00,1,1,6,2.19,50.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-04-19 23:48:48','2020-05-13 19:36:06'),
(28,'70b3d5f830000a68',93.00,1600.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-04-19 23:51:47','2020-05-13 19:36:06'),
(29,'70b3d5f830000a68',93.00,1600.00,1,1,6,2.25,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-20 14:56:07','2020-05-13 19:36:06'),
(30,'70b3d5f830000a68',100.00,1600.00,1,1,6,2.19,50.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-20 15:25:01','2020-05-13 19:36:06'),
(31,'70b3d5f830000a68',100.00,1600.00,1,1,6,2.19,50.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-04-20 15:34:30','2020-05-13 19:36:06'),
(32,'70b3d5f830000a68',93.00,1610.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-23 21:21:07','2020-05-13 19:36:06'),
(33,'70b3d5f830000a68',93.00,1610.00,1,1,6,2.36,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-28 19:18:18','2020-05-13 19:36:06'),
(34,'70b3d5f830000a68',93.00,1610.00,1,1,6,2.26,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-28 19:27:32','2020-05-13 19:36:06'),
(35,'70b3d5f830000a68',93.00,1710.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-28 19:36:03','2020-05-13 19:36:06'),
(36,'70b3d5f830000a68',93.00,1710.00,1,1,6,2.22,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-28 19:41:58','2020-05-13 19:36:06'),
(37,'70b3d5f830000a68',93.00,1710.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-04-28 19:55:04','2020-05-13 19:36:06'),
(38,'70b3d5f830000a68',93.00,1710.00,1,1,6,2.28,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-30 11:37:29','2020-05-13 19:36:06'),
(39,'70b3d5f830000a68',93.00,1710.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-30 12:21:19','2020-05-13 19:36:06'),
(40,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-30 12:28:35','2020-05-13 19:36:06'),
(41,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-30 12:46:49','2020-05-13 19:36:06'),
(42,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-30 12:48:15','2020-05-13 19:36:06'),
(43,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-04-30 14:22:15','2020-05-13 19:36:06'),
(44,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.24,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-02 22:12:36','2020-05-13 19:36:06'),
(45,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.22,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-02 22:19:42','2020-05-13 19:36:06'),
(46,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.22,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-02 22:21:46','2020-05-13 19:36:06'),
(47,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.24,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-05-03 09:53:51','2020-05-13 19:36:06'),
(48,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:03:01','2020-05-13 19:36:06'),
(49,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:06:23','2020-05-13 19:36:06'),
(50,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:19:50','2020-05-13 19:36:06'),
(51,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:23:10','2020-05-13 19:36:06'),
(52,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:25:28','2020-05-13 19:36:06'),
(53,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:28:50','2020-05-13 19:36:06'),
(54,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:34:08','2020-05-13 19:36:06'),
(55,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:35:28','2020-05-13 19:36:06'),
(56,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:42:48','2020-05-13 19:36:06'),
(57,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:55:11','2020-05-13 19:36:06'),
(58,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-03 11:57:44','2020-05-13 19:36:06'),
(59,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,0,0,'4567','HAN0006',0,'2020-05-03 12:09:30','2020-05-13 19:36:06'),
(60,'70b3d5f830000a68',93.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-05-03 12:22:10','2020-05-13 19:36:06'),
(61,'70b3d5f830000a68',0.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-05-03 12:27:19','2020-05-13 19:36:06');

/*Table structure for table `block` */

DROP TABLE IF EXISTS `block`;

CREATE TABLE `block` (
  `BlockID` bigint(255) NOT NULL AUTO_INCREMENT,
  `BlockName` varchar(80) NOT NULL,
  `Location` varchar(300) DEFAULT NULL,
  `MobileNumber` varchar(10) DEFAULT NULL,
  `Email` varchar(300) DEFAULT NULL,
  `CommunityID` int(255) NOT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`BlockID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `block` */

insert  into `block`(`BlockID`,`BlockName`,`Location`,`MobileNumber`,`Email`,`CommunityID`,`CreatedByID`,`CreatedByRoleID`,`CreatedDate`,`ModifiedDate`) values 
(1,'DemoBlock','Bangalore','9876543210','Demoblock@gmail.com',1,1,1,'2020-04-05 01:53:16','2020-04-05 01:53:16'),
(3,'Demoblock1','Hyderabad','9876543211','Demoblock1@gmail.com',2,1,1,'2020-04-05 21:27:08','2020-04-05 21:27:08'),
(4,'Demoblock2','Mumbai','9876543212','Demoblock2@gmail.com',1,1,1,'2020-04-06 15:39:11','2020-04-06 15:39:14'),
(5,'Demoblock3','Warangal','9876543213','Demoblock3@gmail.com',2,1,1,'2020-04-06 15:39:46','2020-04-06 15:39:48'),
(8,'Demoblock5','Secunderabad','9000941923','bhrt@gmail.com',2,1,1,'2020-04-11 23:54:59','2020-04-11 23:54:59'),
(9,'Demoblock6','Maredpally','2345678901','Demoblock6@gmail.com',3,1,1,'2020-04-12 15:32:00','2020-04-12 15:32:00'),
(10,'testing','hyderabad','6789012345','testing@gmail.com',4,1,1,'2020-04-28 22:47:57','2020-04-28 22:47:57');

/*Table structure for table `command` */

DROP TABLE IF EXISTS `command`;

CREATE TABLE `command` (
  `TransactionID` bigint(255) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` bigint(20) NOT NULL,
  `CustomerID` int(255) NOT NULL,
  `MeterID` varchar(80) NOT NULL,
  `CommandType` int(255) NOT NULL,
  `Status` int(245) NOT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `CreatedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `command` */

insert  into `command`(`TransactionID`,`TataReferenceNumber`,`CustomerID`,`MeterID`,`CommandType`,`Status`,`CRNNumber`,`CreatedDate`,`ModifiedDate`) values 
(1,1,2,'70b3d5f83000157a',6,2,'HAN0002','2020-04-15 16:48:38','2020-04-15 16:48:38'),
(2,2,2,'70b3d5f83000157a',6,2,'HAN0002','2020-04-15 17:50:05','2020-04-15 17:50:05'),
(3,3,2,'70b3d5f83000157a',6,2,'HAN0002','2020-04-15 19:24:26','2020-04-15 19:24:26'),
(4,4,6,'70b3d5f830000a68',3,2,'HAN0006','2020-04-20 00:25:17','2020-04-20 00:25:17'),
(5,5,6,'70b3d5f830000a68',5,5,'HAN0006','2020-04-20 00:31:48','2020-04-20 15:00:00'),
(6,6,6,'70b3d5f830000a68',6,2,'HAN0006','2020-04-20 15:23:07','2020-04-20 15:25:00'),
(7,7,6,'70b3d5f830000a68',40,2,'HAN0006','2020-04-20 15:28:53','2020-04-20 15:30:01'),
(8,1587655984186,6,'70b3d5f83000157a',40,3,'HAN0002','2020-04-23 21:03:05','2020-04-23 21:05:02'),
(9,1587656297103,6,'70b3d5f83000157a',40,5,'HAN0002','2020-04-23 21:08:18','2020-05-03 12:30:01'),
(10,1588231239857,0,'70b3d5f830000a68',0,5,'HAN0006','2020-04-30 12:50:41','2020-04-30 12:55:00'),
(11,1588488112335,0,'70b3d5f830000a68',0,5,'HAN0006','2020-05-03 12:11:52','2020-05-03 12:15:00'),
(12,1588488443361,0,'70b3d5f830000a68',0,2,'HAN0006','2020-05-03 12:17:23','2020-05-03 12:20:00'),
(13,1588488808889,0,'70b3d5f830000a68',0,5,'HAN0006','2020-05-03 12:23:28','2020-05-03 12:30:00');

/*Table structure for table `community` */

DROP TABLE IF EXISTS `community`;

CREATE TABLE `community` (
  `CommunityID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CommunityName` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CommunityID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `community` */

insert  into `community`(`CommunityID`,`CommunityName`,`Email`,`MobileNumber`,`Address`,`CreatedDate`,`ModifiedDate`) values 
(1,'TestCommunity','testcommunity@gmail.com','9876543210','Bangalore','2020-04-04 23:13:10','2020-04-04 23:13:10'),
(2,'TestCommunity1','testcommunity1@gmail.com','9876543211','Hyderabaaaaad','2020-04-05 19:30:44','2020-04-05 19:49:04'),
(3,'TestCommunity2','testcommunity2@gmail.com','1234567890','Secunderabad','2020-04-12 15:30:26',NULL),
(4,'Testing','testing@gmail.com','6789012345','hyderabad','2020-04-28 22:44:18','2020-05-02 00:34:27');

/*Table structure for table `customerdeletemeter` */

DROP TABLE IF EXISTS `customerdeletemeter`;

CREATE TABLE `customerdeletemeter` (
  `CustomerDeleteID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(255) NOT NULL,
  `CommunityID` int(11) DEFAULT NULL,
  `BlockID` int(255) NOT NULL,
  `HouseNumber` varchar(30) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `MeterID` varchar(70) DEFAULT NULL,
  `MeterSerialNumber` varchar(30) DEFAULT NULL,
  `TariffID` int(11) NOT NULL,
  `CRNNumber` varchar(50) DEFAULT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `RegistrationDate` datetime NOT NULL,
  `DeletedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CustomerDeleteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customerdeletemeter` */

/*Table structure for table `customermeterdetails` */

DROP TABLE IF EXISTS `customermeterdetails`;

CREATE TABLE `customermeterdetails` (
  `CustomerID` int(255) NOT NULL AUTO_INCREMENT,
  `CommunityID` int(11) DEFAULT NULL,
  `BlockID` int(255) NOT NULL,
  `HouseNumber` varchar(30) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `MeterID` varchar(70) DEFAULT NULL,
  `MeterSerialNumber` varchar(30) DEFAULT NULL,
  `TariffID` int(11) NOT NULL,
  `ActiveStatus` tinyint(4) DEFAULT NULL,
  `CRNNumber` varchar(50) DEFAULT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  UNIQUE KEY `CRNNumber` (`CRNNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `customermeterdetails` */

insert  into `customermeterdetails`(`CustomerID`,`CommunityID`,`BlockID`,`HouseNumber`,`FirstName`,`LastName`,`Email`,`MobileNumber`,`MeterID`,`MeterSerialNumber`,`TariffID`,`ActiveStatus`,`CRNNumber`,`CreatedByID`,`CreatedByRoleID`,`RegistrationDate`,`ModifiedDate`) values 
(1,1,1,'101','Vimal','Kumar','kvk9889@gmail.com','8498891111','70b3d5f830004f56','1234',1,1,'HAN0001',2,2,'2020-04-05 01:58:12','2020-05-01 00:09:26'),
(2,2,3,'301','Vml','Kvk','vml@gmail.com','9999999999','70b3d5f83000157a','3456',2,1,'HAN0002',2,2,'2020-04-06 15:33:13','2020-04-06 15:33:17'),
(4,2,3,'302','Bharatttttt','sriram','bharat@gmail.com','7777777777','3002','3457',1,1,'HAN0004',1,1,'2020-04-06 15:36:17','2020-04-09 19:58:42'),
(5,2,5,'501','Demo','Customer','democustomer@gmail.com','6666666666','5001','5678',2,1,'HAN0005',6,2,'2020-04-06 22:31:15','2020-04-06 22:39:04'),
(6,1,1,'102','Amanora','testing','Amanora@gmail.com','8498890000','70b3d5f830000a68','4567',2,1,'HAN0006',1,1,'2020-04-16 15:43:25','2020-05-01 00:09:04'),
(9,2,5,'502','Demo502','Customer502','democustomer502@gmail.com','9876598765','5002','6789',2,1,'HAN0009',1,1,'2020-04-17 15:29:25','2020-04-17 15:29:25'),
(10,4,10,'Sri # 45','Sri babu','kasamsetty','sri@gmail.com','9703668598','k486','987645k',3,1,'HAN0010',1,1,'2020-04-30 13:19:57','2020-04-30 13:19:57'),
(11,4,10,'V-24','VIjay','Bhaskar','bhaskarnimmagadda@gmail.com','9494281300','1300','949428',3,1,'HAN0011',1,1,'2020-04-30 13:22:36','2020-04-30 13:22:36');

/*Table structure for table `displaybalancelog` */

DROP TABLE IF EXISTS `displaybalancelog`;

CREATE TABLE `displaybalancelog` (
  `ReadingID` bigint(255) NOT NULL AUTO_INCREMENT,
  `MainBalanceLogID` bigint(255) NOT NULL,
  `MeterID` varchar(70) NOT NULL,
  `Reading` decimal(10,2) NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `CommunityID` int(11) DEFAULT NULL,
  `BlockID` int(11) DEFAULT NULL,
  `CustomerID` bigint(20) DEFAULT NULL,
  `BatteryVoltage` decimal(10,2) NOT NULL,
  `TariffAmount` decimal(18,2) NOT NULL,
  `EmergencyCredit` varchar(30) NOT NULL,
  `MeterType` int(255) NOT NULL,
  `SolonideStatus` tinyint(2) DEFAULT NULL,
  `CreditStatus` tinyint(2) DEFAULT NULL,
  `TamperDetect` tinyint(2) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `Vacation` tinyint(4) DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `Minutes` int(11) DEFAULT NULL,
  `IoTTimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `displaybalancelog` */

insert  into `displaybalancelog`(`ReadingID`,`MainBalanceLogID`,`MeterID`,`Reading`,`Balance`,`CommunityID`,`BlockID`,`CustomerID`,`BatteryVoltage`,`TariffAmount`,`EmergencyCredit`,`MeterType`,`SolonideStatus`,`CreditStatus`,`TamperDetect`,`LowBattery`,`Vacation`,`MeterSerialNumber`,`CRNNumber`,`Minutes`,`IoTTimeStamp`,`LogDate`) values 
(1,23,'70b3d5f830004f56',0.00,30.00,1,1,1,3.59,50.00,'20.0',3,0,0,0,0,0,'1234','HAN0001',0,'2020-04-20 13:34:40','2020-05-13 19:36:28'),
(2,17,'70b3d5f83000157a',164.00,900.00,2,3,2,2.18,1000.00,'0.0',3,1,0,0,0,0,'3456','HAN0002',0,'2020-04-16 10:36:55','2020-05-13 19:36:28'),
(3,61,'70b3d5f830000a68',0.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,0,0,'4567','HAN0006',0,'2020-05-12 00:17:19','2020-05-13 19:36:28');

/*Table structure for table `mailsettings` */

DROP TABLE IF EXISTS `mailsettings`;

CREATE TABLE `mailsettings` (
  `Host` varchar(50) NOT NULL,
  `User` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `port` varchar(50) NOT NULL,
  `FromMail` varchar(50) NOT NULL,
  `ToMail` varchar(50) NOT NULL,
  `Subject` varchar(60) NOT NULL,
  `Text` varchar(60) NOT NULL,
  `CommunityID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mailsettings` */

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `Sno` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `bank_trans_id` varchar(50) DEFAULT NULL,
  `card_brand` varchar(50) DEFAULT NULL,
  `card_issuer` varchar(50) DEFAULT NULL,
  `card_issuer_country` varchar(50) DEFAULT NULL,
  `card_issuer_country_code` varchar(50) DEFAULT NULL,
  `card_no` varchar(50) DEFAULT NULL,
  `card_type` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `store_amount` double DEFAULT NULL,
  `store_id` varchar(50) DEFAULT NULL,
  `trans_date` datetime DEFAULT NULL,
  `trans_id` varchar(50) DEFAULT NULL,
  `val_id` varchar(50) DEFAULT NULL,
  `verify_sign` varchar(50) DEFAULT NULL,
  `verify_key` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

/*Table structure for table `supplierinfo` */

DROP TABLE IF EXISTS `supplierinfo`;

CREATE TABLE `supplierinfo` (
  `supplierName` varchar(40) NOT NULL,
  `supplierAddress` varchar(50) NOT NULL,
  `supplierMobile` varchar(15) NOT NULL,
  `supplierEmail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `supplierinfo` */

/*Table structure for table `tariff` */

DROP TABLE IF EXISTS `tariff`;

CREATE TABLE `tariff` (
  `TariffID` int(11) NOT NULL AUTO_INCREMENT,
  `Tariff` float NOT NULL,
  `TariffName` varchar(100) NOT NULL,
  `EmergencyCredit` float DEFAULT NULL,
  `AlarmCredit` float DEFAULT NULL,
  `FixedCharges` float DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TariffID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `tariff` */

insert  into `tariff`(`TariffID`,`Tariff`,`TariffName`,`EmergencyCredit`,`AlarmCredit`,`FixedCharges`,`RegisteredDate`,`ModifiedDate`) values 
(1,30,'Tariff1',10,15,25,'2020-04-04 23:06:14','2020-04-27 22:27:00'),
(2,50,'Tariff2',20,10,10,'2020-04-07 00:13:09','2020-04-28 22:26:52'),
(3,42,'newTariff',17,10,20,'2020-04-28 22:39:05','2020-04-28 22:39:28');

/*Table structure for table `testtable` */

DROP TABLE IF EXISTS `testtable`;

CREATE TABLE `testtable` (
  `HolidayID` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt` datetime NOT NULL,
  `numb` int(11) DEFAULT NULL,
  PRIMARY KEY (`HolidayID`),
  UNIQUE KEY `numb` (`numb`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

/*Data for the table `testtable` */

insert  into `testtable`(`HolidayID`,`date`,`dt`,`numb`) values 
(64,'2020-04-09 00:09:22','2020-04-09 00:09:22',-101),
(66,'2020-04-09 00:19:19','2020-04-09 00:19:19',301),
(67,'2020-04-11 16:22:35','2020-04-14 22:30:58',NULL),
(68,'2020-04-20 20:58:44','2020-04-20 20:59:00',302);

/*Table structure for table `topup` */

DROP TABLE IF EXISTS `topup`;

CREATE TABLE `topup` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` bigint(20) NOT NULL,
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` bigint(20) NOT NULL,
  `MeterID` varchar(50) NOT NULL,
  `TariffID` int(11) NOT NULL,
  `Amount` decimal(10,0) NOT NULL,
  `Status` tinyint(4) NOT NULL,
  `FixedCharges` int(11) DEFAULT NULL,
  `ReconnectionCharges` int(11) DEFAULT NULL,
  `ModeOfPayment` varchar(50) NOT NULL,
  `PaymentStatus` tinyint(4) NOT NULL,
  `Source` varchar(10) NOT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` int(11) NOT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `CardNumber` varchar(20) DEFAULT NULL,
  `CardType` varchar(20) DEFAULT NULL,
  `TransactionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `AcknowledgeDate` datetime NOT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `topup` */

insert  into `topup`(`TransactionID`,`TataReferenceNumber`,`CommunityID`,`BlockID`,`CustomerID`,`MeterID`,`TariffID`,`Amount`,`Status`,`FixedCharges`,`ReconnectionCharges`,`ModeOfPayment`,`PaymentStatus`,`Source`,`CreatedByID`,`CreatedByRoleID`,`CRNNumber`,`CardNumber`,`CardType`,`TransactionDate`,`AcknowledgeDate`) values 
(1,1,2,3,2,'70b3d5f83000157a',2,400,2,0,0,'cash',0,'web',1,1,'HAN0002',NULL,NULL,'2020-04-15 19:42:21','2020-04-15 19:42:21'),
(2,2,2,3,2,'70b3d5f83000157a',2,400,2,0,NULL,'cash',0,'web',1,1,'HAN0002',NULL,NULL,'2020-04-15 19:51:54','2020-04-15 19:51:54'),
(3,3,2,3,2,'70b3d5f83000157a',2,400,2,0,NULL,'cash',0,'web',1,1,'HAN0002',NULL,NULL,'2020-04-15 20:30:15','2020-04-15 20:30:15'),
(4,4,2,3,2,'70b3d5f83000157a',2,400,2,0,NULL,'cash',0,'web',1,1,'HAN0002',NULL,NULL,'2020-04-15 22:09:03','2020-04-15 22:09:03'),
(5,5,1,1,6,'70b3d5f830000a68',2,400,2,0,0,'cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-16 16:05:14','2020-04-16 16:05:14'),
(6,6,1,1,6,'70b3d5f830000a68',2,400,2,0,0,'cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-16 16:21:48','2020-04-16 16:21:48'),
(7,7,1,1,6,'70b3d5f830000a68',2,350,2,0,0,'cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-16 16:35:59','2020-04-17 00:54:48'),
(8,0,1,1,6,'70b3d5f830000a68',2,150,2,0,0,'Cash',0,'mobile',1,1,'HAN0006',NULL,NULL,'2020-04-19 17:43:16','2020-04-19 17:43:16'),
(9,9,1,1,6,'70b3d5f830000a68',2,150,2,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-19 23:35:54','2020-04-19 23:35:54'),
(10,10,1,1,6,'70b3d5f830000a68',2,200,2,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-19 23:50:28','2020-04-20 00:00:00'),
(11,1587653596267,1,1,6,'70b3d5f830000a68',2,250,3,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-23 20:24:42','2020-04-23 20:35:02'),
(12,1587654437786,1,1,6,'70b3d5f830000a68',2,250,5,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-23 20:37:25','2020-04-23 20:40:00'),
(13,1587654759579,1,1,6,'70b3d5f830000a68',2,250,5,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-23 20:42:40','2020-04-23 20:45:01'),
(14,1587654931314,1,1,6,'70b3d5f830000a68',2,250,5,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-23 20:45:32','2020-04-23 20:50:00'),
(15,1587655338303,1,1,6,'70b3d5f830000a68',2,200,5,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-23 20:52:19','2020-04-23 20:52:19'),
(16,1587655608667,1,1,6,'70b3d5f830000a68',2,200,5,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-23 20:56:49','2020-04-23 21:00:00'),
(17,1587656429119,1,1,6,'70b3d5f830000a68',2,200,5,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-23 21:10:30','2020-04-23 21:15:02'),
(18,1588082156028,1,1,6,'70b3d5f830000a68',2,100,2,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-28 19:25:56','2020-04-28 19:30:00'),
(19,1588229688805,1,1,6,'70b3d5f830000a68',2,200,2,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-30 12:24:50','2020-04-30 12:30:00'),
(20,1588231499381,1,1,6,'70b3d5f830000a68',2,100,5,0,0,'Cash',0,'web',1,1,'HAN0006',NULL,NULL,'2020-04-30 12:55:01','2020-04-30 13:00:01');

/*Table structure for table `updaterequestcustomermeterdetails` */

DROP TABLE IF EXISTS `updaterequestcustomermeterdetails`;

CREATE TABLE `updaterequestcustomermeterdetails` (
  `RequestID` int(255) NOT NULL AUTO_INCREMENT,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `CRNNumber` varchar(100) NOT NULL,
  `HouseNumber` varchar(30) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `ToBeApprovedByID` int(11) NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RequestID`),
  UNIQUE KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `updaterequestcustomermeterdetails_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customermeterdetails` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `updaterequestcustomermeterdetails` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(100) DEFAULT NULL,
  `UserName` varchar(100) NOT NULL,
  `UserPassword` varchar(100) NOT NULL,
  `RoleID` int(11) NOT NULL,
  `ActiveStatus` int(11) NOT NULL,
  `CommunityID` bigint(11) NOT NULL,
  `BlockID` bigint(20) DEFAULT NULL,
  `CustomerID` bigint(20) DEFAULT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `CreatedByID` bigint(20) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CommunityID` (`CommunityID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`ID`,`UserID`,`UserName`,`UserPassword`,`RoleID`,`ActiveStatus`,`CommunityID`,`BlockID`,`CustomerID`,`CRNNumber`,`CreatedByID`,`CreatedByRoleID`,`RegisteredDate`,`ModifiedDate`) values 
(1,'Superadmin','Hanbit','cvp/LzpadrQT+2k0WDjyOQ==',1,1,0,0,0,NULL,0,0,'2020-04-04 17:36:33','2020-04-04 17:36:33'),
(2,'kvkadmin','Kvk','cvp/LzpadrQT+2k0WDjyOQ==',2,1,1,1,0,NULL,1,1,'2020-04-05 00:08:42','2020-04-05 00:08:42'),
(3,'Vimal','Vimal Kumar','cvp/LzpadrQT+2k0WDjyOQ==',3,1,1,1,1,'HAN0001',2,2,'2020-04-05 00:09:19','2020-04-05 00:09:19'),
(4,'Superadminsupervisor','HanbitSuperadminsupervisor','cvp/LzpadrQT+2k0WDjyOQ==',4,1,0,0,0,NULL,1,1,'2020-04-05 01:41:13','2020-04-05 01:41:13'),
(5,'Adminsupervisor','KvkAdminsupervisor','cvp/LzpadrQT+2k0WDjyOQ==',5,1,1,1,0,NULL,1,1,'2020-04-05 01:42:05','2020-04-05 01:42:05'),
(6,'Admin1','Demoblock3','cvp/LzpadrQT+2k0WDjyOQ==',2,1,2,5,0,NULL,1,1,'2020-04-06 22:28:23','2020-04-07 13:35:23'),
(7,'Demoblock1','Sri babu','qoH4Tc1M0Oh+Bc7LH4LtMA==',5,1,2,3,0,NULL,1,1,'2020-04-08 00:51:26','2020-04-08 01:23:43'),
(9,'Demoblock2','Demoblock2','cvp/LzpadrQT+2k0WDjyOQ==',2,1,2,3,0,NULL,1,1,'2020-04-09 02:58:48','2020-04-09 03:00:02'),
(10,'bhrtsrm','Bharat Sriram','cvp/LzpadrQT+2k0WDjyOQ==',3,1,2,3,4,'HAN0004',9,2,'2020-04-09 05:00:11','2020-04-09 05:00:14'),
(11,'TestCommunity1Demoblock5','Demoblock5','wih1Khk5v8/1bgPmA8lVuA==',2,1,2,8,0,NULL,1,1,'2020-04-11 23:54:59','2020-04-11 23:54:59'),
(12,'TestCommunity2Demoblock6','Demoblock6','kVA6fP+zltT3x1e91Xa5lA==',2,1,3,9,0,NULL,1,1,'2020-04-12 15:32:00','2020-04-12 15:32:00'),
(13,'De02Cu02democustomer502','Demo502 Customer502','Mb5vqPCVR48upDxrShoKjU/uF9DJyKSl9czmqz6hS6Y=',3,1,2,5,9,'HAN0009',1,1,'2020-04-17 15:29:26','2020-04-17 15:29:26'),
(14,'Testingtesting','testing','WV1UipqkSze2zl0HHoY+aQ==',2,1,4,10,0,NULL,1,1,'2020-04-28 22:47:57','2020-04-28 22:47:57'),
(15,'Amanora','Amanora testing','cvp/LzpadrQT+2k0WDjyOQ==',3,1,1,1,6,'HAN0006',1,1,'2020-04-30 10:32:37','2020-05-10 01:35:35'),
(16,'Srbukatysri','Sri babu kasamsetty','Q7u8tBIDBFgMKzvqfRfUHA==',3,1,4,10,10,NULL,1,1,'2020-04-30 13:19:57','2020-04-30 13:19:57'),
(17,'VIayBharbhaskarnimmagadda','VIjay Bhaskar','NwCfJchKOl2wGzmihPkCvA==',3,1,4,10,11,'HAN0011',1,1,'2020-04-30 13:22:37','2020-04-30 13:22:37');

/*Table structure for table `userrole` */

DROP TABLE IF EXISTS `userrole`;

CREATE TABLE `userrole` (
  `RoleID` int(11) NOT NULL,
  `RoleDescription` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userrole` */

insert  into `userrole`(`RoleID`,`RoleDescription`) values 
(1,'Super Admin'),
(2,'Admin'),
(3,'User'),
(4,'SuperAdminSupervisor'),
(5,'AdminSupervisor');

/*Table structure for table `vacation` */

DROP TABLE IF EXISTS `vacation`;

CREATE TABLE `vacation` (
  `VacationID` int(11) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` bigint(20) NOT NULL,
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `MeterID` varchar(20) NOT NULL,
  `VacationName` varchar(300) DEFAULT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `Status` tinyint(4) NOT NULL,
  `Source` varchar(10) NOT NULL,
  `CRNNumber` varchar(100) NOT NULL,
  `mode` varchar(100) NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`VacationID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `vacation` */

insert  into `vacation`(`VacationID`,`TataReferenceNumber`,`CommunityID`,`BlockID`,`CustomerID`,`MeterID`,`VacationName`,`StartDate`,`EndDate`,`Status`,`Source`,`CRNNumber`,`mode`,`RegisteredDate`,`ModifiedDate`) values 
(1,0,1,1,6,'70b3d5f830000a68','out of Station','2020-04-21 12:00:00','2020-04-21 12:15:00',2,'mobile','HAN0006','add','2020-04-20 23:51:12','2020-04-20 23:51:12'),
(2,0,1,1,6,'70b3d5f830000a68','out of Station','2020-04-21 13:00:00','2020-04-21 13:15:00',2,'mobile','HAN0006','add','2020-04-21 00:30:21','2020-04-21 00:30:21'),
(3,1588438077819,1,1,6,'70b3d5f830000a68','testing','2020-05-02 22:20:00','2020-05-02 22:25:00',2,'web','HAN0006','add','2020-05-02 22:17:53','2020-05-02 22:20:00'),
(4,1588441392073,1,1,6,'70b3d5f830000a68','testing1','2020-05-03 11:00:00','2020-05-03 11:15:00',5,'web','HAN0006','add','2020-05-02 23:13:06','2020-05-03 10:50:00'),
(5,1588484098972,1,1,6,'70b3d5f830000a68','testing2','2020-05-03 11:20:00','2020-05-03 11:30:00',5,'web','HAN0006','add','2020-05-03 11:04:59','2020-05-03 11:10:00'),
(6,1588484921691,1,1,6,'70b3d5f830000a68','test','2020-05-03 11:25:00','2020-05-03 11:35:00',2,'web','HAN0006','add','2020-05-03 11:18:41','2020-05-03 11:20:00'),
(7,1588486125582,1,1,6,'70b3d5f830000a68','testedit','2020-05-03 11:45:00','2020-05-03 12:00:00',5,'web','HAN0006','add','2020-05-03 11:38:45','2020-05-03 11:45:00'),
(8,1588487012297,1,1,6,'70b3d5f830000a68','testdelete','2020-05-03 12:00:00','2020-05-03 12:15:00',5,'web','HAN0006','add','2020-05-03 11:53:45','2020-05-03 11:55:00'),
(9,1588487199277,1,1,6,'70b3d5f830000a68','delete','2020-05-03 12:30:00','2020-05-03 01:00:00',5,'web','HAN0006','add','2020-05-03 11:56:39','2020-05-03 12:00:00'),
(10,1588487462782,1,1,6,'70b3d5f830000a68','delete','2020-05-03 12:30:00','2020-05-03 12:45:00',5,'web','HAN0006','add','2020-05-03 12:01:02','2020-05-03 12:05:00'),
(11,1588487901118,1,1,6,'70b3d5f830000a68','deletetest','2020-05-03 12:30:00','2020-05-03 12:50:00',5,'web','HAN0006','add','2020-05-03 12:08:21','2020-05-03 12:10:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
