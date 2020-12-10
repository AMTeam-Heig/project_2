#mvn clean install

mvn clean package

cp ./target/Project_01.war ./docker/images/openliberty/liberty/artifact

# shellcheck disable=SC2164
cd docker/topologies/

docker-compose up --build
