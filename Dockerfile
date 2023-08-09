FROM openjdk:17-alpine
ADD web/target/api-*.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]