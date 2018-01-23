package com.zwj.blog.auth.domain;

public class Role {

	private String id;

	private String name;

	public Role(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object that) {
		return that != null && that.getClass() == getClass() && (that == this || id.equals(((Role) that).id));
	}

	@Override
	public String toString() {
		return getId() + "," + getName();
	}

}