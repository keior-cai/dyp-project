/*
Navicat MySQL Data Transfer

Source Server         : 192.168.220.141_3306
Source Server Version : 50642
Source Host           : 192.168.220.141:3306
Source Database       : dyp_2

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2020-02-24 23:10:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `text` text,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '127.0.0.1', '2', '登录成功', '2020-02-21 21:03:46');
INSERT INTO `log` VALUES ('2', '127.0.0.1', '2', '登出成功', '2020-02-22 15:52:22');
INSERT INTO `log` VALUES ('3', '127.0.0.1', '2', '登出成功', '2020-02-22 15:52:32');
INSERT INTO `log` VALUES ('4', '127.0.0.1', '2', '登录成功', '2020-02-22 16:03:50');
INSERT INTO `log` VALUES ('5', '127.0.0.1', '2', '登录成功', '2020-02-22 16:04:28');

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `vip_price` double(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `actor` varchar(255) DEFAULT NULL,
  `up_time` datetime DEFAULT NULL,
  `down_time` datetime DEFAULT NULL,
  `labels` text,
  `img_url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('12', '一部电影', '这是一部电影', '这是一部电影的详情', '50.00', '48.00', '2', '0', '导演', '演员1，演员2，演员3', '2020-02-06 00:00:00', '2020-02-07 00:00:00', null, 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '2020-02-06 22:41:56', '2020-02-23 23:24:00');
INSERT INTO `movie` VALUES ('13', '这又是一部电影', '这又是一个标题', '这又是一个详情', '30.00', '28.00', '2', '0', '这又是一个导演', '演员1演员2', '2020-02-07 00:00:00', '2020-02-08 00:00:00', null, 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200207%2F1581089696825__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '2020-02-07 23:34:58', '2020-02-23 23:24:00');

-- ----------------------------
-- Table structure for m_space
-- ----------------------------
DROP TABLE IF EXISTS `m_space`;
CREATE TABLE `m_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `m_id` int(11) DEFAULT NULL COMMENT '电影ID',
  `up_time` varchar(255) DEFAULT NULL COMMENT '播放时间',
  `s_id` int(11) DEFAULT NULL COMMENT '场地ID',
  `date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0-可购买，1-不可购买',
  `down_time` varchar(255) DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `num` int(11) DEFAULT NULL,
  `info` text,
  `deleted` int(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of m_space
-- ----------------------------
INSERT INTO `m_space` VALUES ('6', '12', '22:44:39', '5', '2020-2-7', '0', '23:44:39', '2020-02-06 22:44:43', '2020-02-06 22:44:43', '48', '[[0,0,1,0,0,0],[0,0,0,0,1,0,1,0,0,0],[0,0,0,1,1,1,1,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0]]', '0');
INSERT INTO `m_space` VALUES ('7', '13', '23:35:45', '5', '2020-2-8', '0', '23:59:59', '2020-02-07 23:35:52', '2020-02-07 23:35:52', '55', '[[0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0]]', '0');
INSERT INTO `m_space` VALUES ('8', '13', '23:35:00', '5', '2020-2-7', '0', '23:59:59', '2020-02-07 23:39:33', '2020-02-07 23:39:33', '55', '[[0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0]]', '0');
INSERT INTO `m_space` VALUES ('9', '13', '21:35:00', '5', '2020-2-7', '0', '22:59:59', '2020-02-07 23:39:40', '2020-02-07 23:39:40', '55', '[[0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0]]', '0');
INSERT INTO `m_space` VALUES ('10', '12', '19:35:00', '5', '2020-2-8', '0', '20:59:59', '2020-02-07 23:40:17', '2020-02-07 23:40:17', '55', '[[0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0]]', '0');

-- ----------------------------
-- Table structure for open_order
-- ----------------------------
DROP TABLE IF EXISTS `open_order`;
CREATE TABLE `open_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `info` text,
  `type` int(11) DEFAULT NULL COMMENT '0-下单，1-退单，2-取票，3-支付,4-超时',
  `model` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0-待发送，1-已发送',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of open_order
-- ----------------------------
INSERT INTO `open_order` VALUES ('173', '{\"dbPrefix\":\"dyp_2\",\"id\":26,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行9位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":8}]\",\"mId\":12,\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700052730\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 00:05:29', '2020-02-07 00:05:29');
INSERT INTO `open_order` VALUES ('174', '{\"dbPrefix\":\"dyp_2\",\"id\":28,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行8位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":7}]\",\"mId\":12,\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700065731\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 00:06:58', '2020-02-07 00:06:58');
INSERT INTO `open_order` VALUES ('175', '{\"dbPrefix\":\"dyp_2\",\"id\":29,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行10位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":9}]\",\"mId\":12,\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700092932\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0}', '0', null, null, '0', '2020-02-07 00:09:30', '2020-02-07 00:09:30');
INSERT INTO `open_order` VALUES ('176', '{\"createTime\":1581005218000,\"id\":28,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行8位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":7}]\",\"mId\":12,\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700065731\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"updateTime\":1581005218000,\"yId\":2}', '1', null, null, '0', '2020-02-07 00:11:59', '2020-02-07 00:11:59');
INSERT INTO `open_order` VALUES ('177', '{\"dbPrefix\":\"dyp_2\",\"id\":30,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行8位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":7}]\",\"mId\":12,\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700132233\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0}', '0', null, null, '0', '2020-02-07 00:13:23', '2020-02-07 00:13:23');
INSERT INTO `open_order` VALUES ('178', '{\"createTime\":1581005370000,\"id\":29,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行10位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":9}]\",\"mId\":12,\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700092932\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"updateTime\":1581005370000}', '1', null, null, '0', '2020-02-07 00:14:36', '2020-02-07 00:14:36');
INSERT INTO `open_order` VALUES ('179', '{\"createTime\":1581005603000,\"id\":30,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行8位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":7}]\",\"mId\":12,\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700132233\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"updateTime\":1581005603000}', '1', null, null, '0', '2020-02-07 00:18:23', '2020-02-07 00:18:23');
INSERT INTO `open_order` VALUES ('180', '{\"dbPrefix\":\"dyp_2\",\"id\":31,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行10位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":9}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700271834\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0}', '0', null, null, '0', '2020-02-07 00:27:18', '2020-02-07 00:27:18');
INSERT INTO `open_order` VALUES ('181', '{\"createTime\":1581006438000,\"id\":31,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行10位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":9}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700271834\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"updateTime\":1581006438000}', '1', null, null, '0', '2020-02-07 00:32:19', '2020-02-07 00:32:19');
INSERT INTO `open_order` VALUES ('182', '{\"dbPrefix\":\"dyp_2\",\"id\":32,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行11位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":10}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700354335\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 00:35:43', '2020-02-07 00:35:43');
INSERT INTO `open_order` VALUES ('183', '{\"dbPrefix\":\"dyp_2\",\"id\":33,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行11位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":10}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700354336\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 00:35:43', '2020-02-07 00:35:43');
INSERT INTO `open_order` VALUES ('184', '{\"dbPrefix\":\"dyp_2\",\"id\":34,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行12位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":11}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700355438\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 00:35:55', '2020-02-07 00:35:55');
INSERT INTO `open_order` VALUES ('185', '{\"dbPrefix\":\"dyp_2\",\"id\":35,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行8位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":7}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020101003239\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":50.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 01:00:34', '2020-02-07 01:00:34');
INSERT INTO `open_order` VALUES ('186', '{\"content\":\"这是一部电影的详情\",\"dbPrefix\":\"dyp_2\",\"id\":36,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行2位\\\"}]\",\"isVip\":0,\"location\":\"[{\\\"x\\\":2,\\\"y\\\":1}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020713500742\",\"price\":50.0,\"psId\":6,\"status\":0,\"title\":\"这是一部电影\",\"total\":50.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 04:00:23', '2020-02-07 04:00:23');
INSERT INTO `open_order` VALUES ('187', '{\"content\":\"这是一部电影的详情\",\"dbPrefix\":\"dyp_2\",\"id\":37,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"2行7位\\\"}]\",\"location\":\"[{\\\"x\\\":1,\\\"y\\\":6}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020714415043\",\"price\":50.0,\"psId\":6,\"status\":0,\"title\":\"这是一部电影\",\"total\":50.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 04:56:24', '2020-02-07 04:56:24');
INSERT INTO `open_order` VALUES ('188', '{\"content\":\"这是一部电影的详情\",\"dbPrefix\":\"dyp_2\",\"id\":39,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"1行3位\\\"},{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"2行5位\\\"}]\",\"location\":\"[{\\\"x\\\":0,\\\"y\\\":2},{\\\"x\\\":1,\\\"y\\\":4}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":2,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020714422444\",\"price\":50.0,\"psId\":6,\"status\":0,\"title\":\"这是一部电影\",\"total\":100.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 04:57:01', '2020-02-07 04:57:01');
INSERT INTO `open_order` VALUES ('189', '{\"content\":\"这是一部电影的详情\",\"dbPrefix\":\"dyp_2\",\"id\":41,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行4位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":3}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020723151245\",\"price\":50.0,\"psId\":6,\"status\":0,\"title\":\"这是一部电影\",\"total\":50.0,\"yId\":2}', '0', null, null, '0', '2020-02-07 23:15:12', '2020-02-07 23:15:12');
INSERT INTO `open_order` VALUES ('190', '{\"content\":\"这是一部电影的详情\",\"createTime\":1581019223000,\"id\":36,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行2位\\\"}]\",\"isVip\":0,\"location\":\"[{\\\"x\\\":2,\\\"y\\\":1}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020713500742\",\"price\":50.0,\"psId\":6,\"status\":0,\"title\":\"这是一部电影\",\"total\":50.0,\"updateTime\":1581019223000,\"yId\":2}', '1', null, null, '0', '2020-02-23 23:12:22', '2020-02-23 23:12:22');
INSERT INTO `open_order` VALUES ('191', '{\"createTime\":1581008434000,\"id\":35,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行8位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":7}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020101003239\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":50.0,\"updateTime\":1581008434000,\"yId\":2}', '1', null, null, '0', '2020-02-23 23:12:29', '2020-02-23 23:12:29');
INSERT INTO `open_order` VALUES ('192', '{\"createTime\":1581006955000,\"id\":34,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行12位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":11}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700355438\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"updateTime\":1581006955000,\"yId\":2}', '1', null, null, '0', '2020-02-23 23:12:33', '2020-02-23 23:12:33');
INSERT INTO `open_order` VALUES ('193', '{\"createTime\":1581006943000,\"id\":32,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行11位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":10}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700354335\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"updateTime\":1581006943000,\"yId\":2}', '1', null, null, '0', '2020-02-23 23:12:41', '2020-02-23 23:12:41');
INSERT INTO `open_order` VALUES ('194', '{\"createTime\":1581006943000,\"id\":33,\"info\":\"[{\\\"id\\\":12,\\\"movieName\\\":\\\"一部电影\\\",\\\"address\\\":\\\"一号影院\\\",\\\"spaceName\\\":\\\"一楼一号场\\\",\\\"upTime\\\":\\\"22:44:39\\\",\\\"downTime\\\":\\\"23:44:39\\\",\\\"location\\\":\\\"3行11位\\\"}]\",\"location\":\"[{\\\"x\\\":2,\\\"y\\\":10}]\",\"mId\":12,\"movieName\":\"一部电影\",\"movieUrl\":\"http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png\",\"num\":1,\"openId\":\"o0gKn1UcAxgSXpFEukgm1yjzZrk4\",\"orderSn\":\"2020020700354336\",\"price\":50.0,\"psId\":6,\"status\":0,\"total\":5000.0,\"updateTime\":1581006943000,\"yId\":2}', '1', null, null, '0', '2020-02-23 23:12:54', '2020-02-23 23:12:54');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(100) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `movie_name` varchar(255) DEFAULT NULL,
  `movie_url` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total` double(8,2) DEFAULT NULL,
  `price` double(8,2) DEFAULT NULL,
  `is_vip` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0-下单，1-已支付，2-已出票，3-已过期，4-已完成,5-订单取消',
  `info` text,
  `y_id` int(11) DEFAULT NULL COMMENT '影院ID',
  `m_id` int(11) DEFAULT NULL COMMENT '电影ID',
  `ps_id` int(11) DEFAULT NULL COMMENT '排场ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `location` text,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_sn` (`order_sn`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('22', '2020020622513427', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', null, null, '1', null, '5000.00', '50.00', null, '1', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行6位\"}]', '2', '12', '6', '2020-02-06 22:51:34', '2020-02-06 22:51:34', null, null);
INSERT INTO `order` VALUES ('24', '2020020623284928', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', null, null, '1', null, '5000.00', '50.00', null, '0', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行7位\"}]', '2', '12', '6', '2020-02-06 23:28:50', '2020-02-06 23:28:50', null, null);
INSERT INTO `order` VALUES ('25', '2020020623480729', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', null, 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行8位\"}]', null, '12', '6', '2020-02-06 23:48:08', '2020-02-06 23:48:08', '[{\"x\":2,\"y\":7}]', null);
INSERT INTO `order` VALUES ('26', '2020020700052730', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', null, 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '1', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行9位\"}]', '2', '12', '6', '2020-02-07 00:05:29', '2020-02-07 00:05:29', '[{\"x\":2,\"y\":8}]', null);
INSERT INTO `order` VALUES ('28', '2020020700065731', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', null, 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行8位\"}]', '2', '12', '6', '2020-02-07 00:06:58', '2020-02-07 00:06:58', '[{\"x\":2,\"y\":7}]', null);
INSERT INTO `order` VALUES ('29', '2020020700092932', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', null, 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行10位\"}]', null, '12', '6', '2020-02-07 00:09:30', '2020-02-07 00:09:30', '[{\"x\":2,\"y\":9}]', null);
INSERT INTO `order` VALUES ('30', '2020020700132233', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', null, 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行8位\"}]', null, '12', '6', '2020-02-07 00:13:23', '2020-02-07 00:13:23', '[{\"x\":2,\"y\":7}]', null);
INSERT INTO `order` VALUES ('31', '2020020700271834', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行10位\"}]', null, '12', '6', '2020-02-07 00:27:18', '2020-02-07 00:27:18', '[{\"x\":2,\"y\":9}]', null);
INSERT INTO `order` VALUES ('32', '2020020700354335', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行11位\"}]', '2', '12', '6', '2020-02-07 00:35:43', '2020-02-07 00:35:43', '[{\"x\":2,\"y\":10}]', null);
INSERT INTO `order` VALUES ('33', '2020020700354336', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行11位\"}]', '2', '12', '6', '2020-02-07 00:35:43', '2020-02-07 00:35:43', '[{\"x\":2,\"y\":10}]', null);
INSERT INTO `order` VALUES ('34', '2020020700355438', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '5000.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行12位\"}]', '2', '12', '6', '2020-02-07 00:35:55', '2020-02-07 00:35:55', '[{\"x\":2,\"y\":11}]', null);
INSERT INTO `order` VALUES ('35', '2020020101003239', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', null, '50.00', '50.00', null, '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行8位\"}]', '2', '12', '6', '2020-02-07 01:00:34', '2020-02-07 01:00:34', '[{\"x\":2,\"y\":7}]', null);
INSERT INTO `order` VALUES ('36', '2020020713500742', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', '这是一部电影', '50.00', '50.00', '0', '5', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行2位\"}]', '2', '12', '6', '2020-02-07 04:00:23', '2020-02-07 04:00:23', '[{\"x\":2,\"y\":1}]', '这是一部电影的详情');
INSERT INTO `order` VALUES ('37', '2020020714415043', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', '这是一部电影', '50.00', '50.00', null, '1', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"2行7位\"}]', '2', '12', '6', '2020-02-07 04:56:24', '2020-02-07 04:56:24', '[{\"x\":1,\"y\":6}]', '这是一部电影的详情');
INSERT INTO `order` VALUES ('39', '2020020714422444', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '2', '这是一部电影', '100.00', '50.00', null, '1', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"1行3位\"},{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"2行5位\"}]', '2', '12', '6', '2020-02-07 04:57:01', '2020-02-07 04:57:01', '[{\"x\":0,\"y\":2},{\"x\":1,\"y\":4}]', '这是一部电影的详情');
INSERT INTO `order` VALUES ('41', '2020020723151245', 'o0gKn1UcAxgSXpFEukgm1yjzZrk4', '一部电影', 'http://47.106.78.139:8012/tame/dfs/download?supportType=local&serveName=movie&fileName=20200206%2F1581000114167__UUID__%E4%BC%81%E4%B8%9A%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20200206224145.png', '1', '这是一部电影', '50.00', '50.00', null, '1', '[{\"id\":12,\"movieName\":\"一部电影\",\"address\":\"一号影院\",\"spaceName\":\"一楼一号场\",\"upTime\":\"22:44:39\",\"downTime\":\"23:44:39\",\"location\":\"3行4位\"}]', '2', '12', '6', '2020-02-07 23:15:12', '2020-02-07 23:15:12', '[{\"x\":2,\"y\":3}]', '这是一部电影的详情');

-- ----------------------------
-- Table structure for order_statics
-- ----------------------------
DROP TABLE IF EXISTS `order_statics`;
CREATE TABLE `order_statics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  `y_id` int(11) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `y_id` (`y_id`,`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_statics
-- ----------------------------

-- ----------------------------
-- Table structure for space
-- ----------------------------
DROP TABLE IF EXISTS `space`;
CREATE TABLE `space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `status` varchar(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `info` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of space
-- ----------------------------
INSERT INTO `space` VALUES ('5', '一楼一号场', '一号影院', '55', '55', 'ACTIVE', '0', '2020-02-06 22:32:11', '2020-02-06 22:39:24', '[[0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0]]');
