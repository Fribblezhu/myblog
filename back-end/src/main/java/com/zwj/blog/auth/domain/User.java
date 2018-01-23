package com.zwj.blog.auth.domain;

import java.util.Date;

public class User {

	private String id;

	private String username;

	private String password;

	private int is_enable = 1;

	private Date lastPasswordResetDate = new Date();


	public User(String id, String username, String password, int enabled, Date lastPasswordResetDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.is_enable = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getEnabled() {
		return is_enable;
	}

	public void setEnabled(int enabled) {
		this.is_enable = enabled;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object that) {
		return that != null && that.getClass() == getClass() && (that == this || id.equals(((User) that).id));
	}

	@Override
	public String toString() {
		return getId() + "," + getUsername();
	}
}