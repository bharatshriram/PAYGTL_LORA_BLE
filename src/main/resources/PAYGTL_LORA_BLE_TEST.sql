/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.5.62 : Database - paygtl_lora_ble_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`paygtl_lora_ble_test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `paygtl_lora_ble_test`;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 MAX_ROWS=1;

/*Data for the table `alertsettings` */

insert  into `alertsettings`(`AlertID`,`NoAMRInterval`,`LowBatteryVoltage`,`TimeOut`,`PerUnitValue`,`ReconnectionCharges`,`RegisteredDate`,`ModifiedDate`) values 
(2,2880,3.00,1440,2.4,50,'2020-07-21 17:38:08','2020-07-21 17:38:08');

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
  `TamperTimeStamp` varchar(50) DEFAULT NULL,
  `DoorOpenTimeStamp` varchar(50) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `Vacation` tinyint(4) DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `Minutes` int(11) DEFAULT NULL,
  `IoTTimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=latin1;

/*Data for the table `balancelog` */

insert  into `balancelog`(`ReadingID`,`MeterID`,`Reading`,`Balance`,`CommunityID`,`BlockID`,`CustomerID`,`BatteryVoltage`,`TariffAmount`,`EmergencyCredit`,`MeterType`,`SolonideStatus`,`CreditStatus`,`TamperDetect`,`TamperTimeStamp`,`DoorOpenTimeStamp`,`LowBattery`,`Vacation`,`MeterSerialNumber`,`CRNNumber`,`Minutes`,`IoTTimeStamp`,`LogDate`) values 
(104,'70b3d5f830004fb9',3.00,90.00,6,12,17,3.59,10.00,'10.0',3,0,0,0,'','',0,0,'17221006','hanbit0013',0,'2020-07-21 17:38:59','2020-07-21 17:41:02');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `block` */

insert  into `block`(`BlockID`,`BlockName`,`Location`,`MobileNumber`,`Email`,`CommunityID`,`CreatedByID`,`CreatedByRoleID`,`CreatedDate`,`ModifiedDate`) values 
(12,'Ramya','ecil','8919069543','ramya.punnam@hanbitsolutions.com',6,24,1,'2020-07-21 17:03:16','2020-07-21 17:03:16');

/*Table structure for table `command` */

DROP TABLE IF EXISTS `command`;

