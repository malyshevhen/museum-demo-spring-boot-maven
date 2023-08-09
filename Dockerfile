FROM openjdk:17-alpine
ADD web/target/web-*.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]