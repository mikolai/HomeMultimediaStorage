package home.multimedia.storage.service;

import home.multimedia.storage.domain.Role;

import java.util.List;

public interface RoleService {
	void save(Role role);

	Role getRole(int id);

	List<Role> getRoles();

	void removeRole(Role role);
}
