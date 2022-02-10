package cs.oswego.edu.database;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ibm.websphere.crypto.PasswordUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

@ApplicationScoped
public class Database {

    // Inject from config.props
    @Inject
    @ConfigProperty(name = "mongo.hostname", defaultValue = "localhost")
    String hostname; // db host name

    @Inject
    @ConfigProperty(name = "mongo.port", defaultValue = "27017")
    int port; // db port

    @Inject
    @ConfigProperty(name = "mongo.dbname", defaultValue = "testdb")
    String dbName; // db Name

    @Inject
    @ConfigProperty(name = "mongo.user")
    String user; // db user

    @Inject
    @ConfigProperty(name = "mongo.pass.encoded")
    String encodedPass; // db passcode

    @Produces
    public MongoClient createMongo() { // returns an instance of MongoClient
        String password = PasswordUtil.passwordDecode(encodedPass);

        MongoCredential creds = MongoCredential.createCredential(
                user,
                dbName,
                password.toCharArray()
        );

        // new mongo instance
        return new MongoClient(
                new ServerAddress(hostname, port),
                creds,
                new MongoClientOptions
                        .Builder().
                        build()
        );
    }

    @Produces
    public MongoDatabase createDB(MongoClient client) {
        return client.getDatabase(dbName); // get Database
    }

    // clean up function
    public void close(@Disposes MongoClient toClose) {
        toClose.close(); // close connection to the MongoDatabase instance
    }

}