package home.multimedia.storage.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by nick on 5/18/14.
 */
@Entity
@Table(name = "roles", schema = "security")
public class Role implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5403750613458031956L;
	private int id;
    private String name;
    private int version;
    private Set<User> users;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
        this.users = new HashSet<User>();
    }

    public Role() {
        this(0, null);
    }

    public Role(String name) {
        this(0, name);
    }

    public Role(int id) {
        this(id, null);
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Version
    @Column(name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
