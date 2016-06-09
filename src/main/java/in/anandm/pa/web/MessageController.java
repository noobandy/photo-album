package in.anandm.pa.web;

import in.anandm.pa.dao.MessageDAO;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageDAO messageDAO;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public List<Document> getMessages() {
        return messageDAO.findAllMessages();
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public Document postMessage(String message) {
        return messageDAO.saveMessage(message);
    }
}
