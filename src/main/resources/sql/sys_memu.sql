CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL,
  `name` varchar(256) NOT NULL COMMENT '名称',
  `imgUrl` varchar(256) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `parentId` varchar(32) DEFAULT NULL COMMENT '父id',
  `permId` varchar(100) NOT NULL COMMENT '权限Id',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL,
  `available` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';