package home.multimedia.storage.dao;

import home.multimedia.storage.domain.User;

public interface UserDao extends GenericDao<User, Integer> {
	User getUserByLogin(String name);
}
