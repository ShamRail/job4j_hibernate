package ru.job4j.models.annotatedmodels;

import ru.job4j.models.Unique;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements Unique {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    private String email;

    private String telNumber;

    private String photoPath;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany (mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Advertisement> ads = new LinkedHashSet<>();

    public User() {

    }

    public User(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Advertisement> getAds() {
        return ads;
    }

    public void setAds(Set<Advertisement> ads) {
        this.ads = ads;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(telNumber, user.telNumber) &&
                Objects.equals(photoPath, user.photoPath) &&
                Objects.equals(role, user.role) &&
                Objects.equals(ads, user.ads);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, telNumber, photoPath, role, ads);
    }
}
