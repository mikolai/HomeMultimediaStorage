package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.UserDao;
import home.multimedia.storage.domain.User;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nick on 5/15/14.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User entity) {
        if (entity.isNew()) {
            this.em.persist(entity);
        } else {
            this.em.merge(entity);
        }
    }

    @Override
    public User findById(Integer id) {
        List<User> entities = em.createQuery("select distinct u from User u left join fetch u.role where u.id = :id")
                .setParameter("id", id)
                .getResultList();
        if (entities.size() == 0) {
            return null;
        }
        return entities.get(0);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select distinct u from User u left join fetch u.role")
                .getResultList();
    }

    @Override
    public void delete(User entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }

    @Override
    public User getUserByLogin(String name) {
        List<User> entities = em.createQuery("select distinct u from User u left join fetch u.role where u.name = :name")
                .setParameter("name", name)
                .getResultList();
        if (entities.size() == 0) {
            return null;
        }
        return entities.get(0);
    }
}
