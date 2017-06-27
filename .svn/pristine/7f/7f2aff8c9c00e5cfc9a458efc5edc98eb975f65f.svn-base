package com.pb.controller.userRelated;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Model;
import com.pb.entity.Modelrow;
import com.pb.entity.User;
import com.pb.json.BaseJson;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/revisePassword")
public class revisePasswordController extends BaseController{
	@RequestMapping(value = "/revisePassword", method = { RequestMethod.POST })
	@ResponseBody
	public String revisePassword(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String oldpsd=request.getParameter("oldpsd");
		String newpsd=request.getParameter("newpsd");
		BaseJson bj = new BaseJson();
		List<User> list = userService.findPasswordByName(username);
		String p=list.get(0).getPassword();
		if(p.equals(oldpsd)){    
			bj.setRetcode("success");
			userService.updatePassword(newpsd,list.get(0));
		}else {
			bj.setErrorMsg("The primary password goes wrong.");
		}
		bj.setObj(list);
		return JsonUtil.getInstance().obj2json(bj);
	}
	
}
