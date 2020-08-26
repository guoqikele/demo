docker ps -a

docker stop springio-demo
docker rm -f springio-demo
docker run -d -p 8081:8081 --name springio-demo springio-demo

docker ps -a