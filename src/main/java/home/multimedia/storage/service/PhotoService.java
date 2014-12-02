package home.multimedia.storage.service;

import home.multimedia.storage.domain.Photo;

import java.util.List;

public interface PhotoService {
	void save(Photo photo);

	Photo getPhoto(int id);

	List<Photo> getPhotos();

	void removePhoto(Photo photo);
}
