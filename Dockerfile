FROM openjdk:17-alpine
ADD api/target/api-*.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]