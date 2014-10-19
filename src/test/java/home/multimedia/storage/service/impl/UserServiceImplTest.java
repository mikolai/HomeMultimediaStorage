package home.multimedia.storage.service.impl;

import home.multimedia.storage.domain.Role;
import home.multimedia.storage.domain.User;
import home.multimedia.storage.service.RoleService;
import home.multimedia.storage.service.UserService;
import home.multimedia.storage.util.HashingUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by nick on 6/9/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/servlet-context.xml")
public class UserServiceImplTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private HashingUtil hashingUtil;

    @Ignore
    @Test
    public void firstSave() {
        List<User> expectedUsers = getUsers();

        for (User user : expectedUsers) {
            if (user.getRole() != null) {
                user.setRole(roleService.getRole(user.getRole().getId()));
            }

            userService.save(user);
        }
    }

    @Test
    public void canGetUser() {
        final int expectedId = 1;
        User expectedUser = getUsers().get(expectedId - 1);

        User actualUser = userService.getUser(expectedId);

        assertEquals(expectedId, actualUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getRole().getId(), actualUser.getRole().getId());
    }

    @Test
    public void canGetUsers() {
        List<User> expectedUsers = getUsers();

        List<User> actualUsers = userService.getUsers();

        for (User actualUser : actualUsers) {
            int userId = actualUser.getId();
            User expectedUser = expectedUsers.get(userId - 1);

            assertEquals(expectedUser.getName(), actualUser.getName());
            assertEquals(expectedUser.getUsername(), actualUser.getUsername());
            assertEquals(expectedUser.getPassword(), actualUser.getPassword());
            assertEquals(expectedUser.getRole().getId(), actualUser.getRole().getId());
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        String firstUserPassword = hashingUtil.hash("first_password");
        User firstUser = new User("first_user", "first_user_name", firstUserPassword);
        firstUser.setRole(new Role(1));
        users.add(firstUser);

        String secondUserPassword = hashingUtil.hash("second_password");
        User secondUser = new User("second_user", "second_user_name", secondUserPassword);
        secondUser.setRole(new Role(3));
        users.add(secondUser);

        String thirdUserPassword = hashingUtil.hash("third_password");
        User thirdUser = new User("third_user", "third_user_name", thirdUserPassword);
        thirdUser.setRole(new Role(2));
        users.add(thirdUser);

        String fourthUserPassword = hashingUtil.hash("fourth_password");
        User fourthUser = new User("fourth_user", "fourth_user_name", fourthUserPassword);
        fourthUser.setRole(new Role(4));
        users.add(fourthUser);

        return users;
    }

    @Test
    public void canAddRemoveUser() {
        String newUserPassword = hashingUtil.hash("new_password");
        User expectedNewUser = new User("new_user", "new_user_name", newUserPassword);
        expectedNewUser.setRole(roleService.getRole(3));

        userService.save(expectedNewUser);
        User actualNewUser = userService.getUser(expectedNewUser.getId());

        assertNotNull(actualNewUser);
        assertEquals(expectedNewUser.getId(), actualNewUser.getId());
        assertEquals(expectedNewUser.getName(), actualNewUser.getName());
        assertEquals(expectedNewUser.getUsername(), actualNewUser.getUsername());
        assertEquals(expectedNewUser.getPassword(), actualNewUser.getPassword());
        assertEquals(expectedNewUser.getRole().getId(), actualNewUser.getRole().getId());

        userService.removeUser(expectedNewUser);
        User deletedNewUser = userService.getUser(expectedNewUser.getId());

        assertNull(deletedNewUser);
    }

    @Test
    public void canUpdateUser() {
        final int userId = 3;
        final String newUserName = "udpated_user_name";
        String userName;

        User user = userService.getUser(userId);
        userName = user.getUsername();
        user.setUsername(newUserName);
        userService.save(user);
        User updatedUser = userService.getUser(userId);

        assertEquals(userId, updatedUser.getId());
        assertEquals(newUserName, updatedUser.getUsername());

        updatedUser.setUsername(userName);
        userService.save(updatedUser);
        User restoredUser = userService.getUser(userId);

        assertEquals(userId, restoredUser.getId());
        assertEquals(userName, restoredUser.getUsername());
    }
}
