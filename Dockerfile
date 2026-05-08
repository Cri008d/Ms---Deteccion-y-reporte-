# Etapa de construcción (Build)
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila el proyecto saltando los tests para mayor velocidad
RUN mvn clean package -DskipTests

# Etapa de ejecución (Run)
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copia el .jar generado desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar
# Por seguridad, no ejecutamos como root
USER nobody
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]