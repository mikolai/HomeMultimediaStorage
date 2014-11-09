package home.multimedia.storage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by nick on 5/13/14.
 */
@Entity
@Table(name = "photos", schema = "photo_gallery")
public class Photo extends BaseEntity implements Serializable {
    @Column(name = "topic")
    private String topic;
    @Column(name = "description")
    private String description;
    @Column(name = "tags")
    private String tags;
    @Column(name = "date")
    private Date date;
    @Column(name = "photo_data")
    private byte[] photoData;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogue_id")
    private Catalogue catalogue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Photo(Integer id, String topic, String description, String tags, Catalogue catalogue, User user, Date date, byte[] photoData) {
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
        this(null, null, null, null, null, null, null, null);
    }

    public Photo(String topic, String description, String tags, Catalogue catalogue, User user, Date date, byte[] photoData) {
        this(null, topic, description, tags, catalogue, user, date, photoData);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
