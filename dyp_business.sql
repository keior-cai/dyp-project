/*
Navicat MySQL Data Transfer

Source Server         : 192.168.220.141_3306
Source Server Version : 50642
Source Host           : 192.168.220.141:3306
Source Database       : dyp_business

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2020-02-24 23:10:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `is_vip` int(11) DEFAULT '0' COMMENT '0-非VIP，1-VIP',
  `pay_password` varchar(255) DEFAULT NULL,
  `wechat_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `open_id` (`open_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '4444', '44', '1', '1', '111', '0', null, '1111', '11111', '127.0.0.1', '2020-01-20 17:14:02', '2020-01-20 17:14:02');
INSERT INTO `customer` VALUES ('2', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', null, '1', null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEKDw9Povep3GibxlsuABELjbVy8IooEtFSlqc6F3C29w0Y9wZ2JOZLCRiaxcRnftYmh0Pnn4YzVbdrw/132', '0', '328329', 'simplify', null, null, '2020-02-03 23:57:59', '2020-02-05 23:33:39');

-- ----------------------------
-- Table structure for movie_statics
-- ----------------------------
DROP TABLE IF EXISTS `movie_statics`;
CREATE TABLE `movie_statics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL COMMENT '下单次数',
  `count_point` double(255,0) DEFAULT NULL COMMENT '评分',
  `img_url` varchar(255) DEFAULT NULL,
  `turn_count` int(11) DEFAULT NULL COMMENT '退单数量',
  `total` double(255,0) DEFAULT NULL COMMENT '交易金额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of movie_statics
-- ----------------------------

-- ----------------------------
-- Table structure for statics_customer_count
-- ----------------------------
DROP TABLE IF EXISTS `statics_customer_count`;
CREATE TABLE `statics_customer_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `mouth` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of statics_customer_count
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `info` text,
  `deleted` int(11) DEFAULT '0',
  `ip` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', 'fh', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200208%2F1581092506858__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '15218628770', 'ACTIVE', '1', null, '0', '123456', '2020-02-08 00:24:14', '2020-01-15 14:56:45');
INSERT INTO `user` VALUES ('2', 'admin1', '123456', 'admin', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200207%2F1581014876449__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '15218628770', 'ACTIVE', '0', '{\"name\":\"哈哈电影院\",\"address\":\"123地址\"}', '0', '123456', '2020-02-07 03:06:41', '2020-01-17 11:09:45');
INSERT INTO `user` VALUES ('7', 'cs', '123456', 'ss', null, '15218628770', 'ACTIVE', '0', '{\"name\":\"123电影院\",\"address\":\"123地址\"}', '0', null, '2020-01-29 15:34:10', '2020-01-21 11:13:48');
INSERT INTO `user` VALUES ('8', 'fdfsd', 'sdfsdfsdf', 'sdfsdf', null, null, 'ACTIVE', '0', '{\"name\":\"456影院\",\"address\":\"456地址\"}', '0', null, '2020-01-29 16:07:59', '2020-01-21 11:17:15');
INSERT INTO `user` VALUES ('9', 'adasd', 'asdasdasd', 'asdasd', null, null, 'ACTIVE', '0', '{\"name\":\"789影院\",\"address\":\"789地址\"}', '0', null, '2020-01-29 16:08:39', '2020-01-21 11:17:54');
INSERT INTO `user` VALUES ('10', 'aAd', 'asdasd', 'asdsda', null, null, 'ACTIVE', '0', '{\"name\":\"110影院\",\"address\":\"110地址\"}', '0', null, '2020-01-29 16:08:56', '2020-01-21 11:22:20');
INSERT INTO `user` VALUES ('11', 'saasd', 'asdasd', 'asdasd', null, null, 'ACTIVE', '0', null, '1', null, '2020-01-21 11:57:54', '2020-01-21 11:23:17');
INSERT INTO `user` VALUES ('12', 'dsdfs', 'sfsdf', 'sfdsdf', null, null, 'ACTIVE', '0', null, '1', null, '2020-01-21 11:57:54', '2020-01-21 11:43:15');
INSERT INTO `user` VALUES ('13', 'hggh', 'hfgh', 'fhgh', null, null, 'NOT_ACTIVE', '0', null, '0', null, '2020-01-21 13:27:46', '2020-01-21 13:26:11');
INSERT INTO `user` VALUES ('14', 'hgh', 'fghfgh', 'fghfgh', null, null, 'NOT_ACTIVE', '0', null, '0', null, '2020-01-21 13:27:46', '2020-01-21 13:26:17');
INSERT INTO `user` VALUES ('15', 'gff', 'ggg', 'gg', null, null, 'NOT_ACTIVE', '0', null, '0', null, '2020-01-21 13:27:46', '2020-01-21 13:26:25');
INSERT INTO `user` VALUES ('16', 'dsf', 'sdfsdf', 'sdf', null, null, 'NOT_ACTIVE', '0', null, '0', null, '2020-01-21 13:28:10', '2020-01-21 13:28:10');
INSERT INTO `user` VALUES ('17', 'dsfsdf', 'dfsdf', 'sdfsdf', null, null, 'NOT_ACTIVE', '0', null, '0', null, '2020-01-21 13:28:16', '2020-01-21 13:28:16');
INSERT INTO `user` VALUES ('18', 'sdfsd', 'sdfsdf', 'sdfsdf', null, null, 'NOT_ACTIVE', '0', null, '0', null, '2020-01-21 13:28:21', '2020-01-21 13:28:21');
