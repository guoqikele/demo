#!/bin/bash
#容器名称
CONTAINT_NAME=springio-demo
#镜像名称
VERSION=0.0.1-SNAPSHOT
IMAGE_NAME=demo/springio
#标签名称
REMOTE_IMAGE_NAME=192.168.71.3:6000/${IMAGE_NAME}:${VERSION}
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
	docker rmi ${REMOTE_IMAGE_NAME}
	echo "删除镜像${REMOTE_IMAGE_NAME}成功"
else
	#判断容器是否存在
	EXIST_CONTAINT=`docker ps -a | grep ${CONTAINT_NAME}`
	if [ -n "${EXIST_CONTAINT}" ]
	then
		#删除容器
		docker rm ${CONTAINT_NAME}
		echo "删除容器${CONTAINT_NAME}成功"
		#删除镜像
		docker rmi ${REMOTE_IMAGE_NAME}
		echo "删除镜像${REMOTE_IMAGE_NAME}成功"
	else
		#查看镜像是否存在
		EXIST_IMAGE=`docker images | grep ${REMOTE_IMAGE_NAME}`
		echo ${REMOTE_IMAGE_NAME}
		#存在则删除镜像
		if [ -n "${EXIST_IMAGE}" ]
		then
			#删除镜像
			docker rmi ${REMOTE_IMAGE_NAME}
			echo "删除镜像${REMOTE_IMAGE_NAME}成功"
		fi
	fi
fi
#拉取镜像
docker pull ${REMOTE_IMAGE_NAME}
#运行镜像
docker run -d -p 8081:8081 --name ${CONTAINT_NAME} ${REMOTE_IMAGE_NAME}