## Open Liberty Demo Web Application for CSC 480

Running open liberty in a docker container

mvn package
docker pull icr.io/appcafe/open-liberty:full-java11-openj9-ubi
docker build -t webapp-demo:1.0-SNAPSHOT .
docker run -d --name webapp-demo -p 9080:9080 webapp-demo:1.0-SNAPSHOT
