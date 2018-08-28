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

 Date: 08/28/2018 09:09:08 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_user_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_message`;
CREATE TABLE `t_user_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` varchar(45) DEFAULT NULL,
  `from_user_id` varchar(45) DEFAULT NULL,
  `to_user_id` varchar(45) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `is_read` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
