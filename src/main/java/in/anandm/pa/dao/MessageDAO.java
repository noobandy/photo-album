package in.anandm.pa.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class MessageDAO {

    private MongoCollection<Document> messages;

    /**
     * @param messages
     */
    public MessageDAO(MongoCollection<Document> messages) {
        super();
        this.messages = messages;
    }

    public Document saveMessage(String message) {
        Document messageDocument = new Document("message", message);
        messages.insertOne(messageDocument);
        return messageDocument;
    }

    public List<Document> findAllMessages() {
        List<Document> allMessages = new ArrayList<Document>();
        allMessages = messages.find().into(allMessages);
        return allMessages;
    }
}
