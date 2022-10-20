FROM openjdk:11-jre-slim

WORKDIR root/

ADD build/libs/hello-spring-0.0.1-SNAPSHOT.jar ./application.jar

CMD java -server -Xmx256M -jar /root/application.jar