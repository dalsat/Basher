package me.dalsat.basher.store.mongo;

import me.dalsat.basher.command.Message;
import me.dalsat.basher.store.core.User;
import org.mongodb.morphia.annotations.*;


/**
 * Extends the <code>Message</code> class to define the mappings to
 * the MongoDB database.
 */
@Embedded
public class MongoMessage extends Message {

    @Transient
    private User userBuffer;

    @PrePersist
    void prePersist() {
        // remove the user to break circular dependency
        userBuffer = user;
        user = null;
    }

    @PostPersist
    void postPersist() {
        // put the user back
        user = userBuffer;
    }


    public static MongoMessage newMessage(User user, String text) {
        MongoMessage newMessage = new MongoMessage();
        newMessage.user = user;
        newMessage.text = text;
        return newMessage;
    }

}
