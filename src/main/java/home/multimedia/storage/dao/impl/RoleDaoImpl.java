package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.RoleDao;
import home.multimedia.storage.domain.Role;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nick on 5/18/14.
 */
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Role entity) {
        if (entity.isNew()) {
            em.persist(entity);
            em.flush();
        } else {
            em.merge(entity);
        }
    }

    @Override
    public Role findById(Integer id) {
        return em.find(Role.class, id);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Role> findAll() {
        return em.createQuery("select distinct r from Role r order by r.name")
                .getResultList();
    }

    @Override
    public void delete(Role entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }
}
