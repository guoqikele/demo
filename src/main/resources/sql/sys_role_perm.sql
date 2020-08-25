-- demo_test.sys_role_perm definition

CREATE TABLE `sys_role_perm` (
  `roleId` varchar(32) NOT NULL COMMENT '角色ID',
  `permId` varchar(32) NOT NULL COMMENT '权限ID',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';