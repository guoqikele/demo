-- demo_test.sys_user definition

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `fullName` varchar(100) NOT NULL COMMENT '全名',
  `mail` varchar(128) NOT NULL COMMENT '邮箱',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话号码',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `createTime` timestamp NULL DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '是否可用',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `gender` tinyint(4) NOT NULL COMMENT '性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';