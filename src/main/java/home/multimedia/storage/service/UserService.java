package home.multimedia.storage.service;

import home.multimedia.storage.domain.User;

import java.util.List;

public interface UserService {
	void save(User user);

	User getUser(int id);

	User getUserByLogin(String name);

	List<User> getUsers();

	void removeUser(User user);
}
