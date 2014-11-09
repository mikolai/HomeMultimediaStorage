package home.multimedia.storage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * UserCreated by nick on 5/13/14.
 */
@Entity
@Table(name = "users", schema = "security")
public class User extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Catalogue> catalogues;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Photo> photos;

    public User(Integer id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.catalogues = new HashSet<Catalogue>();
        this.photos = new HashSet<Photo>();
    }

    public User() {
        this(null, null, null, null);
    }

    public User(String name, String username, String password) {
        this(null, name, username, password);
    }

    public User(int id) {
        this(id, null, null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(Set<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
}
