package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.CatalogueDao;
import home.multimedia.storage.domain.Catalogue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nick on 5/15/14.
 */
@Repository("catalogueDao")
public class CatalogueDaoImpl implements CatalogueDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Catalogue entity) {
        if (entity.isNew()) {
            this.em.persist(entity);
        } else {
            this.em.merge(entity);
        }
    }

    @Override
    public Catalogue findById(Integer id) {
        List<Catalogue> entities = em.createQuery("select distinct c from Catalogue c left join fetch c.parent p left join fetch c.childrens cs left join fetch c.photos ps left join fetch c.user u where c.id = :id")
                .setParameter("id", id)
                .getResultList();
        if (entities.size() == 0) {
            return null;
        }
        return entities.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Catalogue> findAll() {
        return em.createQuery("select distinct c from Catalogue c left join fetch c.parent p left join fetch c.childrens cs left join fetch c.photos ps left join fetch c.user u")
                .getResultList();
    }

    @Override
    public void delete(Catalogue entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }
}
