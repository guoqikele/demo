-- demo_test.sys_perm definition

CREATE TABLE `sys_perm` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '名称',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父id',
  `description` varchar(1024) DEFAULT NULL COMMENT '权限描述',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `available` int(11) NOT NULL COMMENT '是否可用',
  `code` varchar(100) NOT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;