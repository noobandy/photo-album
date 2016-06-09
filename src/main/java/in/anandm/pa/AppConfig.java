package in.anandm.pa;

import in.anandm.pa.dao.MessageDAO;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Configuration
@ImportResource(value = "classpath:db.properties")
public class AppConfig {

    @Value("${mognodb.uri}")
    private String mongoURI;

    @Value("${mongodb.database}")
    private String database;

    @Bean
    MessageDAO messageDAO(MongoCollection<Document> messages) {
        return new MessageDAO(messages);
    }

    @Bean
    MongoClientURI mongoClientURI() {
        return new MongoClientURI(mongoURI);
    }

    @Bean
    MongoClient mongoClient(MongoClientURI mongoClientURI) {
        return new MongoClient(mongoClientURI);
    }

    @Bean
    MongoDatabase mongoDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase(database);
    }

    @Bean
    MongoCollection<Document> messageCollection(MongoDatabase mongoDatabase) {
        return mongoDatabase.getCollection("messages");
    }
}
