#基于jdk8镜像进行构建，创建的镜像已经配置好java环境
FROM java:8
VOLUME /tmp
ADD demo-0.0.1-SNAPSHOT.jar springio.demo.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springio.demo.jar"]