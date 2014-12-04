package home.multimedia.storage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "security")
public class User extends BaseEntity implements Serializable {
	@Column(name = "name")
	private String name;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", schema = "security",
			joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
	private Set<Role> roles;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Catalogue> catalogues;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Photo> photos;

	public User(Integer id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.roles = new HashSet<>();
		this.catalogues = new HashSet<>();
		this.photos = new HashSet<>();
	}

	public User() {
		this(null, null, null, null);
	}

	public User(String name, String username, String password) {
		this(null, name, username, password);
	}

	public User(int id) {
		this(id, null, null, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return new HashSet<>(roles);
	}

	public void addRole(Role role) {
		if (!this.roles.contains(role)) {
			this.roles.add(role);
			role.addUser(this);
		}
	}

	public void addRoles(Set<Role> roles) {
		for (Role role : roles) {
			this.addRole(role);
		}
	}

	public void removeRole(Role role) {
		if (this.roles.contains(role)) {
			this.roles.remove(role);
			role.removeUser(this);
		}
	}

	public Set<Catalogue> getCatalogues() {
		return catalogues;
	}

	public void setCatalogues(Set<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		if (!super.equals(o)) return false;

		User user = (User) o;

		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}
}
