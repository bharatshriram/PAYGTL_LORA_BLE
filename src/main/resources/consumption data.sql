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
(1,2880,3.35,330,2.4,50,'2020-04-07 02:20:35','2020-04-07 02:30:33');

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
  `TamperTimeStamp` varchar(80) DEFAULT NULL,
  `DoorOpenTimeStamp` varchar(80) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `Vacation` tinyint(4) DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `Minutes` int(11) DEFAULT NULL,
  `IoTTimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;

/*Data for the table `balancelog` */

insert  into `balancelog`(`ReadingID`,`MeterID`,`Reading`,`Balance`,`CommunityID`,`BlockID`,`CustomerID`,`BatteryVoltage`,`TariffAmount`,`EmergencyCredit`,`MeterType`,`SolonideStatus`,`CreditStatus`,`TamperDetect`,`TamperTimeStamp`,`DoorOpenTimeStamp`,`LowBattery`,`Vacation`,`MeterSerialNumber`,`CRNNumber`,`Minutes`,`IoTTimeStamp`,`LogDate`) values 
(1,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-10 20:50:11','2020-05-13 19:36:06'),
(2,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-13 13:50:11','2020-05-13 19:36:06'),
(3,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-13 13:52:11','2020-05-13 19:36:06'),
(4,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-13 14:00:11','2020-05-13 19:36:06'),
(5,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-14 20:50:56','2020-05-13 19:36:06'),
(6,'70b3d5f83000157a',800.00,170.00,2,3,2,2.17,50.00,'0.0',3,1,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 15:20:35','2020-05-13 19:36:06'),
(7,'70b3d5f83000157a',816.00,20.00,2,3,2,2.18,50.00,'0.0',3,1,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 15:24:21','2020-05-13 19:36:06'),
(8,'70b3d5f83000157a',831.00,220.00,2,3,2,2.18,50.00,'200.0',3,0,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 15:50:27','2020-05-13 19:36:06'),
(9,'70b3d5f83000157a',831.00,220.00,2,3,2,2.18,50.00,'200.0',3,0,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 16:32:15','2020-05-13 19:36:06'),
(10,'70b3d5f83000157a',831.00,220.00,2,3,2,2.17,50.00,'200.0',3,1,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 17:51:46','2020-05-13 19:36:06'),
(11,'70b3d5f83000157a',0.00,220.00,2,3,2,2.18,50.00,'200.0',3,1,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 17:57:10','2020-05-13 19:36:06'),
(12,'70b3d5f83000157a',831.00,220.00,2,3,2,2.17,50.00,'200.0',3,1,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 19:02:57','2020-05-13 19:36:06'),
(13,'70b3d5f83000157a',834.00,70.00,2,3,2,2.17,50.00,'200.0',3,3,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 19:26:52','2020-05-13 19:36:06'),
(14,'70b3d5f83000157a',834.00,70.00,2,3,2,2.17,50.00,'200.0',3,0,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 19:31:08','2020-05-13 19:36:06'),
(15,'70b3d5f83000157a',834.00,70.00,2,3,2,2.18,50.00,'200.0',3,0,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 19:39:50','2020-05-13 19:36:06'),
(16,'70b3d5f83000157a',852.00,70.00,2,3,2,2.18,50.00,'200.0',3,1,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-15 20:38:52','2020-05-13 19:36:06'),
(17,'70b3d5f83000157a',164.00,900.00,2,3,2,2.18,1000.00,'0.0',3,1,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-16 10:36:55','2020-05-13 19:36:06'),
(18,'70b3d5f830000a68',4.00,900.00,1,1,6,2.18,1000.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-16 15:44:10','2020-05-13 19:36:06'),
(19,'70b3d5f830000a68',5.00,1250.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-16 10:37:37','2020-05-26 16:42:26'),
(20,'70b3d5f830000a68',6.00,1250.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-16 16:42:38','2020-05-26 16:35:35'),
(21,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-17 02:51:29','2020-05-13 19:36:06'),
(22,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-18 20:51:49','2020-05-13 19:36:06'),
(23,'70b3d5f830004f56',0.00,30.00,1,1,1,3.59,50.00,'20.0',3,0,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-20 13:34:40','2020-05-13 19:36:06'),
(24,'70b3d5f830000a68',90.00,1250.00,1,1,6,2.25,50.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-19 21:50:56','2020-05-13 19:36:06'),
(25,'70b3d5f830000a68',93.00,1250.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-19 23:39:15','2020-05-13 19:36:06'),
(26,'70b3d5f830000a68',94.00,1250.00,1,1,6,2.21,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-17 13:40:35','2020-05-26 16:43:36'),
(27,'70b3d5f830000a68',95.00,1400.00,1,1,6,2.19,50.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-17 23:48:48','2020-05-26 16:43:31'),
(28,'70b3d5f830000a68',98.00,1600.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-18 14:51:47','2020-05-26 16:44:15'),
(29,'70b3d5f830000a68',99.00,1600.00,1,1,6,2.25,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-18 23:56:07','2020-05-26 16:44:33'),
(30,'70b3d5f830000a68',102.00,1600.00,1,1,6,2.19,50.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-20 15:25:01','2020-05-26 16:36:22'),
(31,'70b3d5f830000a68',105.00,1600.00,1,1,6,2.19,50.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-20 15:34:30','2020-05-26 16:36:25'),
(32,'70b3d5f830000a68',107.00,1610.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-23 10:21:07','2020-05-26 16:45:10'),
(33,'70b3d5f830000a68',108.00,1610.00,1,1,6,2.36,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-23 19:18:18','2020-05-26 16:45:02'),
(34,'70b3d5f830000a68',110.00,1610.00,1,1,6,2.26,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-24 19:27:32','2020-05-26 16:45:26'),
(35,'70b3d5f830000a68',111.00,1710.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-24 19:36:03','2020-05-26 16:45:32'),
(36,'70b3d5f830000a68',112.00,1710.00,1,1,6,2.22,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-25 19:41:58','2020-05-26 16:45:36'),
(37,'70b3d5f830000a68',115.00,1710.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-25 19:55:04','2020-05-26 16:45:41'),
(38,'70b3d5f830000a68',117.00,1710.00,1,1,6,2.28,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-27 11:37:29','2020-05-26 16:46:00'),
(39,'70b3d5f830000a68',120.00,1710.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-27 12:21:19','2020-05-26 16:46:05'),
(40,'70b3d5f830000a68',125.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-28 12:28:35','2020-05-26 16:46:12'),
(41,'70b3d5f830000a68',127.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-28 12:46:49','2020-05-26 16:46:16'),
(42,'70b3d5f830000a68',130.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-29 12:48:15','2020-05-26 16:46:22'),
(43,'70b3d5f830000a68',135.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-04-29 14:22:15','2020-05-26 16:46:28'),
(44,'70b3d5f830000a68',140.00,1910.00,1,1,6,2.24,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-02 22:12:36','2020-05-26 16:37:23'),
(45,'70b3d5f830000a68',142.00,1910.00,1,1,6,2.22,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-02 22:19:42','2020-05-26 16:37:27'),
(46,'70b3d5f830000a68',145.00,1910.00,1,1,6,2.22,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-02 22:21:46','2020-05-26 16:37:31'),
(47,'70b3d5f830000a68',147.00,1910.00,1,1,6,2.24,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-03 09:53:51','2020-05-26 16:37:33'),
(48,'70b3d5f830000a68',149.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-03 11:03:01','2020-05-26 16:37:40'),
(49,'70b3d5f830000a68',151.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-04 11:06:23','2020-05-26 16:47:07'),
(50,'70b3d5f830000a68',152.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-04 11:19:50','2020-05-26 16:47:11'),
(51,'70b3d5f830000a68',153.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-06 11:23:10','2020-05-26 16:47:15'),
(52,'70b3d5f830000a68',155.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-06 11:25:28','2020-05-26 16:47:20'),
(53,'70b3d5f830000a68',157.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-07 11:28:50','2020-05-26 16:47:25'),
(54,'70b3d5f830000a68',159.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-07 11:34:08','2020-05-26 16:47:29'),
(55,'70b3d5f830000a68',160.00,1910.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-08 11:35:28','2020-05-26 16:47:33'),
(56,'70b3d5f830000a68',161.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-08 11:42:48','2020-05-26 16:47:37'),
(57,'70b3d5f830000a68',163.00,1910.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-09 11:55:11','2020-05-26 16:47:41'),
(58,'70b3d5f830000a68',165.00,1910.00,1,1,6,2.19,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-09 11:57:44','2020-05-26 16:47:45'),
(59,'70b3d5f830000a68',167.00,1910.00,1,1,6,2.18,10.00,'0.0',3,0,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-10 12:09:30','2020-05-26 16:47:53'),
(60,'70b3d5f830000a68',169.00,1910.00,1,1,6,2.19,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-10 12:22:10','2020-05-26 16:47:59'),
(61,'70b3d5f830000a68',175.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-03 12:27:19','2020-05-26 16:38:33'),
(62,'70b3d5f83000157b',172.00,0.00,5,11,12,3.59,30.00,'0.0',3,1,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 19:36:58','2020-05-15 19:59:54'),
(63,'70b3d5f83000157b',172.00,0.00,5,11,12,3.59,30.00,'0.0',3,1,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 19:46:58','2020-05-15 20:03:19'),
(64,'70b3d5f83000157b',172.00,305.00,5,11,12,3.59,15.00,'0.0',3,1,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 20:32:43','2020-05-15 20:34:17'),
(65,'70b3d5f83000157b',173.00,340.00,5,11,12,3.59,15.00,'50.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 20:35:34','2020-05-15 20:36:35'),
(66,'70b3d5f83000157b',173.00,340.00,5,11,12,3.59,15.00,'50.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',2,'2020-05-15 20:37:54','2020-05-15 20:38:40'),
(67,'70b3d5f83000157b',177.00,280.00,5,11,12,3.59,15.00,'5.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',4,'2020-05-15 20:39:27','2020-05-15 20:39:59'),
(68,'70b3d5f83000157b',177.00,280.00,5,11,12,3.59,15.00,'5.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',11,'2020-05-15 20:46:23','2020-05-15 20:47:16'),
(69,'70b3d5f83000157b',177.00,280.00,5,11,12,3.59,15.00,'5.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',19,'2020-05-15 20:54:46','2020-05-15 20:55:16'),
(70,'70b3d5f83000157b',177.00,280.00,5,11,12,3.59,15.00,'5.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',23,'2020-05-15 20:58:12','2020-05-15 21:07:16'),
(71,'70b3d5f83000157b',212.00,10.00,5,11,12,3.59,30.00,'0.0',3,1,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 22:11:20','2020-05-15 22:13:59'),
(72,'70b3d5f83000157b',221.00,10.00,5,11,12,3.59,30.00,'0.0',3,1,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 22:19:08','2020-05-15 22:20:03'),
(73,'70b3d5f83000157b',221.00,10.00,5,11,12,3.59,30.00,'0.0',3,1,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 22:21:15','2020-05-15 22:45:59'),
(74,'70b3d5f83000157b',217.00,0.00,5,11,12,3.59,30.00,'0.0',3,1,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 23:00:05','2020-05-15 23:04:18'),
(75,'70b3d5f83000157b',217.00,500.00,5,11,12,3.59,30.00,'50.0',3,1,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 23:04:16','2020-05-15 23:06:17'),
(76,'70b3d5f83000157b',217.00,500.00,5,11,12,3.59,30.00,'50.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 23:08:39','2020-05-15 23:10:23'),
(77,'70b3d5f83000157b',230.00,110.00,5,11,12,3.59,30.00,'50.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 23:48:33','2020-05-15 23:49:41'),
(78,'70b3d5f83000157b',230.00,110.00,5,11,12,3.59,30.00,'50.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 23:49:50','2020-05-15 23:50:52'),
(79,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-16 13:07:18','2020-05-16 13:09:00'),
(80,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,1,0,1,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-16 13:31:36','2020-05-16 13:32:22'),
(81,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-16 21:50:23','2020-05-16 21:51:15'),
(82,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,0,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-16 21:54:27','2020-05-16 21:55:30'),
(83,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,1,'986630','HAN0013',0,'2020-05-16 22:06:43','2020-05-16 22:07:20'),
(84,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,0,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-16 22:10:33','2020-05-16 22:11:09'),
(85,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,1,'986630','HAN0013',0,'2020-05-16 22:35:21','2020-05-16 22:47:05'),
(86,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-17 14:36:45','2020-05-17 14:38:14'),
(87,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,0,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-17 15:00:55','2020-05-17 15:01:57'),
(88,'70b3d5f830004fb4',0.00,0.00,5,11,14,3.59,0.00,'0.0',3,0,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-17 15:03:43','2020-05-17 15:04:59'),
(89,'70b3d5f830004fb4',229.00,300.00,5,11,14,3.59,25.00,'50.0',3,1,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-27 12:49:22','2020-05-27 12:53:13'),
(90,'70b3d5f830004fb4',229.00,300.00,5,11,14,3.59,25.00,'50.0',3,1,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-27 12:58:24','2020-05-27 12:59:12'),
(91,'70b3d5f830004fb5',0.00,0.00,5,11,15,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',0,'2020-05-27 13:18:21','2020-05-27 13:25:47'),
(92,'70b3d5f830004fb5',0.00,0.00,5,11,15,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',0,'2020-05-27 13:53:13','2020-05-27 13:54:52'),
(93,'70b3d5f830004fb5',10.00,0.00,5,11,15,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',0,'2020-05-27 14:28:42','2020-05-27 14:29:15'),
(94,'70b3d5f830004fb5',58.00,0.00,5,11,15,3.59,0.00,'0.0',3,1,0,1,NULL,NULL,0,0,'765993','HAN0014',0,'2020-05-27 14:55:16','2020-05-27 15:04:23'),
(95,'70b3d5f830004fb5',73.00,0.00,5,11,15,3.59,0.00,'0.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',0,'2020-05-27 15:09:51','2020-05-27 15:10:19'),
(96,'70b3d5f830004fb5',159.00,0.00,5,11,15,3.59,25.00,'100.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',2,'2020-05-29 11:09:54','2020-05-29 11:30:26'),
(97,'70b3d5f830004fb5',159.00,0.00,5,11,15,3.59,25.00,'100.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',2,'2020-05-29 11:36:30','2020-05-29 11:39:39'),
(98,'70b3d5f830004fb5',0.00,500.00,5,11,15,3.59,50.00,'100.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',2,'2020-05-29 11:52:15','2020-05-29 11:55:01'),
(99,'70b3d5f830004fb5',0.00,500.00,5,11,15,3.59,50.00,'100.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',2,'2020-05-29 12:08:12','2020-05-29 12:09:18'),
(100,'70b3d5f830004fb5',50.00,500.00,5,11,15,3.59,50.00,'100.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',2,'2020-05-29 12:42:09','2020-05-29 12:45:30'),
(101,'70b3d5f830004fb5',50.00,0.00,5,11,15,3.59,10.00,'20.0',3,1,0,0,NULL,NULL,0,0,'765993','HAN0014',0,'2020-05-29 14:08:31','2020-05-29 14:09:40'),
(102,'70b3d5f830004fb5',34.00,0.00,5,11,15,3.59,10.00,'20.0',3,0,0,0,NULL,NULL,0,0,'765993','HAN0014',0,'2020-05-29 14:23:17','2020-05-29 14:24:30'),
(103,'70b3d5f830004fb5',84.00,440.00,5,11,15,3.59,20.00,'0.0',3,94,0,2,'','',0,0,'765993','HAN0014',0,'2020-06-04 14:27:52','2020-06-04 14:37:24'),
(104,'70b3d5f830004fb5',84.00,440.00,5,11,15,3.59,20.00,'0.0',3,0,0,0,'','',0,0,'765993','HAN0014',0,'2020-06-04 14:33:53','2020-06-04 14:42:25'),
(105,'70b3d5f830004fb5',131.00,0.00,5,11,15,3.59,20.00,'0.0',3,84,0,2,'1970-01-01 11:30:05','1970-01-01 10:30:40',0,0,'765993','HAN0014',0,'2020-06-04 14:59:59','2020-06-04 15:02:04'),
(106,'70b3d5f830004fb5',131.00,0.00,5,11,15,3.59,20.00,'0.0',3,1,0,2,'2014-11-14 05:32','2014-11-14 05:36',0,0,'765993','HAN0014',0,'2020-06-04 15:09:59','2020-06-04 16:25:41'),
(107,'70b3d5f830004fb5',650.00,1490.00,5,11,15,3.59,50.00,'20.0',3,1,0,2,'2020-06-04 22:19','2020-06-04 22:37',0,0,'765993','HAN0014',0,'2020-06-04 17:08:26','2020-06-04 17:31:08'),
(108,'70b3d5f830004fb5',0.00,20.00,5,11,15,3.59,0.00,'3.081e-42',3,1,0,2,'','2014-11-14 05:37',0,0,'765993','HAN0014',65280,'2020-06-05 12:41:50','2020-06-05 12:47:27'),
(109,'70b3d5f830004fb5',0.00,0.00,5,11,15,3.59,0.00,'0.0',3,1,0,2,'','2014-11-14 05:59',0,0,'765993','HAN0014',0,'2020-06-05 16:17:24','2020-06-05 16:18:10'),
(110,'70b3d5f830004fb5',0.00,500.00,5,11,15,3.59,50.00,'20.0',3,1,0,2,'','2014-11-14 06:02',0,0,'765993','HAN0014',0,'2020-06-05 16:20:50','2020-06-05 16:21:32'),
(111,'70b3d5f830004fb5',5.00,500.00,5,11,15,3.59,50.00,'20.0',3,0,0,2,'2014-11-14 06:04','2014-11-14 06:07',0,0,'765993','HAN0014',0,'2020-06-05 16:25:52','2020-06-05 16:27:00'),
(112,'70b3d5f830004fb5',7.00,150.00,5,11,15,3.59,50.00,'20.0',3,0,0,2,'','2014-11-14 05:40',0,0,'765993','HAN0014',0,'2020-06-05 16:36:52','2020-06-05 16:37:17'),
(113,'70b3d5f830004fb5',12.00,500.00,5,11,15,3.59,50.00,'20.0',3,1,0,2,'','2020-06-05 22:11',0,0,'765993','HAN0014',0,'2020-06-05 16:41:28','2020-06-05 16:42:37'),
(114,'70b3d5f830004fb5',12.00,500.00,5,11,15,3.59,50.00,'20.0',3,1,0,2,'','2020-06-05 22:17',0,0,'765993','HAN0014',0,'2020-06-05 16:48:15','2020-06-05 16:52:17');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `block` */

insert  into `block`(`BlockID`,`BlockName`,`Location`,`MobileNumber`,`Email`,`CommunityID`,`CreatedByID`,`CreatedByRoleID`,`CreatedDate`,`ModifiedDate`) values 
(1,'DemoBlock','Bangalore','9876543210','Demoblock@gmail.com',1,1,1,'2020-04-05 01:53:16','2020-04-05 01:53:16'),
(3,'Demoblock1','Hyderabad','9876543211','Demoblock1@gmail.com',2,1,1,'2020-04-05 21:27:08','2020-04-05 21:27:08'),
(4,'Demoblock2','Mumbai','9876543212','Demoblock2@gmail.com',1,1,1,'2020-04-06 15:39:11','2020-04-06 15:39:14'),
(5,'Demoblock3','Warangal','9876543213','Demoblock3@gmail.com',2,1,1,'2020-04-06 15:39:46','2020-04-06 15:39:48'),
(8,'Demoblock5','Secunderabad','9000941923','bhrt@gmail.com',2,1,1,'2020-04-11 23:54:59','2020-04-11 23:54:59'),
(9,'Demoblock6','Maredpally','2345678901','Demoblock6@gmail.com',3,1,1,'2020-04-12 15:32:00','2020-04-12 15:32:00'),
(10,'testing','hyderabad','6789012345','testing@gmail.com',4,1,1,'2020-04-28 22:47:57','2020-04-28 22:47:57'),
(11,'finaltest','koramangala','8498890000','kvk9889@gmail.com',5,1,1,'2020-05-15 17:38:46','2020-05-15 17:38:46');

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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

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
(13,1588488808889,0,'70b3d5f830000a68',0,5,'HAN0006','2020-05-03 12:23:28','2020-05-03 12:30:00'),
(14,1589556416015,12,'70b3d5f83000157b',0,5,'HAN0012','2020-05-15 20:57:03','2020-05-15 20:57:03'),
(15,1589564238261,12,'70b3d5f83000157b',40,2,'HAN0012','2020-05-15 23:07:25','2020-05-15 23:10:00'),
(16,1589649682365,14,'70b3d5f830004fb4',6,5,'HAN0013','2020-05-16 22:51:48','2020-05-16 22:54:00'),
(17,1589649911667,14,'70b3d5f830004fb4',6,5,'HAN0013','2020-05-16 22:55:18','2020-05-16 22:58:00'),
(18,1589650187111,14,'70b3d5f830004fb4',6,2,'HAN0013','2020-05-16 22:59:53','2020-05-16 23:02:00'),
(19,1589650434886,14,'70b3d5f830004fb4',6,5,'HAN0013','2020-05-16 23:04:01','2020-05-16 23:06:00'),
(20,1589650989308,14,'70b3d5f830004fb4',6,5,'HAN0013','2020-05-16 23:13:15','2020-05-16 23:22:00'),
(21,1589712670324,14,'70b3d5f830004fb4',6,5,'HAN0013','2020-05-17 16:21:10','2020-05-17 16:24:00'),
(22,1589712916653,14,'70b3d5f830004fb4',40,5,'HAN0013','2020-05-17 16:25:16','2020-05-17 16:28:00'),
(23,1590564754575,14,'70b3d5f830004fb4',6,1,'HAN0013','2020-05-27 13:02:37','2020-06-05 17:08:01'),
(24,1590567899117,15,'70b3d5f830004fb5',6,2,'HAN0014','2020-05-27 13:55:01','2020-05-27 13:55:02'),
(25,1590568080874,15,'70b3d5f830004fb5',6,5,'HAN0014','2020-05-27 13:58:03','2020-05-27 14:00:01'),
(26,1590569802587,15,'70b3d5f830004fb5',6,2,'HAN0014','2020-05-27 14:26:45','2020-05-27 14:30:01'),
(27,1590570151053,15,'70b3d5f830004fb5',6,5,'HAN0014','2020-05-27 14:32:33','2020-05-27 14:35:01'),
(28,1590570365862,15,'70b3d5f830004fb5',6,5,'HAN0014','2020-05-27 14:36:08','2020-05-27 14:36:08'),
(29,1590570564257,15,'70b3d5f830004fb5',6,5,'HAN0014','2020-05-27 14:39:26','2020-05-27 14:40:06'),
(30,1590570878941,15,'70b3d5f830004fb5',6,2,'HAN0014','2020-05-27 14:44:41','2020-05-27 14:50:01'),
(31,1590733154675,15,'70b3d5f830004fb5',6,2,'HAN0014','2020-05-29 11:49:19','2020-05-29 12:00:01'),
(32,1590734807954,15,'70b3d5f830004fb5',6,2,'HAN0014','2020-05-29 12:16:52','2020-05-29 12:30:01'),
(33,1590735618094,15,'70b3d5f830004fb5',6,2,'HAN0014','2020-05-29 12:30:22','2020-05-29 12:34:01'),
(34,1590736190449,15,'70b3d5f830004fb5',6,2,'HAN0014','2020-05-29 12:39:54','2020-05-29 12:42:01'),
(35,1590741606300,15,'70b3d5f830004fb5',40,2,'HAN0014','2020-05-29 14:10:10','2020-05-29 14:14:01'),
(36,1590742479137,15,'70b3d5f830004fb5',0,2,'HAN0014','2020-05-29 14:24:43','2020-05-29 14:28:01'),
(37,1590742871605,15,'70b3d5f830004fb5',5,5,'HAN0014','2020-05-29 14:31:15','2020-05-29 14:32:04'),
(38,1590748366961,15,'70b3d5f830004fb5',5,5,'HAN0014','2020-05-29 16:02:51','2020-05-29 16:12:01'),
(39,1590750318160,15,'70b3d5f830004fb5',5,5,'HAN0014','2020-05-29 16:35:22','2020-06-01 12:12:03'),
(40,1591353939180,15,'70b3d5f830004fb5',3,2,'HAN0014','2020-06-05 16:15:39','2020-06-05 16:20:19');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `community` */

insert  into `community`(`CommunityID`,`CommunityName`,`Email`,`MobileNumber`,`Address`,`CreatedDate`,`ModifiedDate`) values 
(1,'TestCommunity','testcommunity@gmail.com','9876543210','Bangalore','2020-04-04 23:13:10','2020-04-04 23:13:10'),
(2,'TestCommunity1','testcommunity1@gmail.com','9876543211','Hyderabaaaaad','2020-04-05 19:30:44','2020-04-05 19:49:04'),
(3,'TestCommunity2','testcommunity2@gmail.com','1234567890','Secunderabad','2020-04-12 15:30:26',NULL),
(4,'Testing','testing@gmail.com','6789012345','hyderabad','2020-04-28 22:44:18','2020-05-02 00:34:27'),
(5,'Finaltesting','kvk9889@gmail.com','8498890000','bangalore','2020-05-15 17:38:12',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `customerdeletemeter` */

insert  into `customerdeletemeter`(`CustomerDeleteID`,`CustomerID`,`CommunityID`,`BlockID`,`HouseNumber`,`FirstName`,`LastName`,`Email`,`MobileNumber`,`MeterID`,`MeterSerialNumber`,`TariffID`,`CRNNumber`,`CreatedByID`,`CreatedByRoleID`,`RegistrationDate`,`DeletedDate`) values 
(1,9,2,5,'502','Demo502','Customer502','democustomer502@gmail.com','9876598765','5002','6789',2,'HAN0009',1,1,'2020-04-17 15:29:25','2020-05-16 13:03:36'),
(3,10,4,10,'Sri 45','Sri babu','kasamsetty','sri@gmail.com','9703668598','k486','987645k',3,'HAN0010',1,1,'2020-04-30 13:19:57','2020-06-04 14:48:08');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `customermeterdetails` */

insert  into `customermeterdetails`(`CustomerID`,`CommunityID`,`BlockID`,`HouseNumber`,`FirstName`,`LastName`,`Email`,`MobileNumber`,`MeterID`,`MeterSerialNumber`,`TariffID`,`ActiveStatus`,`CRNNumber`,`CreatedByID`,`CreatedByRoleID`,`RegistrationDate`,`ModifiedDate`) values 
(1,1,1,'101','Vimal','Kumar','kvk9889@gmail.com','8498891111','70b3d5f830004f56','1234',1,1,'HAN0001',2,2,'2020-04-05 01:58:12','2020-05-01 00:09:26'),
(2,2,3,'301','Vml','Kvk','kvk9889@gmail.com','9999999999','70b3d5f83000157a','3456',2,1,'HAN0002',2,2,'2020-04-06 15:33:13','2020-04-06 15:33:17'),
(4,2,3,'302','Bharatttttt','sriram','bharat@gmail.com','7777777777','3002','3457',1,1,'HAN0004',1,1,'2020-04-06 15:36:17','2020-04-09 19:58:42'),
(5,2,5,'501','Demo','Customer','democustomer@gmail.com','6666666666','5001','5678',2,1,'HAN0005',6,2,'2020-04-06 22:31:15','2020-04-06 22:39:04'),
(6,1,1,'102','Amanora','testing','Amanora@gmail.com','8498890000','70b3d5f830000a68','4567',2,1,'HAN0006',1,1,'2020-04-16 15:43:25','2020-05-01 00:09:04'),
(11,4,10,'V-24','VIjay','Bhaskar','bhaskarnimmagadda@gmail.com','9494281300','1300','949428',3,1,'HAN0011',1,1,'2020-04-30 13:22:36','2020-04-30 13:22:36'),
(12,5,11,'49/38','K VIMAL','KUMAR','kvk9889@gmail.com','9398348954','70b3d5f83000157b','17221006',1,1,'HAN0012',18,2,'2020-05-15 17:41:42','2020-05-15 22:47:47'),
(14,5,11,'45','Phani','Varanasi','phani.varanasi@hanbitsolutions.com','9866305554','70b3d5f830004fb4','986630',1,1,'HAN0013',1,1,'2020-05-16 13:01:17','2020-05-16 13:01:17'),
(15,5,11,'1234','Mamatha','Hanbit','vmamatha490@gmail.com','7659935323','70b3d5f830004fb5','765993',2,1,'HAN0014',1,1,'2020-05-27 13:23:33','2020-05-27 13:23:33');

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
  `TamperTimeStamp` varchar(80) DEFAULT NULL,
  `DoorOpenTimeStamp` varchar(80) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `Vacation` tinyint(4) DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `Minutes` int(11) DEFAULT NULL,
  `IoTTimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `displaybalancelog` */

