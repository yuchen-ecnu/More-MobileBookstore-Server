package com.pb.controller.userRelated;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Institution;
import com.pb.entity.User;
import com.pb.json.BaseJson;
import com.pb.json.ModelTree;
import com.pb.services.userRelated.UserService;
import com.pb.util.JsonUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@RequestMapping(value = "/getlogin", method = { RequestMethod.POST })
	@ResponseBody
	public String getInlogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		BaseJson bj = new BaseJson();
		List<User> list = userService.findAll();
		//Integer i=list.get(0).getId();
		String u=list.get(0).getUsername();
		String p=list.get(0).getPassword();
		if(u.equals(username)&&p.equals(password)){
			bj.setRetcode("success");
		}else if(u.equals(null)){
			bj.setErrorMsg("The user is not found.");
		}else{
			bj.setErrorMsg("Username or password is wrong.");
		}
		bj.setObj(list);
		 return JsonUtil.getInstance().obj2json(bj);
	}
	
}
