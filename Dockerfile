FROM openjdk:8-jdk AS build
WORKDIR app
COPY . .
RUN ./mvnw clean install spring-boot:repackage

FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY --from=build app/target/challenge-*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
