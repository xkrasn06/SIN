#!/bin/bash

mkdir -p build
chmod a+xr build

javac -sourcepath src -d build -encoding UTF-8 -classpath libs/jade.jar:libs/commons-codec-1.3.jar src/sin/*.java

mkdir -p bin

cp libs/jade.jar bin/jade.jar
cp libs/commons-codec-1.3.jar bin/commons-codec-1.3.jar

jar cmf src/manifest bin/sin-project.jar build/classes/*.class