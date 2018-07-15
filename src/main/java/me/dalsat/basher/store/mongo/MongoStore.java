package me.dalsat.basher.store.mongo;

import me.dalsat.basher.command.Message;
import me.dalsat.basher.store.core.Store;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class MongoStore implements Store {

    String configLocation = "basher-config.xml";
    ClassPathXmlApplicationContext context;
    private UserRepository userRepository;

    public MongoStore() {
        context = new ClassPathXmlApplicationContext(configLocation);
//        context = new ClassPathXmlApplicationContext(new ClassPathResource("basher-config.xml").getPath());

        userRepository = context.getBean(UserRepository.class);

    }

    @Override
    public me.dalsat.basher.store.core.User getOrAddUser(String username) {
        var user = userRepository.findByUsername(username);
        return new me.dalsat.basher.store.core.User(1,"ada");
    }

    @Override
    public void postMessage(Message message) {

    }

    @Override
    public List<Message> messagesFor(me.dalsat.basher.store.core.User user) {
        return null;
    }

    @Override
    public Stream<Message> wall(me.dalsat.basher.store.core.User user) {
        return null;
    }

    @Override
    public void follow(me.dalsat.basher.store.core.User follower, me.dalsat.basher.store.core.User followee) {

    }

    @Override
    public void unfollow(me.dalsat.basher.store.core.User follower, me.dalsat.basher.store.core.User followee) {

    }

    @Override
    public Collection<me.dalsat.basher.store.core.User> listOfUsers() {
        return null;
    }

    @Override
    public void reset() {

    }

    public void addUser(String username) {
        System.out.println(userRepository);
        userRepository.save(new User(username));
    }

    public User findUserNamed(String username) {
        return null;
    }

    public static void main(String[] args) {

        var repository = new MongoStore();

        repository.addUser("ada");
        System.out.println(repository.findUserNamed("ada"));
    }

}
