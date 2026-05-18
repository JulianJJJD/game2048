FROM tomcat:10-jdk21
COPY target/game2048.war /usr/local/tomcat/webapps/game2048.war
