package org.vie.mydemo520.entity.sys;

import java.io.Serializable;

import org.vie.mydemo520.common.entity.DataEntity;

public class Role extends DataEntity<Role> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private String resource_ids;
	private int available;
	public Role(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResource_ids() {
		return resource_ids;
	}
	public void setResource_ids(String resource_ids) {
		this.resource_ids = resource_ids;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
}
