-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 cms_hnjx.cont_category 结构
CREATE TABLE IF NOT EXISTS `cont_category` (
  `cont_category_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_type` varchar(15) DEFAULT NULL COMMENT '类别类型：（CmsCategoryType）',
  `parent_category_id` int(11) unsigned DEFAULT NULL COMMENT '上级分类',
  `category_name` varchar(63) DEFAULT NULL COMMENT '类别名称',
  `create_by` bigint(255) unsigned DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cont_category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  cms_hnjx.cont_category 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `cont_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `cont_category` ENABLE KEYS */;

-- 导出  表 cms_hnjx.cont_form 结构
CREATE TABLE IF NOT EXISTS `cont_form` (
  `cont_info_id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表单表主键',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `lang_type` varchar(15) DEFAULT NULL COMMENT '语种 字典CmsLangType',
  `seo_title` varchar(64) DEFAULT NULL COMMENT 'seo标题',
  `seo_keywords` varchar(72) DEFAULT NULL COMMENT 'seo关键字',
  `seo_describe` varchar(32) DEFAULT NULL COMMENT 'seo描述',
  `remark` varchar(32) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`cont_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单表';

-- 正在导出表  cms_hnjx.cont_form 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `cont_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `cont_form` ENABLE KEYS */;

-- 导出  表 cms_hnjx.cont_form_data 结构
CREATE TABLE IF NOT EXISTS `cont_form_data` (
  `cont_info_data_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `cont_info_id` int(20) unsigned DEFAULT NULL COMMENT '表单表主键',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `value` varchar(64) DEFAULT NULL COMMENT '值',
  `title_style` varchar(32) DEFAULT NULL COMMENT '标题样式',
  `input_style` varchar(32) DEFAULT NULL COMMENT '输入框样式',
  PRIMARY KEY (`cont_info_data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单数据表';

-- 正在导出表  cms_hnjx.cont_form_data 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `cont_form_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `cont_form_data` ENABLE KEYS */;

-- 导出  表 cms_hnjx.cont_product 结构
CREATE TABLE IF NOT EXISTS `cont_product` (
  `cont_product_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `product_name` varchar(63) DEFAULT NULL COMMENT '产品名称',
  `category_id` int(11) unsigned DEFAULT NULL COMMENT '所属分类：0表示顶级',
  `sale_status` tinyint(3) unsigned DEFAULT NULL COMMENT '销售状态：0-下架，1-上架',
  `create_by` bigint(255) unsigned DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cont_product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  cms_hnjx.cont_product 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `cont_product` DISABLE KEYS */;
INSERT INTO `cont_product` (`cont_product_id`, `product_name`, `category_id`, `sale_status`, `create_by`, `gmt_create`, `gmt_modified`) VALUES
	(1, '商品', 0, 1, NULL, NULL, NULL);
/*!40000 ALTER TABLE `cont_product` ENABLE KEYS */;

-- 导出  表 cms_hnjx.cont_product_img 结构
CREATE TABLE IF NOT EXISTS `cont_product_img` (
  `cont_product_img_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '图片主键',
  `cont_product_id` int(10) unsigned DEFAULT NULL COMMENT '产品主键',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`cont_product_img_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  cms_hnjx.cont_product_img 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `cont_product_img` DISABLE KEYS */;
/*!40000 ALTER TABLE `cont_product_img` ENABLE KEYS */;

-- 导出  表 cms_hnjx.cont_product_info 结构
CREATE TABLE IF NOT EXISTS `cont_product_info` (
  `cont_product_info_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cont_product_id` int(10) unsigned DEFAULT NULL COMMENT '产品主键',
  `lang_type` varchar(15) DEFAULT NULL COMMENT '语种：字段表（CmsLangType）',
  `product_name` varchar(64) DEFAULT NULL COMMENT '标题',
  `product_desc` varchar(255) DEFAULT NULL COMMENT '简介',
  `details` text COMMENT '详情',
  `product_keywords` varchar(255) DEFAULT NULL COMMENT '产品关键词',
  `seo_keywords` varchar(255) DEFAULT NULL COMMENT 'SEO关键词',
  `seo_desc` varchar(255) DEFAULT NULL COMMENT 'SEO产品描述',
  `seo_title` varchar(255) DEFAULT NULL COMMENT 'SEO标题',
  `create_by` bigint(255) unsigned DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cont_product_info_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  cms_hnjx.cont_product_info 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `cont_product_info` DISABLE KEYS */;
INSERT INTO `cont_product_info` (`cont_product_info_id`, `cont_product_id`, `lang_type`, `product_name`, `product_desc`, `details`, `product_keywords`, `seo_keywords`, `seo_desc`, `seo_title`, `create_by`, `gmt_create`, `gmt_modified`) VALUES
	(1, NULL, 'korean', NULL, NULL, '<p><br></p><p>ghghjgkgkjhkj</p><p><br></p><p><br></p>', NULL, NULL, NULL, NULL, 1, '2018-09-20 11:59:02', '2018-09-21 00:33:31'),
	(2, NULL, 'english', NULL, NULL, '更富有哟一百', NULL, NULL, NULL, NULL, 1, '2018-09-20 11:59:02', '2018-09-21 00:33:31'),
	(3, NULL, 'simChinese', 'we4', '134', '比较快，明白吧，模板&nbsp;', NULL, NULL, NULL, NULL, 1, '2018-09-20 11:59:02', '2018-09-21 00:33:31');
/*!40000 ALTER TABLE `cont_product_info` ENABLE KEYS */;

-- 导出  表 cms_hnjx.cont_product_param 结构
CREATE TABLE IF NOT EXISTS `cont_product_param` (
  `cont_product_param_id` int(255) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cont_product_id` int(10) unsigned DEFAULT NULL COMMENT '产品主键',
  `lang_type` varchar(15) DEFAULT NULL COMMENT '语种：字段表（CmsLangType）',
  `param_name` varchar(255) DEFAULT NULL COMMENT '参数名称',
  `limit_num` int(8) unsigned DEFAULT NULL COMMENT '限制数量',
  `sort` tinyint(4) unsigned DEFAULT NULL COMMENT '排序',
  `create_by` bigint(255) unsigned DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cont_product_param_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  cms_hnjx.cont_product_param 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `cont_product_param` DISABLE KEYS */;
INSERT INTO `cont_product_param` (`cont_product_param_id`, `cont_product_id`, `lang_type`, `param_name`, `limit_num`, `sort`, `create_by`, `gmt_create`, `gmt_modified`) VALUES
	(1, NULL, 'simChinese', '1', NULL, 1, 1, '2018-09-25 21:46:46', '2018-09-25 21:46:46');
/*!40000 ALTER TABLE `cont_product_param` ENABLE KEYS */;

-- 导出  表 cms_hnjx.cont_product_pk 结构
CREATE TABLE IF NOT EXISTS `cont_product_pk` (
  `cont_product_pk_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cont_product_param_id` varchar(255) DEFAULT NULL COMMENT '参数主键',
  `param_value` varchar(31) DEFAULT NULL COMMENT '参数值',
  `sort` tinyint(4) unsigned DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`cont_product_pk_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  cms_hnjx.cont_product_pk 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `cont_product_pk` DISABLE KEYS */;
/*!40000 ALTER TABLE `cont_product_pk` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_dept 结构
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门管理';

-- 正在导出表  cms_hnjx.sys_dept 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `name`, `order_num`, `del_flag`) VALUES
	(1, 0, '技术部', 1, 1),
	(9, 0, '商务部', 2, 1),
	(10, 9, '商务一部', 1, 1);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_dict 结构
CREATE TABLE IF NOT EXISTS `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
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
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- 正在导出表  cms_hnjx.sys_dict 的数据：~116 rows (大约)
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` (`id`, `name`, `value`, `type`, `description`, `sort`, `parent_id`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES
	(1, '正常', '0', 'del_flag', '删除标记', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(3, '显示', '1', 'show_hide', '显示/隐藏', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(4, '隐藏', '0', 'show_hide', '显示/隐藏', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(5, '是', '1', 'yes_no', '是/否', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(6, '否', '0', 'yes_no', '是/否', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(7, '红色', 'red', 'color', '颜色值', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(8, '绿色', 'green', 'color', '颜色值', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(9, '蓝色', 'blue', 'color', '颜色值', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(10, '黄色', 'yellow', 'color', '颜色值', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(11, '橙色', 'orange', 'color', '颜色值', 50, 0, 1, NULL, 1, NULL, NULL, '0'),
	(12, '默认主题', 'default', 'theme', '主题方案', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(13, '天蓝主题', 'cerulean', 'theme', '主题方案', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(14, '橙色主题', 'readable', 'theme', '主题方案', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(15, '红色主题', 'united', 'theme', '主题方案', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(16, 'Flat主题', 'flat', 'theme', '主题方案', 60, 0, 1, NULL, 1, NULL, NULL, '0'),
	(17, '国家', '1', 'sys_area_type', '区域类型', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(18, '省份、直辖市', '2', 'sys_area_type', '区域类型', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(19, '地市', '3', 'sys_area_type', '区域类型', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(20, '区县', '4', 'sys_area_type', '区域类型', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(21, '公司', '1', 'sys_office_type', '机构类型', 60, 0, 1, NULL, 1, NULL, NULL, '0'),
	(22, '部门', '2', 'sys_office_type', '机构类型', 70, 0, 1, NULL, 1, NULL, NULL, '0'),
	(23, '小组', '3', 'sys_office_type', '机构类型', 80, 0, 1, NULL, 1, NULL, NULL, '0'),
	(24, '其它', '4', 'sys_office_type', '机构类型', 90, 0, 1, NULL, 1, NULL, NULL, '0'),
	(25, '综合部', '1', 'sys_office_common', '快捷通用部门', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(26, '开发部', '2', 'sys_office_common', '快捷通用部门', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(27, '人力部', '3', 'sys_office_common', '快捷通用部门', 50, 0, 1, NULL, 1, NULL, NULL, '0'),
	(28, '一级', '1', 'sys_office_grade', '机构等级', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(29, '二级', '2', 'sys_office_grade', '机构等级', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(30, '三级', '3', 'sys_office_grade', '机构等级', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(31, '四级', '4', 'sys_office_grade', '机构等级', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(32, '所有数据', '1', 'sys_data_scope', '数据范围', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(33, '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(34, '所在公司数据', '3', 'sys_data_scope', '数据范围', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(35, '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(36, '所在部门数据', '5', 'sys_data_scope', '数据范围', 50, 0, 1, NULL, 1, NULL, NULL, '0'),
	(37, '仅本人数据', '8', 'sys_data_scope', '数据范围', 90, 0, 1, NULL, 1, NULL, NULL, '0'),
	(38, '按明细设置', '9', 'sys_data_scope', '数据范围', 100, 0, 1, NULL, 1, NULL, NULL, '0'),
	(39, '系统管理', '1', 'sys_user_type', '用户类型', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(40, '部门经理', '2', 'sys_user_type', '用户类型', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(41, '普通用户', '3', 'sys_user_type', '用户类型', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(42, '基础主题', 'basic', 'cms_theme', '站点主题', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(43, '蓝色主题', 'blue', 'cms_theme', '站点主题', 20, 0, 1, NULL, 1, NULL, NULL, '1'),
	(44, '红色主题', 'red', 'cms_theme', '站点主题', 30, 0, 1, NULL, 1, NULL, NULL, '1'),
	(45, '文章模型', 'article', 'cms_module', '栏目模型', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(46, '图片模型', 'picture', 'cms_module', '栏目模型', 20, 0, 1, NULL, 1, NULL, NULL, '1'),
	(47, '下载模型', 'download', 'cms_module', '栏目模型', 30, 0, 1, NULL, 1, NULL, NULL, '1'),
	(48, '链接模型', 'link', 'cms_module', '栏目模型', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(49, '专题模型', 'special', 'cms_module', '栏目模型', 50, 0, 1, NULL, 1, NULL, NULL, '1'),
	(50, '默认展现方式', '0', 'cms_show_modes', '展现方式', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(51, '首栏目内容列表', '1', 'cms_show_modes', '展现方式', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(52, '栏目第一条内容', '2', 'cms_show_modes', '展现方式', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(53, '发布', '0', 'cms_del_flag', '内容状态', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(54, '删除', '1', 'cms_del_flag', '内容状态', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(55, '审核', '2', 'cms_del_flag', '内容状态', 15, 0, 1, NULL, 1, NULL, NULL, '0'),
	(56, '首页焦点图', '1', 'cms_posid', '推荐位', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(57, '栏目页文章推荐', '2', 'cms_posid', '推荐位', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(58, '咨询', '1', 'cms_guestbook', '留言板分类', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(59, '建议', '2', 'cms_guestbook', '留言板分类', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(60, '投诉', '3', 'cms_guestbook', '留言板分类', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(61, '其它', '4', 'cms_guestbook', '留言板分类', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(62, '公休', '1', 'oa_leave_type', '请假类型', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(63, '病假', '2', 'oa_leave_type', '请假类型', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(64, '事假', '3', 'oa_leave_type', '请假类型', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(65, '调休', '4', 'oa_leave_type', '请假类型', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(66, '婚假', '5', 'oa_leave_type', '请假类型', 60, 0, 1, NULL, 1, NULL, NULL, '0'),
	(67, '接入日志', '1', 'sys_log_type', '日志类型', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(68, '异常日志', '2', 'sys_log_type', '日志类型', 40, 0, 1, NULL, 1, NULL, NULL, '0'),
	(69, '请假流程', 'leave', 'act_type', '流程类型', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(70, '审批测试流程', 'test_audit', 'act_type', '流程类型', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(71, '分类1', '1', 'act_category', '流程分类', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(72, '分类2', '2', 'act_category', '流程分类', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(73, '增删改查', 'crud', 'gen_category', '代码生成分类', 10, 0, 1, NULL, 1, NULL, NULL, '1'),
	(74, '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', 20, 0, 1, NULL, 1, NULL, NULL, '1'),
	(75, '树结构', 'tree', 'gen_category', '代码生成分类', 30, 0, 1, NULL, 1, NULL, NULL, '1'),
	(76, '=', '=', 'gen_query_type', '查询方式', 10, 0, 1, NULL, 1, NULL, NULL, '1'),
	(77, '!=', '!=', 'gen_query_type', '查询方式', 20, 0, 1, NULL, 1, NULL, NULL, '1'),
	(78, '&gt;', '&gt;', 'gen_query_type', '查询方式', 30, 0, 1, NULL, 1, NULL, NULL, '1'),
	(79, '&lt;', '&lt;', 'gen_query_type', '查询方式', 40, 0, 1, NULL, 1, NULL, NULL, '1'),
	(80, 'Between', 'between', 'gen_query_type', '查询方式', 50, 0, 1, NULL, 1, NULL, NULL, '1'),
	(81, 'Like', 'like', 'gen_query_type', '查询方式', 60, 0, 1, NULL, 1, NULL, NULL, '1'),
	(82, 'Left Like', 'left_like', 'gen_query_type', '查询方式', 70, 0, 1, NULL, 1, NULL, NULL, '1'),
	(83, 'Right Like', 'right_like', 'gen_query_type', '查询方式', 80, 0, 1, NULL, 1, NULL, NULL, '1'),
	(84, '文本框', 'input', 'gen_show_type', '字段生成方案', 10, 0, 1, NULL, 1, NULL, NULL, '1'),
	(85, '文本域', 'textarea', 'gen_show_type', '字段生成方案', 20, 0, 1, NULL, 1, NULL, NULL, '1'),
	(86, '下拉框', 'select', 'gen_show_type', '字段生成方案', 30, 0, 1, NULL, 1, NULL, NULL, '1'),
	(87, '复选框', 'checkbox', 'gen_show_type', '字段生成方案', 40, 0, 1, NULL, 1, NULL, NULL, '1'),
	(88, '单选框', 'radiobox', 'gen_show_type', '字段生成方案', 50, 0, 1, NULL, 1, NULL, NULL, '1'),
	(89, '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', 60, 0, 1, NULL, 1, NULL, NULL, '1'),
	(90, '人员选择', 'userselect', 'gen_show_type', '字段生成方案', 70, 0, 1, NULL, 1, NULL, NULL, '1'),
	(91, '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', 80, 0, 1, NULL, 1, NULL, NULL, '1'),
	(92, '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', 90, 0, 1, NULL, 1, NULL, NULL, '1'),
	(93, 'String', 'String', 'gen_java_type', 'Java类型', 10, 0, 1, NULL, 1, NULL, NULL, '1'),
	(94, 'Long', 'Long', 'gen_java_type', 'Java类型', 20, 0, 1, NULL, 1, NULL, NULL, '1'),
	(95, '仅持久层', 'dao', 'gen_category', '代码生成分类', 40, 0, 1, NULL, 1, NULL, NULL, '1'),
	(96, '男', '1', 'sex', '性别', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(97, '女', '2', 'sex', '性别', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(98, 'Integer', 'Integer', 'gen_java_type', 'Java类型', 30, 0, 1, NULL, 1, NULL, NULL, '1'),
	(99, 'Double', 'Double', 'gen_java_type', 'Java类型', 40, 0, 1, NULL, 1, NULL, NULL, '1'),
	(100, 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', 50, 0, 1, NULL, 1, NULL, NULL, '1'),
	(104, 'Custom', 'Custom', 'gen_java_type', 'Java类型', 90, 0, 1, NULL, 1, NULL, NULL, '1'),
	(105, '会议通告', '1', 'oa_notify_type', '通知通告类型', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(106, '奖惩通告', '2', 'oa_notify_type', '通知通告类型', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(107, '活动通告', '3', 'oa_notify_type', '通知通告类型', 30, 0, 1, NULL, 1, NULL, NULL, '0'),
	(108, '草稿', '0', 'oa_notify_status', '通知通告状态', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(109, '发布', '1', 'oa_notify_status', '通知通告状态', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(110, '未读', '0', 'oa_notify_read', '通知通告状态', 10, 0, 1, NULL, 1, NULL, NULL, '0'),
	(111, '已读', '1', 'oa_notify_read', '通知通告状态', 20, 0, 1, NULL, 1, NULL, NULL, '0'),
	(112, '草稿', '0', 'oa_notify_status', '通知通告状态', 10, 0, 1, NULL, 1, NULL, '', '0'),
	(113, '删除', '0', 'del_flag', '删除标记', NULL, NULL, NULL, NULL, NULL, NULL, '', ''),
	(118, '关于', 'about', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '全url是:/blog/open/page/about', ''),
	(119, '交流', 'communication', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '', ''),
	(120, '文章', 'article', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '', ''),
	(121, '编码', 'code', 'hobby', '爱好', NULL, NULL, NULL, NULL, NULL, NULL, '', ''),
	(122, '绘画', 'painting', 'hobby', '爱好', NULL, NULL, NULL, NULL, NULL, NULL, '', ''),
	(123, '3345', '3', '3', '3', 1, NULL, NULL, NULL, NULL, NULL, '1', NULL),
	(124, 'endClientCode', '0,0,0,9', 'endClientCode', 'endClientCode', NULL, NULL, NULL, NULL, NULL, NULL, '最后一个客户代码(请不要乱修改)', '0'),
	(125, '简体中文', 'simChinese', 'CmsLangType', '语种', 0, NULL, NULL, NULL, NULL, NULL, NULL, '0'),
	(126, '英文', 'english', 'CmsLangType', '语种', 1, NULL, NULL, NULL, NULL, NULL, NULL, '0'),
	(127, '韩文', 'korean', 'CmsLangType', '语种', 2, NULL, NULL, NULL, NULL, NULL, NULL, '0');
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_file 结构
CREATE TABLE IF NOT EXISTS `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文件上传';

-- 正在导出表  cms_hnjx.sys_file 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_file` DISABLE KEYS */;
INSERT INTO `sys_file` (`id`, `type`, `url`, `create_date`) VALUES
	(142, 0, '/files/8489aec7-cc8f-41e2-98dc-5590aaa752c3.jpg', '2018-09-17 10:42:38'),
	(143, 0, '/files/2f822892-e9e0-4bf7-8abc-519923261aa5.jpg', '2018-09-20 15:57:25');
/*!40000 ALTER TABLE `sys_file` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_log 结构
CREATE TABLE IF NOT EXISTS `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3209 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- 正在导出表  cms_hnjx.sys_log 的数据：~163 rows (大约)
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` (`id`, `user_id`, `username`, `operation`, `time`, `method`, `params`, `ip`, `gmt_create`) VALUES
	(3046, -1, '获取用户信息为空', '登录', 16, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 11:21:10'),
	(3047, -1, '获取用户信息为空', '登录', 6864, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 11:25:13'),
	(3048, 1, 'admin', '登录', 22286, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 11:26:07'),
	(3049, 1, 'admin', '请求访问主页', 217, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 11:26:07'),
	(3050, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/412/1n77lyb1/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-18 11:58:31'),
	(3051, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/412/1n77lyb1/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-18 11:58:31'),
	(3052, 1, 'admin', '登录', 5, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 11:59:11'),
	(3053, 1, 'admin', '请求访问主页', 19, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 11:59:11'),
	(3054, -1, '获取用户信息为空', '登录', 39, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 14:52:18'),
	(3055, -1, '获取用户信息为空', '登录', 10, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 14:52:24'),
	(3056, -1, '获取用户信息为空', '登录', 10, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 14:52:25'),
	(3057, -1, '获取用户信息为空', '登录', 79, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 14:52:25'),
	(3058, -1, '获取用户信息为空', '登录', 17, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 14:52:31'),
	(3059, 1, 'admin', '登录', 11291, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 14:53:57'),
	(3060, 1, 'admin', '请求访问主页', 92, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 14:53:57'),
	(3061, -1, '获取用户信息为空', '登录', 14, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 14:54:11'),
	(3062, -1, '获取用户信息为空', '登录', 11090, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 14:56:38'),
	(3063, 1, 'admin', '登录', 13, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 15:04:17'),
	(3064, 1, 'admin', '请求访问主页', 124, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 15:04:17'),
	(3065, 1, 'admin', '登录', 19, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 16:20:53'),
	(3066, 1, 'admin', '请求访问主页', 296, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 16:20:53'),
	(3067, 1, 'admin', '登录', 14, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 16:27:04'),
	(3068, 1, 'admin', '请求访问主页', 113, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 16:27:04'),
	(3069, 1, 'admin', '登录', 13, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 16:31:26'),
	(3070, 1, 'admin', '请求访问主页', 225, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 16:31:26'),
	(3071, 1, 'admin', '添加菜单', 8, 'com.cloudht.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-18 16:32:05'),
	(3072, 1, 'admin', '编辑菜单', 6, 'com.cloudht.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-18 16:32:30'),
	(3073, 1, 'admin', '更新菜单', 101, 'com.cloudht.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-18 16:32:35'),
	(3074, 1, 'admin', '添加菜单', 7, 'com.cloudht.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-18 16:32:41'),
	(3075, 1, 'admin', '保存菜单', 114, 'com.cloudht.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-18 16:33:29'),
	(3076, 1, 'admin', '编辑菜单', 11, 'com.cloudht.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-18 16:33:35'),
	(3077, 1, 'admin', '编辑菜单', 9, 'com.cloudht.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-18 16:34:05'),
	(3078, 1, 'admin', '更新菜单', 143, 'com.cloudht.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-18 16:35:11'),
	(3079, 1, 'admin', '添加菜单', 5, 'com.cloudht.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-18 16:35:27'),
	(3080, 1, 'admin', '保存菜单', 106, 'com.cloudht.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-18 16:36:09'),
	(3081, 1, 'admin', '添加菜单', 5, 'com.cloudht.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-18 16:36:15'),
	(3082, 1, 'admin', '编辑菜单', 9, 'com.cloudht.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-18 16:37:09'),
	(3083, 1, 'admin', '编辑菜单', 9, 'com.cloudht.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-18 16:37:39'),
	(3084, 1, 'admin', '编辑菜单', 9, 'com.cloudht.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-18 16:37:53'),
	(3085, 1, 'admin', '更新菜单', 49, 'com.cloudht.system.controller.MenuController.update()', NULL, '127.0.0.1', '2018-09-18 16:37:56'),
	(3086, 1, 'admin', '添加菜单', 6, 'com.cloudht.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-18 16:37:59'),
	(3087, 1, 'admin', '保存菜单', 71, 'com.cloudht.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-18 16:38:26'),
	(3088, 1, 'admin', '编辑菜单', 7, 'com.cloudht.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-18 16:38:35'),
	(3089, 1, 'admin', '删除菜单', 132, 'com.cloudht.system.controller.MenuController.remove()', NULL, '127.0.0.1', '2018-09-18 16:38:45'),
	(3090, 1, 'admin', '添加菜单', 4, 'com.cloudht.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-18 16:38:50'),
	(3091, 1, 'admin', '保存菜单', 94, 'com.cloudht.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-18 16:39:00'),
	(3092, 1, 'admin', '添加菜单', 5, 'com.cloudht.system.controller.MenuController.add()', NULL, '127.0.0.1', '2018-09-18 16:39:08'),
	(3093, 1, 'admin', '保存菜单', 129, 'com.cloudht.system.controller.MenuController.save()', NULL, '127.0.0.1', '2018-09-18 16:39:51'),
	(3094, 1, 'admin', '编辑用户', 14, 'com.cloudht.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-18 16:40:02'),
	(3095, 1, 'admin', '编辑用户', 10, 'com.cloudht.system.controller.UserController.edit()', NULL, '127.0.0.1', '2018-09-18 16:40:06'),
	(3096, 1, 'admin', '编辑角色', 5, 'com.cloudht.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-18 16:40:22'),
	(3097, 1, 'admin', '更新角色', 334, 'com.cloudht.system.controller.RoleController.update()', NULL, '127.0.0.1', '2018-09-18 16:40:31'),
	(3098, 1, 'admin', '编辑角色', 2, 'com.cloudht.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-18 16:40:45'),
	(3099, 1, 'admin', '编辑角色', 3, 'com.cloudht.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-18 16:40:49'),
	(3100, 1, 'admin', '编辑角色', 3, 'com.cloudht.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-18 16:40:53'),
	(3101, 1, 'admin', '编辑角色', 3, 'com.cloudht.system.controller.RoleController.edit()', NULL, '127.0.0.1', '2018-09-18 16:41:14'),
	(3102, 1, 'admin', '请求访问主页', 80, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 16:41:18'),
	(3103, 1, 'admin', '登录', 6, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 16:41:48'),
	(3104, 1, 'admin', '请求访问主页', 7, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 16:41:48'),
	(3105, 1, 'admin', '登录', 14, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 16:57:15'),
	(3106, 1, 'admin', '请求访问主页', 302, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 16:57:16'),
	(3107, 1, 'admin', '登录', 13, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 17:01:10'),
	(3108, 1, 'admin', '请求访问主页', 85, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 17:01:10'),
	(3109, 1, 'admin', '登录', 5, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 17:02:21'),
	(3110, 1, 'admin', '请求访问主页', 73, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 17:02:21'),
	(3111, 1, 'admin', '登录', 15, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 17:13:27'),
	(3112, 1, 'admin', '请求访问主页', 117, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 17:13:27'),
	(3113, 1, 'admin', '登录', 12, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 18:04:24'),
	(3114, 1, 'admin', '请求访问主页', 270, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 18:04:25'),
	(3115, 1, 'admin', '登录', 15, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-18 18:05:53'),
	(3116, 1, 'admin', '请求访问主页', 89, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-18 18:05:53'),
	(3117, 1, 'admin', '登录', 28, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-19 15:18:29'),
	(3118, 1, 'admin', '请求访问主页', 143, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 15:18:29'),
	(3119, 1, 'admin', '登录', 17, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-19 16:44:44'),
	(3120, 1, 'admin', '请求访问主页', 303, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 16:44:44'),
	(3121, 1, 'admin', '登录', 13, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-19 16:47:21'),
	(3122, 1, 'admin', '请求访问主页', 124, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 16:47:21'),
	(3123, 1, 'admin', '请求访问主页', 657, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 17:12:45'),
	(3124, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/006/htvkant3/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-19 17:13:13'),
	(3125, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/006/htvkant3/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-19 17:13:14'),
	(3126, 1, 'admin', '请求访问主页', 35, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 17:15:17'),
	(3127, 1, 'admin', '请求访问主页', 54, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 17:26:18'),
	(3128, 1, 'admin', '请求访问主页', 32, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 17:27:19'),
	(3129, 1, 'admin', '登录', 18, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-19 18:30:46'),
	(3130, 1, 'admin', '请求访问主页', 623, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 18:30:47'),
	(3131, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/029/h69jzbej/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-19 18:31:41'),
	(3132, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/029/h69jzbej/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-19 18:31:41'),
	(3133, 1, 'admin', '登录', 13, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-19 18:49:17'),
	(3134, 1, 'admin', '请求访问主页', 133, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 18:49:18'),
	(3135, 1, 'admin', '请求访问主页', 23, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-19 20:32:50'),
	(3136, 1, 'admin', '登录', 17, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 08:46:19'),
	(3137, 1, 'admin', '请求访问主页', 108, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 08:46:19'),
	(3138, 1, 'admin', '登录', 20, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 10:25:12'),
	(3139, 1, 'admin', '请求访问主页', 332, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 10:25:13'),
	(3140, 1, 'admin', '登录', 12, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 10:43:04'),
	(3141, 1, 'admin', '请求访问主页', 281, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 10:43:05'),
	(3142, 1, 'admin', '登录', 5, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 10:48:53'),
	(3143, 1, 'admin', '请求访问主页', 265, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 10:48:54'),
	(3144, 1, 'admin', '登录', 16, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 11:51:19'),
	(3145, 1, 'admin', '请求访问主页', 297, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 11:51:20'),
	(3146, 1, 'admin', 'error', NULL, 'http://localhost:8084/cont/contProduct/saveInfo', 'java.lang.NullPointerException', NULL, '2018-09-20 11:55:47'),
	(3147, 1, 'admin', '登录', 15, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 11:57:27'),
	(3148, 1, 'admin', '请求访问主页', 66, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 11:57:27'),
	(3149, 1, 'admin', 'error', NULL, 'http://localhost:8084/cont/contProduct/saveInfo', 'java.lang.NullPointerException', NULL, '2018-09-20 11:57:45'),
	(3150, 1, 'admin', '登录', 15, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 11:58:53'),
	(3151, 1, 'admin', '请求访问主页', 58, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 11:58:53'),
	(3152, 1, 'admin', '登录', 9, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:22:26'),
	(3153, 1, 'admin', '请求访问主页', 16, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 15:22:26'),
	(3154, -1, '获取用户信息为空', '登录', 59, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:31'),
	(3155, -1, '获取用户信息为空', '登录', 9, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:33'),
	(3156, -1, '获取用户信息为空', '登录', 31, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:33'),
	(3157, -1, '获取用户信息为空', '登录', 13, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:33'),
	(3158, -1, '获取用户信息为空', '登录', 22, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:34'),
	(3159, -1, '获取用户信息为空', '登录', 15, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:34'),
	(3160, -1, '获取用户信息为空', '登录', 11, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:34'),
	(3161, -1, '获取用户信息为空', '登录', 25, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:34'),
	(3162, -1, '获取用户信息为空', '登录', 10, 'com.cloudht.system.controller.LoginController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:25:35'),
	(3163, 1, 'admin', '登录', 4, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-20 15:46:30'),
	(3164, 1, 'admin', '请求访问主页', 79, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 15:46:30'),
	(3165, 1, 'admin', '请求访问主页', 17, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-20 15:55:53'),
	(3166, 1, 'admin', '登录', 150, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-21 00:05:48'),
	(3167, 1, 'admin', '请求访问主页', 1662, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-21 00:05:50'),
	(3168, 1, 'admin', '登录', 10, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-21 00:16:09'),
	(3169, 1, 'admin', '请求访问主页', 107, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-21 00:16:09'),
	(3170, 1, 'admin', '登录', 5, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-21 08:56:14'),
	(3171, 1, 'admin', '请求访问主页', 29, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-21 08:56:14'),
	(3172, 1, 'admin', '登录', 25, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-21 11:03:22'),
	(3173, 1, 'admin', '请求访问主页', 99, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-21 11:03:23'),
	(3174, 1, 'admin', '登录', 18, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-21 11:22:25'),
	(3175, 1, 'admin', '请求访问主页', 270, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-21 11:22:26'),
	(3176, 1, 'admin', '登录', 8, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-21 11:35:21'),
	(3177, 1, 'admin', '请求访问主页', 97, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-21 11:35:21'),
	(3178, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/046/uoxd3ke5/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-21 11:35:49'),
	(3179, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/046/uoxd3ke5/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-21 11:35:49'),
	(3180, 1, 'admin', '登录', 23, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-23 12:52:37'),
	(3181, 1, 'admin', '请求访问主页', 138, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-23 12:52:38'),
	(3182, 1, 'admin', '登录', 19, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-23 15:40:16'),
	(3183, 1, 'admin', '请求访问主页', 247, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-23 15:40:16'),
	(3184, 1, 'admin', '登录', 11, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-23 17:00:41'),
	(3185, 1, 'admin', '请求访问主页', 114, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-23 17:00:41'),
	(3186, 1, 'admin', '编辑菜单', 6, 'com.cloudht.system.controller.MenuController.edit()', NULL, '127.0.0.1', '2018-09-23 17:26:50'),
	(3187, 1, 'admin', '登录', 37, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-24 23:17:23'),
	(3188, 1, 'admin', '请求访问主页', 125, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-24 23:17:23'),
	(3189, 1, 'admin', '登录', 26, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-25 15:29:34'),
	(3190, 1, 'admin', '请求访问主页', 653, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 15:29:35'),
	(3191, 1, 'admin', '请求访问主页', 11, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 15:34:43'),
	(3192, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/837/u3hv1qvo/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-25 15:35:29'),
	(3193, 1, 'admin', 'error', NULL, 'http://localhost:8084/endpointChat/837/u3hv1qvo/xhr_streaming', 'org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。', NULL, '2018-09-25 15:35:29'),
	(3194, 1, 'admin', '登录', 16, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-25 15:44:40'),
	(3195, 1, 'admin', '请求访问主页', 282, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 15:44:41'),
	(3196, 1, 'admin', '登录', 24, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-25 15:47:17'),
	(3197, 1, 'admin', '请求访问主页', 92, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 15:47:17'),
	(3198, 1, 'admin', '登录', 17, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-25 16:22:53'),
	(3199, 1, 'admin', '请求访问主页', 136, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 16:22:53'),
	(3200, 1, 'admin', '登录', 19, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-25 17:52:55'),
	(3201, 1, 'admin', '请求访问主页', 310, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 17:52:55'),
	(3202, 1, 'admin', 'error', NULL, 'http://localhost:8084/cont/contProduct/setParams/1', 'org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.executor.ExecutorException: A query was run and no Result Maps were found for the Mapped Statement \'com.cloudht.cont.dao.ContProductParamDao.listByDict\'.  It\'s likely that neither a Result Type nor a Result Map was specified.', NULL, '2018-09-25 17:53:02'),
	(3203, 1, 'admin', '登录', 15, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-25 17:54:46'),
	(3204, 1, 'admin', '请求访问主页', 124, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 17:54:46'),
	(3205, 1, 'admin', '登录', 47, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-25 21:12:44'),
	(3206, 1, 'admin', '请求访问主页', 480, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 21:12:45'),
	(3207, 1, 'admin', '登录', 14, 'com.cloudht.system.controller.MianController.ajaxLogin()', NULL, '127.0.0.1', '2018-09-25 21:35:35'),
	(3208, 1, 'admin', '请求访问主页', 339, 'com.cloudht.system.controller.MianController.main()', NULL, '127.0.0.1', '2018-09-25 21:35:36');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

-- 正在导出表  cms_hnjx.sys_menu 的数据：~44 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`, `gmt_create`, `gmt_modified`) VALUES
	(1, 0, '基础管理', '', '', 0, 'fa fa-desktop', 1, '2018-09-06 14:26:37', NULL),
	(2, 1, '用户管理', 'sys/user/', 'sys:user:user', 1, 'fa fa-user', 2, '2018-09-06 14:12:11', NULL),
	(3, 2, '用户新增', '', 'sys:user:add', 2, '', 3, '2017-08-14 10:51:35', NULL),
	(4, 2, '用户编辑', '', 'sys:user:edit', 2, '', 4, '2017-08-14 10:52:06', NULL),
	(5, 2, '用户删除', NULL, 'sys:user:remove', 2, NULL, 5, '2017-08-14 10:52:24', NULL),
	(6, 2, '用户批删', '', 'sys:user:batchRemove', 2, '', 6, '2017-08-14 17:27:18', NULL),
	(7, 2, '用户停用', NULL, 'sys:user:disable', 2, NULL, 7, '2017-08-14 17:27:43', NULL),
	(8, 2, '用户重置密码', '', 'sys:user:resetPwd', 2, '', 8, '2017-08-14 17:28:34', NULL),
	(9, 1, '角色管理', 'sys/role', 'sys:role:role', 1, 'fa fa-paw', 9, '2018-09-06 14:13:19', NULL),
	(10, 9, '角色新增', '', 'sys:role:add', 2, '', 10, '2017-08-14 10:56:37', NULL),
	(11, 9, '角色编辑', '', 'sys:role:edit', 2, '', 11, NULL, NULL),
	(12, 9, '角色删除', '', 'sys:role:remove', 2, NULL, 12, NULL, NULL),
	(13, 9, '角色批删', '', 'sys:role:batchRemove', 2, NULL, 13, NULL, NULL),
	(14, 1, '部门管理', '/system/sysDept', 'system:sysDept:sysDept', 1, 'fa fa-users', 14, '2018-09-06 14:27:07', NULL),
	(15, 14, '部门增加', '/system/sysDept/add', 'system:sysDept:add', 2, NULL, 15, NULL, NULL),
	(16, 14, '部门编辑', '/system/sysDept/edit', 'system:sysDept:edit', 2, NULL, 16, NULL, NULL),
	(17, 14, '部门刪除', 'system/sysDept/remove', 'system:sysDept:remove', 2, NULL, 17, NULL, NULL),
	(19, 1, '系统菜单', 'sys/menu/', 'sys:menu:menu', 1, 'fa fa-th-list', 19, '2018-09-06 22:55:15', NULL),
	(20, 19, '菜单新增', '', 'sys:menu:add', 2, '', 20, '2017-08-14 10:59:32', NULL),
	(21, 19, '菜单编辑', '', 'sys:menu:edit', 2, '', 21, '2017-08-14 10:59:56', NULL),
	(22, 19, '菜单删除', '', 'sys:menu:remove', 2, '', 22, '2017-08-14 11:00:26', NULL),
	(23, 19, '菜单批删', '', 'sys:menu:batchRemove', 2, NULL, 23, NULL, NULL),
	(24, 0, '系统管理', '', '', 1, 'fa fa-cogs', 24, '2018-09-06 14:27:24', NULL),
	(25, 24, '数据字典', '/common/dict', 'common:dict:dict', 1, '', 25, '2018-09-12 18:09:45', NULL),
	(26, 25, '字典新增', '/common/dict/add', 'common:dict:add', 2, '', 26, '2018-09-12 18:11:24', NULL),
	(27, 25, '字典编辑', '/common/dict/edit', 'common:dict:edit', 2, '', 27, NULL, NULL),
	(28, 25, '字典删除', '', '', 2, '', 28, NULL, NULL),
	(29, 25, '字典批删', '', '', 2, '', 29, NULL, NULL),
	(30, 24, '在线用户', 'sys/online', '', 1, 'fa fa-user', 30, NULL, NULL),
	(31, 24, '系统日志', 'common/log', 'common:log', 1, 'fa fa-warning', 31, '2017-08-14 22:11:53', NULL),
	(32, 31, '刷新', NULL, 'sys:log:list', 2, NULL, 32, '2017-08-14 22:30:22', NULL),
	(33, 31, '删除', NULL, 'sys:log:remove', 2, NULL, 33, '2017-08-14 22:30:43', NULL),
	(34, 31, '清空', NULL, 'sys:log:clear', 2, NULL, 34, '2017-08-14 22:31:02', NULL),
	(35, 24, '运行监控', '/druid/index.html', '', 1, 'fa fa-caret-square-o-right', 35, NULL, NULL),
	(36, 0, '系统工具', '', '', 0, 'fa fa-gear', 36, NULL, NULL),
	(37, 36, '代码生成', 'common/generator', 'common:generator', 1, 'fa fa-code', 37, NULL, NULL),
	(38, 36, '计划任务', 'common/job', 'common:taskScheduleJob', 1, 'fa fa-hourglass-1', 38, NULL, NULL),
	(39, 36, 'swagger', '/swagger-ui.html', '', 1, '', 39, NULL, NULL),
	(40, 0, '内容管理', '', '', 0, 'fa fa-newspaper-o', 40, NULL, NULL),
	(178, 1, '文件管理', '/common/sysFile', 'common:sysFile:sysFile', 1, '', NULL, NULL, NULL),
	(179, 40, '产品管理', '/cont/contProduct', 'cont:contProduct:contProduct', 1, 'fa fa-gift', 1, NULL, NULL),
	(180, 179, '新增', '', 'cont:contProduct:add', 2, '', 1, NULL, NULL),
	(182, 179, '修改', '', 'cont:contProduct:edit', 2, '', 2, NULL, NULL),
	(183, 179, '删除', '', 'cont:contProduct:remove', 2, '', 3, NULL, NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- 正在导出表  cms_hnjx.sys_role 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_sign`, `remark`, `user_id_create`, `gmt_create`, `gmt_modified`) VALUES
	(1, '超级用户角色', 'admin', '拥有最高权限', 2, '2017-08-12 00:43:52', '2017-08-12 19:14:59'),
	(2, '销售用户角色', NULL, '', NULL, NULL, NULL),
	(3, '销售经理角色', NULL, '', NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8637 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系';

-- 正在导出表  cms_hnjx.sys_role_menu 的数据：~75 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`) VALUES
	(8092, 3, 175),
	(8093, 3, 174),
	(8094, 3, 171),
	(8095, 3, 169),
	(8096, 3, 168),
	(8097, 3, 167),
	(8098, 3, 7),
	(8099, 3, 3),
	(8100, 3, 173),
	(8101, 3, 170),
	(8102, 3, 159),
	(8103, 3, 177),
	(8104, 3, -1),
	(8105, 3, 105),
	(8106, 3, 1),
	(8107, 3, 2),
	(8171, 2, 177),
	(8172, 2, 175),
	(8173, 2, 171),
	(8174, 2, 166),
	(8175, 2, 165),
	(8176, 2, 164),
	(8177, 2, 168),
	(8178, 2, 167),
	(8179, 2, 170),
	(8180, 2, 160),
	(8181, 2, 169),
	(8182, 2, 159),
	(8183, 2, -1),
	(8184, 2, 105),
	(8592, 1, 39),
	(8593, 1, 38),
	(8594, 1, 37),
	(8595, 1, 35),
	(8596, 1, 34),
	(8597, 1, 33),
	(8598, 1, 32),
	(8599, 1, 30),
	(8600, 1, 29),
	(8601, 1, 28),
	(8602, 1, 27),
	(8603, 1, 26),
	(8604, 1, 178),
	(8605, 1, 23),
	(8606, 1, 22),
	(8607, 1, 21),
	(8608, 1, 20),
	(8609, 1, 17),
	(8610, 1, 16),
	(8611, 1, 15),
	(8612, 1, 13),
	(8613, 1, 12),
	(8614, 1, 11),
	(8615, 1, 10),
	(8616, 1, 8),
	(8617, 1, 7),
	(8618, 1, 6),
	(8619, 1, 5),
	(8620, 1, 4),
	(8621, 1, 3),
	(8622, 1, 36),
	(8623, 1, 31),
	(8624, 1, 25),
	(8625, 1, 24),
	(8626, 1, 19),
	(8627, 1, 14),
	(8628, 1, 9),
	(8629, 1, 2),
	(8630, 1, 1),
	(8631, 1, 40),
	(8632, 1, 183),
	(8633, 1, 182),
	(8634, 1, 180),
	(8635, 1, 179),
	(8636, 1, -1);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_task 结构
CREATE TABLE IF NOT EXISTS `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 正在导出表  cms_hnjx.sys_task 的数据：1 rows
/*!40000 ALTER TABLE `sys_task` DISABLE KEYS */;
INSERT INTO `sys_task` (`id`, `cron_expression`, `method_name`, `is_concurrent`, `description`, `update_by`, `bean_class`, `create_date`, `job_status`, `job_group`, `update_date`, `create_by`, `spring_bean`, `job_name`) VALUES
	(2, '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.bootdo.common.task.WelcomeJob', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', NULL, '', 'welcomJob');
/*!40000 ALTER TABLE `sys_task` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` bigint(32) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '出身日期',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 正在导出表  cms_hnjx.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`user_id`, `username`, `name`, `password`, `dept_id`, `email`, `mobile`, `status`, `user_id_create`, `gmt_create`, `gmt_modified`, `sex`, `birth`, `pic_id`, `live_address`, `hobby`, `province`, `city`, `district`) VALUES
	(1, 'admin', '超级管理员', '53e41f3d8e0a9bf52059ef246d11abd4', 1, 'admin@example.com', '17699999999', 1, 1, '2017-08-15 21:40:39', '2017-08-15 21:41:00', 96, '2017-12-14 00:00:00', 138, 'ccc', '122;121;', '北京市', '北京市市辖区', '东城区'),
	(2, 'hzof', '刘建华', '2a233769ac7d6803bc7ebb2b34214e57', 1, 'hzof@qq.com', '18635098085', 1, NULL, NULL, NULL, 96, '1993-01-07 00:00:00', NULL, '', '121;', '山西省', '晋中市', '榆次区');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_user_plus 结构
CREATE TABLE IF NOT EXISTS `sys_user_plus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `payment` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 正在导出表  cms_hnjx.sys_user_plus 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_plus` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_plus` ENABLE KEYS */;

-- 导出  表 cms_hnjx.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户与角色对应关系';

-- 正在导出表  cms_hnjx.sys_user_role 的数据：~18 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1),
	(4, 4, 2),
	(9, 3, 2),
	(16, 9, 2),
	(17, 10, 2),
	(20, 7, 2),
	(22, 12, 2),
	(24, 14, 3),
	(27, 16, 3),
	(28, 2, 1),
	(31, 17, 2),
	(34, 13, 2),
	(35, 8, 2),
	(36, 11, 2),
	(37, 18, 2),
	(39, 6, 2),
	(41, 5, 2),
	(42, 15, 2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
