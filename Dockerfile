FROM adoptopenjdk/maven-openjdk11

COPY target/bus-auth-service-1.0.0.jar app.jar

EXPOSE 8077:8077

RUN apt-get update
RUN apt-get install -y gcc
RUN apt-get install -y curl
ENTRYPOINT ["java","-jar","app.jar"]