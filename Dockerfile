# Stage 1: Build the project and package it into a .jar file.
FROM maven:latest as maven

WORKDIR .
COPY . .

RUN mvn package

# Stage 2: Copy the .jar file and server.xml into the required location.
FROM icr.io/appcafe/open-liberty:full-java11-openj9-ubi

LABEL maintainer="csc480"

COPY --from=maven src/main/liberty/config/server.xml /config/
COPY --from=maven target/*.war /config/apps
