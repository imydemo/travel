package org.vie.mydemo520.service.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vie.mydemo520.mapper.sys.ResourceMapper;
import org.vie.mydemo520.common.utils.UserUtils;
import org.vie.mydemo520.entity.sys.Resource;
import org.vie.mydemo520.entity.sys.User;



@Service
public class ResourceService {
	   @Autowired
	   ResourceMapper mapper;

		public List<Resource> queryPermissions(String username) {
			// TODO Auto-generated method stub
			return mapper.queryPermissions(username);
		}
		public List<Resource> queryMenus(String username) {
			// 判断是否超级管理员
			User user = UserUtils.getUser();
			if (1 == user.getIsAdmin()) {
				return mapper.queryAllMenus();
			}
			List<Resource> menus = mapper.queryMenus(username);
			List<Resource> resources = new ArrayList<Resource>();
			for (Resource node : menus) {  
	            if (node.getParentId() == 0) {
	            	build(node,menus); 
	                resources.add(node);
	            }  
	        } 
			System.out.println(resources);
			return resources;
		}
		
		private void build(Resource node,List<Resource> nodes){  
	        List<Resource> children = getChildren(node,nodes);  
	        if (!children.isEmpty()) {  
	            for (Resource child : children) {  
	                build(child,nodes);  
	            }  
	        }   
	    }  
		
		//获取某一节点下的子节点
		private List<Resource> getChildren(Resource node,List<Resource> nodes){  
	        List<Resource> children = new ArrayList<Resource>();  
	        int id = node.getId();  
	        for (Resource child : nodes) {  
	            if (id==child.getParentId()) {  
	                children.add(child);  
	            }  
	        }
	        node.setChildren(children);
	        return children;  
	    }
		
		//根据role_id查询resource
		public List<Resource> findMenusByRoleId(int id){
			return mapper.findMenusByRoleId(id);
		}
		public List<Resource> findAll() {
			// TODO Auto-generated method stub
			return mapper.findAll();
		}
}
