/*
Navicat MySQL Data Transfer

Source Server         : 本机的
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : cms_hnjx

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-10-16 09:54:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT 0 COMMENT '父级编号',
  `create_by` int(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`name`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '收信箱', 'hzof@qq.com', 'mailbox', '信箱', null, '0', null, null, null, null, '', '0');
INSERT INTO `sys_dict` VALUES ('2', '英文', 'english', 'CmsLangType', '语种', '1', null, null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '韩文', 'korean', 'CmsLangType', '语种', '2', null, null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '中文', 'simChinese', 'CmsLangType', '语种', null, '0', null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '产品类', '0', 'CmsCategoryType', '产品类型', null, '0', null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '图片类', '1', 'CmsCategoryType', '产品类型', null, '0', null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '文章类', '2', 'CmsCategoryType', '产品类型', null, '0', null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', 'smtp_host', 'smtp.qq.com', 'cmsMail', '邮件服务器地址', null, null, null, null, null, null, '', null);
INSERT INTO `sys_dict` VALUES ('9', 'username', 'hzof@qq.com', 'cmsMail', '邮箱账户', null, null, null, null, null, null, '', null);
INSERT INTO `sys_dict` VALUES ('10', 'password', 'exwfujofewnsbeea', 'cmsMail', '邮箱授权码', null, null, null, null, null, null, '', null);
INSERT INTO `sys_dict` VALUES ('11', 'from', 'hzof@qq.com', 'cmsMail', '发件人', null, null, null, null, null, null, '', null);
INSERT INTO `sys_dict` VALUES ('12', '公司简介', 'companyProfile', 'cmsNewsType', '公司简介', null, null, null, null, null, null, '', null);
INSERT INTO `sys_dict` VALUES ('13', '公司新闻', 'companyNew', 'cmsNewsType', '公司新闻', null, null, null, null, null, null, '', null);