CREATE TABLE `command` (
  `TransactionID` bigint(255) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` bigint(20) NOT NULL,
  `CustomerID` int(255) NOT NULL,
  `MeterID` varchar(80) NOT NULL,
  `CommandType` int(255) NOT NULL,
  `DataFrame` varchar(1000) DEFAULT NULL,
  `Value` double DEFAULT NULL,
  `Status` int(245) NOT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `CreatedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

/*Data for the table `command` */

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `community` */

insert  into `community`(`CommunityID`,`CommunityName`,`Email`,`MobileNumber`,`Address`,`CreatedDate`,`ModifiedDate`) values 
(6,'srilatha','srilatha.vakapalli@hanbitsolutions.com','9440208812','house no 11-2 malkajgiri','2020-07-21 17:02:02',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `customermeterdetails` */

insert  into `customermeterdetails`(`CustomerID`,`CommunityID`,`BlockID`,`HouseNumber`,`FirstName`,`LastName`,`Email`,`MobileNumber`,`MeterID`,`MeterSerialNumber`,`TariffID`,`ActiveStatus`,`CRNNumber`,`CreatedByID`,`CreatedByRoleID`,`RegistrationDate`,`ModifiedDate`) values 
(17,6,12,'9-2-123','sravan','malladi','sravan.malladi@hanbitsolutions.com','9963574377','70B3D5F830004FB9','17221006',4,1,'hanbit0013',24,1,'2020-07-21 17:13:14','2020-07-21 17:13:14');

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
  `TamperTimeStamp` varchar(50) DEFAULT NULL,
  `DoorOpenTimeStamp` varchar(50) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `Vacation` tinyint(4) DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CRNNumber` varchar(100) DEFAULT NULL,
  `Minutes` int(11) DEFAULT NULL,
  `IoTTimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `displaybalancelog` */

insert  into `displaybalancelog`(`ReadingID`,`MainBalanceLogID`,`MeterID`,`Reading`,`Balance`,`CommunityID`,`BlockID`,`CustomerID`,`BatteryVoltage`,`TariffAmount`,`EmergencyCredit`,`MeterType`,`SolonideStatus`,`CreditStatus`,`TamperDetect`,`TamperTimeStamp`,`DoorOpenTimeStamp`,`LowBattery`,`Vacation`,`MeterSerialNumber`,`CRNNumber`,`Minutes`,`IoTTimeStamp`,`LogDate`) values 
(8,104,'70b3d5f830004fb9',3.00,90.00,6,12,17,3.59,10.00,'10.0',3,0,0,0,'','',0,0,'17221006','hanbit0013',0,'2020-07-21 17:38:59','2020-07-21 17:41:02');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tariff` */

insert  into `tariff`(`TariffID`,`Tariff`,`TariffName`,`EmergencyCredit`,`AlarmCredit`,`FixedCharges`,`RegisteredDate`,`ModifiedDate`) values 
(4,10,'test',10,10,10,'2020-07-21 17:06:46','2020-07-21 17:09:46');

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

/*Table structure for table `topup` */

DROP TABLE IF EXISTS `topup`;

CREATE TABLE `topup` (
  `TransactionID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` bigint(20) unsigned NOT NULL DEFAULT '0',
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` bigint(20) NOT NULL,
  `MeterID` varchar(50) NOT NULL,
  `TariffID` int(11) NOT NULL,
  `Amount` decimal(10,0) NOT NULL,
  `Status` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `FixedCharges` int(10) unsigned DEFAULT '0',
  `ReconnectionCharges` int(10) unsigned DEFAULT '0',
  `Source` varchar(10) NOT NULL,
  `ModeOfPayment` varchar(50) NOT NULL,
  `PaymentStatus` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `RazorPayOrderID` varchar(50) DEFAULT NULL,
  `RazorPayPaymentID` varchar(50) DEFAULT NULL,
  `RazorPaySignature` varchar(5000) DEFAULT NULL,
  `ErrorResponse` varchar(10000) DEFAULT NULL,
  `RazorPayRefundID` varchar(50) DEFAULT NULL,
  `RazorPayRefundStatus` varchar(50) DEFAULT NULL,
  `RazorPayRefundEntity` varchar(10000) DEFAULT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` int(11) NOT NULL,
  `CRNNumber` varchar(100) NOT NULL,
  `TransactionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `AcknowledgeDate` datetime NOT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

/*Data for the table `topup` */

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`ID`,`UserID`,`UserName`,`UserPassword`,`RoleID`,`ActiveStatus`,`CommunityID`,`BlockID`,`CustomerID`,`CRNNumber`,`CreatedByID`,`CreatedByRoleID`,`RegisteredDate`,`ModifiedDate`) values 
(24,'Superadmin','Hanbit','cvp/LzpadrQT+2k0WDjyOQ==',1,1,0,0,0,NULL,0,0,'2020-07-21 16:46:15','2020-07-21 16:46:19'),
(25,'Ramya','Ramya','RyMlBC2WyJjG/Q+x0i4qzQ==',2,1,6,12,0,'NULL',24,1,'2020-07-21 17:03:16','2020-07-21 17:03:16'),
(26,'hanbit0013','sravan malladi','TkBeYuN/kHnjStv5hEzpZg==',3,1,6,12,17,'hanbit0013',24,1,'2020-07-21 17:13:14','2020-07-21 17:13:14'),
(27,'adminsupervisor','hanbit admin supervisor','MEVd1+d7s2DoZt8mgx+1kg==',5,1,6,12,0,NULL,24,1,'2020-07-21 17:53:42','2020-07-21 17:53:42');

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
(5,'AdminSupervisor'),
(6,'Support');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `vacation` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
