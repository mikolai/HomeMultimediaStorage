package home.multimedia.storage.service;

import home.multimedia.storage.domain.Photo;

import java.util.List;

/**
 * Created by nick on 6/9/14.
 */
public interface PhotoService {
    void save(Photo photo);
    Photo getPhoto(int id);
    List<Photo> getPhotos();
    void removePhoto(Photo photo);
}