insert  into `displaybalancelog`(`ReadingID`,`MainBalanceLogID`,`MeterID`,`Reading`,`Balance`,`CommunityID`,`BlockID`,`CustomerID`,`BatteryVoltage`,`TariffAmount`,`EmergencyCredit`,`MeterType`,`SolonideStatus`,`CreditStatus`,`TamperDetect`,`TamperTimeStamp`,`DoorOpenTimeStamp`,`LowBattery`,`Vacation`,`MeterSerialNumber`,`CRNNumber`,`Minutes`,`IoTTimeStamp`,`LogDate`) values 
(1,23,'70b3d5f830004f56',0.00,30.00,1,1,1,3.59,50.00,'20.0',3,0,0,0,NULL,NULL,0,0,'1234','HAN0001',0,'2020-04-20 13:34:40','2020-05-13 19:36:28'),
(2,17,'70b3d5f83000157a',164.00,900.00,2,3,2,2.18,1000.00,'0.0',3,1,0,0,NULL,NULL,0,0,'3456','HAN0002',0,'2020-04-16 10:36:55','2020-05-16 00:52:59'),
(3,61,'70b3d5f830000a68',0.00,1910.00,1,1,6,2.18,10.00,'0.0',3,1,0,0,NULL,NULL,0,0,'4567','HAN0006',0,'2020-05-13 23:11:19','2020-05-27 15:23:48'),
(4,78,'70b3d5f83000157b',230.00,110.00,5,11,12,3.59,30.00,'50.0',3,0,0,0,NULL,NULL,0,0,'17221006','HAN0012',0,'2020-05-15 23:49:50','2020-05-15 23:50:52'),
(5,90,'70b3d5f830004fb4',229.00,300.00,5,11,14,3.59,25.00,'50.0',3,1,0,0,NULL,NULL,0,0,'986630','HAN0013',0,'2020-05-27 12:58:24','2020-05-27 12:59:12'),
(6,114,'70b3d5f830004fb5',12.00,500.00,5,11,15,3.59,50.00,'20.0',3,1,0,2,'','2020-06-05 22:17',0,0,'765993','HAN0014',0,'2020-06-05 16:48:15','2020-06-05 16:52:18');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `FeedbackID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Feedback` varchar(200) DEFAULT NULL,
  `Description` varchar(2000) DEFAULT NULL,
  `Status` int(1) NOT NULL DEFAULT '0',
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `CRNNumber` varchar(20) NOT NULL,
  `MeterID` varchar(20) NOT NULL,
  `Remarks` varchar(2000) DEFAULT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`FeedbackID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`FeedbackID`,`Feedback`,`Description`,`Status`,`CommunityID`,`BlockID`,`CustomerID`,`CRNNumber`,`MeterID`,`Remarks`,`RegisteredDate`,`ModifiedDate`) values 
(1,'test','testing',1,5,11,12,'HAN0012','70b3d5f83000157b','processed successfully','2020-05-25 01:09:36','2020-05-25 01:15:57'),
(2,'test1','testing1',0,5,11,12,'HAN0012','70b3d5f83000157b',NULL,'2020-05-25 01:12:28','2020-05-25 01:12:28');

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
(1,30,'Tariff1',50,15,25,'2020-04-04 23:06:14','2020-04-27 22:27:00'),
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
  `TransactionID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` bigint(20) NOT NULL DEFAULT '0',
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` bigint(20) NOT NULL,
  `MeterID` varchar(50) NOT NULL,
  `TariffID` int(11) NOT NULL,
  `Amount` decimal(10,0) NOT NULL,
  `Status` tinyint(4) NOT NULL DEFAULT '0',
  `FixedCharges` int(5) DEFAULT '0',
  `ReconnectionCharges` int(5) DEFAULT '0',
  `Source` varchar(10) NOT NULL,
  `ModeOfPayment` varchar(50) NOT NULL,
  `PaymentStatus` tinyint(4) NOT NULL DEFAULT '0',
  `RazorPayOrderID` varchar(50) DEFAULT NULL,
  `RazorPayPaymentID` varchar(50) DEFAULT NULL,
  `RazorPaySignature` varchar(200) DEFAULT NULL,
  `ErrorResponse` varchar(10000) DEFAULT NULL,
  `RazorPayRefundID` varchar(50) DEFAULT NULL,
  `RazorPayRefundStatus` varchar(50) DEFAULT NULL,
  `RazorPayRefundEntity` varchar(10000) DEFAULT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` int(11) NOT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `TransactionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `AcknowledgeDate` datetime NOT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

