package in.anandm.pa.web.resources;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Message implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ObjectId objectId;

    private String message;

    /**
     * 
     */
    public Message() {
        super();

    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
