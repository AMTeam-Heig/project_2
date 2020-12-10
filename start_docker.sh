#!/usr/bin/env bash

#mvn clean install

# docker run -d --name=gamification -p "9081:9081" stellucidam/amt-gamification

mvn clean package

cp ./target/Project_01.war ./docker/images/openliberty/liberty/artifact

# shellcheck disable=SC2164
cd docker/topologies/

docker-compose pull

docker-compose up --build
