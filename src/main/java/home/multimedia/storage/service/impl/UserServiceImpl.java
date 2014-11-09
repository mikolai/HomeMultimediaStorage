package home.multimedia.storage.service.impl;

import home.multimedia.storage.dao.UserDao;
import home.multimedia.storage.domain.User;
import home.multimedia.storage.service.UserService;

import java.util.List;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nick on 5/25/14.
 */
@Service("userService")
public class UserServiceImpl implements UserService, InitializingBean {

    private UserDao userDao;

    @Autowired(required = false)
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(int id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByLogin(String name) {
        return userDao.getUserByLogin(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userDao == null) {
            throw new BeanInitializationException("Need set UserDAO");
        }
    }
}
