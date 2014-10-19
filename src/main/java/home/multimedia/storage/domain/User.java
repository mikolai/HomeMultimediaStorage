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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * UserCreated by nick on 5/13/14.
 */
@Entity
@Table(name = "users", schema = "security")
@NamedQueries({
        @NamedQuery(name = "User.selectUserWithRoles",
                query = "select distinct u from User u left join fetch u.role where u.id = :id"),
        @NamedQuery(name = "User.selectUsersWithRoles",
                query = "select distinct u from User u left join fetch u.role"),
        @NamedQuery(name = "User.selectUserWithRoleByName",
                query = "select distinct u from User u left join fetch u.role where u.name = :name")
})
public class User implements Serializable {

    private static final long serialVersionUID = 5748453289142349888L;

	private int id;
    private String name;
    private String username;
    private String password;
    private int version;
    private Role role;
    private Set<Catalogue> catalogues;
    private Set<Photo> photos;

    public User(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.catalogues = new HashSet<Catalogue>();
        this.photos = new HashSet<Photo>();
    }

    public User() {
        this(0, null, null, null);
    }

    public User(String name, String username, String password) {
        this(0, name, username, password);
    }

    public User(int id) {
        this(id, null, null, null);
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

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Version
    @Column(name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(Set<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
}
