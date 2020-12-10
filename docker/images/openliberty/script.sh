#!/bin/bash

#make sure that we generate the WAR
# mvn  -f ../../../pom.xml clean package
#build the image by the Dockerfile
cp ../../../target/Project_01.war ./liberty/artifact/Project_01.war

