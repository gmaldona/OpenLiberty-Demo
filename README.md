# Calibrated Peer Review Demo

## Getting Started

This web application is built on the production environment using Docker images. The guide will contain 2 sections, aimed more heavily toward the Docker deployment on Linux environment.

## Running the Project

This guide will assume that [Docker](https://docs.docker.com/engine/install/) and [Docker Compose](https://docs.docker.com/compose/install/) are already installed on your machine.

**Step 1:** Pull the following Docker images:
- Maven: `docker pull maven`
- Open Liberty: `docker pull icr.io/appcafe/open-liberty:full-java11-openj9-ubi`
- MongoDB: `docker pull mongo`
- Nginx: `docker pull nginx`

**Step 2:** Clone the repository.

**Step 3:** Navigate to `src/main/webapp/META-INF` directory and update the config variables in `microprofile-config.properties`. At the same time, make sure your database name, username, and password match the one in the `mongo-init.js` in the root directory.

**Step 4:** At the root of the directory, create a `.env` file using `.env.example` as the template to fill out the variables.

**Step 5:** Update the server name and localhost port value in the `nginx.conf` file to set up a reverse proxy and make sure the listening port matches the one that is mapped from in `docker-compose.yml` (`8080` by default). If you do not wish to use a reverse proxy, simply ignore this file and remove the `nginx` service in your `docker-compose.yml`.

**Step 6:** Create the Docker image and start the containers by running `docker-compose -f "docker-compose.yml" up -d --build` in the same directory.

## Local Development Environment

For the local development environment, a Docker setup is not necessary. This guide assumes you already have [Maven](https://maven.apache.org/guides/getting-started/windows-prerequisites.html) 3.8.4 or higher and [JDK 17](https://openjdk.java.net/projects/jdk/17/) or higher installed. For Windows users:

**Step 1:** Install [MongoDB for Windows](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/#install-mongodb-community-edition) or [MongoDB for Mac](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/). Optionally, install [MongoDB Compass](https://www.mongodb.com/products/compass) and [mongosh](https://docs.mongodb.com/mongodb-shell/) for your own convenience.

**Step 2:** Using the MongoDB shell (either using MongoDB shell from terminal or the built-in one in MongoDB Compass), create authentication for the database using the command in `mongo-init.js` but with the database name, username, and password of your choice.

**Step 3:** Navigate to `src/main/webapp/META-INF` directory and update the config variables in `microprofile-config.properties` to match the values that you set up in step 2. Use the command `use <your database name>` to select a database.

**Step 4:** Run `mvn liberty:dev` to start the project in developer mode. The web app should be running on http://127.0.0.1:9080 

## Contributing

Contributors are more than welcome to improve the project by creating a new issue to report bugs, suggest new features, or make changes to the source code by making a pull request. To have your work merged in, please make sure the following is done:

1. Fork the repository and create your branch from master.
2. If you’ve fixed a bug or added something new, add a comprehensive list of changes.
3. Ensure that your code is tested, functional, and is linted.

## Built On

This project is built on:

- [Docker](https://www.docker.com/)
- [Java](https://openjdk.java.net/)
- [Maven](https://maven.apache.org/)
- [MongoDB](https://www.mongodb.com/)
- [Nginx](https://www.nginx.com/)
- [Open Liberty](https://openliberty.io/)
- [ReactJS](https://reactjs.org/)


