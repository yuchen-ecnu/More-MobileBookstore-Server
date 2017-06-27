package com.pb.services.userRelated;

import com.pb.services.common.CommService;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Model;
import com.pb.entity.User;
import com.pb.services.common.CommService;
@Service(value = "userService")
public class UserService extends CommService {


	public  List<User> findAll() {
		List<User> list = baseDAO.findByHQL("from User");
		return list;
	}
	public List<User> findPasswordByName(String name){
		List<User> list = baseDAO.findByHQL("from User m where m.username = ? ",new Object[] {name } );
		return list;
	}
	
	public List<User> findEmailByUsername(String name){
		List<User> list = baseDAO.findByHQL("from User user where user.username = ? ",new Object[] {name } );
		return list;
	}
	public void updatePassword(String newpsd,User one){
		one.setPassword(newpsd);
		baseDAO.update(one);
	}
}
