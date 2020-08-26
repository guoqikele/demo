mvn clean

mvn package -DskipTests

docker rmi -f springio-demo

mvn dockerfile:build

docker images