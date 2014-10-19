package home.multimedia.storage.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by nick on 5/13/14.
 */
@Entity
@Table(name = "catalogues", schema = "photo_gallery")
@NamedQueries({
        @NamedQuery(name = "Catalogue.selectCatalogueWithDetails",
                query = "select distinct c from Catalogue c left join fetch c.parent p left join fetch c.childrens cs left join fetch c.photos ps where c.id = :id"),
        @NamedQuery(name = "Catalogue.selectCataloguesWithDetails",
                query = "select distinct c from Catalogue c left join fetch c.parent p left join fetch c.childrens cs left join fetch c.photos ps")
})
public class Catalogue implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1937426616342132128L;
	private int id;
    private String name;
    private int version;
    private User user;
    private Catalogue parent;
    private Set<Catalogue> childrens;
    private Set<Photo> photos;

    public Catalogue(int id, Catalogue parent, String name, User user) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.user = user;
        this.childrens = new HashSet<Catalogue>();
        this.photos = new HashSet<Photo>();
    }

    public Catalogue(Catalogue parent, String name, User user) {
        this(0, parent, name, user);
    }

    public Catalogue(int id) {
        this(id, null, null, null);
    }

    public Catalogue() {
        this(0, null, null, null);
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    @JoinColumn(name = "parent_id")
    public Catalogue getParent() {
        return parent;
    }

    public void setParent(Catalogue parent) {
        this.parent = parent;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    public Set<Catalogue> getChildrens() {
        return childrens;
    }

    public void setChildrens(Set<Catalogue> childrens) {
        this.childrens = childrens;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogue")
    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
}
