FROM maven:3.8.2-jdk-11

COPY . .

RUN mvn clean package

# set the startup command to execute the jar
CMD ["java", "-jar", "target/whatsx-1.0-jar-with-dependencies.jar"]