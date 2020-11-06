/*
Navicat MySQL Data Transfer

Source Server         : MysqlConnect
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : hqydb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-11-03 22:55:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_paramattrdefinition`
-- ----------------------------
DROP TABLE IF EXISTS `t_paramattrdefinition`;
CREATE TABLE `t_paramattrdefinition` (
  `id` bigint(19) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(128) NOT NULL,
  `local_name` varchar(128) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `parent_id` bigint(19) NOT NULL,
  `datatype` varchar(50) DEFAULT NULL,
  `format` varchar(100) DEFAULT NULL,
  `fixed` bigint(1) DEFAULT NULL,
  `allowedvalues` varchar(100) DEFAULT NULL,
  `parametername` varchar(128) DEFAULT NULL,
  `defaultvalue` varchar(128) DEFAULT NULL,
  `nullable` bigint(1) DEFAULT NULL,
  `extendeddescription` varchar(4000) DEFAULT NULL,
  `display_order` bigint(19) DEFAULT NULL,
  `pasecurity` bigint(1) DEFAULT NULL,
  `curr_version` bigint(19) DEFAULT NULL,
  `userid` bigint(19) DEFAULT NULL,
  `removed` bigint(1) DEFAULT '0',
  `parameter_rootid` bigint(19) DEFAULT NULL,
  `maxv_alue` bigint(19) DEFAULT NULL,
  `min_value` bigint(19) DEFAULT NULL,
  `usercode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_paramattrdefinition
-- ----------------------------

-- ----------------------------
-- Table structure for `t_parameter`
-- ----------------------------
DROP TABLE IF EXISTS `t_parameter`;
CREATE TABLE `t_parameter` (
  `id` bigint(19) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `local_name` varchar(128) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL,
  `haschild` bigint(1) DEFAULT NULL,
  `extendeddescription` varchar(4000) DEFAULT NULL,
  `security` varchar(50) DEFAULT NULL,
  `userid` bigint(19) DEFAULT NULL,
  `operatortype` varchar(50) DEFAULT NULL,
  `autocache` bigint(1) DEFAULT NULL,
  `curr_version` bigint(19) DEFAULT NULL,
  `removed` bigint(1) DEFAULT NULL,
  `parameter_rootid` bigint(19) DEFAULT NULL,
  `standardcode` varchar(50) DEFAULT NULL,
  `sorttype` varchar(50) DEFAULT NULL,
  `usercode` varchar(50) DEFAULT NULL,
  `chinesedescription` varchar(2000) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `show_code` bigint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for `t_parameterattributevalue`
-- ----------------------------
DROP TABLE IF EXISTS `t_parameterattributevalue`;
CREATE TABLE `t_parameterattributevalue` (
  `id` bigint(19) NOT NULL,
  `attribute_name` varchar(128) DEFAULT NULL,
  `parent_id` bigint(19) DEFAULT NULL,
  `int_value` bigint(19) DEFAULT NULL,
  `string_value` varchar(1000) DEFAULT NULL,
  `long_value` bigint(19) DEFAULT NULL,
  `datetime_value` datetime DEFAULT NULL,
  `datetime_value2` datetime DEFAULT NULL,
  `boolean_value` bigint(1) DEFAULT NULL,
  `attribute_code` varchar(50) DEFAULT NULL,
  `data_type` varchar(50) DEFAULT NULL,
  `removed` bigint(1) DEFAULT '0',
  `curr_version` bigint(19) DEFAULT '0',
  `parameter_rootid` bigint(19) DEFAULT NULL,
  `double_value` bigint(19) DEFAULT NULL,
  `text_value` text,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parameterattributevalue
-- ----------------------------

-- ----------------------------
-- Table structure for `t_parametervalue`
-- ----------------------------
DROP TABLE IF EXISTS `t_parametervalue`;
CREATE TABLE `t_parametervalue` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `name` varchar(128) NOT NULL,
  `local_name` varchar(128) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `parent_id` bigint(19) NOT NULL,
  `extendeddescription` varchar(4000) DEFAULT NULL,
  `display_order` bigint(19) DEFAULT NULL,
  `standardcode` varchar(50) DEFAULT NULL,
  `userid` bigint(19) DEFAULT NULL,
  `curr_version` bigint(19) DEFAULT NULL,
  `security` bigint(1) DEFAULT NULL,
  `removed` bigint(1) DEFAULT '0',
  `parameter_rootid` bigint(19) DEFAULT NULL,
  `usercode` varchar(50) DEFAULT NULL,
  `chinesedescription` varchar(2000) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parametervalue
-- ----------------------------
