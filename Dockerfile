FROM openjdk:17-alpine
RUN addgroup --system spring && adduser --system spring && adduser spring spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java -Dapi.security.token.secret=${JWT_TOKEN} -jar /app.jar"]