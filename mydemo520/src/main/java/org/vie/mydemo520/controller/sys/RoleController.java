package org.vie.mydemo520.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vie.mydemo520.common.entity.EasyUITree;
import org.vie.mydemo520.entity.sys.Resource;
import org.vie.mydemo520.entity.sys.Role;
import org.vie.mydemo520.entity.sys.User;
import org.vie.mydemo520.service.sys.ResourceService;
import org.vie.mydemo520.service.sys.RoleService;


@Controller
@RequestMapping("/sys/role")
public class RoleController {
     @Autowired 
     private RoleService roleService;
     @Autowired 
     private ResourceService resourceService;
     
     @RequestMapping("list")
     public String list(Role role, Model model, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	 List<Role> rolelist=roleService.queryRole();
    	 model.addAttribute("rolelist", rolelist);
    	 return "sys/role/list";
     }
     
     @RequestMapping(value="save", method = RequestMethod.GET)
     public String save(Role role,Model model){ 
     	model.addAttribute("Role",role);
     	return "sys/role/edit";
     }
     
     @RequestMapping(value="save", method = RequestMethod.POST)
     public String save(Role role, HttpServletRequest request, HttpServletResponse response)throws Exception{ 
    	 roleService.save(role);
     	return "redirect:/sys/role/list";
     }
     
     
     @RequestMapping(value="update/{id}", method = RequestMethod.GET)
     public String update(@PathVariable("id") int id,Model model, HttpServletRequest request, HttpServletResponse response)throws Exception{
    	Role role=roleService.get(id);
     	model.addAttribute("role", role);
     	return "sys/role/edit";
     }
     
     @RequestMapping(value="update", method = RequestMethod.POST)
     public String update(@Valid Role role, HttpServletRequest request, HttpServletResponse response)throws Exception{
    	 roleService.save(role);
     	return "sys/role/edit";
     }
     
     @RequestMapping(value="shareresource/{id}",method=RequestMethod.GET)
 	 public String showmenu(@PathVariable("id") String id, Model model) throws Exception {
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("id", id);
    	model.addAllAttributes(map);
 	    return "sys/role/menu";
 	 }
     
     @RequestMapping(value="delete/{ids}")
     public String delete(@PathVariable("ids") List<String> ids, HttpServletRequest request, HttpServletResponse response)throws Exception{
     	List<Integer> idlist=null;
     	for(int i=0;i<ids.size();i++){
     		idlist.add(Integer.parseInt(ids.get(i)));
     	}
     	roleService.delete(idlist);
     	return "sys/role/list";
     }
     
     
     @RequestMapping(value="loadMenu")
     public List<EasyUITree> loadMenu(String id){
     //findByRoleId(roleid)获取当前角色权限,并为其selected
	 List<Resource> chekedList = resourceService.findMenusByRoleId(Integer.parseInt(id));
     //从数据库中查询的保存tree的集合、比如id、父类id、text等等、可自己扩展  
     List<Resource> list = resourceService.findAll();  
     List<EasyUITree> eList = this.getEasyUITreeList(list,chekedList);  
     return eList;
     }
    		 
     private List<EasyUITree> getEasyUITreeList(List<Resource> list,List<Resource> chekedList) {
         //用于前台显示的tree的属性、比如id、state、text、checked等等  
         List<EasyUITree> eList = new ArrayList<EasyUITree>();  
         Map<Integer,EasyUITree> eMap = new HashMap<Integer,EasyUITree>();
         if(list.size() != 0){  
             for(Resource p : list){  
                 EasyUITree e = new EasyUITree();  
                 e.setId(p.getId()); 
                 e.setText(p.getName());  
                 e.setChecked(true);
                 e.setAttributes(p.getUrl());  
                 int count = p.getChildren().size(); 
                 if(count > 0){  
                     for(Resource pchild:p.getChildren()){
                         EasyUITree echild = new EasyUITree();
                         echild.setId(pchild.getId());
                         echild.setText(pchild.getName());
                         echild.setChecked(true);
                         echild.setAttributes(pchild.getUrl());
                         e.getChildren().add(echild);
                     }
                 }  
                 eMap.put(e.getId(), e);
             } 
             for(Resource p : chekedList){  
                 EasyUITree e = new EasyUITree();  
                 e.setId(p.getId()); 
                 e.setText(p.getName());  
                 e.setChecked(true);
                 int count = p.getChildren().size(); 
                 if(count > 0){  
                     for(Resource pchild:p.getChildren()){
                         EasyUITree echild = new EasyUITree();
                         echild.setId(pchild.getId());
                         echild.setText(pchild.getName());
                         echild.setChecked(true);
                         e.getChildren().add(echild);
                     }
                 }  
                 eMap.put(e.getId(), e);
             } 
         }
         eList.addAll(eMap.values());
         return eList;
     }
}
