#!/bin/bash
#容器名称
CONTAINT_NAME=springio-demo
#镜像名称
OLD_VERSION=0.0.1-SNAPSHOT
NEW_VERSION=0.0.1-SNAPSHOT
IMAGE_NAME=demo/springio
#标签名称
OLD_REMOTE_IMAGE_NAME=192.168.71.3:6000/${IMAGE_NAME}:${OLD_VERSION}
NEW_REMOTE_IMAGE_NAME=192.168.71.3:6000/${IMAGE_NAME}:${NEW_VERSION}
RUN_CONTAINT=`docker ps | grep ${CONTAINT_NAME}`
echo ${RUN_CONTAINT}
#判断容器是否启动
if [ -n "${RUN_CONTAINT}" ]
then
	#暂停容器
	docker stop ${CONTAINT_NAME}
	echo "暂停容器${CONTAINT_NAME}成功"
	#删除容器
	docker rm ${CONTAINT_NAME}
	echo "删除容器${CONTAINT_NAME}成功"
	#删除镜像
	docker rmi ${OLD_REMOTE_IMAGE_NAME}
	echo "删除镜像${OLD_REMOTE_IMAGE_NAME}成功"
else
	#判断容器是否存在
	EXIST_CONTAINT=`docker ps -a | grep ${CONTAINT_NAME}`
	if [ -n "${EXIST_CONTAINT}" ]
	then
		#删除容器
		docker rm ${CONTAINT_NAME}
		echo "删除容器${CONTAINT_NAME}成功"
		#删除旧的镜像
		docker rmi ${OLD_REMOTE_IMAGE_NAME}
		echo "删除镜像${OLD_REMOTE_IMAGE_NAME}成功"
	else
		#查看镜像是否存在
		EXIST_IMAGE=`docker images | grep ${OLD_REMOTE_IMAGE_NAME}`
		echo ${OLD_REMOTE_IMAGE_NAME}
		#存在则删除旧的镜像
		if [ -n "${EXIST_IMAGE}" ]
		then
			#删除旧的镜像
			docker rmi ${OLD_REMOTE_IMAGE_NAME}
			echo "删除镜像${OLD_REMOTE_IMAGE_NAME}成功"
		fi
	fi
fi
#拉取新的镜像
docker pull ${NEW_REMOTE_IMAGE_NAME}
#运行镜像
docker run -d -p 8081:8081 -v /home/demo:/home/demo --name ${CONTAINT_NAME} ${NEW_REMOTE_IMAGE_NAME}