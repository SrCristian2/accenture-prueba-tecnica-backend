FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_NAME=franchise_db
ENV DB_USERNAME=root
ENV DB_PASSWORD=123456
ENV JPA_DDL_AUTO=update
ENV JPA_SHOW_SQL=true
ENV SERVER_PORT=8080

ENTRYPOINT ["java", "-jar", "app.jar"]
