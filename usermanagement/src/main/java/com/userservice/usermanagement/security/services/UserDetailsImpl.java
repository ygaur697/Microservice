package com.userservice.usermanagement.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userservice.usermanagement.models.MongoUserModel;
import com.userservice.usermanagement.models.PostgresUserModel;

public class UserDetailsImpl implements UserDetails {
	/**
	 * User details implementation to get user data
	 */

	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private static Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String i, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = i;
		this.username = username;
		this.email = email;
		this.password = password;
		UserDetailsImpl.authorities = authorities;
	}

	public static UserDetailsImpl build(PostgresUserModel user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	public static UserDetailsImpl build(MongoUserModel user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
	}
}
