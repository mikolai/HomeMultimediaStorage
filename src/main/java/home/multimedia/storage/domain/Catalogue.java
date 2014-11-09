package home.multimedia.storage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nick on 5/13/14.
 */
@Entity
@Table(name = "catalogues", schema = "photo_gallery")
public class Catalogue extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Catalogue parent;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Catalogue> childrens;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogue")
    private Set<Photo> photos;

    public Catalogue(Integer id, Catalogue parent, String name, User user) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.user = user;
        this.childrens = new HashSet<Catalogue>();
        this.photos = new HashSet<Photo>();
    }

    public Catalogue(Catalogue parent, String name, User user) {
        this(null, parent, name, user);
    }

    public Catalogue(int id) {
        this(id, null, null, null);
    }

    public Catalogue() {
        this(null, null, null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Catalogue getParent() {
        return parent;
    }

    public void setParent(Catalogue parent) {
        this.parent = parent;
    }

    public Set<Catalogue> getChildrens() {
        return childrens;
    }

    public void setChildrens(Set<Catalogue> childrens) {
        this.childrens = childrens;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
}
