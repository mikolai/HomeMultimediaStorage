package home.multimedia.storage.service.impl;

import home.multimedia.storage.domain.SecurityUser;
import home.multimedia.storage.domain.User;
import home.multimedia.storage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.getUserByLogin(userName);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Username %s not found", userName));
		}
		return new SecurityUser(user);
	}
}
