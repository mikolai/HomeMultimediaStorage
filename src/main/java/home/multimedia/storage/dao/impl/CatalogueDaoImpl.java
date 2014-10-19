package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.CatalogueDao;
import home.multimedia.storage.domain.Catalogue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nick on 5/15/14.
 */
@Repository("catalogueDao")
public class CatalogueDaoImpl implements CatalogueDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CatalogueDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Catalogue entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public Catalogue read(Integer id) {
        return (Catalogue) getCurrentSession().getNamedQuery("Catalogue.selectCatalogueWithDetails").setParameter("id", id).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Catalogue> read() {
        return getCurrentSession().getNamedQuery("Catalogue.selectCataloguesWithDetails").list();
    }

    @Override
    public void delete(Catalogue entity) {
        getCurrentSession().delete(entity);
    }
}
