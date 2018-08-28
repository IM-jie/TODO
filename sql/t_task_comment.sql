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

 Date: 08/28/2018 09:08:33 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_task_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_task_comment`;
CREATE TABLE `t_task_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` varchar(45) DEFAULT NULL,
  `task_id` varchar(45) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  `commentator` varchar(45) DEFAULT NULL,
  `comment_time` timestamp NULL DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
