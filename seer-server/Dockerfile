#FROM maven:3.8.2-jdk-8
#WORKDIR /seer-server
#COPY . .
#RUN mvn clean install
#CMD mvn spring-boot:run

FROM openjdk:8-jdk-alpine
COPY target/seer-server.jar seer-server.jar
ENTRYPOINT ["java","-Xmx32m","-Xss256k","-jar","/seer-server.jar"]
# -Xmx 堆内存的最大大小
# -Xss 每个线程可使用的栈的大小
