// Edit your user, pwd, and db variables that you want your database to be initialized with when running `docker-compose.yml`.
// Make sure the variables entered here also match the ones in your src/main/webapp/META-INF/microprofile-config.properties file.
db.createUser(
    {
        user: "",
        pwd: "",
        roles: [
            {
                role: "readWrite",
                db: ""
            }
        ]
    }
);
