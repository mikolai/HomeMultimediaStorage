package home.multimedia.storage.service.impl;

import home.multimedia.storage.domain.Role;
import home.multimedia.storage.service.RoleService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by nick on 6/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/business-config.xml")
public class RoleServiceImplTest {
    @Autowired
    private RoleService roleService;

    @Ignore
    @Test
    public void firstSave() {
        List<Role> expectedRoles = getRoles();

        for (Role role : expectedRoles) {
            roleService.save(role);
        }
    }

    @Test
    public void canGetRole() {
        final Integer expectedRoleId = 1;
        Role expectedRole = getRoles().get(expectedRoleId - 1);

        Role actualRole = roleService.getRole(expectedRoleId);

        assertEquals(expectedRoleId, actualRole.getId());
        assertEquals(expectedRole.getName(), actualRole.getName());
    }

    @Test
    public void canGetRoles() {
        List<Role> expectedRoles = getRoles();

        List<Role> actualRoles = roleService.getRoles();

        assertEquals(expectedRoles.size(), actualRoles.size());
        for (Role actualRole : actualRoles) {
            int id = actualRole.getId();
            Role expectedRole = expectedRoles.get(id - 1);

            assertEquals(expectedRole.getName(), actualRole.getName());
        }
    }

    private List<Role> getRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("first_role"));
        roles.add(new Role("second_role"));
        roles.add(new Role("third_role"));
        roles.add(new Role("fourth_role"));

        return roles;
    }

    @Test
    public void canAddRemoveRole() {
        Role newRole = new Role("newRole");

        roleService.save(newRole);
        Role actualRole = roleService.getRole(newRole.getId());

        assertEquals(newRole.getId(), actualRole.getId());
        assertEquals(newRole.getName(), actualRole.getName());

        roleService.removeRole(actualRole);
        Role deletedRole = roleService.getRole(newRole.getId());

        assertNull(deletedRole);
    }

    @Test
    public void canUpdateRole() {
        final Integer roleId = 1;
        final String newRoleName = "updated_role";
        String roleName;

        Role role = roleService.getRole(roleId);
        roleName = role.getName();
        role.setName(newRoleName);
        roleService.save(role);
        Role updatedRole = roleService.getRole(roleId);

        assertEquals(roleId, updatedRole.getId());
        assertEquals(newRoleName, updatedRole.getName());

        updatedRole.setName(roleName);
        roleService.save(updatedRole);
        Role restoredRole = roleService.getRole(roleId);

        assertEquals(roleName, restoredRole.getName());
    }
}
