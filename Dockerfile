#FROM adoptopenjdk/openjdk19:alpine-jre
#ADD target/VehicleHub-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:19-oracle
ADD target/VehicleHub-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]