# 依赖环境
FROM openjdk:17

EXPOSE 8080
ADD javaProject-0.0.1-SNAPSHOT.jar java2project.jar
RUN sh -c 'touch /java2project.jar'
ENTRYPOINT ["java","-jar","/java2project.jar"]