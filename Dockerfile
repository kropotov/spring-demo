FROM maven:3.9.4-eclipse-temurin-17-alpine as build
COPY .. .
RUN mvn -f pom.xml clean -DskipTests=true package
FROM eclipse-temurin:17
COPY --from=build /spring-jpa/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]