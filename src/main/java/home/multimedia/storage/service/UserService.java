package home.multimedia.storage.service;

import home.multimedia.storage.domain.User;

import java.util.List;

/**
 * Created by nick on 6/9/14.
 */
public interface UserService {
    void save(User user);
    User getUser(int id);
    User getUserByLogin(String name);
    List<User> getUsers();
    void removeUser(User user);
}
