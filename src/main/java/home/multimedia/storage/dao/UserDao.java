package home.multimedia.storage.dao;

import home.multimedia.storage.domain.User;

/**
 * Created by nick on 5/15/14.
 */
public interface UserDao extends GenericDao<User, Integer> {
    User getUserByLogin(String name);
}
