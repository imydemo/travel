package org.vie.mydemo520.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vie.mydemo520.entity.sys.Role;
import org.vie.mydemo520.mapper.sys.RoleMapper;

@Service
public class RoleService {
     @Autowired
     RoleMapper maper;

	public List<Role> queryRole() {
		// TODO Auto-generated method stub
		return maper.queryRole();
	}

	public void save(Role role) {
		// TODO Auto-generated method stub
		    maper.save(role);
	}

	public Role get(int id) {
		// TODO Auto-generated method stub
		return maper.get(id);
	}

	public void delete(List<Integer> idlist) {
		// TODO Auto-generated method stub
		maper.delete(idlist);
	}
}
