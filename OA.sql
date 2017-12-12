/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : OA

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 12/12/2017 16:28:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Permission
-- ----------------------------
DROP TABLE IF EXISTS `Permission`;
CREATE TABLE `Permission` (
  `UserID` int(10) NOT NULL,
  `Auth` varchar(20) NOT NULL DEFAULT 'user',
  KEY `Permission_User_UserID_fk` (`UserID`),
  CONSTRAINT `Permission_User_UserID_fk` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Permission
-- ----------------------------
BEGIN;
INSERT INTO `Permission` VALUES (2, 'user');
INSERT INTO `Permission` VALUES (1, 'admin');
INSERT INTO `Permission` VALUES (3, 'user');
INSERT INTO `Permission` VALUES (4, 'user');
INSERT INTO `Permission` VALUES (5, 'user');
COMMIT;

-- ----------------------------
-- Table structure for Project
-- ----------------------------
DROP TABLE IF EXISTS `Project`;
CREATE TABLE `Project` (
  `ProjectID` int(11) NOT NULL,
  `ProjectName` char(30) NOT NULL,
  PRIMARY KEY (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Project
-- ----------------------------
BEGIN;
INSERT INTO `Project` VALUES (1, 'oa');
INSERT INTO `Project` VALUES (2, 'edgebox');
INSERT INTO `Project` VALUES (3, 'ui');
COMMIT;

-- ----------------------------
-- Table structure for Task
-- ----------------------------
DROP TABLE IF EXISTS `Task`;
CREATE TABLE `Task` (
  `TaskID` int(11) NOT NULL,
  `TaskName` char(20) NOT NULL,
  PRIMARY KEY (`TaskID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Task
-- ----------------------------
BEGIN;
INSERT INTO `Task` VALUES (1, 'modifiy-bug');
INSERT INTO `Task` VALUES (2, 'coding');
INSERT INTO `Task` VALUES (3, 'learn');
COMMIT;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` char(20) NOT NULL,
  `Password` char(20) NOT NULL,
  `DisplayName` char(30) NOT NULL,
  `Status` smallint(6) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO `User` VALUES (1, 'xujin', '123456', '徐进', 1);
INSERT INTO `User` VALUES (2, 'johnny', 'jiangli', '蒋力', 1);
INSERT INTO `User` VALUES (3, 'liyuhui', '110', '李渝辉', 1);
INSERT INTO `User` VALUES (4, 'Kobe', '120', '许达', 1);
INSERT INTO `User` VALUES (5, 'nieyinghao', '119', '聂英豪', 1);
COMMIT;

-- ----------------------------
-- Table structure for Work
-- ----------------------------
DROP TABLE IF EXISTS `Work`;
CREATE TABLE `Work` (
  `WorkID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `ProjectID` int(11) NOT NULL,
  `TaskID` int(11) NOT NULL,
  `WeekId` mediumtext NOT NULL,
  `Stamp` mediumtext NOT NULL,
  `Hour` int(11) NOT NULL,
  `m_STATUS` tinyint(1) NOT NULL,
  PRIMARY KEY (`WorkID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Work
-- ----------------------------
BEGIN;
INSERT INTO `Work` VALUES (1, 1, 1, 1, '11111', '1', 1, 0);
INSERT INTO `Work` VALUES (2, 1, 1, 2, '22222', '2', 1, 0);
INSERT INTO `Work` VALUES (3, 1, 2, 1, '22222', '3', 1, 0);
INSERT INTO `Work` VALUES (4, 1, 3, 2, '22222', '2', 1, 0);
INSERT INTO `Work` VALUES (5, 2, 1, 1, '22222', '1', 1, 0);
INSERT INTO `Work` VALUES (6, 7, 1, 2, '22222', '1', 1, 0);
INSERT INTO `Work` VALUES (7, 6, 1, 2, '22222', '1', 1, 0);
INSERT INTO `Work` VALUES (8, 7, 1, 2, '22222', '1', 1, 0);
INSERT INTO `Work` VALUES (9, 7, 1, 2, '22222', '1', 1, 0);
INSERT INTO `Work` VALUES (10, 8, 1, 6, '22222', '1234', 10, 1);
INSERT INTO `Work` VALUES (14, 1, 4, 6, '22222', '89757', 1, -1);
INSERT INTO `Work` VALUES (15, 2, 4, 6, '22222', '66666', 1, 0);
INSERT INTO `Work` VALUES (16, 8, 2, 6, '22222', '1234', 10, 0);
INSERT INTO `Work` VALUES (17, 1, 1, 2, '1511712000000', '1511836200000', 2, 1);
INSERT INTO `Work` VALUES (18, 1, 2, 3, '1511712000000', '1512023400000', 1, 1);
INSERT INTO `Work` VALUES (19, 1, 3, 1, '1511712000000', '1511836200000', 4, 1);
INSERT INTO `Work` VALUES (20, 1, 2, 2, '1511712000000', '1512023400000', 3, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
