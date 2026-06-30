FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .

# Baixa as dependências primeiro (melhora o cache do Docker)
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# ---------- RUNTIME ----------

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]