/*Data for the table `topup` */

insert  into `topup`(`TransactionID`,`TataReferenceNumber`,`CommunityID`,`BlockID`,`CustomerID`,`MeterID`,`TariffID`,`Amount`,`Status`,`FixedCharges`,`ReconnectionCharges`,`Source`,`ModeOfPayment`,`PaymentStatus`,`RazorPayOrderID`,`RazorPayPaymentID`,`RazorPaySignature`,`ErrorResponse`,`RazorPayRefundID`,`RazorPayRefundStatus`,`RazorPayRefundEntity`,`CreatedByID`,`CreatedByRoleID`,`CRNNumber`,`TransactionDate`,`AcknowledgeDate`) values 
(1,1,2,3,2,'70b3d5f83000157a',2,400,2,0,0,'web','cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0002','2020-04-15 19:42:21','2020-04-15 19:42:21'),
(2,2,2,3,2,'70b3d5f83000157a',2,400,2,0,0,'web','cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0002','2020-04-15 19:51:54','2020-04-15 19:51:54'),
(3,3,2,3,2,'70b3d5f83000157a',2,400,2,0,0,'web','cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0002','2020-04-15 20:30:15','2020-04-15 20:30:15'),
(4,4,2,3,2,'70b3d5f83000157a',2,400,2,0,0,'web','cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0002','2020-04-15 22:09:03','2020-04-15 22:09:03'),
(5,5,1,1,6,'70b3d5f830000a68',2,400,2,0,0,'web','cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-16 16:05:14','2020-04-16 16:05:14'),
(6,6,1,1,6,'70b3d5f830000a68',2,400,2,0,0,'web','cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-16 16:21:48','2020-04-16 16:21:48'),
(7,7,1,1,6,'70b3d5f830000a68',2,350,2,0,0,'web','cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-16 16:35:59','2020-04-17 00:54:48'),
(8,0,1,1,6,'70b3d5f830000a68',2,150,2,0,0,'mobile','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-19 17:43:16','2020-04-19 17:43:16'),
(9,9,1,1,6,'70b3d5f830000a68',2,150,2,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-19 23:35:54','2020-04-19 23:35:54'),
(10,10,1,1,6,'70b3d5f830000a68',2,200,2,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-19 23:50:28','2020-04-20 00:00:00'),
(11,1587653596267,1,1,6,'70b3d5f830000a68',2,250,3,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-23 20:24:42','2020-04-23 20:35:02'),
(12,1587654437786,1,1,6,'70b3d5f830000a68',2,250,5,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-23 20:37:25','2020-04-23 20:40:00'),
(13,1587654759579,1,1,6,'70b3d5f830000a68',2,250,5,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-23 20:42:40','2020-04-23 20:45:01'),
(14,1587654931314,1,1,6,'70b3d5f830000a68',2,250,5,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-23 20:45:32','2020-04-23 20:50:00'),
(15,1587655338303,1,1,6,'70b3d5f830000a68',2,200,5,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-23 20:52:19','2020-04-23 20:52:19'),
(16,1587655608667,1,1,6,'70b3d5f830000a68',2,200,5,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-23 20:56:49','2020-04-23 21:00:00'),
(17,1587656429119,1,1,6,'70b3d5f830000a68',2,200,5,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-23 21:10:30','2020-04-23 21:15:02'),
(18,1588082156028,1,1,6,'70b3d5f830000a68',2,100,2,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-28 19:25:56','2020-04-28 19:30:00'),
(19,1588229688805,1,1,6,'70b3d5f830000a68',2,200,2,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-30 12:24:50','2020-04-30 12:30:00'),
(20,1588231499381,1,1,6,'70b3d5f830000a68',2,100,5,0,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0006','2020-04-30 12:55:01','2020-04-30 13:00:01'),
(21,1589554744474,5,11,12,'70b3d5f83000157b',1,300,5,25,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,19,3,'HAN0012','2020-05-15 20:29:11','2020-05-15 20:30:11'),
(22,1589554886283,5,11,12,'70b3d5f83000157b',1,330,2,25,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,19,3,'HAN0012','2020-05-15 20:31:33','2020-05-15 20:35:00'),
(23,1589555706921,5,11,12,'70b3d5f83000157b',1,200,5,0,50,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,19,3,'HAN0012','2020-05-15 20:45:14','2020-05-15 20:50:00'),
(24,1590572161552,5,11,15,'70b3d5f830004fb5',2,200,2,10,0,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-05-27 15:06:04','2020-05-27 15:10:00'),
(25,1590734111013,5,11,15,'70b3d5f830004fb5',2,300,3,0,50,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-05-29 12:05:15','2020-05-29 12:10:00'),
(26,1590736440423,5,11,15,'70b3d5f830004fb5',2,300,3,0,50,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-05-29 12:44:04','2020-05-29 12:48:01'),
(27,1590738349711,5,11,15,'70b3d5f830004fb5',2,600,3,0,50,'web','Cash',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-05-29 13:15:54','2020-05-29 13:25:00'),
(29,0,5,11,15,'70b3d5f830004fb5',2,300,0,10,0,'web','Online',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-01 14:41:27','2020-06-01 14:41:27'),
(30,0,5,11,15,'70b3d5f830004fb5',2,300,0,10,0,'web','Online',0,'order_ExJNepSgkcgiE7',NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-01 15:01:36','2020-06-01 15:01:36'),
(31,0,5,11,15,'70b3d5f830004fb5',2,550,0,10,0,'web','Online',0,'order_Ey49aT45b1ebJK',NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-03 12:47:20','2020-06-03 12:47:20'),
(32,0,5,11,15,'70b3d5f830004fb5',2,500,0,10,0,'web','Online',1,'order_EyUzp9uXHCF2Y8','pay_EyV0iQAq5jbY3h','bb68d77f48f27ad1c90df76ef6f0838e35d0a933cbc3781eb06e5ff67d626aad',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 15:02:55','2020-06-04 15:02:55'),
(33,0,5,11,15,'70b3d5f830004fb5',2,600,0,10,0,'web','Online',1,'order_EyWRbAvK1kElFw','pay_EyWRn8kmYE9bGg','23e3f0fb67a18e2d00cc849a22072c5c1c9fd2ee7cb43d891fe8a2eb25e1762f',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 16:27:54','2020-06-04 16:27:54'),
(34,1591268462855,5,11,15,'70b3d5f830004fb5',2,700,5,10,0,'web','Online',1,'order_EyWUU8x2Fz5nT5','pay_EyWUdAlCcZDMvQ','9d61f64376c165a7d974fe44bae8a67aaaccc3c95b875ed2341532a6e054ba6d',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 16:30:38','2020-06-04 16:44:00'),
(35,1591270056394,5,11,15,'70b3d5f830004fb5',2,300,2,10,0,'web','Online',1,'order_EyWwJn2QJilbCV','pay_EyWwlIs8ZBUNce','361ed721f2daf6d19699edda5af371c77230db4e294217209ed98dbb5c6f0c34',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 16:56:59','2020-06-04 17:00:00'),
(36,0,5,11,15,'70b3d5f830004fb5',2,700,0,0,0,'web','Online',0,'order_EyX1Tx8HQHwAKU',NULL,NULL,NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 17:01:53','2020-06-04 17:01:53'),
(37,1591270375600,5,11,15,'70b3d5f830004fb5',2,700,2,0,0,'web','Online',1,'order_EyX21cLRjdcBMl','pay_EyX28ntzdpKz5N','fd75431c28b52371e81ef6da5bc59da7f62d1aea3bd574bbd03acc6113f3d486',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 17:02:24','2020-06-04 17:04:08'),
(38,1591270610754,5,11,15,'70b3d5f830004fb5',2,600,3,0,0,'web','Online',1,'order_EyX6UMvwQZKdio','pay_EyX6bThz9inLb4','e382c6a1b3b4368af566e4a477ba5ed7a82858f5df8a2ecb945fce7a981fe788',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 17:06:37','2020-06-04 17:08:00'),
(39,1591270631253,5,11,15,'70b3d5f830004fb5',2,500,2,0,0,'web','Online',1,'order_EyX6pjSrmOSqxB','pay_EyX6xJ8O3b19Ay','dec9d2eb39f3f686dcada5635ea98ad35478b16731ba50f27110438452f39bc1',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 17:06:58','2020-06-04 17:08:08'),
(40,1591271207714,5,11,15,'70b3d5f830004fb5',2,250,5,0,0,'web','Online',1,'order_EyXGz1XA9FZlEr','pay_EyXH7CO9hSMhtL','a3310f4f90d499f1464f5907a93d8b973e6a43d8d8992af210a777a4cc61a151',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 17:16:34','2020-06-05 12:04:03'),
(41,1591271237960,5,11,15,'70b3d5f830004fb5',2,350,5,0,0,'web','Online',1,'order_EyXHSjX9HAP89k','pay_EyXHbB59Ve5p3t','8a639413e4f037e5b1543bd3e0b7e6464b918115ae1490fb99b3b95f7e031493',NULL,NULL,NULL,NULL,1,1,'HAN0014','2020-06-04 17:17:01','2020-06-05 12:04:15'),
(42,1591339026687,5,11,15,'70b3d5f830004fb5',2,200,5,0,0,'web','Online',1,'order_EyqWsmKLViYMm9','pay_EyqX5ZIjNyjOZc','cc8cc432614f1e5efd60dabb32cd6d392a9e8242d8458d0855e87ed6d1551ea5',NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-06-05 12:06:48','2020-06-05 12:34:00'),
(43,1591340881770,5,11,15,'70b3d5f830004fb5',2,350,3,0,0,'web','Online',3,'order_Eyr3XdpYXSBKsQ','pay_Eyr3kr9iV7ey3b','9390f75071bbd75668a4e7600f6f6a87786d6958997f867dc3f23f37fc28e7c4',NULL,'rfnd_Eyr89Jxb6ap0as','processed','com.hanbit.PAYGTL_LORA_BLE.response.vo.RazorPayResponseVO@551f02ac',18,2,'HAN0014','2020-06-05 12:37:43','2020-06-05 12:42:00'),
(44,1591341530825,5,11,15,'70b3d5f830004fb5',2,360,2,0,50,'web','Online',1,'order_EyrF0iC6VEzV7j','pay_EyrFArcjwWEu7n','09bd14eab054a79840fb0e1fe9625f5056c969712ab8b39e3e5acf9abbdba269',NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-06-05 12:48:34','2020-06-05 12:52:03'),
(45,1591342299638,5,11,15,'70b3d5f830004fb5',2,300,2,0,50,'web','Online',1,'order_EyrSaYCBmX6Ngs','pay_EyrShvAassmmyD','8900df9a155c6270defe1f030de8b2e96b293a7c0b3aad825ab732e86f95aceb',NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-06-05 13:01:26','2020-06-05 13:14:02'),
(46,1591343073934,5,11,15,'70b3d5f830004fb5',2,450,2,0,50,'web','Online',1,'order_EyrgCOxpe8fOel','pay_EyrgJkG1oUReDZ','8faf76096f07bb4e6fa87b67441d43738d3340a5fb8c2a4ae1c262e0ea005a31',NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-06-05 13:14:19','2020-06-05 13:16:00'),
(47,1591343195030,5,11,15,'70b3d5f830004fb5',2,900,5,0,50,'web','Online',3,'order_EyriNYUh81mR1K','pay_EyriTpy5OqXQ77','3588c66fecc76be241e4865881c4a0814f179e24edfca28fad3afcc3a83ef860',NULL,'rfnd_EyrkAkRUSqHwGK','processed','{\"id\":\"rfnd_EyrkAkRUSqHwGK\",\"entity\":\"refund\",\"amount\":90000,\"currency\":\"INR\",\"payment_id\":\"pay_EyriTpy5OqXQ77\",\"notes\":[],\"receipt\":null,\"acquirer_data\":{\"arn\":null},\"created_at\":1591343286,\"status\":\"processed\",\"speed_processed\":\"normal\",\"speed_requested\":\"normal\"}',18,2,'HAN0014','2020-06-05 13:16:23','2020-06-05 13:18:00'),
(48,0,5,11,15,'70b3d5f830004fb5',2,650,0,0,50,'web','Online',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-06-05 15:50:18','2020-06-05 15:50:18'),
(49,0,5,11,15,'70b3d5f830004fb5',2,650,0,0,50,'web','Online',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-06-05 15:51:41','2020-06-05 15:51:41'),
(50,0,5,11,15,'70b3d5f830004fb5',2,650,0,0,50,'web','Online',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-06-05 15:54:08','2020-06-05 15:54:08'),
(51,1591353223299,5,11,15,'70b3d5f830004fb5',2,650,3,0,50,'web','Online',3,'order_EyuYiwZVGyd3xx','pay_EyuZ1MeWjt15sP','43f6dbbb8f963949bc30cac8c2efc03f2e588375ae74312eee78e858789bc06b',NULL,'rfnd_EyubeUOMc7VQfI','processed','{\"id\":\"rfnd_EyubeUOMc7VQfI\",\"entity\":\"refund\",\"amount\":65000,\"currency\":\"INR\",\"payment_id\":\"pay_EyuZ1MeWjt15sP\",\"notes\":[],\"receipt\":null,\"acquirer_data\":{\"arn\":null},\"created_at\":1591353367,\"status\":\"processed\",\"speed_processed\":\"normal\",\"speed_requested\":\"normal\"}',18,2,'HAN0014','2020-06-05 16:03:20','2020-06-05 16:06:00'),
(52,1591354135629,5,11,15,'70b3d5f830004fb5',2,500,2,0,0,'web','Online',1,'order_EyuowyRemKydQ8','pay_Eyup5nKuxGRwVG','11e7035ed773bd899a27b6685785f8cb71c796c6edae9f3ec7d3c0ddba3696f1',NULL,NULL,NULL,NULL,18,2,'HAN0014','2020-06-05 16:18:41','2020-06-05 16:22:00');

/*Table structure for table `updaterequestcustomermeterdetails` */

DROP TABLE IF EXISTS `updaterequestcustomermeterdetails`;

CREATE TABLE `updaterequestcustomermeterdetails` (
  `RequestID` bigint(255) NOT NULL AUTO_INCREMENT,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `CRNNumber` varchar(100) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

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
(14,'Testingtesting','testing','WV1UipqkSze2zl0HHoY+aQ==',2,1,4,10,0,NULL,1,1,'2020-04-28 22:47:57','2020-04-28 22:47:57'),
(15,'Amanora','Amanora testing','cvp/LzpadrQT+2k0WDjyOQ==',3,1,1,1,6,'HAN0006',1,1,'2020-04-30 10:32:37','2020-05-10 01:35:35'),
(17,'VIayBharbhaskarnimmagadda','VIjay Bhaskar','NwCfJchKOl2wGzmihPkCvA==',3,1,4,10,11,'HAN0011',1,1,'2020-04-30 13:22:37','2020-04-30 13:22:37'),
(18,'Finaltestingfinaltest','finaltest','cvp/LzpadrQT+2k0WDjyOQ==',2,1,5,11,0,'NULL',1,1,'2020-05-15 17:38:46','2020-05-15 17:38:46'),
(19,'HAN0012','K VIMAL KUMAR','cvp/LzpadrQT+2k0WDjyOQ==',3,1,5,11,12,'HAN0012',18,2,'2020-05-15 17:41:42','2020-05-15 17:41:42'),
(20,'HAN0013','Phani Varanasi','5P+Phux2WXyFrrD01hRAzg==',3,1,5,11,14,'HAN0013',1,1,'2020-05-16 13:01:17','2020-05-16 13:01:17'),
(21,'HAN0014','Mamatha Hanbit','wOvU8V5BD6MW3jXLAbWqrg==',3,1,5,11,15,'HAN0014',1,1,'2020-05-27 13:23:34','2020-05-27 13:23:34');

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
  `VacationID` bigint(20) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

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
(11,1588487901118,1,1,6,'70b3d5f830000a68','deletetest','2020-05-03 12:30:00','2020-05-03 12:50:00',5,'web','HAN0006','add','2020-05-03 12:08:21','2020-05-03 12:10:00'),
(12,1589564844140,5,11,12,'70b3d5f83000157b','finaltesting','2020-05-15 23:20:00','2020-05-15 23:25:00',5,'web','HAN0012','add','2020-05-15 23:17:31','2020-05-15 23:20:00'),
(13,1589565077586,5,11,12,'70b3d5f83000157b','finaltest','2020-05-15 23:25:00','2020-05-15 23:30:00',5,'web','HAN0012','add','2020-05-15 23:21:24','2020-05-15 23:21:24'),
(14,1589566064799,5,11,12,'70b3d5f83000157b','finals','2020-05-15 23:40:00','2020-05-15 23:45:00',5,'web','HAN0012','add','2020-05-15 23:37:52','2020-05-15 23:40:00'),
(15,1589566422672,5,11,12,'70b3d5f83000157b','demo','2020-05-15 23:46:00','2020-05-15 23:50:00',5,'web','HAN0012','add','2020-05-15 23:43:50','2020-05-15 23:45:00'),
(16,1589646174760,5,11,14,'70b3d5f830004fb4','test','2020-05-16 22:05:00','2020-05-16 22:10:00',2,'web','HAN0013','add','2020-05-16 21:53:01','2020-05-16 21:55:00'),
(17,1589647335610,5,11,14,'70b3d5f830004fb4','test2','2020-05-16 22:30:00','2020-05-16 22:45:00',5,'web','HAN0013','add','2020-05-16 22:12:22','2020-05-16 22:15:00'),
(18,1589647812466,5,11,14,'70b3d5f830004fb4','test3','2020-05-16 22:55:00','2020-05-16 22:59:00',5,'web','HAN0013','add','2020-05-16 22:20:18','2020-05-16 22:25:00'),
(19,1589648286350,5,11,14,'70b3d5f830004fb4','test4','2020-05-16 22:50:00','2020-05-16 22:55:00',5,'web','HAN0013','add','2020-05-16 22:29:59','2020-05-16 22:30:00'),
(20,1589648286355,5,11,14,'70b3d5f830004fb4','test5','2020-05-17 15:15:00','2020-05-17 15:30:59',2,'web','HAN0013','add','2020-05-17 14:43:37','2020-05-17 14:43:39'),
(21,1589709236164,5,11,14,'70b3d5f830004fb4','testing1','2020-05-17 15:40:00','2020-05-17 15:50:00',2,'web','HAN0013','add','2020-05-17 15:23:56','2020-05-17 15:30:00'),
(22,1589710864860,5,11,14,'70b3d5f830004fb4','newtest','2020-05-17 16:10:00','2020-05-17 16:30:00',3,'web','HAN0013','add','2020-05-17 15:51:04','2020-05-17 15:55:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
