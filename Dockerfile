FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY pom.xml .

COPY --chmod=0755 mvnw .

COPY .mvn/ .mvn/


RUN ./mvnw dependency:go-offline

COPY src/ src/

RUN ./mvnw package -DskipTests

ENTRYPOINT ["java", "-jar", "target/food-delivery-api-0.0.1-SNAPSHOT.jar"]
