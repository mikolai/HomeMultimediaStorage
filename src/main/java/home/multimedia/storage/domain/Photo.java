package home.multimedia.storage.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by nick on 5/13/14.
 */
@Entity
@Table(name = "photos", schema = "photo_gallery")
@NamedQueries({
        @NamedQuery(name = "Photo.selectPhotoWithDetails",
                query = "select distinct p from Photo p left join fetch p.catalogue left join fetch p.user where p.id = :id"),
        @NamedQuery(name = "Photo.selectPhotosWithDetails",
                query = "select distinct p from Photo p left join fetch p.catalogue left join fetch p.user")
})
public class Photo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1887176170679156018L;
	private int id;
    private String topic;
    private String description;
    private String tags;
    private Date date;
    private byte[] photoData;
    private int version;
    private Catalogue catalogue;
    private User user;

    public Photo(int id, String topic, String description, String tags, Catalogue catalogue, User user, Date date, byte[] photoData) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.tags = tags;
        this.catalogue = catalogue;
        this.user = user;
        this.date = date;
        this.photoData = photoData;
    }

    public Photo() {
        this(0, null, null, null, null, null, null, null);
    }

    public Photo(String topic, String description, String tags, Catalogue catalogue, User user, Date date, byte[] photoData) {
        this(0, topic, description, tags, catalogue, user, date, photoData);
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

    @Column(name = "topic")
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "photo_data")
    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
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
    @JoinColumn(name = "catalogue_id")
    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
