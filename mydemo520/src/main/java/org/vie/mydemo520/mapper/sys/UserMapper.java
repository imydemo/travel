package org.vie.mydemo520.mapper.sys;

import java.util.List;

import org.vie.mydemo520.entity.sys.User;

public interface UserMapper {
     public User getByUsername(String usrname);
	 public List<User> queryUser();
	 public void save(User user);
	 public User get(int id);
	 public void delete(List<Integer> idlist);
	 public void deleteUserRole(User user);
	 public void saveUserRole(User user);
}
