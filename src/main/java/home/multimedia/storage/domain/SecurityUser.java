package home.multimedia.storage.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser extends User implements UserDetails {
	private final List<GrantedAuthority> authorities = new ArrayList<>();

	public SecurityUser(User user) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.addRoles(user.getRoles());
		for (Role userRole : this.getRoles()) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getName());
			authorities.add(authority);
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
