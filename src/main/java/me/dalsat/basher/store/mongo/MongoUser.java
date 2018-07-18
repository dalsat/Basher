package me.dalsat.basher.store.mongo;


import me.dalsat.basher.command.Message;
import me.dalsat.basher.store.core.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * Implements a user that can be stored into a <code>MongoStore</code>
 * repository.
 */
@Entity("users")
@Indexes({ @Index(fields = @Field("name")) })
public class MongoUser implements User {

    @Id
    private ObjectId id;

    private String name;

    @Reference
    private List<MongoUser> follows = new ArrayList<>();

    @Embedded
    private List<MongoMessage> messages = new ArrayList<>();

    @Transient
    private Datastore datastore;

    static MongoUser named(String name) {
        var newUser = new MongoUser();
        newUser.name = name;
        return newUser;
    }

    void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    private void commit() {
        datastore.save(this);
    }

    public ObjectId getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public void follow(User user) {
        var mongoUser = (MongoUser)user;
        if (!follows.contains(mongoUser)) {
            follows.add(mongoUser);
        }
        commit();
    }

    @Override
    public void postMessage(String message) {
        var newMessage = MongoMessage.newMessage(this, message);
        messages.add(newMessage);
        commit();
    }

    @Override
    public List<Message> listOfMessages() {
        messages.forEach(message -> message.setUser(this));
        return List.copyOf(messages);
    }

    @Override
    public Stream<Message> wall() {
        return follows.stream()
                .flatMap(eachUser -> eachUser.listOfMessages().stream())
                .sorted();
    }

    @Override
    public String toString() {
        return String.format("MongoUser[id='%s', username='%s']", id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return this.name.equals(((User) obj).getName());
        } else {
            System.out.println(obj.getClass() + " is not instance of User");
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
