FROM openjdk:17-jdk-alpine
RUN mkdir /app
WORKDIR /app
COPY build/libs/credit-application-system-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java","-jar","/app/app.jar"]