package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.PhotoDao;
import home.multimedia.storage.domain.Photo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nick on 5/15/14.
 */
@Repository("photoDao")
public class PhotoDaoImpl implements PhotoDao {

    private SessionFactory sessionFactory;

    @Autowired
    public PhotoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Photo entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public Photo read(Integer id) {
        return (Photo) getCurrentSession().getNamedQuery("Photo.selectPhotoWithDetails").setParameter("id", id).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Photo> read() {
        return getCurrentSession().getNamedQuery("Photo.selectPhotosWithDetails").list();
    }

    @Override
    public void delete(Photo entity) {
        getCurrentSession().delete(entity);
    }
}
