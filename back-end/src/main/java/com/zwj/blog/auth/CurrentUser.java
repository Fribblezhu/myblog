package com.zwj.blog.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrentUser {

	public String getId() {
		return getUser().getId();
	}

	public List<String> getRoles() {
		return getUser().getAuthorities().stream().map(g -> g.getAuthority()).collect(Collectors.toList());
	}

	private JwtUser getUser() {
		return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}