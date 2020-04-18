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
  `active` tinyint(2) NOT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`AlertID`),
  KEY `AlertID` (`AlertID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 MAX_ROWS=1;

/*Data for the table `alertsettings` */

insert  into `alertsettings`(`AlertID`,`NoAMRInterval`,`LowBatteryVoltage`,`TimeOut`,`active`,`RegisteredDate`,`ModifiedDate`) values 
(1,1442,3.35,330,1,'2020-04-07 02:20:35','2020-04-07 02:30:33');

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
  `AlarmCredit` varchar(40) NOT NULL,
  `EmergencyCredit` varchar(30) NOT NULL,
  `MeterType` int(255) NOT NULL,
  `SolonideStatus` tinyint(2) DEFAULT NULL,
  `CreditStatus` tinyint(2) DEFAULT NULL,
  `TamperDetect` tinyint(2) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `IoTTimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `balancelog` */

insert  into `balancelog`(`ReadingID`,`MeterID`,`Reading`,`Balance`,`CommunityID`,`BlockID`,`CustomerID`,`BatteryVoltage`,`TariffAmount`,`AlarmCredit`,`EmergencyCredit`,`MeterType`,`SolonideStatus`,`CreditStatus`,`TamperDetect`,`LowBattery`,`IoTTimeStamp`,`LogDate`) values 
(1,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0','0.0',3,1,0,0,0,'2020-04-10 20:50:11','2020-04-13 12:48:27'),
(2,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0','0.0',3,1,0,0,0,'2020-04-13 13:50:11','2020-04-13 13:22:41'),
(3,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0','0.0',3,1,0,0,0,'2020-04-13 13:52:11','2020-04-13 13:23:54'),
(4,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0','0.0',3,1,0,0,0,'2020-04-13 14:00:11','2020-04-13 13:25:40'),
(5,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0','0.0',3,1,0,0,0,'2020-04-14 20:50:56','2020-04-14 21:22:56'),
(6,'70b3d5f83000157a',800.00,170.00,2,3,2,2.17,50.00,'0','0.0',3,1,0,0,0,'2020-04-15 15:20:35','2020-04-15 15:23:09'),
(7,'70b3d5f83000157a',816.00,20.00,2,3,2,2.18,50.00,'0','0.0',3,1,0,0,0,'2020-04-15 15:24:21','2020-04-15 15:26:08'),
(8,'70b3d5f83000157a',831.00,220.00,2,3,2,2.18,50.00,'0','200.0',3,0,0,0,0,'2020-04-15 15:50:27','2020-04-15 16:24:30'),
(9,'70b3d5f83000157a',831.00,220.00,2,3,2,2.18,50.00,'0','200.0',3,0,0,0,0,'2020-04-15 16:32:15','2020-04-15 16:52:46'),
(10,'70b3d5f83000157a',831.00,220.00,2,3,2,2.17,50.00,'0','200.0',3,1,0,0,0,'2020-04-15 17:51:46','2020-04-15 17:51:57'),
(11,'70b3d5f83000157a',0.00,220.00,2,3,2,2.18,50.00,'0','200.0',3,1,0,0,0,'2020-04-15 17:57:10','2020-04-15 17:58:25'),
(12,'70b3d5f83000157a',831.00,220.00,2,3,2,2.17,50.00,'0','200.0',3,1,0,0,0,'2020-04-15 19:02:57','2020-04-15 19:16:48'),
(13,'70b3d5f83000157a',834.00,70.00,2,3,2,2.17,50.00,'0','200.0',3,0,0,0,0,'2020-04-15 19:26:52','2020-04-15 19:27:48'),
(14,'70b3d5f83000157a',834.00,70.00,2,3,2,2.17,50.00,'0','200.0',3,0,0,0,0,'2020-04-15 19:31:08','2020-04-15 19:35:42'),
(15,'70b3d5f83000157a',834.00,70.00,2,3,2,2.18,50.00,'0','200.0',3,0,0,0,0,'2020-04-15 19:39:50','2020-04-15 20:16:10'),
(16,'70b3d5f83000157a',852.00,70.00,2,3,2,2.18,50.00,'0','200.0',3,1,0,0,0,'2020-04-15 20:38:52','2020-04-15 20:39:52'),
(17,'70b3d5f83000157a',164.00,900.00,2,3,2,2.18,1000.00,'0','0.0',3,1,0,0,0,'2020-04-16 10:36:55','2020-04-16 12:07:09'),
(18,'70b3d5f830000a68',4.00,900.00,1,1,6,2.18,1000.00,'0','0.0',3,1,0,0,0,'2020-04-16 15:44:10','2020-04-16 16:00:45'),
(19,'70b3d5f830000a68',4.00,1250.00,1,1,6,2.18,10.00,'0','0.0',3,1,0,0,0,'2020-04-16 16:37:37','2020-04-16 16:38:39'),
(20,'70b3d5f830000a68',4.00,1250.00,1,1,6,2.18,10.00,'0','0.0',3,1,0,0,0,'2020-04-16 16:42:38','2020-04-16 17:00:47'),
(21,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0','0.0',3,1,0,0,0,'2020-04-17 02:51:29','2020-04-17 16:05:18');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `block` */

insert  into `block`(`BlockID`,`BlockName`,`Location`,`MobileNumber`,`Email`,`CommunityID`,`CreatedByID`,`CreatedByRoleID`,`CreatedDate`,`ModifiedDate`) values 
(1,'DemoBlock','Bangalore','9876543210','Demoblock@gmail.com',1,1,1,'2020-04-05 01:53:16','2020-04-05 01:53:16'),
(3,'Demoblock1','Hyderabad','9876543211','Demoblock1@gmail.com',2,1,1,'2020-04-05 21:27:08','2020-04-05 21:27:08'),
(4,'Demoblock2','Mumbai','9876543212','Demoblock2@gmail.com',1,1,1,'2020-04-06 15:39:11','2020-04-06 15:39:14'),
(5,'Demoblock3','Warangal','9876543213','Demoblock3@gmail.com',2,1,1,'2020-04-06 15:39:46','2020-04-06 15:39:48'),
(8,'Demoblock5','Secunderabad','9000941923','bhrt@gmail.com',2,1,1,'2020-04-11 23:54:59','2020-04-11 23:54:59'),
(9,'Demoblock6','Maredpally','2345678901','Demoblock6@gmail.com',3,1,1,'2020-04-12 15:32:00','2020-04-12 15:32:00');

/*Table structure for table `command` */

DROP TABLE IF EXISTS `command`;

CREATE TABLE `command` (
  `TransactionID` bigint(255) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` varchar(100) NOT NULL,
  `CustomerID` int(255) NOT NULL,
  `MeterID` varchar(80) NOT NULL,
  `CommandType` int(255) NOT NULL,
  `Status` int(245) DEFAULT NULL,
  `CreatedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `command` */

insert  into `command`(`TransactionID`,`TataReferenceNumber`,`CustomerID`,`MeterID`,`CommandType`,`Status`,`CreatedDate`,`ModifiedDate`) values 
(1,'C-1',2,'70b3d5f83000157a',6,2,'2020-04-15 16:48:38','2020-04-15 16:48:38'),
(2,'C-2',2,'70b3d5f83000157a',6,2,'2020-04-15 17:50:05','2020-04-15 17:50:05'),
(3,'C-3',2,'70b3d5f83000157a',6,0,'2020-04-15 19:24:26','2020-04-15 19:24:26');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `community` */

insert  into `community`(`CommunityID`,`CommunityName`,`Email`,`MobileNumber`,`Address`,`CreatedDate`,`ModifiedDate`) values 
(1,'TestCommunity','testcommunity@gmail.com','9876543210','Bangalore','2020-04-04 23:13:10','2020-04-04 23:13:10'),
(2,'TestCommunity1','testcommunity1@gmail.com','9876543211','Hyderabaaaaad','2020-04-05 19:30:44','2020-04-05 19:49:04'),
(3,'TestCommunity2','testcommunity2@gmail.com','1234567890','Secunderabad','2020-04-12 15:30:26',NULL);

/*Table structure for table `customerdeletemeter` */

DROP TABLE IF EXISTS `customerdeletemeter`;

CREATE TABLE `customerdeletemeter` (
  `SerialNumber` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(255) NOT NULL,
  `MeterID` varchar(40) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`CustomerID`),
  KEY `SerialNumber` (`SerialNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `customerdeletemeter` */

insert  into `customerdeletemeter`(`SerialNumber`,`CustomerID`,`MeterID`,`Date`) values 
(2,3,'1002','2020-04-09 02:16:23'),
(1,6,'5002','2020-04-06 22:41:45');

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
  `DefaultReading` int(11) DEFAULT NULL,
  `TariffID` int(11) NOT NULL,
  `ActiveStatus` tinyint(4) DEFAULT NULL,
  `CRNNumber` varchar(50) DEFAULT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `customermeterdetails` */

insert  into `customermeterdetails`(`CustomerID`,`CommunityID`,`BlockID`,`HouseNumber`,`FirstName`,`LastName`,`Email`,`MobileNumber`,`MeterID`,`MeterSerialNumber`,`DefaultReading`,`TariffID`,`ActiveStatus`,`CRNNumber`,`CreatedByID`,`CreatedByRoleID`,`RegistrationDate`,`ModifiedDate`) values 
(1,1,1,'101','Vimal','Kumar','kvk9889@gmail.com','8498890000','70b3d5f830004f56','1234',0,1,1,NULL,2,2,'2020-04-05 01:58:12','2020-04-05 01:58:12'),
(2,2,3,'301','Vml','Kvk','vml@gmail.com','9999999999','70b3d5f83000157a','3456',0,2,1,NULL,2,2,'2020-04-06 15:33:13','2020-04-06 15:33:17'),
(4,2,3,'302','Bharatttttt','sriram','bharat@gmail.com','7777777777','3002','3457',0,1,1,NULL,1,1,'2020-04-06 15:36:17','2020-04-09 19:58:42'),
(5,2,5,'501','Demo','Customer','democustomer@gmail.com','6666666666','5001','5678',0,2,1,NULL,6,2,'2020-04-06 22:31:15','2020-04-06 22:39:04'),
(6,1,1,'102','Amanora','testing','Amanora@gmail.com','5555555555','70b3d5f830000a68','4567',0,2,1,NULL,1,1,'2020-04-16 15:43:25','2020-04-16 15:43:28'),
(9,2,5,'502','Demo502','Customer502','democustomer502@gmail.com','9876598765','5002','6789',0,2,1,'han202009',1,1,'2020-04-17 15:29:25','2020-04-17 15:29:25');

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
  `AlarmCredit` varchar(40) NOT NULL,
  `EmergencyCredit` varchar(30) NOT NULL,
  `MeterType` int(255) NOT NULL,
  `SolonideStatus` tinyint(2) DEFAULT NULL,
  `CreditStatus` tinyint(2) DEFAULT NULL,
  `TamperDetect` tinyint(2) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `IoTTimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `displaybalancelog` */

insert  into `displaybalancelog`(`ReadingID`,`MainBalanceLogID`,`MeterID`,`Reading`,`Balance`,`CommunityID`,`BlockID`,`CustomerID`,`BatteryVoltage`,`TariffAmount`,`AlarmCredit`,`EmergencyCredit`,`MeterType`,`SolonideStatus`,`CreditStatus`,`TamperDetect`,`LowBattery`,`IoTTimeStamp`,`LogDate`) values 
(1,21,'70b3d5f830004f56',0.00,0.00,1,1,1,3.59,0.00,'0','0.0',3,1,0,0,0,'2020-04-17 02:51:29','2020-04-17 16:05:18'),
(2,17,'70b3d5f83000157a',164.00,900.00,2,3,2,2.18,1000.00,'0','0.0',3,1,0,0,0,'2020-04-16 10:36:55','2020-04-16 12:07:09'),
(3,20,'70b3d5f830000a68',4.00,1250.00,1,1,6,2.18,10.00,'0','0.0',3,1,0,0,0,'2020-04-16 16:42:38','2020-04-16 17:00:47');

/*Table structure for table `holidaylist` */

DROP TABLE IF EXISTS `holidaylist`;

CREATE TABLE `holidaylist` (
  `HolidayID` int(11) DEFAULT NULL,
  `HolidayName` varchar(50) DEFAULT NULL,
  `HolidayDate` varchar(50) DEFAULT NULL,
  `communityID` int(11) DEFAULT NULL,
  `LastModified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `holidaylist` */

insert  into `holidaylist`(`HolidayID`,`HolidayName`,`HolidayDate`,`communityID`,`LastModified`) values 
(61,'holiday',NULL,NULL,NULL);

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
  PRIMARY KEY (`TariffID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tariff` */

insert  into `tariff`(`TariffID`,`Tariff`,`TariffName`,`EmergencyCredit`,`AlarmCredit`,`FixedCharges`,`RegisteredDate`) values 
(1,30,'Tariff1',10,15,25,'2020-04-04 23:06:14'),
(2,50,'Tariff2',20,10,10,'2020-04-07 00:13:09');

/*Table structure for table `testtable` */

DROP TABLE IF EXISTS `testtable`;

CREATE TABLE `testtable` (
  `HolidayID` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dt` datetime NOT NULL,
  `numb` int(11) DEFAULT NULL,
  PRIMARY KEY (`HolidayID`),
  UNIQUE KEY `numb` (`numb`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

/*Data for the table `testtable` */

insert  into `testtable`(`HolidayID`,`date`,`dt`,`numb`) values 
(64,'2020-04-09 00:09:22','2020-04-09 00:09:22',101),
(66,'2020-04-09 00:19:19','2020-04-09 00:19:19',301),
(67,'2020-04-11 16:22:35','2020-04-14 22:30:58',NULL);

/*Table structure for table `topup` */

DROP TABLE IF EXISTS `topup`;

CREATE TABLE `topup` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` varchar(100) NOT NULL,
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` bigint(20) NOT NULL,
  `MeterID` varchar(50) NOT NULL,
  `TariffID` int(11) NOT NULL,
  `Amount` decimal(10,0) NOT NULL,
  `Status` tinyint(4) NOT NULL,
  `ModeOfPayment` varchar(50) NOT NULL,
  `PaymentStatus` tinyint(4) NOT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` int(11) NOT NULL,
  `CardNumber` varchar(20) DEFAULT NULL,
  `CardType` varchar(20) DEFAULT NULL,
  `TransactionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `AcknowledgeDate` datetime NOT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `topup` */

insert  into `topup`(`TransactionID`,`TataReferenceNumber`,`CommunityID`,`BlockID`,`CustomerID`,`MeterID`,`TariffID`,`Amount`,`Status`,`ModeOfPayment`,`PaymentStatus`,`CreatedByID`,`CreatedByRoleID`,`CardNumber`,`CardType`,`TransactionDate`,`AcknowledgeDate`) values 
(1,'T-1',2,3,2,'70b3d5f83000157a',2,400,2,'cash',0,1,1,NULL,NULL,'2020-04-15 19:42:21','2020-04-15 19:42:21'),
(2,'T-2',2,3,2,'70b3d5f83000157a',2,400,2,'cash',0,1,1,NULL,NULL,'2020-04-15 19:51:54','2020-04-15 19:51:54'),
(3,'T-3',2,3,2,'70b3d5f83000157a',2,400,2,'cash',0,1,1,NULL,NULL,'2020-04-15 20:30:15','2020-04-15 20:30:15'),
(4,'T-4',2,3,2,'70b3d5f83000157a',2,400,2,'cash',0,1,1,NULL,NULL,'2020-04-15 22:09:03','2020-04-15 22:09:03'),
(5,'T-5',1,1,6,'70b3d5f830000a68',2,400,2,'cash',0,1,1,NULL,NULL,'2020-04-16 16:05:14','2020-04-16 16:05:14'),
(6,'T-6',1,1,6,'70b3d5f830000a68',2,400,2,'cash',0,1,1,NULL,NULL,'2020-04-16 16:21:48','2020-04-16 16:21:48'),
(7,'T-7',1,1,6,'70b3d5f830000a68',2,350,2,'cash',0,1,1,NULL,NULL,'2020-04-16 16:35:59','2020-04-17 00:54:48');

/*Table structure for table `updaterequestcustomermeterdetails` */

DROP TABLE IF EXISTS `updaterequestcustomermeterdetails`;

CREATE TABLE `updaterequestcustomermeterdetails` (
  `RequestID` int(255) NOT NULL AUTO_INCREMENT,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
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
  `CreatedByID` bigint(20) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CommunityID` (`CommunityID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`ID`,`UserID`,`UserName`,`UserPassword`,`RoleID`,`ActiveStatus`,`CommunityID`,`BlockID`,`CustomerID`,`CreatedByID`,`CreatedByRoleID`,`RegisteredDate`,`ModifiedDate`) values 
(1,'Superadmin','Hanbit','cvp/LzpadrQT+2k0WDjyOQ==',1,1,0,0,0,0,0,'2020-04-04 17:36:33','2020-04-04 17:36:33'),
(2,'Admin','Kvk','cvp/LzpadrQT+2k0WDjyOQ==',2,1,1,1,0,1,1,'2020-04-05 00:08:42','2020-04-05 00:08:42'),
(3,'Vimal','Vimal Kumar','cvp/LzpadrQT+2k0WDjyOQ==',3,1,1,1,1,2,2,'2020-04-05 00:09:19','2020-04-05 00:09:19'),
(4,'Superadminsupervisor','HanbitSuperadminsupervisor','cvp/LzpadrQT+2k0WDjyOQ==',4,1,0,0,0,1,1,'2020-04-05 01:41:13','2020-04-05 01:41:13'),
(5,'Adminsupervisor','KvkAdminsupervisor','cvp/LzpadrQT+2k0WDjyOQ==',5,1,1,1,0,1,1,'2020-04-05 01:42:05','2020-04-05 01:42:05'),
(6,'Admin1','Demoblock3','cvp/LzpadrQT+2k0WDjyOQ==',2,1,2,5,0,1,1,'2020-04-06 22:28:23','2020-04-07 13:35:23'),
(7,'Demoblock1','Sri babu','qoH4Tc1M0Oh+Bc7LH4LtMA==',5,1,2,3,0,1,1,'2020-04-08 00:51:26','2020-04-08 01:23:43'),
(9,'Demoblock2','Demoblock2','cvp/LzpadrQT+2k0WDjyOQ==',2,1,2,3,0,1,1,'2020-04-09 02:58:48','2020-04-09 03:00:02'),
(10,'bhrtsrm','Bharat Sriram','cvp/LzpadrQT+2k0WDjyOQ==',3,1,2,3,4,9,2,'2020-04-09 05:00:11','2020-04-09 05:00:14'),
(11,'TestCommunity1Demoblock5','Demoblock5','wih1Khk5v8/1bgPmA8lVuA==',2,1,2,8,0,1,1,'2020-04-11 23:54:59','2020-04-11 23:54:59'),
(12,'TestCommunity2Demoblock6','Demoblock6','kVA6fP+zltT3x1e91Xa5lA==',2,1,3,9,0,1,1,'2020-04-12 15:32:00','2020-04-12 15:32:00'),
(13,'De02Cu02democustomer502','Demo502 Customer502','Mb5vqPCVR48upDxrShoKjU/uF9DJyKSl9czmqz6hS6Y=',3,1,2,5,9,1,1,'2020-04-17 15:29:26','2020-04-17 15:29:26');

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
