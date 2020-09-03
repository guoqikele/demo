#!/bin/bash
#容器名称
CONTAINT_NAME=springio-demo
JAR_PATH=/root/docker/demo/
VARR="X"
#镜像名称
IMAGE_NAME=springio-demo
VERSION=latest
LOCAL_DEPOSITORY=192.168.71.3:6000
RUN_CONTAINT=`docker ps | grep ${CONTAINT_NAME}`
echo $RUN_CONTAINT
#判断容器是否启动
if [ "$VARR$RUN_CONTAINT" = "$VARR" ]
then
	#暂停容器
	docker stop ${CONTAINT_NAME}
	#删除容器
	docker rm $CONTAINT_NAME
	echo "删除容器${CONTAINT_NAME}成功"
	#删除镜像
	docker rmi $IMAGE_NAME
	echo "删除镜像${IMAGE_NAME}成功"
else
	#判断容器是否存在
	EXIST_CONTAINT=`docker ps -a | grep ${CONTAINT_NAME}`
	if [ "$VARR$EXIST_CONTAINT" = "$VARR" ]
	then
		#删除容器
		docker rm $CONTAINT_NAME
		echo "删除容器${CONTAINT_NAME}成功"
		#删除镜像
		docker rmi $IMAGE_NAME
		echo "删除镜像${IMAGE_NAME}成功"
	else
		#查看镜像是否存在
		EXIST_IMAGE=`docker images | grep IMAGE_NAME`
		echo $EXIST_IMAGE
		#存在则删除镜像
		if [ "$VARR$EXIST_IMAGE" = "$VARR" ]
		then
			#删除镜像
			docker rmi $IMAGE_NAME
			echo "删除镜像${IMAGE_NAME}成功"
		fi
	fi
fi
#进入jar所在目录
cd $JAR_PATH
pwd
#拉取镜像
docker pull $LOCAL_DEPOSITORY/$IMAGE_NAME:$VERSION
echo "镜像${IMAGE_NAME}打包完成"
#运行镜像
docker run -d -p 8081:8081 --name $CONTAINT_NAME $IMAGE_NAME