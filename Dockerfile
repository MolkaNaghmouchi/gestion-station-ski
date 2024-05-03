
FROM eclipse-temurin:17-jdk-alpine
 
ARG JAR_FILE=./target/gestion-station-ski-12.0.jar
#ARG YAML_FILE=./src/main/resources/application.properties
WORKDIR /home/esprit/deploy
COPY ${JAR_FILE} api.jar
#COPY ${YAML_FILE} application.yaml
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "api.jar"] 
