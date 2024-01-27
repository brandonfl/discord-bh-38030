FROM maven:openjdk AS build
COPY src /src/bot/src
COPY pom.xml /src/bot
RUN mvn -f /usr/src/bot/pom.xml clean package

FROM openjdk:11-slim

LABEL maintainer="Brandon Fontany--Legall <brandon@fontany-legall.xyz>"
LABEL description="Discord BH 38030"

COPY --from=build /src/bot/target/bot.war .

ENTRYPOINT ["java","-jar","bot.war"]