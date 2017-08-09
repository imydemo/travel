package org.vie.mydemo520.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vie.mydemo520.entity.sys.User;
import org.vie.mydemo520.service.sys.RoleService;
import org.vie.mydemo520.service.sys.UserService;

@Controller
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
	
    @RequestMapping(value="list")
    public String list(User user, Model model, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	List<User> userlist=userService.queryUser();
    	model.addAttribute("userlist", userlist);
        return "sys/user/list";
    }
    
    @RequestMapping(value="save", method = RequestMethod.GET)
    public String save(User user,Model model){ 
    	model.addAttribute("user",user);
    	model.addAttribute("rolelist",roleService.queryRole());
    	return "sys/user/edit";
    }
    
    @RequestMapping(value="save", method = RequestMethod.POST)
    public String save(User user, HttpServletRequest request, HttpServletResponse response)throws Exception{ 
    	System.out.println("password:"+user.getPassword());
    	userService.save(user);
    	return "redirect:/sys/user/list";
    }
    
    @RequestMapping(value="update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id,Model model, HttpServletRequest request, HttpServletResponse response)throws Exception{
    	User user=userService.get(id);
    	model.addAttribute("user", user);
    	model.addAttribute("rolelist",roleService.queryRole());
    	return "sys/user/edit";
    }
    
    @RequestMapping(value="update", method = RequestMethod.POST)
    public String update(@Valid User user, HttpServletRequest request, HttpServletResponse response)throws Exception{
    	userService.save(user);
    	return "sys/user/edit";
    }
    
    @RequestMapping(value="delete/{ids}")
    public String delete(@PathVariable("ids") List<String> ids, HttpServletRequest request, HttpServletResponse response)throws Exception{
    	List<Integer> idlist=null;
    	for(int i=0;i<ids.size();i++){
    		idlist.add(Integer.parseInt(ids.get(i)));
    	}
    	userService.delete(idlist);
    	return "sys/user/list";
    }
}
