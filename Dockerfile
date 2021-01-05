FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
 
COPY ./ ./
 
RUN mvn clean package

RUN pwd
 
FROM openjdk:8-jre-alpine3.9
 
COPY --from=MAVEN_BUILD target/metricsingestion-0.0.1-SNAPSHOT.jar /metrics.jar
 
CMD ["java", "-jar", "/metrics.jar"]
