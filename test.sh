#!/bin/bash
#容器名称
base_contain_name='springio-demo'
#镜像名称
base_image_name='demo/springio'
#容器名称
contain_name=''
#镜像名称
image_name=''
#当前镜像版本
image_version=''
#标记位，记录容器是否运行
flag=false
#获取所有运行的容器
run_contains=$(docker ps)
#转换数组
arr_run_contains=(${run_contains})
for run_contain in ${arr_run_contains}
do
  if [[ ${run_contain} == *${base_contain_name}* ]];
  then
    #赋值
    contain_name=${run_contain}
    flag=true
  else
    #获取所有存在的容器
    exist_contains=$(docker ps -a)
    #转换数组
    arr_exist_contains=(${exist_contains})
    #遍历所有的容器
    for exist_contain in ${arr_exist_contains}
    do
      if [[ ${exist_contain} == *${base_demo_contain}* ]];
      then
        #赋值
        contain_name=${exist_contain}
      fi
    done
  fi
done
echo "容器名称${contain_name}"
#获取镜像
exist_images=$(docker images)
#转换数组
arr_exist_images=(${exist_images})
index=0
for (( i = 0; i < ${#arr_exist_images[*]}; i++ )); do
    if [[ ${arr_exist_images[i]} == *${base_image_name}* ]]; then
      #赋值
      image_name=${arr_exist_images[i]}
      index=`expr $i + 1`
      echo $image_name
    fi
    if [[ $index != 0 && $i == $index ]]; then
      image_version=${arr_exist_images[i]}
      echo ${image_version}
    fi
done
if [[ $flag == true ]];
 then
    if [ -z ${contain_name} ]; then
      #删除容器
      docker rm ${contain_name}
      echo "删除容器 ${contain_name} 成功"
    fi
  else
    if [ -z ${contain_name} ]; then
      #暂停容器
      docker stop ${contain_name}
      echo "停止容器 ${contain_name} 成功"
      #删除容器
      docker rm ${contain_name}
      echo "删除容器 ${contain_name} 成功"
    fi
fi

if [ -z $image_name ]; then
    docker rmi "${image_name}:${image_version}"
    echo "删除镜像 ${image_name}:${image_version} 成功"
fi

#获取本地镜像仓库的镜像
tag=""
#获取镜像名称
online_images=`curl http://192.168.71.3:6000/v2/_catalog | jq '.repositories'`
echo "$online_images"
online_images=${online_images/[/}
online_images=${online_images/]/}
#"阿斯克码值转换
temp=`printf "%x" 34`
quat=`printf "\\x${temp}"`
online_images=${online_images//${quat}/}
echo "在线镜像${online_images}"
arr_online_images=(${online_images//,/ })
for online_image in $arr_online_images; do
    if [[ $base_image_name == $online_image ]]; then
        #获取版本
        online_tags_image_version=`curl http://192.168.71.3:6000/v2/$base_image_name/tags/list | jq '.tags'`
        echo "镜像版本信息 $online_tags_image_version"
        lenth=${#online_tags_image_version[*]}
        tag="${base_image_name}:${online_tags_image_version[$lenth]}"
        #拉取最新的镜像
        docker pull tag
        #启动最新的版本
        docker run -d -p 8081:8081 -v /home/demo:/home/demo --name ${base_contain_name}:${online_tags_image_version[$lenth]} $tag
        echo "容器${base_contain_name}:${online_tags_image_version[$lenth]}启动成功"
    fi
done