package org.dalsat.basher.store.mongo;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    public String id;

    public String username;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, username='%s']", id, username);
    }
}
