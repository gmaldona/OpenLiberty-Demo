# Demo App (OpenLiberty + mongoDB)

## Instructions on how to run

After clone the repo, run docker to get mongodb image and process

```
docker pull mongo
docker run --name mongo-sample -p 127.0.0.1:27017:27017 -d mongo
```

After that, get into docker bash to create new user and database

```
docker exec -it mongo-sample bash
```

```
mongo
use testdb
db.createUser({user: 'sampleUser', pwd:'openliberty', roles: [{ role: 'readWrite', db:'testdb'}]})
```

Then just exit twice to get out

### Running the project

#### Backend

First time run: 
```
mvn clean package liberty:dev
```
After the first time, just do: 
```
mvn liberty:dev 
```

The server will now listen to port 9085. 
http://localhost:9085/teamnames/addteamname


