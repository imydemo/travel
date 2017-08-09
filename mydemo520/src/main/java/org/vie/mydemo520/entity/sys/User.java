package org.vie.mydemo520.entity.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.vie.mydemo520.common.entity.DataEntity;

public class User extends DataEntity<User> implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nickname;
     private String username;
     private String password;
     private String salt;
     private int locked;
     private int isAdmin;
     private Role role;
     private List<Role> roles = new ArrayList<Role>();
     public User(){}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public int getRoleId() {
		if(role!=null){
			return role.getId();
		}else{
			return 0;
		}
	}

	public void setRoleId(int roleId) {
		role.setId(roleId);
	}
	
	public List<Integer> getRoleIds() {
		List<Integer> roleIds = new ArrayList<Integer>();
		for (Role role : roles) {
			roleIds.add(role.getId());
		}
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		for (Integer id : roleIds) {
			Role role = new Role();
			role.setId(id);
			roles.add(role);
		}
	}     
}
