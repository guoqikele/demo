-- demo_test.sys_user_perm definition

CREATE TABLE `sys_user_perm` (
  `userId` varchar(32) NOT NULL COMMENT '用户ID',
  `permId` varchar(32) NOT NULL COMMENT '权限ID',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;