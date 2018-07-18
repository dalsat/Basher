package me.dalsat.basher.store.mongo;

import com.mongodb.MongoClient;
import me.dalsat.basher.store.core.Store;
import me.dalsat.basher.store.core.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class MongoStore implements Store {

    private final Morphia morphia = new Morphia();

    private Datastore datastore;
    private String dbName = "basher";

    public MongoStore() {
        initializeStore();
    }

    public MongoStore(String dbName) {
        this.dbName = dbName;
        initializeStore();
    }

    private void initializeStore() {
        morphia.mapPackage("me.dalsat.basher.store.mongo");

        datastore = morphia.createDatastore(new MongoClient(), dbName);
        datastore.ensureIndexes();
    }

    private Optional<User> getUser(String username) {
        var query = datastore.createQuery(MongoUser.class)
                .field("name").equal(username);
        final List<MongoUser> users = query.asList(new FindOptions().limit(1));
        if (users.size() > 0) {
            var user = users.get(0);
            user.setDatastore(datastore);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public User getOrAddUser(String username) {
        return getUser(username).orElseGet(() -> addUser(username));
    }

    private User addUser(String username) {
        final var newUser = MongoUser.named(username);
        newUser.setDatastore(datastore);
        datastore.save(newUser);
        newUser.follow(newUser);
        return newUser;
    }

    @Override
    public Collection<? extends User> listOfUsers() {
        final var query = datastore.createQuery(MongoUser.class);
        return query.asList();
    }

    @Override
    public void reset() {
        datastore.getDB().dropDatabase();
    }

}
