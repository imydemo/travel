package org.vie.mydemo520.mapper.sys;

import java.util.List;

import org.vie.mydemo520.entity.sys.Resource;

public interface ResourceMapper {
	List<Resource> queryPermissions(String username);
	List<Resource> queryAllMenus();
	List<Resource> queryMenus(String username);
	List<Resource> findMenusByRoleId(int id);
	List<Resource> findAll();
}
