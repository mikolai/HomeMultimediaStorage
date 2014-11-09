package home.multimedia.storage.service.impl;

import home.multimedia.storage.dao.RoleDao;
import home.multimedia.storage.domain.Role;
import home.multimedia.storage.service.RoleService;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nick on 5/25/14.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService, InitializingBean {

    private RoleDao roleDao;

    @Autowired(required = false)
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRole(int id) {
        return roleDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

    @Transactional
    @Override
    public void removeRole(Role role) {
        roleDao.delete(role);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roleDao == null) {
            throw new BeanInitializationException("Need set RoleDAO");
        }
    }
}
