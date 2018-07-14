package org.dalsat.basher.store.mongo;

import org.dalsat.basher.command.Message;
import org.dalsat.basher.store.core.Store;
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
    public org.dalsat.basher.store.core.User getOrAddUser(String username) {
        var user = userRepository.findByUsername(username);
        return new org.dalsat.basher.store.core.User(1,"ada");
    }

    @Override
    public void postMessage(Message message) {

    }

    @Override
    public List<Message> messagesFor(org.dalsat.basher.store.core.User user) {
        return null;
    }

    @Override
    public Stream<Message> wall(org.dalsat.basher.store.core.User user) {
        return null;
    }

    @Override
    public void follow(org.dalsat.basher.store.core.User follower, org.dalsat.basher.store.core.User followee) {

    }

    @Override
    public void unfollow(org.dalsat.basher.store.core.User follower, org.dalsat.basher.store.core.User followee) {

    }

    @Override
    public Collection<org.dalsat.basher.store.core.User> listOfUsers() {
        return null;
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
