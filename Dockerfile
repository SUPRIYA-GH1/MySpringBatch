#define base docker image
FROM openjdk:17-alpine
LABEL maintainer="SupriyaGh"
ADD target/mini-project-0.0.1-SNAPSHOT.jar mini-project.jar
ENTRYPOINT ["java", "-jar", "mini-project.jar"]