package ru.job4j.example;

import java.sql.Timestamp;
import java.util.Objects;

public class User {

    private int id;

    private String name;

    private Timestamp expired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name) &&
                expired.equals(user.expired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expired);
    }

    @Override
    public String toString() {
        return String.format("id: %s name: %s expired: %s", id, name, expired);
    }

}
