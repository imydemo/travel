package org.vie.mydemo520.mapper.sys;

import java.util.List;

import org.vie.mydemo520.entity.sys.Role;

public interface RoleMapper {

	List<Role> queryRole();

	void save(Role role);

	Role get(int id);

	void delete(List<Integer> idlist);
}
