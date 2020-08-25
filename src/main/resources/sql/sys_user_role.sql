-- demo_test.sys_user_role definition

CREATE TABLE `sys_user_role` (
  `userId` varchar(32) NOT NULL COMMENT '用户id',
  `roleId` varchar(32) NOT NULL COMMENT '角色ID',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';