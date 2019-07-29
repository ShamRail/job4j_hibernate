package ru.job4j.models.xmlmappedmodels;

import java.util.*;

public class Role {

    private int id;

    private String name;

    private Set<User> users = new TreeSet<>(new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return Integer.compare(o2.getId(), o1.getId());
        }
    });

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(name, role.name) &&
                Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
