package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.RoleDao;
import home.multimedia.storage.domain.Role;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by nick on 5/18/14.
 */
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Role entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public Role read(Integer id) {
        return (Role) getCurrentSession().get(Role.class, id);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Role> read() {
        return getCurrentSession().createCriteria(Role.class).list();
    }

    @Override
    public void delete(Role entity) {
        getCurrentSession().delete(entity);
    }
}
