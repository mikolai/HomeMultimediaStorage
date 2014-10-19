package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.UserDao;
import home.multimedia.storage.domain.User;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by nick on 5/15/14.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(User entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public User read(Integer id) {
        return (User) getCurrentSession().getNamedQuery("User.selectUserWithRoles").setParameter("id", id).uniqueResult();
    }

    @Override
    public List<User> read() {
        return getCurrentSession().getNamedQuery("User.selectUsersWithRoles").list();
    }

    @Override
    public void delete(User entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public User getUserByLogin(String name) {
        return (User) getCurrentSession().getNamedQuery("User.selectUserWithRoleByName").setParameter("name", name).uniqueResult();
    }
}
