package home.multimedia.storage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nick on 5/18/14.
 */
@Entity
@Table(name = "roles", schema = "security")
public class Role extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<User> users;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.users = new HashSet<User>();
    }

    public Role() {
        this(null, null);
    }

    public Role(String name) {
        this(null, name);
    }

    public Role(int id) {
        this(id, null);
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
}
