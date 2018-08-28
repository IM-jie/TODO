/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost
 Source Database       : mytodo

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : utf-8

 Date: 08/28/2018 09:08:48 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_task_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_task_record`;
CREATE TABLE `t_task_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `record_id` varchar(45) DEFAULT NULL,
  `task_id` varchar(45) DEFAULT NULL,
  `operate_type` tinyint(4) DEFAULT NULL,
  `operate` varchar(200) DEFAULT NULL,
  `operator` varchar(45) DEFAULT NULL,
  `operate_time` timestamp NULL DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
