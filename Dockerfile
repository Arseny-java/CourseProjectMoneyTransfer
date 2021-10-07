FROM openjdk:14-ea-12-jdk-alpine
EXPOSE 8080
ADD target/CourseProjectFinal-0.0.1-SNAPSHOT.jar /service.jar
ENTRYPOINT ["java","-jar","/service.jar"]

