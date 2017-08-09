package org.vie.mydemo520.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vie.mydemo520.entity.test.Test;
import org.vie.mydemo520.mapper.test.TestMapper;

@Service
public class UserServiceTest {
	@Autowired
	 TestMapper mapper;
	 public Test getByName(String name) {
			// TODO Auto-generated method stub
		 Test test=mapper.getByName(name);
		 System.out.println("hello"+test.getName());
		 return test;
		}
}
