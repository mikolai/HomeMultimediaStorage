package home.multimedia.storage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "security")
public class Role extends BaseEntity implements Serializable {
	@Column(name = "name")
	private String name;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<User> users;

	public Role(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.users = new HashSet<>();
	}

	public Role() {
		this(null, null);
	}

	public Role(String name) {
		this(null, name);
	}

	public Role(int id) {
		this(id, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return new HashSet<>(users);
	}

	public void addUser(User user) {
		if (!this.users.contains(user)) {
			this.users.add(user);
			user.addRole(this);
		}
	}

	public void addUsers(Set<User> users) {
		for (User user : users) {
			this.addUser(user);
		}
	}

	public void removeUser(User user) {
		if (this.users.contains(user)) {
			this.users.remove(user);
			user.removeRole(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;
		if (!super.equals(o)) return false;

		Role role = (Role) o;

		if (name != null ? !name.equals(role.name) : role.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
