package org.vie.mydemo520.entity.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.vie.mydemo520.common.entity.DataEntity;

public class Resource extends DataEntity<Resource> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private String type;
    private int priority;
    private String url;
    private int parentId;
    private String permission;
    private int available;
    private List<Resource> children = new ArrayList<Resource>(); //子节点
    public Resource(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public List<Resource> getChildren() {
		return children;
	}
	public void setChildren(List<Resource> children) {
		this.children = children;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
    
}
