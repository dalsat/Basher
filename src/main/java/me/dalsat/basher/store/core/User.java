package me.dalsat.basher.store.core;

public class User {
    String name;
    long id;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    };

    public String getName() {
        return name;
    }

    public long getId() { return id; }

    @Override
    public String toString() {
        return name + '(' + id + ')';
    }
}
