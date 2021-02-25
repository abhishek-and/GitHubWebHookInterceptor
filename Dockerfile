FROM openjdk:11
EXPOSE 8080
ADD target/github-webhook-interceptor.jar github-webhook-interceptor.jar
ENTRYPOINT ["java", "-jar", "/github-webhook-interceptor.jar"]