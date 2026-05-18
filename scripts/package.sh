#!/bin/sh
javac -d bin/WEB-INF/classes -classpath "libs/*" src/main/tcd3/game2048/*
jar -cvf Game2048.war -C src/webapp . -C bin .
