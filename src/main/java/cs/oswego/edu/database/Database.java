package cs.oswego.edu.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class Database {

    @Inject
    @ConfigProperty(name = "mongo.hostname", defaultValue = "127.0.0.1")
    String hostname;

    @Inject
    @ConfigProperty(name = "mongo.port", defaultValue = "27017")
    int port;

    @Inject
    @ConfigProperty(name = "mongo.database")
    String database;

    @Inject
    @ConfigProperty(name = "mongo.user", defaultValue = "root")
    String user;

    @Inject
    @ConfigProperty(name = "mongo.password")
    String password;

    @Produces
    public MongoClient createMongoClient() {
        MongoCredential credentials = MongoCredential.createCredential(user, database, password.toCharArray());
        return new MongoClient(
                new ServerAddress(hostname, port),
                credentials,
                new MongoClientOptions.Builder().build()
        );
    }

    @Produces
    public MongoDatabase getDB(MongoClient client) {
        return client.getDatabase(database);
    }

    public void close(@Disposes MongoClient toClose) {
        toClose.close();
    }
}