FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY . .

RUN mvn clean package \
    -pl consumer \
    -am \
    -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build \
    /workspace/consumer/target/consumer-1.0.0-SNAPSHOT.jar \
    app.jar

ENTRYPOINT ["java","-jar","app.jar"]