package home.multimedia.storage.service.impl;

import home.multimedia.storage.dao.PhotoDao;
import home.multimedia.storage.domain.Photo;
import home.multimedia.storage.service.PhotoService;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService, InitializingBean {
	private PhotoDao photoDao;

	@Autowired(required = false)
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	@Transactional
	@Override
	public void save(Photo photo) {
		photoDao.save(photo);
	}

	@Transactional(readOnly = true)
	@Override
	public Photo getPhoto(int id) {
		return photoDao.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Photo> getPhotos() {
		return photoDao.findAll();
	}

	@Transactional
	@Override
	public void removePhoto(Photo photo) {
		photoDao.delete(photo);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (photoDao == null) {
			throw new BeanInitializationException("Need set PhotoDAO");
		}
	}
}
