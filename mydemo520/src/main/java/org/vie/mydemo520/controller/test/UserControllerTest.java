package org.vie.mydemo520.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vie.mydemo520.entity.test.Test;
import org.vie.mydemo520.service.test.UserServiceTest;



@Controller
@RequestMapping("/test")
public class UserControllerTest {
   @Autowired
   UserServiceTest testservice;
   
   @RequestMapping(value = "/test", method = RequestMethod.GET)
   public  void test(){
	  Test test=testservice.getByName("admin");
	  System.out.println(test.getRemark());
   }
}
