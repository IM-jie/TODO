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

 Date: 08/28/2018 09:08:41 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_task_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_task_info`;
CREATE TABLE `t_task_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` varchar(11) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `finisher` varchar(45) DEFAULT NULL,
  `worker` varchar(45) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `private_status` tinyint(4) DEFAULT NULL,
  `mark_status` tinyint(4) DEFAULT NULL,
  `priority` tinyint(4) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `finish_time` timestamp NULL DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `creator_id` varchar(45) DEFAULT NULL,
  `finisher_id` varchar(45) DEFAULT NULL,
  `worker_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
