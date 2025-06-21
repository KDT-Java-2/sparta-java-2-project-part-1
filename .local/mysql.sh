port=3306
image="mysql:8.0"
container_name="spring-mysql"
MYSQL_ROOT_PASSWORD=root
MYSQL_DATABASE=project_part1

brew install colima docker
colima start
containerID=$(docker ps -a | grep $image | awk '{print $1}')
if [ -n "$containerID" ]; then
  docker start $containerID
else
  docker run --name $container_name \
  -p $port:$port \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  -d $image
fi