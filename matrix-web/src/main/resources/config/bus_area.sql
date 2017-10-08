/*
Navicat MySQL Data Transfer

Source Server         : mywork
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : matrixtest

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2017-01-04 01:00:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bus_area`
-- ----------------------------
DROP TABLE IF EXISTS `bus_area`;
CREATE TABLE `bus_area` (
  `area_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `area_name` varchar(29) DEFAULT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_area
-- ----------------------------
INSERT INTO `bus_area` VALUES ('1', '0', '湖南省');
INSERT INTO `bus_area` VALUES ('2', '0', '广州省');
INSERT INTO `bus_area` VALUES ('3', '1', '长沙市');
INSERT INTO `bus_area` VALUES ('4', '1', '郴州市');
INSERT INTO `bus_area` VALUES ('5', '4', '宜章县');
INSERT INTO `bus_area` VALUES ('6', '2', '东湾');
